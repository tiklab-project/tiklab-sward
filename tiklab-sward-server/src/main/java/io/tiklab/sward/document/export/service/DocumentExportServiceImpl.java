package io.tiklab.sward.document.export.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.document.document.service.DocumentService;
import io.tiklab.sward.document.export.builder.DocBuilder;
import io.tiklab.sward.document.export.builder.html.DocToHtmlBuilder;
import io.tiklab.sward.document.export.builder.word.DocToWordBuilder;
import io.tiklab.sward.document.export.model.DocExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentExportServiceImpl implements DocumentExportService {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocToWordBuilder docToWordBuilder;

    @Autowired
    DocToHtmlBuilder docToHtmlBuilder;

    @Value("${DATA_HOME}")
    private String dataHome;

    @Override
    public DocExport exportWord(String docId) {

        WikiDocument document = documentService.findDocument(docId);
        if (Objects.isNull(document)){
            throw new ApplicationException("文档不存在!");
        }
        try {

            String details = document.getDetails();
            String name = findFileName(document.getNode().getName());
            String savePath = dataHome + "/export/";
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            List<Object> builder = DocBuilder.builder(details);
            String path = savePath + name;
            docToWordBuilder.docToWordBuilderList(builder,path);
            return new DocExport(name,path);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("导出失败");
        }
    }

    @Override
    public DocExport exportPdf(String docId) {
        WikiDocument document = documentService.findDocument(docId);
        if (Objects.isNull(document)){
            throw new ApplicationException("文档不存在!");
        }
        return new DocExport();
    }


    public DocExport exportHtml(String docId) {
        WikiDocument document = documentService.findDocument(docId);
        if (Objects.isNull(document)){
            throw new ApplicationException("文档不存在!");
        }
        try {

            String details = document.getDetails();
            String name = findFileName(document.getNode().getName());
            String savePath = dataHome + "/export/";
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            List<Object> builder = DocBuilder.builder(details);
            String path = savePath + name;
            docToHtmlBuilder.docToHtmlBuilderList(builder,path);
            return new DocExport(name,path);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("导出失败");
        }
    }


    private String  findFileName(String rawName){
        return rawName.replaceAll("[\\\\/:*?\"<>|]", "_")+".docx";
    }

}
