package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

public class BodyContentHandler extends BaseHandler {
    private Element current;
    private boolean isBodyContent = false;
    private boolean isId = false;
    private String currentProperty = "";
    private boolean isContentProperty = false; // 用于标记是否在 <property name="content"> 中

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("object".equals(qName) &&
                "BodyContent".equals(attributes.getValue("class")) &&
                "com.atlassian.confluence.core".equals(attributes.getValue("package"))) {
            isBodyContent = true;
            current = createElement("BodyContent");
        }

        if (isBodyContent) {
            if ("id".equals(qName)) {
                isId = true;
            }
            if ("property".equals(qName)) {
                currentProperty = attributes.getValue("name");
                if ("content".equals(currentProperty)) {
                    isContentProperty = true;
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isBodyContent || current == null) return;
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        // 设置 BodyContent 的自身 id
        if (isId && currentProperty.isEmpty()) {
            current.setAttribute("id", value);
        }

        // 设置 content 中嵌套的 Page id
        if (isId && isContentProperty) {
            current.setAttribute("pageId", value);
        }

        switch (currentProperty) {
            case "body":
                current.setAttribute("content", value);
                break;
            case "bodyType":
                current.setAttribute("bodyType", value);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isBodyContent) {
            elementList.add(current);
            isBodyContent = false;
        }

        if ("id".equals(qName)) {
            isId = false;
        }

        if ("property".equals(qName)) {
            currentProperty = "";
            isContentProperty = false;
        }
    }
}