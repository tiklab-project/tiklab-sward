package io.tiklab.sward.document.document.controller;

import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.DfsObject;
import io.tiklab.dfs.common.model.object.DfsObjectMeta;
import io.tiklab.dfs.common.model.object.FindRequest;
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
import org.springframework.beans.factory.annotation.Value;
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
//@Api(name = "DocumentController",desc = "文档管理")
public class DocumentFileController {

    @Autowired(required = false)
    DfsClient dfsClient;

    @Value("${DATA_HOME}")
    private String dataHome;

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

        DfsObjectMeta dfsObjectMeta = dfsClient.findMeta(objectId);
        String fileName = dfsObjectMeta.getFileName();
        String filePath = dataHome + "/download/"+fileName;

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        FindRequest findRequest = new FindRequest(objectId,fileOutputStream);
         dfsClient.find(findRequest);


        String utf8FileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename="+utf8FileName);

        //输出文件

        if (fileName.endsWith(".pptx") || fileName.endsWith(".ppt")) {
            fileName = fileName.replace(".pptx", ".pdf").replace(".ppt", ".pdf");
            utf8FileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

            response.setHeader("Content-Disposition", "attachment;filename="+utf8FileName);

            InputStream inputStream = new FileInputStream(filePath);

            // 设置 PPT 流可重置（用于 XMLSlideShow 失败后的 HSLFSlideShow 回退解析）
            if (!inputStream.markSupported()) {
                inputStream = new BufferedInputStream(inputStream);
            }
            inputStream.mark(Integer.MAX_VALUE); // mark 支持 reset

            ServletOutputStream os = response.getOutputStream();

            // 直接生成 PDF 到浏览器
            convertPPToPDF(inputStream, os);

            os.flush();
            os.close();
            inputStream.close();

        } else {

            ServletOutputStream os = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            os.flush();
            os.close();
            fileInputStream.close();
        }
    }

    public static void convertPPToPDF(InputStream pptInputStream, OutputStream outputStream) throws Exception {
        SlideShow<?, ?> ppt;
        try {
            ppt = new XMLSlideShow(pptInputStream);
        } catch (Exception e) {
            pptInputStream.reset(); // 注意 reset 前需要 mark
            ppt = new HSLFSlideShow(pptInputStream);
        }

        Dimension pgSize = ppt.getPageSize();
        PDDocument pdfDoc = new PDDocument();

        for (Slide<?, ?> slide : ppt.getSlides()) {
            BufferedImage img = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.WHITE);
            graphics.fill(new Rectangle(0, 0, pgSize.width, pgSize.height));
            slide.draw(graphics);
            graphics.dispose();

            PDPage page = new PDPage(new PDRectangle(pgSize.width, pgSize.height));
            pdfDoc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page);
            PDImageXObject pdfImage = PDImageXObject.createFromByteArray(pdfDoc, toByteArray(img), "ppt-slide");
            contentStream.drawImage(pdfImage, 0, 0, pgSize.width, pgSize.height);
            contentStream.close();
        }

        // 直接写入输出流
        pdfDoc.save(outputStream);
        pdfDoc.close();
        ppt.close();
    }

    // 图片转换为字节数组
    private static byte[] toByteArray(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        return baos.toByteArray();
    }

}
