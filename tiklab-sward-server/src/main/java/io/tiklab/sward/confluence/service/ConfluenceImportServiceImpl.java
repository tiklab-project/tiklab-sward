package io.tiklab.sward.confluence.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dss.client.DssClient;
import io.tiklab.sward.confluence.config.ConfluenceDataBuilder;
import io.tiklab.sward.confluence.config.ConfluenceDataConfig;
import io.tiklab.sward.confluence.config.ConfluenceDataUpload;
import io.tiklab.sward.confluence.config.ConfluenceVersionConfig;
import io.tiklab.sward.confluence.handler.*;
import io.tiklab.sward.confluence.model.*;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.document.document.service.DocumentService;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.sward.node.node.service.NodeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.sward.repository.util.UncompressUtil;
import io.tiklab.user.user.model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * confluence 数据导入服务
 */
@Service
@EnableTransactionManagement
public class ConfluenceImportServiceImpl implements ConfluenceImportService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Value("${DATA_HOME}/unzip/confluence")
    String unzipAddress;

    @Autowired
    ConfluenceDataUpload dataUpload;

    @Autowired
    NodeService nodeService;

    @Autowired
    DocumentService documentService;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    DssClient dssClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ImportStatus status = new ImportStatus();

    private static String Log ;

    private void Log(String message){
        if (StringUtils.isEmpty(Log)){
            Log = message;
        }else {
            Log = Log +"\n" + message;
        }
    }


    @Override
    public ImportStatus findCfInputSchedule() {
        status.setMessage(Log);
        return status;
    }

    @Override
    public void importJiraData(String confluenceAddress) {
        status = ImportStatus.run(1,"开始导入......");
        Log= null;
        Log("开始导入......");
        try {
            transactionTemplate.execute((message) -> {
                // 在这里执行需要在事务中的操作
                try {
                    Thread.sleep(3000);
                    List<ConfluenceSpace> confluenceSpaces = analysis(confluenceAddress);
                    Thread.sleep(3000);
                    createRepository(confluenceSpaces);
                    Log("导入完成。");
                    status = ImportStatus.ok("导入完成。");
                }catch (Exception e){
                    status = ImportStatus.error(1,"导入失败。");
                    Log("导入失败："+e.getMessage());
                    try {
                        FileUtils.deleteDirectory(new File(confluenceAddress));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    throw new RuntimeException(e.getMessage());
                }
                try {
                    FileUtils.deleteDirectory(new File(confluenceAddress));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return "ok";
            });
        }catch (Exception e){
            status = ImportStatus.error(1,"导入失败。");
        }
    }

    @Override
    public ConfluenceVersion validConfluenceVersion(String confluenceAddress){

        File zipFile = new File(confluenceAddress);
        if (!zipFile.exists()){
            throw new ApplicationException("无法找到文件!");
        }
        // 解压文件
        String unzipAddress = unzip(confluenceAddress);
        String versionAddress = unzipAddress + "/exportDescriptor.properties" ;
        File file = new File(versionAddress);
        ConfluenceVersion confluenceVersion = ConfluenceVersionConfig.parseExportDescriptor(file);
        String versionNumber = confluenceVersion.getVersionNumber();
        // if (versionNumber.contains("7.4") && confluenceVersion.getBuildNumber().equals("8402")){
        String buildNumber = confluenceVersion.getBuildNumber();
        if (buildNumber.equals("8501") || buildNumber.equals("8402") ){
            // 使用的是7.4.18 的版本
            confluenceVersion.setSupport(true);
        }else {
            FileUtils.deleteQuietly(new File(unzipAddress));
        }
        confluenceVersion.setPath(unzipAddress);
        confluenceVersion.setFileName(zipFile.getName());
        return confluenceVersion;
    }

    @Override
    public List<ConfluenceSpace> analysisEntityXml(String confluenceAddress){
        String entityAddress = confluenceAddress + "/entities.xml" ;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // 空间
            SpaceHandler spaceHandler = new SpaceHandler();
            parser.parse(entityAddress, spaceHandler);
            List<ConfluenceSpace> allSpaceList = ConfluenceDataConfig.instance().elementSpaceList(spaceHandler.getElementList());

            // 界面
            PageHandler pageHandler = new PageHandler();
            parser.parse(entityAddress, pageHandler);
            List<ConfluencePage> allPageList = ConfluenceDataConfig.instance().elementPageList(pageHandler.getElementList());

            // 界面内容
            BodyContentHandler bodyContentHandler = new BodyContentHandler();
            parser.parse(entityAddress, bodyContentHandler);
            List<ConfluenceBody> allBodyList = ConfluenceDataConfig.instance().elementBodyList(bodyContentHandler.getElementList());

            AttachmentHandler attachmentHandler = new AttachmentHandler();
            parser.parse(entityAddress, attachmentHandler);
            List<ConfluenceAttachment> allAttachmentList = ConfluenceDataConfig.instance().elementAttachmentList(attachmentHandler.getElementList(),confluenceAddress);

            Thread.sleep(1000);
            allSpaceList.forEach(space -> {
                String id = space.getId();
                List<ConfluencePage> pagelist = allPageList.stream()
                        .filter(page -> page.getSpaceId().equals(id))
                        .collect(Collectors.toList());
                pagelist.forEach(page ->{
                    List<ConfluenceBody> bodyList = allBodyList.stream()
                            .filter(body -> page.getId().equals(body.getPageId()))
                            .collect(Collectors.toList());
                    if (!bodyList.isEmpty()){
                        page.setBody(bodyList.get(0));
                    }
                    List<ConfluenceAttachment> attachmentList = allAttachmentList.stream()
                            .filter(attachment -> attachment.getPageId().equals(page.getId()))
                            .collect(Collectors.toList());
                    if (!attachmentList.isEmpty()){
                        page.setAttachmentIdList(attachmentList);
                    }
                });
                List<ConfluencePage> list = pagelist.stream()
                        .filter(page -> !Objects.isNull(page.getBody()))
                        .filter(page -> !StringUtils.isEmpty(page.getBody().getContent()))
                        .collect(Collectors.toList());
                space.setPageList(list);
            });
        return allSpaceList;
        }catch (Exception e){
            throw new ApplicationException(e);
        }
    }

    @Override
    public List<ConfluenceSpace> analysis(String confluenceAddress){
        String entityAddress = confluenceAddress + "/entities.xml" ;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            Log("解析空间......");

            // 空间
            SpaceHandler spaceHandler = new SpaceHandler();
            parser.parse(entityAddress, spaceHandler);
            List<ConfluenceSpace> allSpaceList = ConfluenceDataConfig.instance().elementSpaceList(spaceHandler.getElementList());
            Log("空间解析完成!");

            // 界面
            Log("解析页面......");
            PageHandler pageHandler = new PageHandler();
            parser.parse(entityAddress, pageHandler);
            List<ConfluencePage> allPageList = ConfluenceDataConfig.instance().elementPageList(pageHandler.getElementList());
            Log("页面解析完成!");

            // 界面内容
            Log("解析页面内容......");
            BodyContentHandler bodyContentHandler = new BodyContentHandler();
            parser.parse(entityAddress, bodyContentHandler);
            List<ConfluenceBody> allBodyList = ConfluenceDataConfig.instance().elementBodyList(bodyContentHandler.getElementList());
            Log("页面内容解析完成!");

            Log("解析附件......");
            AttachmentHandler attachmentHandler = new AttachmentHandler();
            parser.parse(entityAddress, attachmentHandler);
            List<ConfluenceAttachment> allAttachmentList = ConfluenceDataConfig.instance().elementAttachmentList(attachmentHandler.getElementList(),confluenceAddress);
            Log("附件解析完成!");

            // 转换附件
            Log("转换附件......");
            for (ConfluenceAttachment attachment : allAttachmentList) {
                File file = new File(attachment.getPath());
                String name = attachment.getName();
                if (!file.exists() || file.isDirectory()) {
                    continue;
                }
                String address = file.getParent() + "/" + name;
                // 转义文件
                writeFile(file.getAbsolutePath(),address);
                logger.info("write file：{}",address);
                try {
                    String upload = dataUpload.upload(new File(address));
                    attachment.setAddress(upload);
                }catch (Exception e){
                    Log("附件"+name+"转换失败!");
                    throw new ApplicationException(e.getMessage());
                }
            }
            Log("附件转换完成!");
            Log("开始导入......");
            allSpaceList.forEach(space -> {
                String id = space.getId();
                List<ConfluencePage> pagelist = allPageList.stream()
                        .filter(page -> page.getSpaceId().equals(id))
                        .collect(Collectors.toList());
                pagelist.forEach(page ->{
                    List<ConfluenceBody> bodyList = allBodyList.stream()
                            .filter(body -> page.getId().equals(body.getPageId()))
                            .collect(Collectors.toList());
                    if (!bodyList.isEmpty()){
                        page.setBody(bodyList.get(0));
                    }
                    List<ConfluenceAttachment> attachmentList = allAttachmentList.stream()
                            .filter(attachment -> attachment.getPageId().equals(page.getId()))
                            .collect(Collectors.toList());
                    if (!attachmentList.isEmpty()){
                        page.setAttachmentIdList(attachmentList);
                    }
                });
                List<ConfluencePage> list = pagelist.stream()
                        .filter(page -> !Objects.isNull(page.getBody()))
                        .filter(page -> !StringUtils.isEmpty(page.getBody().getContent()))
                        .collect(Collectors.toList());
                space.setPageList(list);
            });
        return allSpaceList;
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * 解压压缩包
     * @param ConfluenceFileAddress 压缩包地址
     * @return 解压后的文件位置
     */
    public String unzip(String ConfluenceFileAddress) {

        File zipAddress = new File(ConfluenceFileAddress);
        String name = zipAddress.getName().replace(".zip", "");

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(ConfluenceFileAddress);
        } catch (FileNotFoundException e) {
            throw new ApplicationException("读取压缩包内容失败!");
        }
        String address = unzipAddress + "/" +name;
        File file = new File(address);
        if (!file.exists()){
            file.mkdirs();
        }

        try {
            new UncompressUtil().unZIP(inputStream, address);
        } catch (IOException e) {
            e.printStackTrace();
            zipAddress.delete();
            throw new ApplicationException("无法解压文件！");
        }
        zipAddress.delete();
        return address;
    }

    /**
     * 写入不同的文件
     * @param sourcePath 源文件
     * @param targetPath 写入的文件
     */
    public void writeFile(String sourcePath,String targetPath) {

        // 创建输入流和输出流
        try (InputStream in = new FileInputStream(sourcePath);
             OutputStream out = new FileOutputStream(targetPath)) {

            byte[] buffer = new byte[4096];
            int length;

            // 一边读一边写
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建知识库
     * @param confluenceSpaces 知识库
     */
    public void createRepository(List<ConfluenceSpace> confluenceSpaces){
        for (ConfluenceSpace space : confluenceSpaces) {
            try {
                WikiRepository wikiRepository = new WikiRepository();
                Log("导入知识库:"+space.getName());
                wikiRepository.setName(space.getName());
                User user = new User("111111");
                wikiRepository.setMaster(user);
                wikiRepository.setIconUrl("repository1.png");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(space.getCreateTime());
                Timestamp timestamp = new Timestamp(date.getTime());
                wikiRepository.setCreateTime(timestamp);
                wikiRepository.setTypeId("1");
                wikiRepository.setStatus("nomal");
                wikiRepository.setLimits("0");
                String repositoryId = wikiRepositoryService.createConfluRepository(wikiRepository);

                wikiRepository.setId(repositoryId);
                // dssClient.save(wikiRepository);
                List<ConfluencePage> pageList = space.getPageList();
                // 导入文档
                createDocument(pageList,repositoryId);
                Log("知识库 "+space.getName()+"导入完成!");
            }catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException(2000, "知识库添加失败:" + e.getMessage());
            }
        }

    }

    /**
     * 导入文档
     * @param pageList
     */
    public void createDocument(List<ConfluencePage> pageList,String repositoryId){
        int documentSort = 0;
        for (ConfluencePage page : pageList) {
            WikiDocument wikiDocument = new WikiDocument();
            Node node = new Node();
            node.setName(page.getName());
            Log("创建文档:"+page.getName());
            node.setCreateTime(page.getCreateTime());
            node.setDimension(1);
            node.setSort(documentSort);

            WikiRepository wikiRepository = new WikiRepository();
            wikiRepository.setId(repositoryId);
            node.setWikiRepository(wikiRepository);
            User user = new User("111111");
            node.setMaster(user);
            node.setDocumentType("document");
            node.setType("document");
            node.setRecycle("0");
            node.setStatus("nomal");
            wikiDocument.setRepositoryId(repositoryId);
            wikiDocument.setNode(node);
            ConfluenceBody body = page.getBody();
            String content = body.getContent();

            Map<String,String> map = new HashMap<>();
            List<ConfluenceAttachment> attachmentIdList = page.getAttachmentIdList();
            for (ConfluenceAttachment confluenceAttachment : attachmentIdList) {
                String name = confluenceAttachment.getName();
                String address = confluenceAttachment.getAddress();
                map.put(name, address);
            }
            ConfluenceData confluenceData = ConfluenceDataBuilder.buildFromHtml(content);
            String detail = ConfluenceDataBuilder.buildFromHtmlList(confluenceData,map);
            if (StringUtils.isEmpty(detail) || detail.equals("[]")){
                Log("文档"+ page.getName()+"不支持转换,跳过创建!");
                continue;
            }
            wikiDocument.setDetails(detail);
            Document doc = Jsoup.parse(detail);
            wikiDocument.setDetailText(doc.text());
            documentSort++;
            try {
                 documentService.createConfluDocument(wikiDocument);
            }catch (Exception e) {
                throw new ApplicationException(2000, "文档添加失败:" + e.getMessage());
            }
        }
    }







}
