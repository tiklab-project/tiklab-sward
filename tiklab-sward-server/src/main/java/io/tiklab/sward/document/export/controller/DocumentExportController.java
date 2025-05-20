package io.tiklab.sward.document.export.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.sward.annotation.EnableSwardStatistics;
import io.tiklab.sward.document.export.model.DocExport;
import io.tiklab.sward.document.export.service.DocumentExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/documentExport")
@Api(name = "DocumentExportController",desc = "文档导出管理")
public class DocumentExportController {


    @Autowired
    DocumentExportService documentExportService;


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

}












