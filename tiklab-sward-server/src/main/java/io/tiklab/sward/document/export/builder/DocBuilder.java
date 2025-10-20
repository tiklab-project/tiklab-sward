package io.tiklab.sward.document.export.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.sward.richText.model.*;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocBuilder {

    public static List<Object> builder(String jsonDate) {
        JSONArray objects = JSONObject.parseArray(jsonDate);
        return builderList(objects);
    }

    /**
     * 解析内容
     * @param array 内容数组
     * @return 解析后的内容
     */
    private static List<Object> builderList(JSONArray array){
        List<Object> list = new ArrayList<>();
        for (Object object : array) {
            Object builder = builderModel(object);
            if (Objects.isNull(builder)){
                continue;
            }
            list.add(builder);
        }
        return list;
    }

    /**
     * 判断类型
     * @param object 信息
     * @return 类型对象
     */
    private static Object builderModel(Object object){
        String jsonString = JSONObject.toJSONString(object);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String type = jsonObject.getString("type");
        Boolean bold = jsonObject.getBoolean("bold");
        if (StringUtils.isEmpty(type)){
            return JSONObject.parseObject(jsonString, SwardText.class);
            // if (Objects.isNull(bold)){
            //     return JSONObject.parseObject(jsonString, SwardText.class);
            // }
            // return JSONObject.parseObject(jsonString, SwardListChildren.class);
        }
        switch (type) {
            case "head" ->{
                SwardHead swardHead = JSONObject.parseObject(jsonString, SwardHead.class);
                List<Object> modelList = findBuilderModelList(swardHead.getChildren());
                swardHead.setChildren(modelList);
                return swardHead;
            }

            case "paragraph" ->{
                SwardParagraph swardParagraph = JSONObject.parseObject(jsonString, SwardParagraph.class);
                List<Object> modelList = findBuilderModelList(swardParagraph.getChildren());
                swardParagraph.setChildren(modelList);
                return swardParagraph;
            }
            case "bulleted-list" ->{
                SwardBulletedList swardBulletedList = JSONObject.parseObject(jsonString, SwardBulletedList.class);
                List<Object> modelList = findBuilderModelList(swardBulletedList.getChildren());
                swardBulletedList.setChildren(modelList);
                return swardBulletedList;
            }
            case "numbered-list" ->{
                SwardNumberedList swardNumberedList = JSONObject.parseObject(jsonString, SwardNumberedList.class);
                List<Object> modelList = findBuilderModelList(swardNumberedList.getChildren());
                swardNumberedList.setChildren(modelList);
                return swardNumberedList;
            }case "list-item" ->{
                SwardListItem listItem = JSONObject.parseObject(jsonString, SwardListItem.class);
                List<Object> modelList = findBuilderModelList(listItem.getChildren());
                listItem.setChildren(modelList);
                return listItem;
            }
            case "table" ->{
                SwardTable swardTable = JSONObject.parseObject(jsonString, SwardTable.class);
                List<Object> modelList = findBuilderModelList(swardTable.getChildren());
                swardTable.setChildren(modelList);
                return swardTable;
            }
            case "table-colgroup" ->{
                SwardTableGroup swardTableGroup = JSONObject.parseObject(jsonString, SwardTableGroup.class);
                List<Object> modelList = findBuilderModelList(swardTableGroup.getChildren());
                swardTableGroup.setChildren(modelList);
                return swardTableGroup;
            }
            case "table-col" ->{
                SwardTableCol swardTableCol = JSONObject.parseObject(jsonString, SwardTableCol.class);
                List<Object> modelList = findBuilderModelList(swardTableCol.getChildren());
                swardTableCol.setChildren(modelList);
                return swardTableCol;
            }
            case "table-row" ->{
                SwardTableRow swardTableRow = JSONObject.parseObject(jsonString, SwardTableRow.class);
                List<Object> modelList = findBuilderModelList(swardTableRow.getChildren());
                swardTableRow.setChildren(modelList);
                return swardTableRow;
            }
            case "table-cell" ->{
                SwardTableCell swardTableCell = JSONObject.parseObject(jsonString, SwardTableCell.class);
                List<Object> modelList = findBuilderModelList(swardTableCell.getChildren());
                swardTableCell.setChildren(modelList);
                return swardTableCell;
            }
            case "table-content" ->{
                SwardTableContent swardTableContent = JSONObject.parseObject(jsonString, SwardTableContent.class);
                List<Object> modelList = findBuilderModelList(swardTableContent.getChildren());
                swardTableContent.setChildren(modelList);
                return swardTableContent;
            }
            case "image" ->{
                SwardImage swardImage = JSONObject.parseObject(jsonString, SwardImage.class);
                List<Object> modelList = findBuilderModelList(swardImage.getChildren());
                swardImage.setChildren(modelList);
                return swardImage;
            }
            // case "attachment" ->{
            //     SwardAttachment swardAttachment = JSONObject.parseObject(jsonString, SwardAttachment.class);
            //     List<Object> modelList = findBuilderModelList(swardAttachment.getChildren());
            //     swardAttachment.setChildren(modelList);
            //     return swardAttachment;
            // }
            case "code-block" ->{
                SwardCodeBlock swardCodeBlock = JSONObject.parseObject(jsonString, SwardCodeBlock.class);
                List<Object> modelList = findBuilderModelList(swardCodeBlock.getChildren());
                swardCodeBlock.setChildren(modelList);
                return swardCodeBlock;
            }
            case "date" ->{
                SwardDate swardDate = JSONObject.parseObject(jsonString, SwardDate.class);
                List<Object> modelList = findBuilderModelList(swardDate.getChildren());
                swardDate.setChildren(modelList);
                return swardDate;
            }
            case "link" ->{
                SwardLink swardLink = JSONObject.parseObject(jsonString, SwardLink.class);
                List<Object> modelList = findBuilderModelList(swardLink.getChildren());
                swardLink.setChildren(modelList);
                return swardLink;
            }
             case "check-list-item" ->{
                SwardCheckListItem swardCheckListItem = JSONObject.parseObject(jsonString, SwardCheckListItem.class);
                List<Object> modelList = findBuilderModelList(swardCheckListItem.getChildren());
                 swardCheckListItem.setChildren(modelList);
                return swardCheckListItem;
            }
            case "block-quote" ->{
                SwardBlockQuote swardBlockQuote = JSONObject.parseObject(jsonString, SwardBlockQuote.class);
                List<Object> modelList = findBuilderModelList(swardBlockQuote.getChildren());
                swardBlockQuote.setChildren(modelList);
                return swardBlockQuote;
            }
            default -> {
                JSONObject parseObject = JSONObject.parseObject(jsonString);
                JSONArray children = parseObject.getJSONArray("children");
                if (!Objects.isNull(children)){
                   return builderList(children);
                }
                return null;
            }
        }
    }

    /**
     * 存在节点，开始迭代
     * @param children 节点
     * @return 结果
     */
    public static List<Object> findBuilderModelList(List<Object> children){
        if (Objects.isNull(children) || children.isEmpty()){
           return null;
        }
        String string = JSONObject.toJSONString(children);
        JSONArray jsonArray = JSONObject.parseArray(string);
        return builderList(jsonArray);
    }

}
