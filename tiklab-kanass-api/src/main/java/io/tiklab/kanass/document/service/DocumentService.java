package io.tiklab.kanass.document.service;

import io.tiklab.kanass.support.model.RecentQuery;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* DocumentService
*/
@JoinProvider(model = WikiDocument.class)
public interface DocumentService {

    /**
    * 创建
    * @param wikiDocument
    * @return
    */
    String createDocument(@NotNull @Valid WikiDocument wikiDocument);
    Integer getBrotherNum(String repositoryId, String categoryId);
    /**
    * 更新
    * @param wikiDocument
    */
    void updateDocument(@NotNull @Valid WikiDocument wikiDocument);
    void updateDocumentInit(@NotNull @Valid WikiDocument wikiDocument);
    /**
    * 删除
    * @param id
    */
    void deleteDocument(@NotNull String id);

    void deleteDocumentAndSort(@NotNull @Valid WikiDocument wikiDocument);
    @FindOne
    WikiDocument findOne(@NotNull String id);

    @FindList
    List<WikiDocument> findList(List<String> idList);

    /**
    * 查找
    * @param id
     * 分享出去后调用的查询接口  （跳过ticket验证）
    * @return
    */
    WikiDocument findDocument(@NotNull String id);

    /**
     * 查找
     * @param id
     * @return
     */
     WikiDocument findDocumentById(@NotNull String id);
     List<Map<String, Object>> findDocumentByRepositoryIds(@NotNull String repositoryIds);
    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WikiDocument> findAllDocument();

    /**
    * 查询列表
    * @param documentQuery
    * @return
    */
    List<WikiDocument> findDocumentList(DocumentQuery documentQuery);

    /**
     * 查询列表
     * @param documentQuery
     * @return
     */
    Integer findDocumentCount(DocumentQuery documentQuery);
    /**
    * 按分页查询
    * @param documentQuery
    * @return
    */
    Pagination<WikiDocument> findDocumentPage(DocumentQuery documentQuery);


    List<WikiDocument> findDocuementByKeyWork(String keyWork);

    List<WikiDocument> findRecentDocumentList(RecentQuery recentQuery);

}