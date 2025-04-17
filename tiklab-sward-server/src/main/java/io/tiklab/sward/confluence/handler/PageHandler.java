package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class PageHandler extends BaseHandler {
    private Element current;
    private boolean isDocument = false;
    private String currentProperty = "";
    private boolean isCollection = false;
    private String currentCollection = "";
    private boolean isSpaceId = false;
    private boolean isPageId = false;
    private boolean isAttachmentId = false;

    private int depth = 0;
    private int pageObjectDepth = -1;

    private List<String> attachmentIdList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        depth++;

        if ("object".equals(qName) && "Page".equals(attributes.getValue("class"))) {
            isDocument = true;
            current = createElement("Page");
            pageObjectDepth = depth; // 记录 Page 元素的层级
            attachmentIdList.clear(); // 初始化附件 ID 列表
        }

        if (isDocument) {
            if ("property".equals(qName)) {
                currentProperty = attributes.getValue("name");
                if ("space".equals(currentProperty)) {
                    isSpaceId = true;
                }
            }

            if ("collection".equals(qName)) {
                isCollection = true;
                currentCollection = attributes.getValue("name");
            }

            if ("id".equals(qName)) {
                if (depth == pageObjectDepth + 1) {
                    isPageId = true;
                } else if (isCollection && "attachments".equals(currentCollection)) {
                    isAttachmentId = true;
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isDocument || current == null) return;
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        if (isPageId) {
            current.setAttribute("id", value); // 设置页面 ID
            return;
        }

        if (isAttachmentId) {
            attachmentIdList.add(value); // 添加附件 ID
            return;
        }

        switch (currentProperty) {
            case "title":
                current.setAttribute("title", value);
                break;
            case "creator":
                current.setAttribute("creator", value);
                break;
            case "creationDate":
                current.setAttribute("creationDate", value);
                break;
            case "space":
                if (isSpaceId) {
                    current.setAttribute("spaceId", value);
                }
                break;
            // ✅ 新增对 contentStatus 的解析
            case "contentStatus":
                current.setAttribute("contentStatus", value);
                break;
            // ✅ 新增对 version 的解析
            case "version":
                current.setAttribute("version", value);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isDocument) {
            // 将附件 ID 列表加入属性
            current.setAttribute("attachmentIds", String.join(",", attachmentIdList));
            elementList.add(current);

            isDocument = false;
            pageObjectDepth = -1;
            attachmentIdList.clear(); // 清除已处理数据
        }

        if ("property".equals(qName)) {
            if ("space".equals(currentProperty)) {
                isSpaceId = false;
            }
            currentProperty = ""; // 重置 currentProperty
        }

        if ("collection".equals(qName)) {
            isCollection = false;
            currentCollection = "";
        }

        if ("id".equals(qName)) {
            isPageId = false;
            isAttachmentId = false;
        }

        depth--;
    }
}