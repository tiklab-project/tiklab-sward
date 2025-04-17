package io.tiklab.sward.confluence.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.sward.confluence.model.ConfluenceData;
import io.tiklab.sward.richText.model.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.*;

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
        node.setLabel(element.tagName()); // 保留 "ri:attachment"
        node.setContent(element.ownText().trim());

        // 如果是图片附件，可以提取 filename
        if ("ri:attachment".equals(element.tagName())) {
            node.setContent(element.attr("ri:filename"));
        }

        List<ConfluenceData> children = new ArrayList<>();
        for (Element child : element.children()) {
            children.add(buildFromElement(child));
        }
        node.setChild(children.isEmpty() ? null : children);
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
            String build = buildFromHtml(data,map);
            if (StringUtils.isEmpty(build)){
                continue;
            }
            list.add(build);
        }
        String result = String.join(",", list);
        return "["+result+"]";
    }

    /**
     * html对象装换成sward对象
     * @param data htm对象
     * @return 字符串
     */
    private static String buildFromHtml(ConfluenceData data, Map<String,String> map){
        String label = data.getLabel();
        switch (label) {
            case "h1" -> {
                SwardHead swardHead = new SwardHead("h1",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "h2" -> {
                SwardHead swardHead = new SwardHead("h2",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "h3" -> {
                SwardHead swardHead = new SwardHead("h3",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "h4" -> {
                SwardHead swardHead = new SwardHead("h4",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "h5" -> {
                SwardHead swardHead = new SwardHead("h5",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "h6" -> {
                SwardHead swardHead = new SwardHead("h6",new SwardText(data.getContent()));
                return JSONObject.toJSONString(swardHead);
            }
            case "p" -> {
                SwardParagraph swardHead = findSwardP(data,map);
                return JSONObject.toJSONString(swardHead);
            }
            case "ol" -> {
                SwardNumberedList swardHead = findSwardNumberedList(data);
                return JSONObject.toJSONString(swardHead);
            }
            case "ul" -> {
                SwardBulletedList swardHead = findSwardBulletedList(data);
                return JSONObject.toJSONString(swardHead);
            }
            case "table" -> {
                SwardTable swardHead = findSwardTable(data);
                return JSON.toJSONString(swardHead, SerializerFeature.DisableCircularReferenceDetect);
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * 有序列表
     * @param data 数据
     * @return 列表
     */
    private static SwardNumberedList findSwardNumberedList(ConfluenceData data) {
        List<ConfluenceData> dataChild = data.getChild();
        List<Object> children = new ArrayList<>();

        for (ConfluenceData child : dataChild) {
            String content = child.getContent();
            if (!child.getLabel().equals("li")){
                continue;
            }
            List<Object> numberedChildren = new ArrayList<>();
            SwardNumberedChildren swardNumberedChildren = new SwardNumberedChildren(content);
            numberedChildren.add(swardNumberedChildren);

            SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
            children.add(swardNumberedList);
        }
        return new SwardNumberedList(children);
    }

    /**
     * 无序列表
     * @param data 数据
     * @return 列表
     */
    private static SwardBulletedList findSwardBulletedList(ConfluenceData data) {

        List<Object> children = new ArrayList<>();

        List<ConfluenceData> dataChild = data.getChild();

        for (ConfluenceData child : dataChild) {
            if (!child.getLabel().equals("li")){
                continue;
            }
            String content = child.getContent();

            List<Object> numberedChildren = new ArrayList<>();
            SwardNumberedChildren swardNumberedChildren = new SwardNumberedChildren(content);
            numberedChildren.add(swardNumberedChildren);

            SwardNumberedList swardNumberedList = new SwardNumberedList("list-item", numberedChildren);
            children.add(swardNumberedList);
        }
        return new SwardBulletedList(children);
    }

    /**
     * 表格
     * @param data 数据
     * @return 列表
     */
    private static SwardTable findSwardTable(ConfluenceData data) {
        List<ConfluenceData> dataChild = data.getChild();
        List<Object> tableList = new ArrayList<>();
        for (ConfluenceData confluenceData : dataChild) {
            String label = confluenceData.getLabel();
            List<ConfluenceData> cowList = confluenceData.getChild();
            if (label.equals("colgroup")){
                List<Object> list = new ArrayList<>();
                String uuid = "row_"+UuidGenerator.getRandomIdByUUID(24);
                String with = 100/cowList.size() + "%";
                for (ConfluenceData cow : cowList) {
                    String cowLabel = cow.getLabel();
                    if (cowLabel.equals("col")){
                        String key = "row_"+UuidGenerator.getRandomIdByUUID(24);
                        // List<Object> childList = new ArrayList<>();
                        List<Object> textList = new ArrayList<>();
                        textList.add(new SwardParagraph(new SwardText("")));

                        SwardTableGroup swardTableGroup = new SwardTableGroup(textList,"table-col",key,"10.00%");
                        list.add(swardTableGroup);
                    }
                }
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
                            SwardTableContent swardTableContent = new SwardTableContent();
                            List<Object> textList = new ArrayList<>();
                            textList.add(new SwardParagraph(new SwardText(cell.getContent())));
                            swardTableContent.setChildren(textList);

                            List<Object> childList = new ArrayList<>();
                            childList.add(swardTableContent);

                            String key = "cell_"+UuidGenerator.getRandomIdByUUID(24);
                            SwardTableCell swardTableGroup = new SwardTableCell(childList,"table-cell",key);
                            list.add(swardTableGroup);
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
            SwardParagraph swardParagraph = new SwardParagraph(new SwardText(data.getContent()));
            tableList.add(swardParagraph);
            return new SwardParagraph(tableList);
        }

        for (ConfluenceData confluenceData : dataChild) {
            String label = confluenceData.getLabel();
            if (label.equals("br")){
                SwardParagraph swardParagraph = new SwardParagraph(new SwardText(" "));
                tableList.add(swardParagraph);
            }
            if (label.equals("ac:image")){
                List<ConfluenceData> dataList = confluenceData.getChild();
                for (ConfluenceData image : dataList) {
                    String imageLabel = image.getLabel();
                    if (imageLabel.equals("ri:attachment")){
                        List<Object> textList = new ArrayList<>();
                        textList.add(new SwardText(" "));
                        String content = image.getContent();
                        String s = map.get(content);
                        if (!StringUtils.isEmpty(s)){
                            content = "/image/"+s;
                        }
                        SwardImage swardImage = new SwardImage(content,textList);
                        tableList.add(swardImage);
                    }
                }
            }

            if (label.equals("ac:structured-macro")){
                List<ConfluenceData> dataList = confluenceData.getChild();
                for (ConfluenceData image : dataList) {
                    String imageLabel = image.getLabel();
                    if (imageLabel.equals("ac:parameter")){
                        List<ConfluenceData> dataList1 = image.getChild();
                        if (Objects.isNull(dataList1) || dataList1.isEmpty()) {
                            continue;
                        }
                        for (ConfluenceData image1 : dataList1) {
                            String imageLabel1 = image1.getLabel();
                            if (imageLabel1.equals("ri:attachment")){
                                List<Object> textList = new ArrayList<>();
                                textList.add(new SwardText(" "));
                                String content = image1.getContent();
                                String s = map.get(content);
                                if (!StringUtils.isEmpty(s)){
                                    content = s;
                                }
                                SwardAttachment swardImage = new SwardAttachment(textList,image1.getContent(),content);
                                tableList.add(swardImage);
                            }
                        }
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


    public static void main(String[] args) {
        String html ="<h1 class=\"auto-cursor-target\">一级标题</h1><table><colgroup><col /><col /></colgroup><tbody><tr><th>1</th><th>2</th></tr><tr><td>3</td><td>4</td></tr></tbody></table><p class=\"auto-cursor-target\"><br /></p><p class=\"auto-cursor-target\">表格</p><table class=\"relative-table wrapped\" style=\"width: 31.9083%;\"><colgroup><col style=\"width: 29.3774%;\" /><col style=\"width: 27.821%;\" /><col style=\"width: 42.8016%;\" /></colgroup><tbody><tr><th>1</th><th>2</th><th>3</th></tr><tr><td>4</td><td>5</td><td>6</td></tr></tbody></table><p><br /></p><p>多级表格</p><p><br /></p><table class=\"relative-table\" style=\"width: 27.0011%;\"><colgroup><col style=\"width: 30.5439%;\" /><col style=\"width: 30.5439%;\" /><col style=\"width: 38.9121%;\" /></colgroup><tbody><tr><th><p class=\"auto-cursor-target\"><br /></p><table><tbody><tr><th>1</th><th>2</th></tr><tr><td>3</td><td>4</td></tr></tbody></table><p class=\"auto-cursor-target\">1</p><p class=\"auto-cursor-target\"><br /></p></th><th>2</th><th>3</th></tr><tr><td>1</td><td>2</td><td>3</td></tr></tbody></table><p><br /></p><p>无序列表</p><ul><li>&nbsp;1</li><li><p class=\"auto-cursor-target\">&nbsp;2</p></li><li><p class=\"auto-cursor-target\"><br /></p><table><colgroup><col /><col /></colgroup><tbody><tr><th>1</th><th>3</th></tr><tr><td>2</td><td>4</td></tr></tbody></table><p class=\"auto-cursor-target\"><br /></p></li></ul><p class=\"auto-cursor-target\">有序列表</p><ol><li>&nbsp;1</li><li><p class=\"auto-cursor-target\">&nbsp;2</p><table><colgroup><col /><col /></colgroup><tbody><tr><th>1</th><th>2</th></tr><tr><td>1</td><td>2</td></tr><tr><td>1</td><td>2</td></tr></tbody></table><p class=\"auto-cursor-target\"><br /></p></li><li>&nbsp;3</li></ol>";
        ConfluenceData confluenceData = buildFromHtml(html);
        String s = buildFromHtmlList(confluenceData, new HashMap<>());

        System.out.println(s);

    }

}














