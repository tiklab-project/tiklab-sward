package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

public class SpaceHandler extends BaseHandler {
    private Element current;
    private boolean isSpace = false;
    private String currentProperty = "";
    private boolean isSpaceId = false;
    private int depth = 0;
    private int spaceObjectDepth = -1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        depth++;

        if ("object".equals(qName) && "Space".equals(attributes.getValue("class"))) {
            isSpace = true;
            current = createElement("Space");
            spaceObjectDepth = depth; // 记录 <object> 的深度
        }

        if (isSpace) {
            if ("id".equals(qName)
                    && "id".equals(attributes.getValue("name"))
                    && depth == spaceObjectDepth + 1) {
                isSpaceId = true; // ✅ 只处理 object 的直接子元素 <id>
            }

            if ("property".equals(qName)) {
                currentProperty = attributes.getValue("name");
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isSpace || current == null) return;

        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        if (isSpaceId) {
            current.setAttribute("id", value);
        }

        switch (currentProperty) {
            case "name": current.setAttribute("name", value); break;
            case "creator": current.setAttribute("creator", value); break;
            case "creationDate": current.setAttribute("creationDate", value); break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isSpace) {
            elementList.add(current);
            isSpace = false;
            spaceObjectDepth = -1;
        }

        if ("id".equals(qName)) {
            isSpaceId = false;
        }

        if ("property".equals(qName)) {
            currentProperty = "";
        }

        depth--;
    }
}