package io.tiklab.sward.document.export.builder;

import io.tiklab.sward.document.export.model.*;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WordBuilder {

    private final XWPFDocument doc = new XWPFDocument();

    private final Logger logger = LoggerFactory.getLogger(WordBuilder.class);

    // 添加title
    public void addTitle(String titleText) {
        XWPFParagraph titleParagraph = doc.createParagraph();
        titleParagraph.setAlignment(ParagraphAlignment.LEFT); // ⬅️ 左对齐

        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText(titleText);
        titleRun.setBold(true);
        titleRun.setFontFamily("微软雅黑"); // 可换成你喜欢的字体
        titleRun.setFontSize(24); // 通常标题字号较大
    }

    // 添加标题
    public void addHead(WordHead wordHead ) {
        Integer level = wordHead.getLevel();
        level = Math.max(1, Math.min(level, 6));

        XWPFParagraph para = doc.createParagraph();
        para.setStyle("Heading " + level);

        CTPPr pPr = para.getCTP().isSetPPr() ? para.getCTP().getPPr() : para.getCTP().addNewPPr();

        // 设置大纲级别（Outline Level）
        CTDecimalNumber outlineLvl = pPr.isSetOutlineLvl() ? pPr.getOutlineLvl() : pPr.addNewOutlineLvl();
        outlineLvl.setVal(BigInteger.valueOf(level - 1));

        // 设置上下间距（根据标题级别自适应）
        CTSpacing spacing = pPr.isSetSpacing() ? pPr.getSpacing() : pPr.addNewSpacing();

        // 定义每级标题的间距（twips，1pt=20twips）
        int before = switch (level) {
            case 1 -> 400; // 20pt
            case 2 -> 300;
            case 3 -> 240;
            case 4 -> 160;
            case 5 -> 120;
            case 6 -> 100;
            default -> 200;
        };
        int after = switch (level) {
            case 1 -> 200;
            case 2 -> 160;
            case 3 -> 120;
            case 4 -> 100;
            case 5 -> 80;
            case 6 -> 60;
            default -> 100;
        };
        spacing.setBefore(BigInteger.valueOf(before));
        spacing.setAfter(BigInteger.valueOf(after));

        // 添加文字
        XWPFRun run = para.createRun();
        run.setBold(true);
        run.setFontSize(wordHead.getSize()); // 保留传入字号
        run.setText(wordHead.getText());
    }

    // 添加普通文本
    public void addText(WordText wordText) {
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(wordText.getText());
        run.setBold(wordText.getBlog());
        run.setFontSize(wordText.getSize());
    }

    public void addParagraph(WordParagraph wordParagraph){
        XWPFParagraph para = doc.createParagraph();
        for (Object object : wordParagraph.getList()) {
            if (object instanceof  WordText || object instanceof String) {
                XWPFRun run = para.createRun();
                run.setText(object.toString());
                run.setFontSize(10);
            }
            if (object instanceof  WordImage wordImage) {
               addImage(wordImage);
            }
        }
    }

    // 添加无序列表
    public void addUnorderedList(List<Object> items) {
        BigInteger numId = addNumbering(false);
        addUnorderedList(items, 0, numId);
    }

    private void addUnorderedList(List<?> items, int level, BigInteger numId) {
        for (Object item : items) {
            XWPFParagraph para = doc.createParagraph();

            // 始终设置NumID和NumILvl
            para.setNumID(numId);
            para.setNumILvl(BigInteger.valueOf(level));

            // 动态设置缩进
            para.setIndentationLeft(300);
            para.setIndentationFirstLine(300);

            if (item instanceof WordList wordList) {
                if (!wordList.getType().equals("order")) {
                    addUnorderedList(wordList.getItem(), level, numId); // 处理无序列表
                } else {
                    addOrderedList(wordList.getItem(), level, numId); // 递归处理有序列表
                }
            } else if (item instanceof WordTable wordTable) {
                addTable(wordTable);
            } else if (item instanceof List<?> list) {
                addList(list,para);
            } else if (item instanceof WordCodeBlock wordCodeBlock) {
                addCodeBlock(wordCodeBlock);
            }else if (item instanceof WordParagraph wordParagraph) {
                addList(wordParagraph.getList(),para);
            } else if (item instanceof String || item instanceof WordText) {
                // 处理文本内容
                XWPFRun run = para.createRun();
                run.setText(item.toString());
            } else {
                logger.error("未知的有序列表属性:{}", item.getClass().getSimpleName());
            }
        }
    }

    // 添加列表样式支持
    private BigInteger addNumbering(boolean ordered) {
        XWPFNumbering numbering = doc.createNumbering();

        CTAbstractNum abstractNum = CTAbstractNum.Factory.newInstance();
        abstractNum.setAbstractNumId(BigInteger.valueOf(new Random().nextInt(100000)));

        CTLvl level = abstractNum.addNewLvl();
        level.setIlvl(BigInteger.ZERO);
        level.addNewNumFmt().setVal(ordered ? STNumberFormat.DECIMAL : STNumberFormat.BULLET);
        level.addNewLvlText().setVal(ordered ? "%1." : "•");
        level.addNewStart().setVal(BigInteger.ONE);

        XWPFAbstractNum xwpfAbstractNum = new XWPFAbstractNum(abstractNum);
        BigInteger abstractNumId = numbering.addAbstractNum(xwpfAbstractNum);
        return numbering.addNum(abstractNumId);
    }

    // 添加有序列表
    public void addOrderedList(List<Object> items) {
        BigInteger numId = addNumbering(true);
        addOrderedList(items, 0, numId); // 从 level 0 开始
    }

    private void addOrderedList(List<?> items, int level, BigInteger numId) {
        for (Object item : items) {
            XWPFParagraph para = doc.createParagraph();

            // 始终设置NumID和NumILvl
            para.setNumID(numId);
            para.setNumILvl(BigInteger.valueOf(level));

            // 动态设置缩进
            para.setIndentationLeft(300);
            para.setIndentationFirstLine(300);

            if (item instanceof WordList wordList) {
                if (!wordList.getType().equals("order")) {
                    addUnorderedList(wordList.getItem(), level, numId); // 处理无序列表
                } else {
                    addOrderedList(wordList.getItem(), level, numId); // 递归处理有序列表
                }
            } else if (item instanceof WordTable wordTable) {
                addTable(wordTable);
            } else if (item instanceof List<?> list) {
                addList(list,para);
            } else if (item instanceof WordParagraph wordParagraph) {
                addList(wordParagraph.getList(),para);
            } else if (item instanceof String || item instanceof WordText) {
                // 处理文本内容
                XWPFRun run = para.createRun();
                run.setText(item.toString());
            } else {
                logger.error("未知的有序列表属性:{}", item.getClass().getSimpleName());
            }
        }
    }

    public void addList(List<?> list,XWPFParagraph para){
        for (Object object : list) {
            if (object instanceof String || object instanceof WordText) {
                XWPFRun run = para.createRun();
                run.setText(object.toString());
            }else if (object instanceof List<?> lists) {
                addList(lists,para);
            } else if (object instanceof WordTable wordTable) {
                addTable(wordTable);
            }else if (object instanceof WordParagraph wordParagraph) {
                List<Object> list1 = wordParagraph.getList();
                addList(list1,para);
            }
        }
    }

    // 添加表格
    public void addTable(WordTable wordTable) {
        List<List<Object>> rows = wordTable.getRows();
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("行数据为空！");
        }

        // 创建表格
        XWPFTable table = doc.createTable(rows.size(), rows.get(0).size());
        // 设置表格总宽为页面宽度的 80%（约 9525 twips）
        CTTbl tableCT = table.getCTTbl();
        CTTblPr pr = tableCT.getTblPr() != null ? tableCT.getTblPr() : tableCT.addNewTblPr();
        CTTblWidth tblW = pr.isSetTblW() ? pr.getTblW() : pr.addNewTblW();
        tblW.setW(BigInteger.valueOf(9525)); // 80% 宽度
        tblW.setType(STTblWidth.DXA);

        // 每列平分宽度
        int colSize = rows.get(0).size();
        int colWidth = 9525 / colSize;
        // 填充表格内容并设置居中样式
        for (int i = 0; i < rows.size(); i++) {
            List<Object> row = rows.get(i);
            XWPFTableRow tableRow = table.getRow(i);
            // 设置行高规则为：AT_LEAST（最小高度，内容可撑开）
            CTTrPr trPr = tableRow.getCtRow().isSetTrPr() ? tableRow.getCtRow().getTrPr() : tableRow.getCtRow().addNewTrPr();
            CTHeight trHeight = trPr.addNewTrHeight();
            trHeight.setVal(BigInteger.valueOf(500));
            trHeight.setHRule(STHeightRule.AT_LEAST);
            for (int j = 0; j < row.size(); j++) {
                XWPFTableCell cell = table.getRow(i).getCell(j);
                cell.removeParagraph(0); // 移除默认段落
                CTTc ctTc = cell.getCTTc();
                CTTcPr tcPr = ctTc.isSetTcPr() ? ctTc.getTcPr() : ctTc.addNewTcPr();
                CTTblWidth cellWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
                cellWidth.setW(BigInteger.valueOf(colWidth));
                cellWidth.setType(STTblWidth.DXA);

                if (row.get(j) instanceof List<?> wordList){
                    addTableDistribute(wordList,cell);
                } else if (row.get(j) instanceof WordParagraph wordParagraph) {
                    addTableDistribute(wordParagraph.getList(),cell);
                } else {
                    logger.error("表格对象未解析：{}", row.get(j).getClass().getSimpleName());
                }

            }
        }
    }

    /**
     * 分发列表嵌套信息
     * @param items 列表值
     * @param cell 嵌套信息
     */
    private void addTableDistribute(List<?> items,XWPFTableCell cell) {
        for (Object item : items) {
            if (Objects.isNull(item)){
                continue;
            }
            if (item instanceof WordText wordText){
                // 创建新段落并设置居中
                XWPFParagraph para = cell.addParagraph();
                para.setAlignment(ParagraphAlignment.CENTER); // 水平居中
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);  // 设置垂直居中
                XWPFRun run = para.createRun();
                run.setText(wordText.getText());
            } else if (item instanceof WordList wordList){
                addTableList(wordList,cell);
            } else if (item instanceof List<?> list){
                addTableDistribute(list,cell);
            } else if (item instanceof WordParagraph wordParagraph) {
                addTableDistribute(wordParagraph.getList(),cell);
            } else if (item instanceof WordTable wordTable){
                addTable(wordTable,cell);
            } else {
                logger.error("未解析对象：{}", item.getClass().getSimpleName());
            }
        }
    }

    /**
     * 嵌套表格
     * @param wordTable 表格信息
     * @param cell 表格
     */
    private void addTable(WordTable wordTable ,XWPFTableCell cell) {

        XWPFParagraph paragraph = cell.addParagraph(); // 必须添加段落才能创建有效游标
        XmlCursor cursor = paragraph.getCTP().newCursor();
        XWPFTable nestedTable = cell.insertNewTbl(cursor);

        List<List<Object>> rows = wordTable.getRows();
        for (List<Object> list : rows) {
            XWPFTableRow row = nestedTable.createRow();
            for (int j = 0; j < list.size(); j++) {
                XWPFTableCell cellNested;
                // 第一行可能已有部分单元格
                if (row.getTableCells().size() > j) {
                    cellNested = row.getCell(j);
                } else {
                    cellNested = row.createCell();
                }

                // 设置单元格宽度
                CTTcPr tcPr = cellNested.getCTTc().isSetTcPr() ? cellNested.getCTTc().getTcPr() : cellNested.getCTTc().addNewTcPr();
                CTTblWidth cellWidth = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
                cellWidth.setW(BigInteger.valueOf(800));
                cellWidth.setType(STTblWidth.DXA);

                // 设置垂直居中
                cellNested.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                cellNested.removeParagraph(0); // 移除默认段落

                // 添加段落并设置居中
                XWPFParagraph para = cellNested.addParagraph();
                para.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run = para.createRun();

                // 设置内容
                Object value = list.get(j);
                if (value instanceof String str) {
                    run.setText(str);
                } else if (value instanceof WordTable innerTable) {
                    // 递归插入嵌套表格
                    addTable(innerTable, cellNested);
                }else if (value instanceof List<?> list1) {
                    // 递归插入嵌套表格
                    addTableDistribute(list1, cellNested);
                }else if (value instanceof WordParagraph wordParagraph) {
                    addTableDistribute(wordParagraph.getList(),cell);
                }  else {
                    run.setText("未知类型：" + value.getClass().getSimpleName());
                }
            }
        }
    }

    /**
     * 嵌套列表
     * @param wordList 列表信息
     * @param cell 表格
     */
    private void addTableList(WordList wordList ,XWPFTableCell cell){
        List<Object> itemList = wordList.getItem();
        String type = wordList.getType();

        BigInteger orderNumId  = addNumbering(true);
        BigInteger bulletNumId  = addNumbering(false);

        // 有序列表
        for (Object obj : itemList) {
            XWPFParagraph para = cell.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);

            // 设置编号类型
            if ("order".equalsIgnoreCase(type)) {
                para.setNumID(orderNumId);
            } else {
                para.setNumID(bulletNumId);
            }

            XWPFRun run = para.createRun();
            if (obj instanceof String str) {
                run.setText(str);
            } else if (obj instanceof WordList nestedList) {
                // 嵌套列表（递归调用）
                addTableList(nestedList, cell);
            } else {
                run.setText("未知列表项类型: " + obj.getClass().getSimpleName());
            }
        }
    }

    // 添加图片
    public void addImage(WordImage wordImage) {

        String fileName = wordImage.getFileName();
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run = paragraph.createRun();
        run.addBreak(); // 换行
        try {
            // 图片类型：可根据后缀判断
            int format = XWPFDocument.PICTURE_TYPE_PNG;
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                format = XWPFDocument.PICTURE_TYPE_JPEG;
            } else if (fileName.endsWith(".gif")) {
                format = XWPFDocument.PICTURE_TYPE_GIF;
            }

            String path = wordImage.getPath();
            InputStream imageStream;
            if (wordImage.getPath().startsWith("http")) {
                // 网络图片
                URL url = new URL(wordImage.getPath());
                imageStream = url.openStream();
            } else {
                // 本地图片
                imageStream = new FileInputStream(wordImage.getPath());
            }


            BufferedImage bufferedImage = ImageIO.read(imageStream);

        // 原始宽高（像素）
            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();

            // Word 页面可用宽度（单位：EMU），通常 A4 宽度约为 21cm，减去边距大概 15cm
            int maxWidthEMU = Units.toEMU(450); // 大概是 15cm

            // 若图片宽度超出限制，则等比缩放
            if (Units.toEMU(imageWidth) > maxWidthEMU) {
                double ratio = (double) maxWidthEMU / Units.toEMU(imageWidth);
                imageWidth = (int) (imageWidth * ratio);
                imageHeight = (int) (imageHeight * ratio);
            }

            // 再从 bufferedImage 创建 InputStream 重新插入
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            InputStream finalStream = new ByteArrayInputStream(baos.toByteArray());

            run.addPicture(finalStream, format, fileName,
                    Units.toEMU(imageWidth), Units.toEMU(imageHeight));

            finalStream.close();
            baos.close();

            new File(path).delete();

        }catch (Exception e) {
            run.setText("图片添加失败："+e.getMessage());
        }
    }

    // 添加代码块
    public void addCodeBlock(WordCodeBlock wordCodeBlock) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBefore(200);
        paragraph.setSpacingAfter(200);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        List<Object> codeLines = wordCodeBlock.getList();
        for (Object codeLine : codeLines) {
            codeBlock(paragraph, codeLine);
        }
    }

    private void codeBlock(XWPFParagraph paragraph,Object o ){
        if (o instanceof WordParagraph wordParagraph){
            List<Object> list = wordParagraph.getList();
            for (Object object : list) {
                if (object instanceof WordText || object instanceof String){
                    XWPFRun run = paragraph.createRun();
                    run.setFontFamily("Courier New"); // 等宽字体
                    run.setFontSize(10);
                    run.setText(object.toString());
                    run.setTextHighlightColor("lightGray");
                    run.addBreak();
                }else {
                    codeBlock(paragraph,object);
                }
            }
        }
    }

    // 保存 Word 文档
    public void save(String path) throws IOException {
        try (FileOutputStream out = new FileOutputStream(path)) {
            doc.write(out);
        }
        doc.close();
    }

}














