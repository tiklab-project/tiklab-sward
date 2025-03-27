package io.tiklab.sward.document.controller;

import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.object.model.DfsObject;
import io.tiklab.dfs.common.object.model.DfsObjectMeta;
import io.tiklab.postin.annotation.Api;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
@Api(name = "DocumentController",desc = "文档管理")
public class DocumentFileController {

    @Autowired(required = false)
    DfsClient dfsClient;

    @RequestMapping(path="/download/file/*",method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");
        //获取文件名称
        String requestUrl = request.getRequestURI();
        String objectId = requestUrl.replaceAll("/document/download/file/","");
        //构造DfsClient
        DfsObject dfsObject = dfsClient.find(objectId);
        DfsObjectMeta dfsObjectMeta = dfsObject.getObjectMeta();

        //读取文件
        String fileName = dfsObjectMeta.getFileName();

        String utf8FileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename="+utf8FileName);

        //输出文件
        byte[] content = dfsObject.getContent();
        if (fileName.endsWith(".pptx") || fileName.endsWith(".ppt")) {
            fileName = fileName.replace(".pptx", ".pdf").replace(".ppt", ".pdf");
            utf8FileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            response.setHeader("Content-Disposition", "attachment;filename="+utf8FileName);

            InputStream inputStream = new ByteArrayInputStream(content);

            byte[] pdfBytes = convertPPToPDF(inputStream);

            ServletOutputStream os = response.getOutputStream();

            os.write(pdfBytes);
            os.flush();
            os.close();
        } else {
            ServletOutputStream os = response.getOutputStream();
            os.write(content);
            os.flush();
            os.close();
        }
    }

    public static byte[] convertPPTToPDF(InputStream pptInputStream) throws Exception {

        XMLSlideShow ppt = new XMLSlideShow(pptInputStream);
        Dimension pgSize = ppt.getPageSize();

        // 创建 PDF
        PDDocument pdfDoc = new PDDocument();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

        for (XSLFSlide slide : ppt.getSlides()) {
            BufferedImage img = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fill(new Rectangle(0, 0, pgSize.width, pgSize.height));

            // 渲染 PPT 幻灯片
            slide.draw(graphics);
            graphics.dispose();

            // 添加到 PDF
            PDPage page = new PDPage(new PDRectangle(pgSize.width, pgSize.height));
            pdfDoc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page);
            PDImageXObject pdfImage = PDImageXObject.createFromByteArray(pdfDoc, toByteArray(img), "ppt-slide");
            contentStream.drawImage(pdfImage, 0, 0, pgSize.width, pgSize.height);
            contentStream.close();
        }

        // 保存到字节数组
        pdfDoc.save(pdfOutputStream);
        pdfDoc.close();
        ppt.close();

        return pdfOutputStream.toByteArray();
    }

    public static byte[] convertPPToPDF(InputStream pptInputStream) throws Exception {
        SlideShow<?, ?> ppt;
        try {
            // 尝试解析为 PPTX（Office Open XML）
            ppt = new XMLSlideShow(pptInputStream);
        } catch (Exception e) {
            // 解析失败，可能是 PPT（OLE2 二进制格式），尝试用 HSLF 解析
            pptInputStream.reset();
            ppt = new HSLFSlideShow(pptInputStream);
        }

        Dimension pgSize = ppt.getPageSize();
        PDDocument pdfDoc = new PDDocument();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

        // 遍历 PPT 幻灯片
        for (Slide<?, ?> slide : ppt.getSlides()) {
            BufferedImage img = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fill(new Rectangle(0, 0, pgSize.width, pgSize.height));

            // 渲染 PPT 幻灯片
            slide.draw(graphics);
            graphics.dispose();

            // 添加到 PDF
            PDPage page = new PDPage(new PDRectangle(pgSize.width, pgSize.height));
            pdfDoc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page);
            PDImageXObject pdfImage = PDImageXObject.createFromByteArray(pdfDoc, toByteArray(img), "ppt-slide");
            contentStream.drawImage(pdfImage, 0, 0, pgSize.width, pgSize.height);
            contentStream.close();
        }

        // 保存到 byte[]
        pdfDoc.save(pdfOutputStream);
        pdfDoc.close();
        ppt.close();

        return pdfOutputStream.toByteArray();
    }


    // 图片转换为字节数组
    private static byte[] toByteArray(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }

}
