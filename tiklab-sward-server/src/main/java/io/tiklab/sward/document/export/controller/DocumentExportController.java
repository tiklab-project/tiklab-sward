package io.tiklab.sward.document.export.controller;

import io.tiklab.sward.annotation.EnableSwardStatistics;
import io.tiklab.sward.document.export.model.DocExport;
import io.tiklab.sward.document.export.service.DocumentExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/documentExport")
// @Api(name = "DocumentExportController",desc = "文档导出管理")
public class DocumentExportController {


    @Autowired
    DocumentExportService documentExportService;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DocumentExportController.class);


    @RequestMapping(path="/exportPdf",method = RequestMethod.POST)
    public void exportPdf(@NotNull String docId, HttpServletResponse response) throws IOException{

        DocExport export = documentExportService.exportWord(docId);
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setCharacterEncoding("UTF-8");
        String encodedFileName = URLEncoder.encode(export.getFileName(), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment; filename=\""+encodedFileName+"\"");
        response.setHeader("fileName", encodedFileName);
        response.setHeader("Access-Control-Expose-Headers", "fileName");

        try (InputStream in = new FileInputStream(export.getPath());
             OutputStream out = response.getOutputStream()) {
            in.transferTo(out); // Java 9+ 推荐用法，或用 Apache IOUtils.copy()
            out.flush();
        }
    }

    @EnableSwardStatistics(type = "export")
    @RequestMapping(path="/exportWord",method = RequestMethod.GET)
    public void exportWord(@NotNull String docId, HttpServletResponse response) throws IOException {
        DocExport export = documentExportService.exportWord(docId);

        String fileName = export.getFileName();
        String filePath = export.getPath();

        File file = new File(filePath);

        //浏览器用utf8来解析返回的数据
        response.setContentType("text/html;charset=utf-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "filename");
        String utf8FileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename="+utf8FileName);
        response.setHeader("filename", utf8FileName);

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentLength((int) file.length());

        int buf_size = 8192;
        byte[] buffer = new byte[buf_size];
        int len;
        while (-1 != (len = in.read(buffer, 0, buf_size))) {
            outputStream.write(buffer,0,len);
        }
        in.close();
        outputStream.close();
    }

}












