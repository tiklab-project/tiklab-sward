package io.tiklab.sward.document.export.service;

import io.tiklab.sward.document.export.model.DocExport;

public interface DocumentExportService {

    /**
     * 文档导出为word
     * @param docId 文档Id
     * @return 导出的word地址
     */
    DocExport exportWord(String docId);


    /**
     * 文档导出为pdf
     * @param docId 文档Id
     * @return 导出的pdf地址
     */
    DocExport exportPdf(String docId);



}
