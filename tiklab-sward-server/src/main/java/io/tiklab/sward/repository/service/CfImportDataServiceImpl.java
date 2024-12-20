package io.tiklab.sward.repository.service;


import io.tiklab.sward.document.dao.DocumentDao;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.sward.category.dao.WikiCategoryDao;
import io.tiklab.sward.repository.dao.WikiRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class CfImportDataServiceImpl implements CfImportDataService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    DocumentDao documentDao;

    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

    @Autowired
    WikiCategoryDao wikiCategoryDao;

    @Value("${unzip.path}")
    String unzipAddress;


    @Override
    public String importConfluenceData(InputStream inputStream) {
//        BufferedReader unZIP=null;
//        try {
//            unZIP = new UncompressUtil().unZIP(inputStream,unzipAddress);
//          //  创建SAXReader 对象
//            org.dom4j.io.SAXReader saxReader = new org.dom4j.io.SAXReader();
//            //new ClassPathResource(unzipAddress+"/entities.xml").getFile();
//            String path=unzipAddress+"/entities.xml";
//            org.dom4j.Document document = saxReader.read(new File(path));
//            //获取根节点元素对象
//            org.dom4j.Element rootElement = document.getRootElement();
//            //获取二级节点
//            List<org.dom4j.Element> elements = rootElement.elements();
//            for (org.dom4j.Element element:elements){
//                secondNode(element);
//            }
//            return "succed";
//        } catch (Exception e) {
//            throw new ApplicationException(e);
//        }
        return "succed";
    }

    /**
     * 判断第二级节点类型类型
     * @param element  子节点
     */
//    public void secondNode(org.dom4j.Element element) throws Exception{
//        //获取节点属性
//        String value = element.attribute("class").getValue();
//        //节点类型为空间
//        if ("Space".equals(value)){
//           repository(element);
//        }
//        //文档
//        if ("Page".equals(value)){
//            document(element);
//        }
//        //富文本内容
//        if ("BodyContent".equals(value)){
//            richText(element);
//        }
//    }

    /**
     * 富文本内容解析
     * @param element  节点
     */
//    public void richText(org.dom4j.Element element) throws Exception {
//        //获取三级节点
//        List<org.dom4j.Element> secElements = element.elements();
//        //存富文本内容的list
//        List<Map> textDates = new ArrayList<>();
//        String elementId=null;
//        for (org.dom4j.Element secElement:secElements){
//            //获取 xml里面name属性的值
//            String name = secElement.attribute("name").getValue();
//            //为标题时
//            if ("content".equals(name)){
//                 elementId=(String) secElement.element("id").getData();
//            }
//            String data = (String)secElement.getData();
//            //为body
//            if ("body".equals(name)){
//                //以<p> 开头
//                boolean startsWith = data.startsWith("<p>");
//                if (startsWith){
//                    String s = "<li>" + data + "</li>";
//                    //解析富文本xml
//                    analysisXml(s,textDates);
//
//                }
//                //以<ac 开头的
//                boolean start= data.startsWith("<ac");
//               /* if (start){
//                    String s = "<li>" + data + "</li>";
//                    //解析富文本xml
//                    analysisXml(s,textDates);
//                }*/
//            }
//        }
//        //查询该文档是否已经保存
//        DocumentEntity document = documentDao.findDocument(elementId);
//        //将解析后的富文本内容转json
//        String date = JSON.toJSON(textDates).toString();
//        if (ObjectUtils.isEmpty(document)){
//            String sql = "insert into wiki_document(id,name,repository_id,type_id,category_id,details) values(?,?,?,?,?,?)";
//            getJdbcTemplet().update(sql,elementId,"***","***","**",null,date);
//        }else {
//            document.setDetails(date);
//            documentDao.updateDocument(document);
//        }
//
//    }
//
//    /**
//     * 解析富文本xml
//     * @param xmlData  原XML数据
//     * @param textDates  存富文本内容的list
//     */
//
//    public void analysisXml(String xmlData,List<Map> textDates ) throws org.dom4j.DocumentException {
//        //将&nbsp 替换成&#160
//        String newDate = xmlData.replaceAll("&nbsp", "&#160");
//        String s = newDate.replaceAll("&ldquo", "&#161");
//        String replaceAll1 = s.replaceAll("&rdquo", "&#162");
//        String all1 = replaceAll1.replaceAll("&euro", "&#163");
//        String replaceAll2 = all1.replaceAll("&frac14", "&#164");
//        String replaceAll3 = replaceAll2.replaceAll("&aacute", "&#165");
//        String replaceAll4 = replaceAll3.replaceAll("<br />", "");
//        String replaceAll5 = replaceAll4.replaceAll("&middot", ".");
//
//        String replaceAll = replaceAll5.replaceAll("ac:", "");
//
//        String all = replaceAll.replaceAll("ri:", "");
//        //将富文本框内容转xml
////        org.dom4j.DocumentHelper documentHelper = new org.dom4j.DocumentHelper();
//        org.dom4j.Document document = org.dom4j.DocumentHelper.parseText(all);
//        org.dom4j.Element rootElement = document.getRootElement();
//        List<org.dom4j.Element> elements = rootElement.elements();
//
//      /*  Map<String, Object> dataMap = new HashMap<>();
//        //递归查询添加
//        recursion(rootElement,dataMap);
//
//        textDates.add(dataMap);*/
//        //富文本内容第一级
//        for (org.dom4j.Element elemen:elements){
//            //解析xml后  实例新的map
//            Map<String, Object> newMap = new HashMap<>();
//            Map<String, Object> dataMap = new HashMap<>();
//            //获取标签名
//            String labelName = elemen.getName();
//            //类型匹配添加
//            typeMac(labelName,dataMap);
//            //判断第一层就是最后一层
//            List<org.dom4j.Element> list = elemen.elements();
//            if (!list.isEmpty()){
//                ArrayList arrayList = new ArrayList();
//                Object data = elemen.getData();
//                Map<String, Object> map = new HashMap<>();
//                map.put("text",data);
//                arrayList.add(map);
//                dataMap.put("children",arrayList);
//            }else {
//                //递归查询添加
//                recursion(elemen,dataMap);
//            }
//
//            //recursionTile(dataMap,newMap);
//
//            textDates.add(dataMap);
//        }
//    }

    /**
     * 递归查询
     * @param element  节点
     */
