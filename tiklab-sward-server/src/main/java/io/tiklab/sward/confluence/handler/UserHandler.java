package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

public class UserHandler extends BaseHandler {
    private Element current;
    private boolean isUser = false;
    private String currentProperty = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("object".equals(qName) &&
                "InternalUser".equals(attributes.getValue("class")) &&
                "com.atlassian.crowd.model.user".equals(attributes.getValue("package"))) {
            isUser = true;
            current = createElement("User");
        }

        if (isUser && "property".equals(qName)) {
            currentProperty = attributes.getValue("name");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (!isUser || current == null) return;
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) return;

        switch (currentProperty) {
            case "emailAddress":
                current.setAttribute("emailAddress", value);
                break;
            case "createdDate":
                current.setAttribute("createdDate", value);
                break;
            case "active":
                current.setAttribute("active", value);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isUser) {
            elementList.add(current);
            isUser = false;
        }
        currentProperty = "";
    }
}
