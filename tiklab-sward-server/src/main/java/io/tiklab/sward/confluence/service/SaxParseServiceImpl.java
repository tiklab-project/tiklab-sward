package io.tiklab.sward.confluence.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SaxParseServiceImpl extends DefaultHandler  {

    boolean isDocument = false;
    boolean isDocumentCore = false;

    boolean isDocumentCoreId = false;
    boolean isDocumentCoreContent = false;

    boolean isDocumentCoreProperty = false;
    boolean isSpace = false;
    boolean isDocumentAttribute = false;

    boolean isDocumentSpace = false;

    boolean isCreator = false;

    boolean isDocumentId = false;

    boolean isBodyContent = false;

    boolean isCollection = false;
    boolean isBodyContentId = false;

    boolean hasTitleCharacters = false;

    boolean isTitle = false;

    Integer spaceIdNum = 0;
    boolean isSpaceId = false;
    String flag = new String();
    String flagName = new String();
    boolean isSpaceName = false;
    boolean isCreationDate = false;

    boolean isSpaceCreator = false;

    boolean isSpaceCreatorId = false;
    boolean isUser = false;
    boolean isUserActive = false;
    boolean isUserEmail = false;
    boolean isUserCreateDate = false;

    boolean isConUser = false;
    boolean isConUserId = false;
    boolean isConUserName = false;
    boolean isConUserEmail = false;


    List<Element> ElementList = new ArrayList<org.w3c.dom.Element>();

    String[] propertyName = {"id", "title", "creator", "creationDate", "lastModificationDate"};
    String[] spacePropertyName = {"name", "creator", "creationDate"};
    private static Element globalElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 检查元素名称是否是 object，且 class 属性是否是 "Page"
        flag = qName;
        String className = attributes.getValue("class");
        String packageName = attributes.getValue("package");
        String name = attributes.getValue("name");

        if(qName.equals("object")) {
            if("Page".equals(className) && packageName.equals("com.atlassian.confluence.pages")) {
                 isDocument = true;
                 createElement("Document");
            }

            if("Space".equals(className) && packageName.equals("com.atlassian.confluence.spaces")){
                isSpace = true;
                createElement("Space");
                spaceIdNum = 1;
            }

            if("InternalUser".equals(className) && packageName.equals("com.atlassian.crowd.model.user")){
                isUser = true;
                createElement("User");
            }
            if("ConfluenceUserImpl".equals(className) && packageName.equals("com.atlassian.confluence.user")){
                isConUser = true;
                createElement("ConUser");
            }
            if("BodyContent".equals(className) && packageName.equals("com.atlassian.confluence.core")){
                isDocumentCore = true;
                createElement("BodyContent");
            }
        }

        if(isDocument == true){
            // 如果当前元素是 Document 的一部分，处理 id 和属性
            if(qName.equals("property") || (qName.equals("id") && !isCollection)){
                String propName = attributes.getValue("name");
                if (Arrays.asList(propertyName).contains(propName)) {
                    flagName = propName;
                    isDocumentAttribute = true;
                    if(propName.equals("title")){
                        isTitle = true;
                        hasTitleCharacters = false;
                    }

                }

                if(propName.equals("space")){
                    isDocumentSpace = true;
                }
                if(propName.equals("creator")){
                    isCreator = true;
                }
                if(qName.equals("id") && !isDocumentSpace){
                    isDocumentId = true;
                }
            }

            if(qName.equals("collection")){
                isCollection = true;
            }

            if(isCollection && "BodyContent".equals(className)){
                isBodyContent = true;
            }

            if(isBodyContent && qName.equals("id")){
                isBodyContentId = true;
            }
        }

        if(isSpace == true){
            if(qName.equals("id")){
                if(spaceIdNum == 1){
                    isSpaceId = true;
                    spaceIdNum = spaceIdNum + 1;
                }else {
                    spaceIdNum = spaceIdNum + 1;
                }
            }

            if(qName.equals("property")){
                if("name".equals(name)){
                    isSpaceName = true;
                }
                if("creationDate".equals(name)){
                    isCreationDate = true;
                }
                if("creator".equals(name)){
                    isSpaceCreator = true;
                }
            }
            if(isSpaceCreator && qName.equals("id")){
                isSpaceCreatorId = true;
            }
        }

        if(isUser == true){
            if(qName.equals("property")){
                if(name.equals("createdDate")){
                    isUserCreateDate = true;
                }
                if(name.equals("emailAddress")){
                    isUserEmail = true;
                }
                if(name.equals("active")){
                    isUserActive = true;
                }
            }
        }

        if(isConUser){
            if(qName.equals("property")){
                if(name.equals("name")){
                    isConUserName = true;
                }
                if(name.equals("email")){
                    isConUserEmail = true;
                }
            }

            if(qName.equals("id")){
                isConUserId = true;
            }
        }

        if(isDocumentCore){
            if("id".equals(qName) && !isDocumentCoreProperty){
                isDocumentCoreId = true;
            }
            if(qName.equals("property")){
                isDocumentCoreProperty = true;
                if("body".equals(name)){
                    isDocumentCoreContent = true;
                }

            }
        }
    }

    public void createElement(String name){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            globalElement = document.createElement(name);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 收集元素内容
        if(isDocument){
            String content = new String(ch, start, length).trim();
            if(isDocumentAttribute){
                switch (flagName){
                    case "title":
                        hasTitleCharacters = true;
                        globalElement.setAttribute("title", content);
                        break;
                    case "creationDate":
                        globalElement.setAttribute("creationDate", content);
                        break;
                    default:
                        break;
                }
            }

            if(isDocumentSpace == true && flagName.equals("id")){
                globalElement.setAttribute("space", content);
            }
            if(isCreator == true  && flagName.equals("key")){
                globalElement.setAttribute("creator", content);
            }
            if(isDocumentId == true  && flagName.equals("id")){
                globalElement.setAttribute("id", content);
            }
            if(isBodyContentId == true ){
                globalElement.setAttribute("bodyContentId", content);
                System.out.println("isBodyContentId------" + content);
            }
        }

        if(isSpace){
            String content = new String(ch, start, length).trim();
            if(isSpaceId){
                globalElement.setAttribute("id", content);
            }
            if(isSpaceName){
                globalElement.setAttribute("name", content);
            }
            if(isCreationDate){
                globalElement.setAttribute("creationDate", content);
            }
            if(isSpaceCreatorId){
                globalElement.setAttribute("creator", content);
                isSpaceCreator = false;
            }
        }

        if(isUser){
            String content = new String(ch, start, length).trim();
            if(isUserEmail){
                globalElement.setAttribute("emailAddress", content);
            }
            if(isUserCreateDate){
                globalElement.setAttribute("createdDate", content);
            }
            if(isUserActive){
                globalElement.setAttribute("active", content);
            }
        }

        if(isConUser){
            String content = new String(ch, start, length).trim();
            if(isConUserName){
                globalElement.setAttribute("name", content);
            }
            if(isConUserEmail){
                globalElement.setAttribute("email", content);
            }
            if(isConUserId){
                globalElement.setAttribute("conUserId", content);
            }
        }

        if(isDocumentCore){
            String content = new String(ch, start, length).trim();

            if(isDocumentCoreId && !isDocumentCoreProperty){
                globalElement.setAttribute("id", content);
            }
            if(isDocumentCoreContent){
                globalElement.setAttribute("body", content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isDocument) {
            if(qName.equals("object")){
                ElementList.add(globalElement);
                isDocument = false;
            }

            if(!hasTitleCharacters && isTitle){
                isTitle = false;
                isDocument = false;
                isDocumentAttribute = false;
                isDocumentSpace = false;
                isCreator = false;
                isCollection = false;
                isBodyContentId = false;
                isDocumentId = false;
            }

            if (isDocumentAttribute) {
                isDocumentAttribute = false;
            }

            if(isDocumentSpace && qName.equals("id")){
                isDocumentSpace = false;
            }

            if(isCreator && qName.equals("property")){
                isCreator = false;
            }

            if(isCollection && qName.equals("collection")){
                isCollection = false;
                isBodyContent = false;
            }

            if(isBodyContentId && qName.equals("id")){
                isBodyContentId = false;
            }

            if(isDocumentId && qName.equals("id")){
                isDocumentId = false;
            }
        }
        if(isSpace){
            if(qName.equals("object")){
                ElementList.add(globalElement);
                isSpace = false;
            }
            if(isSpaceId){
                isSpaceId = false;
            }
            if(isSpaceName){
                isSpaceName = false;
            }
            if(isCreationDate){
                isCreationDate = false;
            }
            if(isSpaceCreatorId){
                isSpaceCreatorId = false;
            }
        }

        if(isUser){
            if(qName.equals("object")){
                ElementList.add(globalElement);
                isUser = false;
            }
            if(isUserEmail){
                isUserEmail = false;
            }
            if(isUserCreateDate){
                isUserCreateDate = false;
            }
            if(isUserActive){
                isUserActive = false;
            }
        }

        if(isConUser){
            if(qName.equals("object")){
                ElementList.add(globalElement);
                isConUser = false;
            }
            if(isConUserEmail){
                isConUserEmail = false;
            }
            if(isConUserName){
                isConUserName = false;
            }
            if(isConUserId){
                isConUserId = false;
            }
        }

        if(isDocumentCore){
            if(qName.equals("object")){
                ElementList.add(globalElement);
                isDocumentCore = false;
                isDocumentCoreProperty = false;
            }

            if(isDocumentCoreId){
                isDocumentCoreId = false;
            }
            if(isDocumentCoreContent){
                isDocumentCoreContent = false;
            }
        }
    }

    public List<Element> getElementList(){
        return  ElementList;
    }
}
