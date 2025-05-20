package io.tiklab.sward.confluence.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.sward.confluence.model.ConfluenceData;
import io.tiklab.sward.richText.model.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfluenceDataBuilder {

    /**
     * 解析html
     * @param html html内容
     * @return 解析后数据
     */
    public static ConfluenceData buildFromHtml(String html) {
        Element body = Jsoup.parse(html).body();
        return buildFromElement(body);
    }

    /**
     * 解析方法
     * @param element
     * @return html对象
     */
    private static ConfluenceData buildFromElement(Element element) {
        ConfluenceData node = new ConfluenceData();
        String tagName = element.tagName();
        node.setLabel(tagName);
        node.setContent(element.ownText().trim());

        // 标题
        boolean matches = tagName.matches("h[1-6]");
        boolean acTags = checkIfContainsACTags(element);

        if (tagName.equals("p") && acTags){
            List<Object> traverse = traverse(element, element.tagName(),new ArrayList<>());
            node.setContentList(traverse.isEmpty() ? new ArrayList<>() : traverse);
        }else if (matches && !acTags){
            node.setLabel("p");
            List<ConfluenceData> children = new ArrayList<>();
            for (Element child : element.children()) {
                children.add(buildFromElement(child));
            }
            node.setChild(children.isEmpty() ? new ArrayList<>() : children);
        } else {
            List<ConfluenceData> children = new ArrayList<>();
            for (Element child : element.children()) {
                children.add(buildFromElement(child));
            }
            node.setChild(children.isEmpty() ? new ArrayList<>() : children);
        }

        if ("ri:attachment".equals(element.tagName())) {
            node.setContent(element.attr("ri:filename"));
        }
        return node;
    }

    /**
     * 多个对象解析
     * @param confluenceData
     * @return 解析字符串
     */
    public static String buildFromHtmlList(ConfluenceData confluenceData, Map<String,String> map){
        List<ConfluenceData> dataList = confluenceData.getChild();
        if (Objects.isNull(dataList) || dataList.isEmpty()){
            return null;
        }
        List<String> list = new ArrayList<>();
        for (ConfluenceData data : dataList) {
            Object build = buildFromHtml(data,map);
            if (Objects.isNull(build)){
                continue;
            }
            String jsonString = JSON.toJSONString(build, SerializerFeature.DisableCircularReferenceDetect);
            list.add(jsonString);
        }
        String result = String.join(",", list);
        return "["+result+"]";
    }

    /**
     * html对象装换成sward对象
     * @param data htm对象
     * @return 字符串
     */
    private static Object buildFromHtml(ConfluenceData data, Map<String,String> map){
        String label = data.getLabel();

        if (label.matches("h[1-6]")) {
           return findSwardHead(data,map);
        }

        switch (label) {
            case "p" -> {
                return findSwardP(data,map);
            }
            case "hr" -> {
                return new SwardDivider();
            }
            case "ol" -> {
                return findSwardNumberedList(data,map);
            }
            case "ul" -> {
                return findSwardBulletedList(data,map);
            }
            case "table" -> {
                return findSwardTable(data,map);
            }
            default -> {
                return null;
            }
        }
    }

    public static SwardHead findSwardHead(ConfluenceData data, Map<String,String> map) {
        String label = data.getLabel();
        return new SwardHead(label,new SwardText(data.getContent()));
    }

    /**
     * 有序列表
     * @param data 数据
     * @return 列表
     */
    private static SwardNumberedList findSwardNumberedList(ConfluenceData data, Map<String,String> map) {
        List<ConfluenceData> dataChild = data.getChild();
        List<Object> children = new ArrayList<>();

        for (ConfluenceData child : dataChild) {
            String content = child.getContent();
            if (!child.getLabel().equals("li")){
                continue;
            }
            if (!Objects.isNull(child.getChild()) && !child.getChild().isEmpty()){
                List<Object> numberedChildren = new ArrayList<>();
                for (ConfluenceData confluenceData : child.getChild()) {
                    Object object = buildFromHtml(confluenceData, map);
                    if (!Objects.isNull(object)){
                        numberedChildren.add(object);
                    }
                }
                SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
                children.add(swardNumberedList);
            }else {
                List<Object> numberedChildren = new ArrayList<>();
                SwardNumberedChildren swardNumberedChildren = new SwardNumberedChildren(content);
                numberedChildren.add(swardNumberedChildren);

                SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
                children.add(swardNumberedList);
            }
        }
        return new SwardNumberedList(children);
    }

    /**
     * 无序列表
     * @param data 数据
     * @return 列表
     */
    private static SwardBulletedList findSwardBulletedList(ConfluenceData data,Map<String,String> map) {

        List<Object> children = new ArrayList<>();

        List<ConfluenceData> dataChild = data.getChild();

        for (ConfluenceData child : dataChild) {
            if (!child.getLabel().equals("li")){
                continue;
            }
            if (!Objects.isNull(child.getChild()) && !child.getChild().isEmpty()){
                List<Object> numberedChildren = new ArrayList<>();
                for (ConfluenceData confluenceData : child.getChild()) {
                    Object object = buildFromHtml(confluenceData, map);
                    if (!Objects.isNull(object)){
                        numberedChildren.add(object);
                    }
                }
                SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
                children.add(swardNumberedList);
            }else {
                String content = child.getContent();
                List<Object> numberedChildren = new ArrayList<>();
                SwardNumberedChildren swardNumberedChildren = new SwardNumberedChildren(content);
                numberedChildren.add(swardNumberedChildren);
                SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
                children.add(swardNumberedList);
            }
        }
        return new SwardBulletedList(children);
    }

    /**
     * 表格
     * @param data 数据
     * @return 列表
     */
    private static SwardTable findSwardTable(ConfluenceData data,Map<String,String> map) {
        List<ConfluenceData> dataChild = data.getChild();
        List<Object> tableList = new ArrayList<>();
        for (ConfluenceData confluenceData : dataChild) {
            String label = confluenceData.getLabel();
            List<ConfluenceData> cowList = confluenceData.getChild();
            if (label.equals("colgroup")){
                List<Object> list = new ArrayList<>();
                for (ConfluenceData cow : cowList) {
                    String cowLabel = cow.getLabel();
                    if (cowLabel.equals("col")){
                        String key = "row_"+UuidGenerator.getRandomIdByUUID(24);
                        List<Object> textList = new ArrayList<>();
                        textList.add(new SwardParagraph(new SwardText("")));

                        SwardTableGroup swardTableGroup = new SwardTableGroup(textList,"table-col",key,"10.00%");
                        list.add(swardTableGroup);
                    }else {
                        Object object = buildFromHtml(cow, map);
                        if (!Objects.isNull(object)){
                            list.add(object);
                        }
                    }
                }
                String uuid = "row_"+UuidGenerator.getRandomIdByUUID(24);
                SwardTableRow swardTableRow = new SwardTableRow(list,"table-colgroup",uuid);
                tableList.add(swardTableRow);
            }
            if (label.equals("tbody")){
                for (ConfluenceData cow : cowList) {
                    String cowLabel = cow.getLabel();
                    List<Object> list = new ArrayList<>();
                    String uuid = "row_"+UuidGenerator.getRandomIdByUUID(24);
                    if (cowLabel.equals("th") || cowLabel.equals("tr")){
                        List<ConfluenceData> dataList = cow.getChild();
                        for (ConfluenceData cell : dataList) {
                            if (Objects.isNull(cell.getChild()) || cell.getChild().isEmpty()){
                                SwardTableContent swardTableContent = new SwardTableContent();
                                List<Object> textList = new ArrayList<>();
                                textList.add(new SwardParagraph(new SwardText(cell.getContent())));
                                swardTableContent.setChildren(textList);

                                List<Object> childList = new ArrayList<>();
                                childList.add(swardTableContent);

                                String key = "cell_"+UuidGenerator.getRandomIdByUUID(24);
                                SwardTableCell swardTableGroup = new SwardTableCell(childList,"table-cell",key);
                                list.add(swardTableGroup);
                            }else {
                                List<Object> childList = new ArrayList<>();
                                List<Object> textList = new ArrayList<>();
                                for (ConfluenceData confluenceData1 : cell.getChild()) {
                                    Object object = buildFromHtml(confluenceData1, map);
                                    if (!Objects.isNull(object)){
                                        textList.add(object);
                                    }
                                }
                                SwardTableContent swardTableContent = new SwardTableContent();
                                // textList.add(new SwardParagraph(new SwardText(cell.getContent())));
                                swardTableContent.setChildren(textList);

                                childList.add(swardTableContent);

                                String key = "cell_"+UuidGenerator.getRandomIdByUUID(24);
                                SwardTableCell swardTableGroup = new SwardTableCell(childList,"table-cell",key);
                                list.add(swardTableGroup);
                            }
                        }
                    }
                    SwardTableRow swardTableRow = new SwardTableRow(list,"table-row",uuid);
                    tableList.add(swardTableRow);
                }
            }
        }
        return new SwardTable(tableList);
    }

    /**
     * p标签处理
     * @param data data
     * @return 处理结果
     */
    private static SwardParagraph findSwardP(ConfluenceData data, Map<String,String> map) {
        List<ConfluenceData> dataChild = data.getChild();
        List<Object> tableList = new ArrayList<>();
        if (Objects.isNull(dataChild) || dataChild.isEmpty()) {

            List<Object> contentList = data.getContentList();
            SwardParagraph swardParagraph = new SwardParagraph();
            if (contentList.isEmpty()){
                swardParagraph = new SwardParagraph(new SwardText(data.getContent()));
            }else {
                List<Object> swardTextList = new ArrayList<>(contentList);
                swardParagraph.setChildren(swardTextList);
            }
            tableList.add(swardParagraph);
            return new SwardParagraph(tableList);
        }

        if (!StringUtils.isEmpty(data.getContent())){
            SwardParagraph swardParagraph = new SwardParagraph(new SwardText(data.getContent()));
            tableList.add(swardParagraph);
        }

        for (ConfluenceData confluenceData : dataChild) {
            String label = confluenceData.getLabel();
            switch (label) {
                case "br" -> {
                    SwardParagraph swardParagraph = new SwardParagraph(new SwardText(" "));
                    tableList.add(swardParagraph);
                }
                case "ac:image" -> {
                    List<ConfluenceData> dataList = confluenceData.getChild();
                    for (ConfluenceData image : dataList) {
                        String imageLabel = image.getLabel();
                        if (imageLabel.equals("ri:attachment")) {
                            List<Object> textList = new ArrayList<>();
                            textList.add(new SwardText(" "));
                            String content = image.getContent();
                            String s = map.get(content);
                            if (!StringUtils.isEmpty(s)) {
                                content = "/image/" + s;
                            }
                            SwardImage swardImage = new SwardImage(content, textList);
                            tableList.add(swardImage);
                        }
                    }
                }
                case "span" -> {
                    List<ConfluenceData> dataList = confluenceData.getChild();
                    if (Objects.isNull(dataList) || dataList.isEmpty()){
                        continue;
                    }
                    for (ConfluenceData image : dataList) {
                        String imageLabel = image.getLabel();
                        if (imageLabel.equals("ac:image")) {
                            SwardParagraph swardP = findSwardP(confluenceData, map);
                            tableList.add(swardP);
                        }
                        if(imageLabel.equals("strong")){
                            SwardText swardText = new SwardText(image.getContent());
                            tableList.add(swardText);
                        }
                    }
                }
                case "ac:structured-macro" -> {
                    List<ConfluenceData> dataList = confluenceData.getChild();
                    for (ConfluenceData image : dataList) {
                        String imageLabel = image.getLabel();
                        if (imageLabel.equals("ac:parameter")) {
                            List<ConfluenceData> dataList1 = image.getChild();
                            if (Objects.isNull(dataList1) || dataList1.isEmpty()) {
                                continue;
                            }
                            for (ConfluenceData image1 : dataList1) {
                                String imageLabel1 = image1.getLabel();
                                if (imageLabel1.equals("ri:attachment")) {
                                    List<Object> textList = new ArrayList<>();
                                    textList.add(new SwardText(" "));
                                    String content = image1.getContent();
                                    String s = map.get(content);
                                    if (!StringUtils.isEmpty(s)) {
                                        content = s;
                                    }
                                    SwardAttachment swardImage = new SwardAttachment(textList, image1.getContent(), content);
                                    tableList.add(swardImage);
                                }
                            }
                        }
                    }
                }
                case "ac:link" -> {
                    List<ConfluenceData> dataList = confluenceData.getChild();
                    for (ConfluenceData image : dataList) {
                        String imageLabel = image.getLabel();
                        if (imageLabel.equals("ri:attachment")) {
                            List<Object> textList = new ArrayList<>();
                            textList.add(new SwardText(" "));
                            String content = image.getContent();
                            String s = map.get(content);
                            if (!StringUtils.isEmpty(s)) {
                                content = s;
                            }
                            SwardAttachment swardImage = new SwardAttachment(textList, image.getContent(), content);
                            tableList.add(swardImage);
                        }
                    }
                }
                default -> {
                    Object object = buildFromHtml(confluenceData, map);
                    if (!Objects.isNull(object)) {
                        tableList.add(object);
                    }
                }
            }

        }
        if (tableList.isEmpty()){
            SwardParagraph swardParagraph = new SwardParagraph(new SwardText(" "));
            tableList.add(swardParagraph);
        }
        return new SwardParagraph(tableList);
    }



    private static boolean checkIfContainsACTags(Element pElement) {
        // 遍历所有子节点
        for (Node childNode : pElement.childNodes()) {
            // 如果是元素节点
            if (childNode instanceof Element) {
                Element childElement = (Element) childNode;
                // 如果当前标签的标签名以 "ac" 开头，直接返回 false
                if (childElement.tagName().startsWith("ac")) {
                    return false;
                }
                // 递归检查子元素的子节点
                if (!checkIfContainsACTags(childElement)) {
                    return false; // 如果子元素检查后发现包含ac标签，返回 false
                }
            }
        }
        return true; // 如果没有找到以"ac"开头的标签，返回 true
    }


    private static List<Object> traverse(Node node, String path, List<Object> list) {
        if (node instanceof TextNode) {
            String text = ((TextNode) node).text();
            if (!text.isEmpty()) {
                if (hasAncestorTag(node, "a")) {
                    Element a = (Element) getAncestorTag(node, "a");
                    if (!Objects.isNull(a)){
                        String href = a.attr("href");
                        SwardLink swardLink = new SwardLink(href, text);
                        list.add(swardLink);
                    }
                } else {
                    SwardText swardText = new SwardText();
                    if (hasAncestorTag(node, "strong")) swardText.setBold(true);
                    if (hasAncestorTag(node, "em")) swardText.setItalic(true);
                    if (hasAncestorTag(node, "u")) swardText.setUnderline(true);
                    if (hasAncestorTag(node, "s")) swardText.setStrike(true);
                    if (hasAncestorTag(node, "span")) {
                        Element span = (Element) getAncestorTag(node, "span");
                        if (!Objects.isNull(span)){
                            String style = span.attr("style");
                            if (style.contains("color")) {
                                Pattern pattern = Pattern.compile("color:\\s*rgb\\((\\d+),\\s*(\\d+),\\s*(\\d+)\\)");
                                Matcher matcher = pattern.matcher(style);
                                if (matcher.find()) {
                                    int r = Integer.parseInt(matcher.group(1));
                                    int g = Integer.parseInt(matcher.group(2));
                                    int b = Integer.parseInt(matcher.group(3));
                                    String hexColor = String.format("#%02X%02X%02X", r, g, b);
                                    swardText.setColor(hexColor);
                                }
                            }
                        }
                    }
                    swardText.setText(text);
                    list.add(swardText);
                }
            }
        } else if (node instanceof Element element) {

            // 水平线
            if ("hr".equalsIgnoreCase(element.tagName())) {
                list.add(new SwardDivider());
            }

            // 时间标签
            if ("time".equalsIgnoreCase(element.tagName())) {
                String datetime = element.attr("datetime");
                if (datetime != null && !datetime.isEmpty()) {
                    list.add(new SwardTime(datetime));
                }
            }
            // 表情解析 <ac:emoticon ac:name="sad" />
            // if ("ac:emoticon".equalsIgnoreCase(element.tagName())) {
            //     String name = element.attr("ac:name");
            //     if (name != null && !name.isEmpty()) {
            //         // list.add(new SwardEmoji(name));
            //     }
            // }
            for (Node child : element.childNodes()) {
                traverse(child, path + " > " + element.tagName(), list);
            }
        }
        return list;
    }

    private static boolean hasAncestorTag(Node node, String tagName) {
        return getAncestorTag(node, tagName) != null;
    }

    private static Node getAncestorTag(Node node, String tagName) {
        Node parent = node.parent();
        while (parent != null) {
            if (parent instanceof Element && ((Element) parent).tagName().equalsIgnoreCase(tagName)) {
                return parent;
            }
            parent = parent.parent();
        }
        return null;
    }

    public static void main(String[] args) {

        String html = "<h1>一、 这是一级标题</h1><h2>这是二级标题</h2><h3>这是三级标题</h3><h4>这是四级标题</h4><h5>这是五级标题</h5><p>有序列表</p><ol><li>1级</li><li>2级</li><li>3级</li><li>4级</li><li>5级</li></ol><p>无序列表</p><ul><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li></ul><p>表格：</p><table class=\"wrapped\"><colgroup><col /><col /><col /><col /></colgroup><tbody><tr><th>11</th><th>12</th><th>13</th><th>14</th></tr><tr><td>12</td><td>13</td><td>14</td><td>15</td></tr><tr><td>13</td><td>14</td><td>15</td><td>16</td></tr><tr><td>14</td><td>15</td><td>16</td><td>17</td></tr></tbody></table><p><br /></p><h2>其他样式</h2><p>倾斜：<em>测试文字</em></p><p>加粗：<strong>测试文字</strong></p><p>下划线：<u>测试文字</u></p><p>中划线：<s><span style=\"color: rgb(0,51,102);\">测试文字</span></s></p><p><span style=\"color: rgb(0,51,102);\">颜色：</span><span style=\"color: rgb(255,0,0);\">红色&nbsp; &nbsp;<span style=\"color: rgb(0,128,0);\">绿色</span></span></p><p>水平线：</p><hr /><p>日期:</p><p><time datetime=\"2025-05-13\" />&nbsp;</p><p>表情：</p><p><ac:emoticon ac:name=\"sad\" /><ac:emoticon ac:name=\"thumbs-up\" /></p><p><br /></p><p><br /></p><p>文本：</p><p class=\"p1\"><span class=\"s1\">\uD83D\uDE80 </span><strong>Tiklab &mdash; 开源的一站式研发管理平台</strong></p><p class=\"p3\">Tiklab 是一款专为研发团队打造的<span class=\"s2\"><strong>开源研发管理平台</strong></span>，全面覆盖 <span class=\"s2\"><strong>项目规划、敏捷研发、代码管理、持续集成、测试管理、部署发布、运行监控</strong></span> 等关键流程，真正实现研发全生命周期的集中化管理与自动化协作。</p><p class=\"p3\">平台优势一览：</p><p class=\"p3\">✅ <span class=\"s2\"><strong>功能全面</strong></span>：支持敏捷看板、迭代计划、任务协同、测试计划、缺陷跟踪、CI/CD流水线、部署管理、运行监控、权限控制等核心模块，满足企业从开发到运维的全链路管理需求。</p><p class=\"p3\">✅ <span class=\"s2\"><strong>结构灵活</strong></span>：模块化架构，按需启用，支持私有化部署，适配不同规模和行业场景。</p><p class=\"p3\">✅ <span class=\"s2\"><strong>简洁易用</strong></span>：界面设计清爽直观，操作逻辑清晰上手快，提升团队效率和体验。</p><p class=\"p3\">✅ <span class=\"s2\"><strong>开源免费</strong></span>：完全开源，遵循开源协议，无授权费用，支持社区自主部署与二次开发。</p><p class=\"p1\"><span class=\"s1\">\uD83C\uDF10 </span><strong>为什么选择 Tiklab？</strong></p><ul><li><p class=\"p1\">开源透明，掌控自主权</p></li><li><p class=\"p1\">降本增效，摆脱高额商业系统束缚</p></li><li><p class=\"p1\">可持续演进，适合长期维护和扩展</p></li><li><p class=\"p1\">社区支持活跃，文档完善，持续更新</p></li></ul><p class=\"p3\">\uD83D\uDCA1 无论你是初创团队，还是大型企业，Tiklab 都是你打造高效、敏捷、自主研发体系的不二之选！</p><p class=\"p2\"><br /></p><p class=\"p3\">立即体验：<a href=\"https://tiklab.net\">https://tiklab.net</a></p><p class=\"p3\">共建开源生态，让研发更简单</p>";
        Element body = Jsoup.parse(html).body();

        List<Object> traverse = traverse(body, body.tagName(), new ArrayList<>());

        for (Object s : traverse) {
            System.out.println(s.toString());
        }


    }

}














