package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class AttachmentHandler extends BaseHandler {

    private boolean isAttachment = false;
    private boolean isProperty = false;
    private boolean isId = false;
    private boolean isContainerContent = false;
    private boolean isSpace = false;
    private boolean isContentProperty = false; // 用于标记是否在 contentProperties 中解析 ID

    private String currentProperty = "";
    private Element currentElement;
    private List<String> contentPropertiesIds = new ArrayList<>(); // 存储 contentProperties 中的所有 ID

    private String attachmentId = ""; // 用于保存附件的顶层 ID

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("object".equals(qName)
                && "Attachment".equals(attributes.getValue("class"))
                && "com.atlassian.confluence.pages".equals(attributes.getValue("package"))) {
            isAttachment = true;
            currentElement = createElement("Attachment");
            contentPropertiesIds.clear(); // 每次新建 Attachment 重置列表
            attachmentId = ""; // 重置附件 ID
        }

        if (isAttachment) {
            if ("id".equals(qName) && "id".equals(attributes.getValue("name"))) {
                isId = true; // 标记 ID 元素
            }

            if ("property".equals(qName)) {
                currentProperty = attributes.getValue("name");
                isProperty = true;

                isContainerContent = "containerContent".equals(currentProperty);
                isSpace = "space".equals(currentProperty);
            }

            // 进入 contentProperties 集合时
            if ("collection".equals(qName) && "contentProperties".equals(attributes.getValue("name"))) {
                isContentProperty = true;
            }

            // 进入 ContentProperty 元素时
            if ("element".equals(qName) && "ContentProperty".equals(attributes.getValue("class"))) {
                isContentProperty = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isAttachment || (!isId && !isContentProperty && !isProperty)) return;

        String content = new String(ch, start, length).trim();
        if (content.isEmpty()) return;

        // 处理顶层附件 ID
        if (isId && attachmentId.isEmpty()) {
            attachmentId = content;  // 设置附件的顶层 ID
            currentElement.setAttribute("id", attachmentId);  // 设置附件 ID 属性
            return;
        }

        if (isProperty) {
            switch (currentProperty) {
                case "title":
                    currentElement.setAttribute("title", content);
                    break;
                case "creationDate":
                    currentElement.setAttribute("creationDate", content);
                    break;
                case "contentStatus":
                    currentElement.setAttribute("status", content);
                    break;
                case "version":
                    currentElement.setAttribute("version", content);
                    break;
            }
        }

        // 处理 containerContent 的页面 ID
        if (isContainerContent) {
            currentElement.setAttribute("pageId", content);
        }

        // 处理空间 ID
        if (isSpace) {
            currentElement.setAttribute("spaceId", content);
        }

        // 处理 contentProperties 中的所有 ID
        if (isContentProperty && isId) {
            contentPropertiesIds.add(content);  // 将 contentProperties 中的 ID 添加到列表
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isAttachment) {
            // 将 contentProperties 中的所有 ID 添加到 Attachment 元素中
            if (!contentPropertiesIds.isEmpty()) {
                currentElement.setAttribute("contentProperties", String.join(",", contentPropertiesIds));
            }
            elementList.add(currentElement);
            isAttachment = false;
        }

        if ("id".equals(qName)) {
            isId = false;
        }

        if ("property".equals(qName)) {
            isProperty = false;
            isContainerContent = false;
            isSpace = false;
            currentProperty = "";
        }

        if ("collection".equals(qName) && "contentProperties".equals(localName)) {
            isContentProperty = false; // 离开 contentProperties 集合
        }

        if ("element".equals(qName) && "ContentProperty".equals(localName)) {
            isContentProperty = false; // 结束 contentProperty 元素的解析
        }
    }
}