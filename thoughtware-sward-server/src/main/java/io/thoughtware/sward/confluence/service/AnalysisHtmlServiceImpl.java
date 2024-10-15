package io.thoughtware.sward.confluence.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.thoughtware.core.utils.UuidGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnalysisHtmlServiceImpl implements AnalysisHtmlService{

    private static boolean isBlockElement(String tag) {
        return Arrays.asList("p", "div", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol").contains(tag);
    }
    public String htmlToNode(String html) {
        Document doc = Jsoup.parse(html, "", Parser.htmlParser());
        List<Map<String, Object>> blocks = new ArrayList<>();

        // 遍历块级元素
        doc.body().children().forEach(element -> {
            Map<String, Object> block = new HashMap<>();
            String tag = element.tagName();
            String id = UuidGenerator.getRandomIdByUUID(8);
            if (element.tagName().equals("table")) {
                Map<String, Object> tableBlock = setTable(element);
                blocks.add(tableBlock);
            }
            String text = element.text();

            if (isBlockElement(tag) && !text.isEmpty()) {
                switch(tag){
                    case "p":
                        block.put("type", "paragraph");
                        break;
                    case "h1":
                        block.put("type", "head");
                        block.put("head", "h1");
                        block.put("id", id);
                        break;
                    case "h2":
                        block.put("type", "head");
                        block.put("head", "h2");
                        block.put("id", id);
                        break;
                    case "h3":
                        block.put("type", "head");
                        block.put("head", "h3");
                        block.put("id", id);
                        break;
                    case "h4":
                        block.put("type", "head");
                        block.put("head", "h4");
                        block.put("id", id);
                        break;
                    case "h5":
                        block.put("type", "head");
                        block.put("head", "h5");
                        block.put("id", id);
                        break;
                    case "h6":
                        block.put("type", "head");
                        block.put("head", "h6");
                        block.put("id", id);
                        break;
                    case "ul":
                        block.put("type", "bulleted-list");
                        break;
                    case "ol":
                        block.put("type", "numbered-list");
                        break;
                    default:
                        break;
                }
                List<Map<String, Object>> children = new ArrayList<>();
                block.put("children", children);
                System.out.println("tagNameBig: " + element.tagName());
                // 遍历块级元素内的子节点
                Map<String, Object> textNode = new HashMap<>();
                for (Element child : element.children()) {
                    processElement(child, textNode);
                }
//                element.traverse(new NodeVisitor() {
//                    @Override
//                    public void head(Node node, int depth) {
//
//                        if (node instanceof Element) {
//                            Element el = (Element) node;
//                            Map<String, Object> textNode = new HashMap<>();
//                            String text = el.text();
//                            System.out.println("tagName: " + el.text());
//                            switch (el.tagName()) {
//                                case "a":
//                                    textNode.put("type", "link");
//                                    String href = el.attr("href");
//                                    textNode.put("url", href);
//                                    children.add(textNode);
//                                    break;
//                                case "strong":
//                                    if(!text.isEmpty()){
//                                        textNode.put("bold", true);
//                                        children.add(textNode);
//                                    }
//
//                                    break;
//                                case "em":
//                                    if(!text.isEmpty()){
//                                        textNode.put("italic", true);
//                                        children.add(textNode);
//                                    }
//                                    break;
//                                case "u":
//                                    if(!text.isEmpty()){
//                                        textNode.put("underline", true);
//                                        children.add(textNode);
//                                    }
//                                    break;
//                                case "s":
//                                    if(!text.isEmpty()){
//                                        textNode.put("strike", true);
//                                        children.add(textNode);
//                                    }
//                                    break;
//                                case "li":
//                                    textNode.put("type", "list-item");
//                                    children.add(textNode);
//                                    break;
//                                default:
//                                    break;
//                            }
//
//                        } else if (node instanceof TextNode) {
//                            TextNode textNode = (TextNode) node;
//                            List<Map<String, Object>> children1 = new ArrayList<>();
//                            Map<String, Object> textMap = new HashMap<>();
//                            textMap.put("text", textNode.text().trim());  // Trim whitespace
//                            children1.add(textMap);
//                            if (!textNode.text().trim().isEmpty()) {  // 忽略空白文本节点
//                                if(children.size() > 0){
//                                    int size = children.size();
//                                    Map<String, Object> child = children.get(size-1);
//                                    Object type = child.get("type");
//                                    if(type != null){
//                                        child.put("children", children1);
//                                    }else {
//                                        child.put("text", textNode.text().trim());
//                                    }
//                                }else {
//                                    children.add(textMap);
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void tail(Node node, int depth) {
//                        // Do nothing
//                    }
//                });

                blocks.add(block);
            }
        });

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonOutput = null;
        try {
            jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blocks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("+++++++++++:" + jsonOutput);
        return jsonOutput;
    }
    private Map<String, Object> setLineElement(Element el){
        Map<String, Object> textNode = new HashMap<>();
        String text = el.text();
        System.out.println("tagName: " + el.text());
        switch (el.tagName()) {
            case "a":
                textNode.put("type", "link");
                String href = el.attr("href");
                textNode.put("url", href);
                break;
            case "strong":
                textNode.put("bold", true);
                break;
            case "em":
                textNode.put("italic", true);
                break;
            case "u":
                textNode.put("underline", true);
                break;
            case "s":
                textNode.put("strike", true);
                break;
            case "li":
                textNode.put("type", "list-item");
                break;
            case "span":
                textNode.put("type", "span");
                break;
            default:
                break;
        }
        return textNode;
    }

    private static void processElement(Element el, Map<String, Object> textNode) {
//        Map<String, Object> textNode = new HashMap<>();
        switch (el.tagName()) {
            case "a":
                textNode.put("type", "link");
                String href = el.attr("href");
                textNode.put("url", href);
                break;
            case "strong":
                textNode.put("bold", true);
                break;
            case "em":
                textNode.put("italic", true);
                break;
            case "u":
                textNode.put("underline", true);
                break;
            case "s":
                textNode.put("strike", true);
                break;
            case "li":
                textNode.put("type", "list-item");
                break;
            case "span":
                textNode.put("type", "span");
                break;
            default:
                break;
        }

        // 获取文本内容
        String text = el.ownText();
        if (!text.isEmpty()) {
            textNode.put("text", text);
        }
        // 处理子元素
        for (Element child : el.children()) {
            processElement(child, textNode);
        }
    }
    private Map<String, Object> setTable(Element element){

        Map<String, Object> block = new HashMap<>();
        ArrayList<Map<String, Object>> tableChildren = new ArrayList<>();
        block.put("type", "table");
        block.put("data", new JSONObject());
        AtomicInteger trIndex = new AtomicInteger(0);
        element.select("tr").forEach(row -> {
            // 打印行的内容
            int currentIndex = trIndex.incrementAndGet(); // 递增并获取当前值
            System.out.println("Row index: " + currentIndex);
            Map<String, Object>  rowMap = new HashMap<>();
            rowMap.put("type", "table-row");
            rowMap.put("data", new JSONObject());
            List<Map<String, Object>> children = new ArrayList<>();
            List<Map<String, Object>> colGroupList = new ArrayList<>();
            Map<String, Object> colGroup = new HashMap<>();
            List<Map<String, Object>> colGroupContentList = new ArrayList<>();
            int size = row.children().size();
            double width = 100 / size;
            String widthStr = String.valueOf(width);
            System.out.println("widthStr:   "+  widthStr);
            row.children().forEach(cell -> {
                if(currentIndex == 1){
                    Map<String, Object> colGroupContent = new HashMap<>();
                    colGroupContent.put("type", "table-col");
                    colGroupContent.put("width", widthStr + "%");
                    List<Map<String, Object>> colGroupContentChildrenList = new ArrayList<>();
                    Map<String, Object> colGroupContentChildren = new HashMap<>();
                    colGroupContentChildren.put("type", "paragraph");

                    List<Map<String, Object>> paragraphChildrenList = new ArrayList<>();
                    Map<String, Object> paragraphChildren = new HashMap<>();
                    paragraphChildren.put("text", "");
                    paragraphChildrenList.add(paragraphChildren);
                    colGroupContentChildren.put("children", paragraphChildrenList);
                    colGroupContentChildrenList.add(colGroupContentChildren);
                    colGroupContent.put("children", colGroupContentChildrenList);
                    colGroupContentList.add(colGroupContent);
                }
                Map<String, Object>  cellMap = new HashMap<>();
                cellMap.put("type", "table-cell");
                cellMap.put("width", "100px");
                cellMap.put("height", "44px");
                List<Map<String, Object>> contents = new ArrayList<>();
                Map<String, Object> content = new HashMap<>();
                content.put("type", "table-content");
                List<Map<String, Object>> cellChildren = new ArrayList<>();

                Map<String, Object> paragraph = new HashMap<>();
                paragraph.put("type", "paragraph");
                List<Map<String, Object>> textChildren = new ArrayList<>();
                Map<String, Object> cellContent =  new HashMap<>();
                cellContent.put("text", cell.text());
                textChildren.add(cellContent);

                paragraph.put("children", textChildren);
                cellChildren.add(paragraph);
                content.put("children", cellChildren);
                contents.add(content);
                cellMap.put("children", contents);
                children.add(cellMap);
                // 打印单元格内容
                System.out.print(cell.text() + "\t");
            });
            rowMap.put("children", children);
            if(currentIndex == 1){
                colGroup.put("type", "table-colgroup");
                colGroup.put("data", new JSONObject());
                colGroup.put("children", colGroupContentList);
                tableChildren.add(colGroup);
            }

            tableChildren.add(rowMap);
            System.out.println(); // 换行
        });
        block.put("children", tableChildren);
        return block;
    }

    private List<Map<String, Object>> processElement(Node node, List<Map<String, Object>> children) {
        Map<String, Object> textNode = new HashMap<>();

        if(node instanceof Element){
            Element el = (Element) node;
            switch (el.tagName()) {
                case "a":
                    textNode.put("type", "link");
                    String href = el.attr("href");
                    textNode.put("url", href);
                    children.add(textNode);
                    break;
                case "strong":
                    textNode.put("text", el.text());
                    textNode.put("bold", true);
                    children.add(textNode);
                    break;
                case "em":
                    textNode.put("text", el.text());
                    textNode.put("italic", true);
                    children.add(textNode);
                    break;
                case "u":
                    textNode.put("text", el.text());
                    textNode.put("underline", true);
                    children.add(textNode);
                    break;
                case "s":
                    textNode.put("text", el.text());
                    textNode.put("strike", true);
                    children.add(textNode);
                    break;
                case "li":
                    textNode.put("type", "list-item");
                    children.add(textNode);
                    break;
                default:
                    break;
            }
            for (Node childNode : el.childNodes()) {
                processElement(childNode, children);
            }
        } else if (node instanceof TextNode) {
            TextNode textNode1 = (TextNode) node;
            Map<String, Object> textMap = new HashMap<>();
            List<Map<String, Object>> children1 = new ArrayList<>();
            textMap.put("text", textNode1.text().trim());  // Trim whitespace
            if (!textNode1.text().trim().isEmpty()) {  // 忽略空白文本节点
                if(children.size() > 0){
                    children1.add(textMap);
                    Map<String, Object> child = children.get(0);
                    child.put("children5555", children1);
                }else {
                    children.add(textMap);
                }
            }
        }
        return  children;
    }

    @Override
    public String importRichText(String htmlString) {


        // 解析 HTML 为 JSON 树
        String rich = htmlToNode(htmlString);

        return rich;
    }

    public static ObjectNode traverseJson(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();
        if (node.isObject()) {
            // 如果是对象节点，遍历字段
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String key = field.getKey();
                if(key.equals("flagName")){
                    JsonNode value = field.getValue();
                    if(value.equals("\"p\"")){
                        jsonNode.put("type", "paragraph");
                    }
                }
                System.out.println("Key: " + field.getKey());

                // 递归遍历子节点
                traverseJson(field.getValue());
            }
        } else if (node.isArray()) {
            // 如果是数组节点，遍历每个元素
            for (JsonNode arrayElement : node) {
                ObjectNode jsonNodes = traverseJson(arrayElement);
                jsonNode.withArray("children").add(jsonNodes);
            }
        } else {
            // 如果是值节点，直接打印内容
            System.out.println("Value: " + node.asText());
        }
        return  jsonNode;
    }

}
