package io.tiklab.kanass.document.service;

import io.tiklab.kanass.document.model.Document;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentService
*/
@JoinProvider(model = Document.class)
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
    Document findOne(@NotNull String id);

    @FindList
    List<Document> findList(List<String> idList);

    /**
    * 查找
    * @param id
     * 分享出去后调用的查询接口  （跳过ticket验证）
    * @return
    */
    Document findDocument(@NotNull String id);

    /**
     * 查找
     * @param id
     * @return
     */
     Document findDocumentById(@NotNull String id);

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