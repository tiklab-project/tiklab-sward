package io.tiklab.sward.confluence.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.sward.document.service.DocumentService;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.model.UserQuery;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * jira 数据导入服务
 */
@Service
@EnableTransactionManagement
public class ConfluenceImportData719ServiceImpl implements ConfluenceImportData719Service {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    UserService userService;

    @Autowired
    NodeService nodeService;

    @Autowired
    DocumentService documentService;
    @Autowired
    AnalysisHtmlService analysisHtmlService;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    private ThreadLocal<ArrayList<Element>> UserElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> ConfluUserElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> SpaceElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> DocumentElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> DocumentCoreElementList = new ThreadLocal<>();
    @Override
    public String writeData(List<Element> elements, Map<String, WikiRepository> CurrentProject, Map<String, Integer> Percent) {
        String createUserId = LoginContext.getLoginId();
        try {

            ArrayList<Element> userElementList = new ArrayList<>();
            ArrayList<Element> confluUserElementList = new ArrayList<>();
            ArrayList<Element> spaceElementList = new ArrayList<>();
            ArrayList<Element> documentElementList = new ArrayList<>();
            ArrayList<Element> documentCoreElementList = new ArrayList<>();
            for (Element element : elements) {
                String name = element.getTagName();
                switch (name){
                    case "User":
                        userElementList.add(element);
                        break;
                    case "ConUser":
                        confluUserElementList.add(element);
                        break;
                    case "Space":
                        spaceElementList.add(element);
                        break;
                    case "Document":
                        documentElementList.add(element);
                        break;
                    case "BodyContent":
                        documentCoreElementList.add(element);
                        break;
                    default:
                        break;
                }
            }

            this.UserElementList.set(userElementList);
            this.ConfluUserElementList.set(confluUserElementList);
            this.SpaceElementList.set(spaceElementList);
            this.DocumentElementList.set(documentElementList);
            this.DocumentCoreElementList.set(documentCoreElementList);
            for (Element userElement : this.UserElementList.get()) {
                setGlobalUser(userElement);
            }

            // 获取导入项目的总数
            int size = spaceElementList.size();
            Percent.put(createUserId + "total", size);
            for (Element spaceElement : spaceElementList) {
                setSpace(spaceElement, createUserId, CurrentProject, Percent);
            }
            return "succed";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public void setGlobalUser(Element element){
        String active = element.getAttribute("active");
        String emailAddress = element.getAttribute("emailAddress");
        String createdDate = element.getAttribute("createdDate");

        if(active.equals("true")) {
            for (Element confluUserElement : this.ConfluUserElementList.get()) {
                String confluUserId = confluUserElement.getAttribute("conUserId");
                String email = confluUserElement.getAttribute("email");
                String name = confluUserElement.getAttribute("name");
                try {
                    if (emailAddress.equals(email)) {
                        UserQuery userQuery = new UserQuery();
                        userQuery.setEmail(emailAddress);
                        List<User> userList = userService.findUserList(userQuery);
                        userQuery.setEmail(null);
                        userQuery.setName(name);
                        List<User> userList1 = userService.findUserList(userQuery);
                        if (userList.size() == 0 && userList1.size() == 0) {

                            User user = new User();
                            user.setName(name);
                            user.setNickname(name);
                            user.setEmail(email);
                            user.setStatus(1);
                            user.setDirId("1");
                            user.setPassword("123456");
                            user.setType(0);
                            String userId = userService.createUser(user);
                            element.setAttribute("newId", userId);
                            element.setAttribute("conUserId", confluUserId);
                        } else {
                            String userId = new String();
                            if(userList.size() > 0){
                                User user = userList.get(0);
                                userId = user.getId();
                            }else if(userList1.size() > 0){
                                User user = userList1.get(0);
                                userId = user.getId();
                            }

                            element.setAttribute("newId", userId);
                            element.setAttribute("conUserId", confluUserId);
                        }
                    }
                } catch (Exception e) {
                    throw new ApplicationException(2000, "成员添加失败: " + e.getMessage());
                }
            }
        }
    }

    public void setSpace(Element element, String createUserId, Map<String, WikiRepository> CurrentProject, Map<String, Integer> Percent){
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String creationDate = element.getAttribute("creationDate");
        String creator = element.getAttribute("creator");
        try {
            WikiRepository wikiRepository = new WikiRepository();
            wikiRepository.setName(name);
            String userId = getUserId(creator);
            User user = new User();
            if(userId.isEmpty()){
                user.setId("111111");
            }else {
                user.setId(userId);
            }

            wikiRepository.setMaster(user);
            wikiRepository.setIconUrl("repository1.png");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(creationDate);
            Timestamp timestamp = new Timestamp(date.getTime());
            wikiRepository.setCreateTime(timestamp);
            wikiRepository.setTypeId("1");
            wikiRepository.setStatus("nomal");
            wikiRepository.setLimits("0");
            String repositoryId = wikiRepositoryService.createConfluRepository(wikiRepository);
            element.setAttribute("newId", repositoryId);
            setDocument(element);
            Integer integer = Percent.get(createUserId + "currentNum");
            if(!ObjectUtils.isEmpty(integer)){
                integer++;
                Percent.put(createUserId + "currentNum", integer);
            }else {
                Percent.put(createUserId + "currentNum", 1);
            }
            CurrentProject.put(createUserId + "wikiRepository",wikiRepository);

        }catch (Exception e) {
            throw new ApplicationException(2000, "知识库添加失败:" + e.getMessage());
        }
    }

    public static String escapeSql(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("'", "''");
    }
    public void setDocumentText(Element documentElement) {
        String title = documentElement.getAttribute("title");
        String name = escapeSql(title);
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setName(name);
        nodeQuery.setRepositoryId("b9c95c3e4193");
        List<Node> nodeList = nodeService.findNodeListByName(nodeQuery);
//        if (nodeList.size() <= 0) {
            String creationDate = documentElement.getAttribute("creationDate");
            String creator = documentElement.getAttribute("creator");
            String bodyContentId = documentElement.getAttribute("bodyContentId");
            Integer documentSort = 0;
            documentSort = documentSort + 1;
            WikiDocument wikiDocument = new WikiDocument();
            Node node = new Node();
            node.setName(title);
            node.setCreateTime(creationDate);
            node.setDimension(1);
            node.setSort(documentSort);

            WikiRepository wikiRepository = new WikiRepository();
            wikiRepository.setId("b9c95c3e4193");
            node.setWikiRepository(wikiRepository);
            String userId = getUserId(creator);
            User user = new User();
            user.setId(userId);
            node.setMaster(user);
            node.setDocumentType("document");
            node.setType("document");
            node.setRecycle("0");
            node.setStatus("nomal");
            wikiDocument.setNode(node);
            try {
                String documentId = documentService.createConfluDocument(wikiDocument);
                setDocumentContent(documentId, bodyContentId);
            } catch (Exception e) {
                throw new ApplicationException(2000, "文档添加失败:" + e.getMessage());
            }

//        }
    }
    public void setDocument(Element element){
        String spaceId = element.getAttribute("id");
        String newId = element.getAttribute("newId");
        ArrayList<String> documentNameList = new ArrayList<>();
        // 解决重复
        for (Element documentElement : this.DocumentElementList.get()) {
            String title = documentElement.getAttribute("title");
            String name = escapeSql(title);
            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setName(name);
            nodeQuery.setRepositoryId(newId);
            List<Node> nodeList = nodeService.findNodeListByName(nodeQuery);
            if(!documentNameList.contains(name)){
                String creationDate = documentElement.getAttribute("creationDate");
                String space = documentElement.getAttribute("space");
                String creator = documentElement.getAttribute("creator");
                String id = documentElement.getAttribute("id");
                String bodyContentId = documentElement.getAttribute("bodyContentId");
                Integer documentSort = 0;
                if(spaceId.equals(space)){
                    documentSort = documentSort + 1;
                    WikiDocument wikiDocument = new WikiDocument();
                    Node node = new Node();
                    node.setName(title);
                    node.setCreateTime(creationDate);
                    node.setDimension(1);
                    node.setSort(documentSort);

                    WikiRepository wikiRepository = new WikiRepository();
                    wikiRepository.setId(newId);
                    node.setWikiRepository(wikiRepository);
                    String userId = getUserId(creator);
                    User user = new User();
                    user.setId(userId);
                    node.setMaster(user);
                    node.setDocumentType("document");
                    node.setType("document");
                    node.setRecycle("0");
                    node.setStatus("nomal");
                    wikiDocument.setNode(node);
                    try {
                        String documentId = documentService.createConfluDocument(wikiDocument);
                        documentNameList.add(name);
                        setDocumentContent(documentId, bodyContentId);
                    }catch (Exception e) {
                        throw new ApplicationException(2000, "文档添加失败:" + e.getMessage());
                    }

                }
            }
        }
    }
    public void setDocumentContent(String documentId, String bodyContentId){
        for (Element element : this.DocumentCoreElementList.get()) {
            String id = element.getAttribute("id");
            if(id.equals(bodyContentId)){
                String body = element.getAttribute("body");
                HashMap<String, String> contentJson = new HashMap<>();
                contentJson.put("cdata", body);
                // 创建 ObjectMapper 实例
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = new String();
                try {
                    // 将 HashMap 转换为 JSON 字符串
                    jsonString = objectMapper.writeValueAsString(contentJson);
                    WikiDocument wikiDocument = new WikiDocument();
                    wikiDocument.setDetails(jsonString);
                    wikiDocument.setId(documentId);
                    documentService.updateDocument(wikiDocument);
                    // 输出 JSON 字符串
                    System.out.println("JSON 字符串: " + jsonString);

                    // 可以在这里将 jsonString 存储到数据库
                    // saveToDatabase(jsonString);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    }

    public String getUserId(String creator){
        String userId = new String();
        ArrayList<Element> userElementList = this.UserElementList.get();
        for (Element userElement : userElementList) {
            String conUserId = userElement.getAttribute("conUserId");
            String id = userElement.getAttribute("newId");
            if(conUserId.equals(creator)){
                userId = id;
            }
        }
        return  userId;
    }

    /**
     * 转换为时间戳转换为日期
     * @param timetamp
     * @return
     */
    public String toDateString(String timetamp){
         // 示例时间戳字符串
        String formattedDate = new String();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long timestamp = Long.parseLong(timetamp);
            Date date = new Date(timestamp); // 时间戳通常是以秒为单位，所以需要乘以1000转成毫秒
            formattedDate = sdf.format(date);
            System.out.println("Formatted Date: " + formattedDate);
        } catch (NumberFormatException e) {
            System.out.println("Invalid timestamp");
        }
        return formattedDate;
    }
    public JdbcTemplate getJdbcTemplet(){
      return  jpaTemplate.getJdbcTemplate();
    }

}
