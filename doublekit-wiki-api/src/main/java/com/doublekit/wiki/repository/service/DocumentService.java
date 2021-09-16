package com.doublekit.wiki.repository.service;

import com.doublekit.common.Pagination;

import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.Provider;
import com.doublekit.wiki.repository.model.Document;
import com.doublekit.wiki.repository.model.DocumentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentService
*/
@Provider(model = Document.class)
public interface DocumentService {

    /**
    * 创建
    * @param document
    * @return
    */
    String createDocument(@NotNull @Valid Document document);

    /**
    * 更新
    * @param document
    */
    void updateDocument(@NotNull @Valid Document document);

    /**
    * 删除
    * @param id
    */
    void deleteDocument(@NotNull String id);

    @FindOne
    Document findOne(@NotNull String id,String type);

    @FindList
    List<Document> findList(List<String> idList);

    /**
    * 查找
    * @param id
     * @param type  分享出去后调用的查询接口  （跳过ticket验证）
    * @return
    */
    Document findDocument(@NotNull String id,String type);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Document> findAllDocument();

    /**
    * 查询列表
    * @param documentQuery
    * @return
    */
    List<Document> findDocumentList(DocumentQuery documentQuery);

    /**
    * 按分页查询
    * @param documentQuery
    * @return
    */
    Pagination<Document> findDocumentPage(DocumentQuery documentQuery);

}