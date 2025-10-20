package io.tiklab.sward.document.export.builder.html;

import io.tiklab.core.exception.SystemException;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.DfsObjectMeta;
import io.tiklab.dfs.common.model.object.FindRequest;
import io.tiklab.sward.document.export.builder.word.WordBuilder;
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
public class DocToHtmlBuilder {

    @Autowired(required = false)
    DfsClient dfsClient;

    @Value("${DATA_HOME}")
    String dataHome;


    /**
     * 导出
     * @param list 对象信息
     * @param path 导出位置
     */
    public void docToHtmlBuilderList(List<Object> list,String path){
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
            addHtml(objectList,builder);
           builder.save(path);
       }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("导出失败："+e);
       }
    }

    private void addHtml(List<?> objectList,WordBuilder builder){
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
                addHtml(list,builder);
            }else if (object instanceof WordLink wordLink){
                builder.addLink(wordLink);
            }else if (object instanceof WordCodeBlock wordCodeBlock){
                builder.addCodeBlock(wordCodeBlock);
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
        } else if (object instanceof SwardCodeBlock codeBlock){
            return codeBlock(codeBlock);
        }else if (object instanceof SwardText swardText){
            return new WordText(swardText.getText(), "10px");
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
        Object child = children.get(0);
        switch (head) {
            case "h1" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 22, 1);
                }

                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 22, 1);
                }
            }
            case "h2" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 19, 2);
                }
                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 19, 2);
                }
            }
            case "h3" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 16, 3);
                }
                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 16, 3);
                }
            }
            case "h4" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 13, 4);
                }
                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 13, 4);
                }
            }
            case "h5" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 10, 5);
                }
                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 10, 5);
                }
            }
            case "h6" -> {
                if (child instanceof SwardText swardText) {
                    wordHead = new WordHead(swardText.getText(), 8, 6);
                }
                if (child instanceof SwardListChildren swardListChildren) {
                    wordHead = new WordHead(swardListChildren.getText(), 8, 6);
                }
            }
        }
        return wordHead;
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
                        WordText wordText = new WordText(swardText.getText(), "10px");
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
                WordText wordText = new WordText(swardText.getText(), "10px");
                list.add(wordText);
            } else if (child instanceof SwardListChildren listChildren) {
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
            SwardListItem listItem = (SwardListItem) child;
            List<Object> childList = new ArrayList<>();
            for (Object itemChild : listItem.getChildren()) {
                if (itemChild instanceof SwardListChildren swardListChildren){
                    childList.add(swardListChildren.getText());
                } else if (itemChild instanceof SwardText  swardText){
                    childList.add(swardText.getText());
                } else {
                    Object object = docToWordBuilder(itemChild);
                    childList.add(object);
                }
            }
            list.add(childList);
        }
        wordList.setItem(list);
        return wordList;
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
            SwardListItem listItem = (SwardListItem) child;
            List<Object> childList = new ArrayList<>();
            List<Object> itemChildrenList = listItem.getChildren();
            for (Object itemChild : itemChildrenList) {
                if (itemChild instanceof SwardListChildren swardListChildren){
                    childList.add(swardListChildren.getText());
                } else if (itemChild instanceof SwardText  swardText){
                    childList.add(swardText.getText());
                } else {
                    Object object = docToWordBuilder(itemChild);
                    childList.add(object);
                }
            }
            list.add(childList);
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
        // wordImage.setHeight((swardImage.getHeight()));
        // wordImage.setWidth( (swardImage.getWidth()));
        return wordImage;
    }

    /**
     * 代码块转义
     * @param codeBlock 代码快信息
     * @return 转义内容
     */
    private WordCodeBlock codeBlock(SwardCodeBlock codeBlock){
        List<Object> list = new ArrayList<>();
        for (Object child : codeBlock.getChildren()) {
            if (child instanceof SwardText || child instanceof String){
                WordText wordText = new WordText(child.toString(), "10px");
                list.add(wordText);
            }else {
                Object object = docToWordBuilder(child);
                list.add(object);
            }
        }
        return new WordCodeBlock(list);
    }

}













