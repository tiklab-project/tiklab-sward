package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;

public class BlogPostHandler extends BaseHandler {

    private boolean isBlogPost = false;
    private boolean isProperty = false;
    private String currentProperty = "";
    private Element currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("object".equals(qName) &&
                "BlogPost".equals(attributes.getValue("class")) &&
                "com.atlassian.confluence.pages".equals(attributes.getValue("package"))) {
            isBlogPost = true;
            currentElement = createElement("BlogPost");
        }

        if (isBlogPost && "property".equals(qName)) {
            currentProperty = attributes.getValue("name");
            isProperty = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isBlogPost && isProperty) {
            String content = new String(ch, start, length).trim();
            if (!content.isEmpty() && currentProperty != null) {
                currentElement.setAttribute(currentProperty, content);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("object".equals(qName) && isBlogPost) {
            elementList.add(currentElement);
            isBlogPost = false;
        }

        if ("property".equals(qName)) {
            isProperty = false;
            currentProperty = "";
        }
    }
}
