package io.tiklab.sward.document.export.builder.word;

import io.tiklab.sward.document.export.model.word.*;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.*;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void addHead(WordHead wordHead) {
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

        List<HeadTextSegment> segments = parseRichText(wordHead.getText());

        for (HeadTextSegment seg : segments) {
            XWPFRun run = para.createRun();
            run.setBold(true);
            run.setFontSize(wordHead.getSize());
            if (seg.getColor() != null) {
                run.setColor(seg.getColor()); // 设置字体颜色
            }

            if (seg.getBackground() != null) {
                // 获取或创建 CTRPr（Run 的属性节点）
                // 获取 CTR 对象
                CTR ctr = run.getCTR();
                // 获取/创建属性 rPr
                CTRPr rPr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                // 获取/创建 shading 节点
                CTShd shd = rPr.addNewShd();

                shd.setFill(seg.getBackground()); // 设置背景填充色
                shd.setVal(STShd.CLEAR);     // 一般设为 CLEAR 表示正常显示
            }

            if (StringUtils.isEmpty(seg.getText())){
                run.setText(" ");
            }else{
                run.setText(seg.getText());
            }

        }
    }

    private List<HeadTextSegment> parseRichText(String htmlLike) {
        List<HeadTextSegment> list = new ArrayList<>();

        if (htmlLike == null || htmlLike.isEmpty()) {
            return list;
        }

        // 支持单引号或双引号样式： <span style='...'>或<span style="...">
        Pattern p = Pattern.compile("<span\\s+style\\s*=\\s*(['\"])(.*?)\\1\\s*>(.*?)</span>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(htmlLike);

        int lastEnd = 0;
        while (m.find()) {
            // 添加上一个匹配之前的普通文本（如果有）
            if (m.start() > lastEnd) {
                String normalText = htmlLike.substring(lastEnd, m.start());
                if (!normalText.isEmpty()) {
                    HeadTextSegment t = new HeadTextSegment();
                    t.setText(normalText);
                    list.add(t);
                }
            }

            String style = m.group(2); // 样式内容，如 color:red;background:yellow
            String inner = m.group(3); // span 内部文本

            HeadTextSegment seg = new HeadTextSegment();
            seg.setText(inner == null ? "" : inner);

            // 提取 color/background（简单解析 key:value; 的形式）
            String[] parts = style.split(";");
            for (String part : parts) {
                String ptrim = part.trim();
                if (ptrim.isEmpty()) continue;
                String[] kv = ptrim.split(":", 2);
                if (kv.length != 2) continue;
                String key = kv[0].trim().toLowerCase();
                String val = kv[1].trim();
                // 去掉可能的引号和#号
                if (val.startsWith("\"") && val.endsWith("\"") || val.startsWith("'") && val.endsWith("'")) {
                    val = val.substring(1, val.length() - 1);
                }
                val = val.replace("#", "");
                switch (key) {
                    case "color" -> seg.setColor(val);
                    case "background", "background-color" -> seg.setBackground(val);
                    case "font_size" -> seg.setFontSize(val);
                    case "italic" -> seg.setItalic(Boolean.valueOf(val));
                    case "strike" -> seg.setStrike(Boolean.valueOf(val));
                    case "bold" -> seg.setBold(Boolean.valueOf(val));
                    case "underline" -> seg.setUnderline(Boolean.valueOf(val));
                    case "code_line" -> seg.setCodeLine(Boolean.valueOf(val));
                }
            }

            list.add(seg);
            lastEnd = m.end();
        }

        // 添加剩余文本（只有在 lastEnd < length 时才处理）
        if (lastEnd < htmlLike.length()) {
            String tail = htmlLike.substring(lastEnd);
            if (!tail.isEmpty()) {
                HeadTextSegment t = new HeadTextSegment();
                t.setText(tail);
                list.add(t);
            }
        }
        return list;
    }

    // 添加普通文本
    public void addText(WordText wordText) {
        XWPFParagraph para = doc.createParagraph();
        addHtmlText(para,wordText);
    }

    public void addLink(WordLink wordLink) {
        String text = wordLink.getText();
        // 创建超链接 Run
        XWPFParagraph para = doc.createParagraph();

        List<HeadTextSegment> segments = parseRichText(text);

        for (HeadTextSegment seg : segments) {
            XWPFRun run = para.createHyperlinkRun(wordLink.getUrl());
            String replace = seg.getFontSize().replace("px", "");
            run.setFontSize(Integer.parseInt(replace));
            if (seg.getColor() != null) {
                run.setColor(seg.getColor()); // 设置字体颜色
            }

            if (seg.getBold() != null) {
                run.setBold(seg.getBold());
            }

            if (seg.getItalic() != null) {
                run.setItalic(seg.getItalic());
            }

            if (seg.getUnderline() != null) {
                if (seg.getUnderline()) {
                    run.setUnderline(UnderlinePatterns.SINGLE);
                }else{
                    run.setUnderline(UnderlinePatterns.NONE);
                }
            }

            if (seg.getStrike() != null) {
                run.setStrike(seg.getStrike());
            }


            if (seg.getBackground() != null) {
                // 获取 CTR 对象
                CTR ctr = run.getCTR();
                // 获取/创建属性 rPr
                CTRPr rPr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                // 获取/创建 shading 节点
                CTShd shd = rPr.addNewShd();

                shd.setFill(seg.getBackground()); // 设置背景填充色
                shd.setVal(STShd.CLEAR);     // 一般设为 CLEAR 表示正常显示
            }

            if (StringUtils.isEmpty(seg.getText())){
                run.setText("");
            }else{
                run.setText(seg.getText());
            }

        }



    }

    public void addCheckListItem(WordCheckListItem checkListItem) {

        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();

        // 设置字体（确保显示符号）
        run.setFontFamily("Wingdings 2");

        // 选择显示空框或勾选框
        String checkbox = checkListItem.isChecked() ? "☑" : "☐";
        run.setText(checkbox); // 复选框符号 + 空格

        // 设置普通文本标签（显示中文或英文）
        addHtmlText(para,new WordText(checkListItem.getText()));
    }

    public void addAttachment(WordAttachment wordAttachment) {
        try {
            Path path = Paths.get(wordAttachment.getFilePath());
            String fileName = wordAttachment.getFileName();
            OPCPackage opcPackage = doc.getPackage();

            String safeFileName = fileName.replaceAll("[^a-zA-Z0-9_.-]", "_");
            // 创建 PartName
            PackagePartName partName = PackagingURIHelper.createPartName("/word/embeddings/" + safeFileName);
            // 创建 Part
            String contentType = Files.probeContentType(path);
            if (contentType == null) contentType = "application/octet-stream";
            PackagePart part = opcPackage.createPart(partName, contentType);
            try (InputStream in = Files.newInputStream(path);
                 OutputStream out = part.getOutputStream()) {
                 in.transferTo(out);
            }

            // 添加内部关系
            PackageRelationship rel = doc.getPackagePart().addRelationship(
                    partName, TargetMode.INTERNAL,
                    "http://schemas.openxmlformats.org/officeDocument/2006/relationships/package"
            );
            String relId = rel.getId();

            // 插入可点击文字
            XWPFParagraph para = doc.createParagraph();
            XWPFHyperlinkRun hyperlinkRun = para.createHyperlinkRun(relId);
            hyperlinkRun.setText("附件：" + fileName);
            hyperlinkRun.setColor("0000FF"); // 蓝色
            hyperlinkRun.setUnderline(UnderlinePatterns.SINGLE);

        } catch (ClientAbortException e) {
            // 客户端断开，不处理或记录即可
            logger.warn("客户端已断开连接", e);
        } catch (Exception e) {
            e.printStackTrace();
            addText(new WordText("无法加载附件：" + wordAttachment.getFileName()));
        }
    }

    private void addHtmlText(XWPFParagraph para,WordText wordText){
        List<HeadTextSegment> segments = parseRichText(wordText.getText());

        for (HeadTextSegment seg : segments) {
            XWPFRun run = para.createRun();
            String replace;
            if (seg.getFontSize() != null) {
                replace = seg.getFontSize().replace("px", "");
            }else{
                replace = wordText.getSize().replace("px", "");
            }
            run.setFontSize(Integer.parseInt(replace));
            if (seg.getColor() != null) {
                run.setColor(seg.getColor()); // 设置字体颜色
            }

            if (seg.getBold() != null) {
                run.setBold(seg.getBold());
            }

            if (seg.getItalic() != null) {
                run.setItalic(seg.getItalic());
            }

            if (seg.getUnderline() != null) {
                if (seg.getUnderline()) {
                    run.setUnderline(UnderlinePatterns.SINGLE);
                }else{
                    run.setUnderline(UnderlinePatterns.NONE);
                }
            }

            if (seg.getStrike() != null) {
                run.setStrike(seg.getStrike());
            }


            if (seg.getBackground() != null) {
                // 获取 CTR 对象
                CTR ctr = run.getCTR();
                // 获取/创建属性 rPr
                CTRPr rPr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                // 获取/创建 shading 节点
                CTShd shd = rPr.addNewShd();

                shd.setFill(seg.getBackground()); // 设置背景填充色
                shd.setVal(STShd.CLEAR);     // 一般设为 CLEAR 表示正常显示
            }

            // 代码行
            if (seg.getCodeLine() != null) {
                if (seg.getCodeLine()) {
                    run.setFontFamily("Consolas");
                    CTR ctr = run.getCTR();
                    // 获取/创建属性 rPr
                    CTRPr rPr = ctr.isSetRPr() ? ctr.getRPr() : ctr.addNewRPr();
                    // 获取/创建 shading 节点
                    CTShd shd = rPr.addNewShd();

                    shd.setFill(seg.getBackground()); // 设置背景填充色
                    shd.setFill("EDEDED");

                    seg.setText(" "+seg.getText()+" ");
                }
            }


            if (StringUtils.isEmpty(seg.getText())){
                run.setText(" ");
            }else{
                run.setText(seg.getText());
            }

        }
    }

    public void addParagraph(WordParagraph wordParagraph){
        XWPFParagraph para = doc.createParagraph();
        for (Object object : wordParagraph.getList()) {
            if (object instanceof  WordText wordText) {
                addHtmlText(para,wordText);
            }
            if (object instanceof  WordLink wordLink) {
                addLink(wordLink);
            }
            if (object instanceof  WordImage wordImage) {
                addImage(wordImage);
            }
            if (object instanceof  WordAttachment wordAttachment) {
                addAttachment(wordAttachment);
            }
            if (object instanceof  String s) {
                XWPFRun run = para.createRun();
                run.setText(s);
                run.setFontSize(10);
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
            } else if ( item instanceof WordText wordText) {
                // 处理文本内容
                addHtmlText(para,wordText);
            } else if (item instanceof String s) {
                // 处理文本内容
                XWPFRun run = para.createRun();
                run.setText(s);
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
            } else if ( item instanceof WordText wordText) {
                // 处理文本内容
                addHtmlText(para,wordText);
            } else if (item instanceof String s) {
                // 处理文本内容
                addHtmlText(para,new WordText(s));
            }else {
                logger.error("未知的有序列表属性:{}", item.getClass().getSimpleName());
            }
        }
    }

    public void addList(List<?> list,XWPFParagraph para){
        for (Object object : list) {
            if ( object instanceof WordText wordText) {
                addHtmlText(para,wordText);
            } else if (object instanceof String s) {
                // XWPFRun run = para.createRun();
                addHtmlText(para,new WordText(s));
            } else if (object instanceof List<?> lists) {
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

                addHtmlText(para,wordText);
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

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            // 原始宽高（像素）
            int imageWidth = wordImage.getWidth();
            int imageHeight = wordImage.getHeight();

            // 等比补全逻辑
            if (imageWidth <= 0 && imageHeight <= 0) {
                // 未指定宽高，使用原图尺寸
                imageWidth = width;
                imageHeight = height;
            } else if (imageWidth > 0 && imageHeight <= 0) {
                // 只指定宽度
                double ratio = (double) imageWidth / width;
                imageHeight = (int) (height * ratio);
            } else if (imageWidth <= 0) {
                // 只指定高度
                double ratio = (double) imageHeight / height;
                imageWidth = (int) (width * ratio);
            }


            // Word 页面可用宽度（单位：EMU），通常 A4 宽度约为 21cm，减去边距大概 15cm
            int maxWidthEMU = Units.toEMU(400); // 大概是 15cm

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
        XWPFTable table = doc.createTable(1, 1);
        XWPFTableCell cell = table.getRow(0).getCell(0);
        XWPFParagraph para = cell.getParagraphs().get(0);
        XWPFRun run = para.createRun();
        run.setFontFamily("Consolas");
        run.setFontSize(10);

        String code = wordCodeBlock.getText(); // 多行代码
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            run.setText(lines[i]);
            if (i < lines.length - 1) {
                run.addBreak(); // 手动换行
            }
        }
        cell.setColor("EDEDED");
    }


    // 添加引用
    public void addBlockQuote(WordBlockQuote wordBlockQuote) {
        XWPFParagraph para = doc.createParagraph();

        // 段落样式设置：左缩进 + 左边框 + 背景灰
        para.setIndentationLeft(100);
        para.setSpacingBefore(100);
        para.setSpacingAfter(100);

        // 左边框加粗效果（通过CTPPr手动控制）
        CTPPr pPr = para.getCTP().isSetPPr() ? para.getCTP().getPPr() : para.getCTP().addNewPPr();
        CTPBdr borders = pPr.isSetPBdr() ? pPr.getPBdr() : pPr.addNewPBdr();
        CTBorder left = borders.isSetLeft() ? borders.getLeft() : borders.addNewLeft();
        left.setVal(STBorder.SINGLE);
        left.setSz(BigInteger.valueOf(40)); // 加粗线条
        left.setColor("999999"); // 灰色边框

        // 整段灰色背景
        CTShd shd = pPr.isSetShd() ? pPr.getShd() : pPr.addNewShd();
        shd.setVal(STShd.CLEAR);
        shd.setColor("auto");
        shd.setFill("F5F5F5"); // 整段背景灰

        // 内容
        XWPFRun run = para.createRun();
        String[] lines = wordBlockQuote.getText().split("\n");
        for (int i = 0; i < lines.length; i++) {
            run.setText(lines[i]);
            if (i < lines.length - 1) {
                run.addBreak();
            }
        }

        run.setFontSize(11);
        run.setColor("555555");
        run.setItalic(true);
    }

    // 保存 Word 文档
    public void save(String path) throws IOException {
        try (FileOutputStream out = new FileOutputStream(path)) {
            doc.write(out);
        }
        doc.close();
    }

}














