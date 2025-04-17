package io.tiklab.sward.confluence.handler;

import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DispatcherHandler extends DefaultHandler {
    private final List<BaseHandler> handlers = new ArrayList<>();

    public DispatcherHandler() {
        // 注册所有具体的 Handler
        handlers.add(new PageHandler());
        handlers.add(new SpaceHandler());
        handlers.add(new UserHandler());
        handlers.add(new ConfluenceUserHandler());
        handlers.add(new BodyContentHandler());
    }

    public List<Element> getAllElements() {
        return handlers.stream()
                .flatMap(h -> h.getElementList().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        for (BaseHandler handler : handlers) {
            handler.startElement(uri, localName, qName, attributes);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for (BaseHandler handler : handlers) {
            handler.characters(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        for (BaseHandler handler : handlers) {
            handler.endElement(uri, localName, qName);
        }
    }
}
