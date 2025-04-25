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
        switch (label) {
            case "h1" -> {
                return new SwardHead("h1",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "h2" -> {
                return new SwardHead("h2",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "h3" -> {
                return new SwardHead("h3",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "h4" -> {
                return new SwardHead("h4",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "h5" -> {
                return new SwardHead("h5",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "h6" -> {
                return new SwardHead("h6",new SwardText(data.getContent()));
                // return JSONObject.toJSONString(swardHead);
            }
            case "p" -> {
                return findSwardP(data,map);
                // return JSONObject.toJSONString(swardHead);
            }
            case "ol" -> {
                return findSwardNumberedList(data,map);
                // return JSONObject.toJSONString(swardHead);
            }
            case "ul" -> {
                return findSwardBulletedList(data,map);
                // return JSONObject.toJSONString(swardHead);
            }
            case "table" -> {
                return findSwardTable(data,map);
                // return JSON.toJSONString(swardHead, SerializerFeature.DisableCircularReferenceDetect);
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
            SwardParagraph swardParagraph = new SwardParagraph(new SwardText(data.getContent()));
            tableList.add(swardParagraph);
            return new SwardParagraph(tableList);
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


}














