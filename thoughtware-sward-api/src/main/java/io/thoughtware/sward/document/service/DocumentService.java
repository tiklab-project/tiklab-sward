package io.thoughtware.sward.document.service;

import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

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

    Integer getMaxSort(String repositoryId, String categoryId);
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
    void deleteDocumentCondition(@NotNull @Valid DocumentQuery documentQuery );
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

    void addSortInCategory(String wikiCategoryId, Integer sort);

    void addSortInRepository(String wikiCategoryId, Integer sort);

    void reduceSortInCategory(String wikiCategoryId, Integer sort);

    void reduceSortInRepository(String wikiCategoryId, Integer sort);

    List<WikiDocument> findAllChildrenDocumentList(String categoryId);
}