//    public  void recursion ( org.dom4j.Element element, Map<String, Object> dataMap){
//        //查询xml下一级节点
//        List<org.dom4j.Element> list = element.elements();
//        List<Map> richTextDates = new ArrayList<>();
//        Map<String, Object> childMap=null;
//        String labelName=null;
//        for (org.dom4j.Element textDateList:list){
//            childMap = new HashMap<>();
//            //获取标签名
//             labelName = textDateList.getName();
//            //类型匹配添加
//            typeMac(labelName,childMap);
//            String elemenData =(String) textDateList.getData();
//            String replaceAll = elemenData.replaceAll("\n", "");
//            if (!StringUtils.isEmpty(replaceAll)){
//                //添加富文本内容
//                childMap.put("text",elemenData);
//            }
//            richTextDates.add(childMap);
//        }
//        if (!list.isEmpty()  && list.size()>0){
//            dataMap.put("children",richTextDates);
//            for (org.dom4j.Element  el:list){
//                recursion(el,childMap);
//            }
//        }
//    }


    /**
     * 类型匹配添加
     * @param  name xml 标签名
     * @param  dataMap
     *
     */
//    public void typeMac(String name,  Map<String, Object> dataMap){
//        //xml标签   匹配类型转换
//        if ("p".equals(name)){
//            dataMap.put("type","paragraph");
//        }
//        if ("layout".equals(name)){
//            dataMap.put("type","paragraph");
//        }
//        if ("layout-section".equals(name)){
//            dataMap.put("type","paragraph");
//        }
//        if ("layout-cell".equals(name)){
//            dataMap.put("type","paragraph");
//        }
//        if ("h1".equals(name)){
//            dataMap.put("type","head");
//            dataMap.put("head","h1");
//        }
//        if ("h2".equals(name)){
//            dataMap.put("type","head");
//            dataMap.put("head","h2");
//        }
//        if ("h3".equals(name)){
//            dataMap.put("type","head");
//            dataMap.put("head","h3");
//        }
//        if ("h4".equals(name)){
//            dataMap.put("type","head");
//            dataMap.put("head","h4");
//        }
//        if ("h5".equals(name)){
//            dataMap.put("type","head");
//            dataMap.put("head","h5");
//        }
//        if ("task-body".equals(name)){
//            dataMap.put("type","check-list-item");
//        }
//        if ("image ".equals(name)){
//            dataMap.put("type","image");
//            dataMap.put("url","");
//        }
//        if ("a".equals(name)){
//            dataMap.put("type","link");
//            dataMap.put("url","");
//        }
//
//        //strong 标签对应加粗
//        if ("strong".equals(name)){
//            dataMap.put("bold",true);
//        }
//        //斜体
//        if ("em".equals(name)){
//            dataMap.put("italic",true);
//        }
//        //下划线
//        if ("u".equals(name)){
//            dataMap.put("underline",true);
//        }
//    }
//
//    /**
//     * elemen
//     * @param element  节点
//     */
//    public void repository(org.dom4j.Element element){
//        //定义属性字段
//        String id=null;
//        String reName=null;
//        String desc=null;
//        String creator=null;
//        String spaceType=null;
//        //获取三级节点
//        List<org.dom4j.Element> secElements = element.elements();
//        for (org.dom4j.Element secElement:secElements){
//            //获取节点属性为name
//            String name = secElement.attribute("name").getValue();
//            //属性值为id
//            if ("id".equals(name)){
//                id = (String)secElement.getData();
//            }
//            //名称
//            if ("name".equals(name)){
//                reName = (String)secElement.getData();
//            }
//            //描述
//            if ("description".equals(name)){
//                desc = (String)secElement.getData();
//            }
//            //创建人
//            if ("creator".equals(name)){
//                org.dom4j.Element elementId= secElement.element("id");
//                 creator =(String) elementId.getData();
//            }
//            //类型
//            if ("spaceType".equals(name)){
//                spaceType = (String)secElement.getData();
//            }
//        }
//        RepositoryEntity repository = repositoryDao.findRepository(id);
//        if (ObjectUtils.isEmpty(repository)){
//            String sql = "insert into wiki_repository(id,name,type_id,master,description) values(?,?,?,?,?)";
//            getJdbcTemplet().update(sql,id,reName,spaceType,creator,"描述");
//            //给默认con的目录添加 空间
//            CategoryEntity categoryEntity = new CategoryEntity();
//            categoryEntity.setId("1234567890");
//            categoryEntity.setRepositoryId(id);
//            categoryDao.updateCategory(categoryEntity);
//        }else {
//            repository.setName(reName);
//            repository.setTypeId(spaceType);
//            repository.setMaster(creator);
//            repositoryDao.updateRepository(repository);
//        }
//
//    }
//    /**
//     * 文档类型导入
//     * @param element  节点
//     */
//    public void document(org.dom4j.Element element){
//        String id=null;
//        String docName=null;
//        String spaceId="null";
//        //获取三级节点
//        List<org.dom4j.Element> secElements = element.elements();
//        for (org.dom4j.Element secElement:secElements){
//            //获取节点属性为name
//            String name = secElement.attribute("name").getValue();
//            if ("id".equals(name)){
//                  id = (String)secElement.getData();
//            }
//            //名字
//            if ("title".equals(name)){
//                docName = (String)secElement.getData();
//            }
//            //空间id
//            if ("space".equals(name)){
//                org.dom4j.Element elementId= secElement.element("id");
//                spaceId =(String) elementId.getData();
//            }
//        }
//        DocumentEntity document = documentDao.findDocument(id);
//        //为空添加
//        if (ObjectUtils.isEmpty(document)){
//            String sql = "insert into wiki_document(id,name,repository_id,type_id,category_id,details) values(?,?,?,?,?,?)";
//            getJdbcTemplet().update(sql,id,docName,spaceId,"**",null,"**");
//        }else {
//            //不为空 修改
//            document.setName(docName);
//            document.setRepositoryId(spaceId);
//            documentDao.updateDocument(document);
//        }
//
//    }
//
//
//    /**
//     * 解析xml后  获取的结果  递归
//     * @param dataMap  节点
//     */
//    public  void recursionTile(Map<String, Object> dataMap,Map<String, Object> newMap){
//        //获取childer
//        Object childrenData = dataMap.get("children");
//        List<Map>  fixationMp=null;
//        if (!ObjectUtils.isEmpty(childrenData)){
//            List<Map> children=  (List)childrenData;
//            List<Map> objects = new ArrayList<>();
//            for (Map map :children){
//
//                findEmbe(map,newMap);
//                //objects.add()
//                if (newMap.size()<=0){
//                    fixationMp =children;
//                }
//                //递归
//                recursionTile(map,newMap);
//
//            }
//            if (!ObjectUtils.isEmpty(fixationMp)){
//                fixationMp.add(newMap);
//            }
//        }
//        System.out.println("");
//    }
//    /**
//     * 解析xml后   查询修饰
//     * @param map  xml解析后的map
//     * @param newMap  新的map
//     */
//    public void findEmbe(Map map,Map<String, Object> newMap){
//        //加粗
//        Object bold =  map.get("bold");
//        if (!ObjectUtils.isEmpty(bold)){
//            newMap.put("bold",true);
//        }
//        //斜体
//        Object italic = map.get("italic");
//        if (!ObjectUtils.isEmpty(italic)){
//            newMap.put("italic",true);
//        }
//        //下划线
//        Object underline = map.get("underline");
//        if (!ObjectUtils.isEmpty(underline)){
//            newMap.put("underline",true);
//        }
//        Object text = map.get("text");
//        if (!ObjectUtils.isEmpty(text)){
//            String data = (String)map.get("text");
//            newMap.put("text",data);
//        }
//        System.out.println("");
//    }
//
//
//
//    public JdbcTemplate getJdbcTemplet(){
//        return  jpaTemplate.getJdbcTemplate();
//    }

}
