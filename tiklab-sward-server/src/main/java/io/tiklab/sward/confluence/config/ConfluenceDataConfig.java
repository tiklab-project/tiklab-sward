package io.tiklab.sward.confluence.config;

import io.tiklab.sward.confluence.model.ConfluenceAttachment;
import io.tiklab.sward.confluence.model.ConfluenceBody;
import io.tiklab.sward.confluence.model.ConfluencePage;
import io.tiklab.sward.confluence.model.ConfluenceSpace;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfluenceDataConfig {

    public static ConfluenceDataConfig  instance(){
        return new ConfluenceDataConfig();
    }

    /**
     * element转换为空间模型
     * @param elementList
     * @return
     */
    public List<ConfluenceSpace> elementSpaceList(List<Element> elementList){
        List<ConfluenceSpace> spaceList = new ArrayList<>();
        for (Element element : elementList) {
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String creationDate = element.getAttribute("creationDate");
            String creator = element.getAttribute("creator");
            ConfluenceSpace confluenceSpace = new ConfluenceSpace(id, name, updateTime(creationDate), creator);
            spaceList.add(confluenceSpace);
        }
        return spaceList;
    }

    /**
     * element转换为文档模型
     * @param elementList
     * @return
     */
    public List<ConfluencePage> elementPageList(List<Element> elementList){
        List<ConfluencePage> pageList = new ArrayList<>();
        for (Element element : elementList) {
            String contentStatus = element.getAttribute("contentStatus");
            if (!contentStatus.equals("current")){
                continue;
            }

            String name = element.getAttribute("title");
            String id = element.getAttribute("id");
            String creationDate = element.getAttribute("creationDate");
            String creator = element.getAttribute("creator");
            String spaceId = element.getAttribute("spaceId");

            String version = element.getAttribute("version");
            // String attachmentIds = element.getAttribute("attachmentIds");
            ConfluencePage confluencePage = new ConfluencePage(name,updateTime(creationDate),creator,id, spaceId);
            confluencePage.setVersion(version);
            confluencePage.setContentStatus(contentStatus);
            pageList.add(confluencePage);
        }
        return pageList.stream()
                .filter(page -> !StringUtils.isEmpty(page.getName()))
                .collect(Collectors.toList());
    }

    /**
     * element转换为内容模型
     * @param elementList
     * @return
     */
    public List<ConfluenceBody> elementBodyList(List<Element> elementList){
        List<ConfluenceBody> bodyList = new ArrayList<>();
        for (Element element : elementList) {
            String id = element.getAttribute("id");
            String content = element.getAttribute("content");
            String bodyType = element.getAttribute("bodyType");
            String pageId = element.getAttribute("pageId");
            ConfluenceBody confluencePage = new ConfluenceBody(id, content, bodyType,pageId);
            bodyList.add(confluencePage);
        }
        return bodyList.stream()
                .filter(body -> !StringUtils.isEmpty(body.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * element转换为内容模型(没有具体内容)
     * @param elementList
     * @return
     */
    public List<ConfluenceBody> elementBodyListNoContent(List<Element> elementList){
        List<ConfluenceBody> bodyList = new ArrayList<>();
        for (Element element : elementList) {
            String id = element.getAttribute("id");
            // String content = element.getAttribute("content");
            String bodyType = element.getAttribute("bodyType");
            String pageId = element.getAttribute("pageId");
            ConfluenceBody confluencePage = new ConfluenceBody(id, bodyType,pageId);
            bodyList.add(confluencePage);
        }
        return bodyList.stream()
                .filter(body -> !StringUtils.isEmpty(body.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * element转换为附件模型
     * @param elementList
     * @return
     */
    public List<ConfluenceAttachment> elementAttachmentList(List<Element> elementList,String confluenceAddress){
        List<ConfluenceAttachment> bodyList = new ArrayList<>();
        for (Element element : elementList) {
            String id = element.getAttribute("id");
            String name = element.getAttribute("title");
            String creationDate = element.getAttribute("creationDate");
            String status = element.getAttribute("status");
            String pageId = element.getAttribute("pageId");
            String version = element.getAttribute("version");
            String spaceId = element.getAttribute("spaceId");
            String contentProperties = element.getAttribute("contentProperties");
            ConfluenceAttachment confluenceAttachment = new ConfluenceAttachment(id,name,updateTime(creationDate),status,pageId);
            confluenceAttachment.setVersion(version);
            confluenceAttachment.setSpaceId(spaceId);
            confluenceAttachment.setPath(confluenceAddress+"/attachments/"+pageId+"/"+id+"/"+version);
            if (StringUtils.isEmpty(contentProperties)){
                confluenceAttachment.setContentPropertiesList(Arrays.stream(contentProperties.split(",")).toList());
            }
            bodyList.add(confluenceAttachment);
        }
        return bodyList.stream()
                .collect(Collectors.toList());
    }

    // 转换时间
    public static String updateTime(String original) {
        if (StringUtils.isEmpty(original)){
            return null;
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(original, inputFormatter);
        return dateTime.format(outputFormatter);
    }

}


















