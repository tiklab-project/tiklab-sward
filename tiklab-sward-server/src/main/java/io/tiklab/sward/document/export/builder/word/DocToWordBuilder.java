package io.tiklab.sward.document.export.builder.word;

import io.tiklab.core.exception.SystemException;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.DfsObjectMeta;
import io.tiklab.dfs.common.model.object.FindRequest;
import io.tiklab.sward.document.export.model.word.*;
import io.tiklab.sward.richText.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DocToWordBuilder {

    @Autowired(required = false)
    DfsClient dfsClient;

    @Value("${DATA_HOME}")
    String dataHome;


    /**
     * 导出
     * @param list 对象信息
     * @param path 导出位置
     */
    public void docToWordBuilderList(List<Object> list,String path){

        File file = new File(path);
        String fileName = file.getName().substring(0,file.getName().lastIndexOf("."));
        try {
           WordBuilder builder = new WordBuilder();
           builder.addTitle(fileName);
           List<Object> objectList = new ArrayList<>();
           for (Object object : list) {
               Object builder1 = docToWordBuilder(object);
               if (Objects.isNull(builder1)){
                   continue;
               }
               objectList.add(builder1);
           }
           addWord(objectList,builder);
           builder.save(path);
       }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("导出失败："+e);
       }
    }

    private void addWord(List<?> objectList,WordBuilder builder){
        for (Object object : objectList) {
            if (object instanceof WordTable wordTable){
                builder.addTable(wordTable);
            }else if (object instanceof WordText wordText){
                builder.addText(wordText);
            }else if (object instanceof WordHead wordHead){
                builder.addHead(wordHead);
            }else if (object instanceof WordParagraph wordParagraph){
                builder.addParagraph(wordParagraph);
            }else if (object instanceof WordImage wordImage){
                builder.addImage(wordImage);
            }else if (object instanceof List<?> list){
                addWord(list,builder);
            }else if (object instanceof WordCodeBlock wordCodeBlock){
                builder.addCodeBlock(wordCodeBlock);
            }else if (object instanceof WordLink wordLink){
                builder.addLink(wordLink);
            }else if (object instanceof WordAttachment wordAttachment){
                builder.addAttachment(wordAttachment);
            }else if (object instanceof WordCheckListItem wordCheckListItem){
                builder.addCheckListItem(wordCheckListItem);
            }else if (object instanceof WordBlockQuote wordBlockQuote){
                builder.addBlockQuote(wordBlockQuote);
            }else if (object instanceof WordList wordList){
                if (wordList.getType().equals("order")){
                    builder.addOrderedList(wordList.getItem());
                }else {
                    builder.addUnorderedList(wordList.getItem());
                }
            }
        }

    }

    /**
     * 解析类型
     * @param object 对象
     * @return 解析信息
     */
    private Object docToWordBuilder(Object object){
        if (object instanceof SwardHead swardHead){
            return headBuilder(swardHead);
        }else if (object instanceof SwardParagraph swardParagraph){
            return paragraphBuilder(swardParagraph);
        } else if (object instanceof SwardNumberedList swardNumberedList){
            return numberedListBuilder(swardNumberedList);
        } else if (object instanceof SwardBulletedList swardBulletedList ){
            return bulletedListBuilder(swardBulletedList);
        } else if (object instanceof SwardTable swardTable ){
            return tableBuilder(swardTable);
        } else if (object instanceof SwardImage swardImage){
            return imageBuilder(swardImage);
        } else if (object instanceof SwardCodeBlock swardCodeBlock){
            return codeBlock(swardCodeBlock);
        }else if (object instanceof SwardDate swardDate){
            return dateBuilder(swardDate);
        }else if (object instanceof SwardText swardText){
            return new WordText(swardText.getText());
        }else if (object instanceof SwardLink swardLink){
            return linkBuilder(swardLink);
        }else if (object instanceof SwardAttachment swardAttachment){
            return attachmentBuilder(swardAttachment);
        }else if (object instanceof SwardCheckListItem swardCheckListItem){
            return checkListItemBuilder(swardCheckListItem);
        }else if (object instanceof SwardBlockQuote swardBlockQuote){
            return blockQuoteBuilder(swardBlockQuote);
        }else if (object instanceof List<?> list){
            return docToWordBuilder(list.get(0));
        } else {
            return null;
        }
    }

    /**
     * 标题转义
     * @param swardHead 标题信息
     */
    private WordHead headBuilder(SwardHead swardHead) {
        String head = swardHead.getHead();
        List<Object> children = swardHead.getChildren();
        WordHead wordHead = new WordHead();
        switch (head) {
            case "h1" -> {
                wordHead = new WordHead(findHeadHtml(children), 22, 1);
            }
            case "h2" -> {
                wordHead = new WordHead(findHeadHtml(children), 19, 2);
            }
            case "h3" -> {
                wordHead = new WordHead(findHeadHtml(children), 16, 3);
            }
            case "h4" -> {
                wordHead = new WordHead(findHeadHtml(children), 13, 4);
            }
            case "h5" -> {
                wordHead = new WordHead(findHeadHtml(children), 10, 5);
            }
            case "h6" -> {
                wordHead = new WordHead(findHeadHtml(children), 8, 6);
            }
        }
        return wordHead;
    }

    private String findHeadHtml(List<Object> children){
        StringBuilder text = new StringBuilder();
        for (Object o : children) {
            if (o instanceof SwardText swardText) {
                text.append(textToHtml(swardText));
            }
            if (o instanceof SwardListChildren swardListChildren) {
                text.append(swardListChildren.getText());
            }
        }
        return text.toString();
    }

    private String findHeadHtml(List<Object> children,String color){
        StringBuilder text = new StringBuilder();
        for (Object o : children) {
            if (o instanceof SwardText swardText) {
                if (swardText.getColor().equals("#000000")){
                    swardText.setColor(color);
                }
                text.append(textToHtml(swardText));
            }
            if (o instanceof SwardListChildren swardListChildren) {
                text.append(swardListChildren.getText());
            }
        }
        return text.toString();
    }

    private String textToHtml(Object o){
        String tem = """
                <span style='
                color:${color};
                background:${background};
                underline:${underline};
                italic:${italic};
                strike:${strike};
                font_size:${fontSize};
                bold:${bold};
                code_line:${codeLine};
                '>${text}</span>""";
        if (o instanceof SwardText swardText){
            String color = swardText.getColor();
            String background = swardText.getBackgroundColor();
            return tem.replace("${color}", color)
                    .replace("${background}", background)
                    .replace("${fontSize}", swardText.getFontSize())
                    .replace("${underline}", swardText.getUnderline().toString())
                    .replace("${italic}", swardText.getItalic().toString())
                    .replace("${strike}", swardText.getStrike().toString())
                    .replace("${bold}", swardText.getBold().toString())
                    .replace("${codeLine}", swardText.getCodeLine().toString())
                    .replace("${text}", swardText.getText());
        }
        return "";
    }

    /**
     * 段落转义
     * @param swardParagraph 信息
     */
    private WordParagraph paragraphBuilder(SwardParagraph swardParagraph){
        List<Object> list = new ArrayList<>();
        for (Object child : swardParagraph.getChildren()) {
            if (child instanceof SwardParagraph paragraph){
                for (Object paragraphChild : paragraph.getChildren()) {
                    if (paragraphChild instanceof SwardText swardText){
                        WordText wordText = new WordText(textToHtml(swardText), swardText.getFontSize());
                        list.add(wordText);
                    } else if (paragraphChild instanceof SwardListChildren swardListChildren) {
                        WordText wordText = new WordText(swardListChildren.getText(), "10px");
                        list.add(wordText);
                    }else {
                        Object object = docToWordBuilder(child);
                        list.add(object);
                    }
                }
            } else if (child instanceof SwardText swardText){
                WordText wordText = new WordText(textToHtml(swardText), "10px");
                list.add(wordText);
            }else if (child instanceof SwardLink swardLink){
                WordLink wordLink = linkBuilder(swardLink);
                list.add(wordLink);
            } else if (child instanceof SwardAttachment swardAttachment){
                WordAttachment wordAttachment = attachmentBuilder(swardAttachment);
                list.add(wordAttachment);
            }else if (child instanceof SwardListChildren listChildren) {
                WordText wordText = new WordText(listChildren.getText(), "10px");
                list.add(wordText);
            }else {
                Object object = docToWordBuilder(child);
                list.add(object);
            }
        }
        return new WordParagraph(list);
    }

    /**
     * 有序列表转义
     * @param swardNumberedList 信息
     */
    private WordList numberedListBuilder(SwardNumberedList swardNumberedList){
        WordList wordList = new WordList();
        wordList.setType("order");
        List<Object> list = new ArrayList<>();
        for (Object child : swardNumberedList.getChildren()) {
            try {
                SwardListItem listItem = (SwardListItem) child;
                List<Object> childList = new ArrayList<>();
                for (Object itemChild : listItem.getChildren()) {
                    addListItem(itemChild,childList);
                }
                list.add(childList);
            }catch (Exception e){
                continue;
            }
        }
        wordList.setItem(list);
        return wordList;
    }

    public void addListItem(Object itemChild,List<Object> childList){
        if (itemChild instanceof SwardListChildren swardListChildren){
            childList.add(swardListChildren.getText());
        } else if (itemChild instanceof SwardText  swardText){
            childList.add(textToHtml(swardText));
        }else if (itemChild instanceof List list){
            for (Object o : list) {
                addListItem(o,childList);
            }
        } else {
            Object object = docToWordBuilder(itemChild);
            childList.add(object);
        }
    }

    /**
     * 段落无序列表转义
     * @param swardBulletedList 信息
     */
    private WordList bulletedListBuilder(SwardBulletedList swardBulletedList){

        WordList wordList = new WordList();
        wordList.setType("notOrder");
        List<Object> list = new ArrayList<>();
        for (Object child : swardBulletedList.getChildren()) {
            try {
                SwardListItem listItem = (SwardListItem) child;
                List<Object> childList = new ArrayList<>();
                List<Object> itemChildrenList = listItem.getChildren();
                for (Object itemChild : itemChildrenList) {
                    if (itemChild instanceof SwardListChildren swardListChildren){
                        childList.add(swardListChildren.getText());
                    } else if (itemChild instanceof SwardText  swardText){
                        childList.add(textToHtml(swardText));
                    } else {
                        Object object = docToWordBuilder(itemChild);
                        childList.add(object);
                    }
                }
                list.add(childList);
            }catch (Exception e){
                continue;
            }
        }
        wordList.setItem(list);
        return wordList;
    }

    /**
     * 表格转义
     * @param swardTable 信息
     */
    private WordTable tableBuilder(SwardTable swardTable){
        List<Object> children = swardTable.getChildren();
        WordTable wordTable = new WordTable();
        List<List<Object>> rows = new ArrayList<>();
        for (Object child : children) {
            List<Object> childrenList = new ArrayList<>();
            if (child instanceof SwardTableRow swardTableRow){
                List<Object> rowchildrenList = swardTableRow.getChildren();
                for (Object object : rowchildrenList) {
                    List<Object> ceList = new ArrayList<>();
                    if (object instanceof SwardTableCell swardTableCell){
                        for (Object o : swardTableCell.getChildren()) {
                            List<Object> cList = new ArrayList<>();
                            if (o instanceof SwardTableContent swardTableContent){
                                for (Object os : swardTableContent.getChildren()) {
                                    if (os instanceof List<?> list){
                                        Object object1 = list.get(0);
                                        Object toWordBuilder = docToWordBuilder(object1);
                                        if (Objects.isNull(toWordBuilder)){
                                            System.out.println("----");
                                        }
                                        cList.add(toWordBuilder);
                                    }else {
                                        cList.add(docToWordBuilder(os));
                                    }
                                }
                            } else {
                                cList.add(docToWordBuilder(o));
                            }
                            ceList.add(cList);
                        }
                        childrenList.addAll(ceList);
                    }
                }
                rows.add(childrenList);
            }else if (child instanceof SwardTableGroup swardTableGroup){
                List<Object> rowchildrenList = swardTableGroup.getChildren();
            }
        }
        wordTable.setRows(rows);
        return wordTable;
    }

    /**
     * 图片转义
     * @param swardImage 图片信息
     * @return 转义
     */
    private WordImage imageBuilder(SwardImage swardImage){
        WordImage wordImage = new WordImage();
        String url = swardImage.getUrl();

        if (url.startsWith("http") || url.startsWith("https")){
            wordImage.setPath(url);
            wordImage.setFileName(UuidGenerator.getRandomIdByUUID(12)+".png");
        } else {

            String fileName;
            String objectId;
            try {
                objectId = url.replace("/image/", "");

                DfsObjectMeta dfsObjectMeta = dfsClient.findMeta(objectId);

                fileName = objectId + "_" + dfsObjectMeta.getFileName();
            }catch (Exception e){
                throw new SystemException("下载图片："+e);
            }

            try {
                String filePath = dataHome + "/export/"+fileName;

                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                FindRequest findRequest = new FindRequest(objectId,fileOutputStream);
                dfsClient.find(findRequest);

                wordImage.setPath(filePath);
                wordImage.setFileName(fileName);
            }catch (Exception e){
                throw new SystemException("图片写入失败："+e);
            }
        }
        wordImage.setHeight(Integer.parseInt(swardImage.getHeight().replace("px","").replace("%", "")));
        wordImage.setWidth(Integer.parseInt(swardImage.getWidth().replace("px","").replace("%", "")));
        return wordImage;
    }

    /**
     * 代码块转义
     * @param codeBlock 代码快信息
     * @return 转义内容
     */
    private WordCodeBlock codeBlock(SwardCodeBlock codeBlock){
        List<Object> list = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        for (Object child : codeBlock.getChildren()) {
            if (child instanceof SwardText swardText){
                text.append(swardText.getText()).append("\n");
            } else if (child instanceof String s){
                text.append(s).append("\n");
            } else if (child instanceof SwardParagraph swardParagraph){
                List<Object> children = swardParagraph.getChildren();
                for (Object object : children) {
                    if (object instanceof SwardText swardText){
                        text.append(swardText.getText()).append("\n");
                    } else if (object instanceof String s){
                        text.append(s).append("\n");
                    }
                }
            } else {
                Object object = docToWordBuilder(child);
                list.add(object);
            }
        }
        return new WordCodeBlock(text.toString());
    }

    /**
     * 时间转义
     * @param swardDate 时间转义
     * @return 转义内容
     */
    private WordText dateBuilder(SwardDate swardDate){
        String dateValue = swardDate.getDateValue();
        return new WordText(dateValue,"10px");
    }

    /**
     * 超链接转义
     * @param swardLink 时间转义
     * @return 转义内容
     */
    private WordLink linkBuilder(SwardLink swardLink){
        List<Object> children = swardLink.getChildren();
        String text = findHeadHtml(children,"#1890ff");
        return new WordLink(swardLink.getUrl(),text);
    }


    private WordCheckListItem checkListItemBuilder(SwardCheckListItem swardCheckListItem){
        List<Object> children= swardCheckListItem.getChildren();
        boolean checked = swardCheckListItem.isChecked();
        String text = findHeadHtml(children);
        return new WordCheckListItem(text,checked);
    }

    private WordBlockQuote blockQuoteBuilder(SwardBlockQuote swardBlockQuote){
        List<Object> children= swardBlockQuote.getChildren();
        StringBuilder text = new StringBuilder();
        for (Object child : children) {
            if (child instanceof SwardText swardText){
                text.append(" ").append(swardText.getText()).append("\n");
            } else if (child instanceof String s){
                text.append(" ").append(s).append("\n");
            } else if (child instanceof SwardParagraph swardParagraph){
                List<Object> childrens = swardParagraph.getChildren();
                for (Object object : childrens) {
                    if (object instanceof SwardText swardText){
                        text.append(" ").append(swardText.getText()).append("\n");
                    } else if (object instanceof String s){
                        text.append(" ").append(s).append("\n");
                    }
                }
            }
        }
        return new WordBlockQuote(text.toString());
    }

    private WordAttachment attachmentBuilder(SwardAttachment swardAttachment){
        WordAttachment wordAttachment = new WordAttachment();
        try {
            String fileName = swardAttachment.getFileName();
            String url = swardAttachment.getFileUrl();
            wordAttachment.setFileName(fileName);
            String filePath = dataHome + "/export/"+fileName;

            String[] split = fileName.split("\\.");
            if (split.length > 1){
                wordAttachment.setFileType(split[1]);
            }else {
                wordAttachment.setFileType("txt");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            FindRequest findRequest = new FindRequest(url,fileOutputStream);
            dfsClient.find(findRequest);

            wordAttachment.setFilePath(filePath);
        }catch (Exception e){
            e.printStackTrace();
            wordAttachment.setFileName("导出文件失败!");
        }
        return wordAttachment;

    }
}













