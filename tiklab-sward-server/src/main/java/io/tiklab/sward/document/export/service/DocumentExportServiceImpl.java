package io.tiklab.sward.document.export.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.document.document.service.DocumentService;
import io.tiklab.sward.document.export.builder.DocBuilder;
import io.tiklab.sward.document.export.builder.DocToWordBuilder;
import io.tiklab.sward.document.export.model.DocExport;
import io.tiklab.sward.node.node.model.Node;
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
            Node node = document.getNode();
            String rawName = node.getName();
            String name = rawName.replaceAll("[\\\\/:*?\"<>|]", "_")+".docx";
            String s = dataHome + "/export/";
            new File(s).mkdirs();

            List<Object> builder = DocBuilder.builder(details);
            String path = s + name;
            docToWordBuilder.docToWordBuilderList(builder,path);

            // byte[] bytes = Files.readAllBytes(new File(path).toPath());
            return new DocExport(name,path);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("导出失败");
        }
    }

    @Override
    public DocExport exportPdf(String docId) {
        return new DocExport();
    }


}
