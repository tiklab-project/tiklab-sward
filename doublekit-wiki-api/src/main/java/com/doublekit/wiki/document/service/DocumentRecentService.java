package com.doublekit.wiki.document.service;


import com.doublekit.core.page.Pagination;
import com.doublekit.wiki.document.model.DocumentRecent;
import com.doublekit.wiki.document.model.DocumentRecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentRecentService
*/
public interface DocumentRecentService {

    /**
    * 创建
    * @param documentRecent
    * @return
    */
    String createDocumentRecent(@NotNull @Valid DocumentRecent documentRecent);

    /**
    * 更新
    * @param documentRecent
    */
    void updateDocumentRecent(@NotNull @Valid DocumentRecent documentRecent);

    /**
    * 删除
    * @param id
    */
    void deleteDocumentRecent(@NotNull String id);

    DocumentRecent findOne(@NotNull String id);

    List<DocumentRecent> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    DocumentRecent findDocumentRecent(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<DocumentRecent> findAllDocumentRecent();

    /**
    * 查询列表
    * @param documentRecentQuery
    * @return
    */
    List<DocumentRecent> findDocumentRecentList(DocumentRecentQuery documentRecentQuery);

    /**
     * 按分页查询
     *
     * @param documentRecentQuery
     * @return
     */
    Pagination<DocumentRecent> findDocumentRecentPage(DocumentRecentQuery documentRecentQuery);

}