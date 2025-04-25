package io.tiklab.sward.document.document.service;

import io.tiklab.sward.support.model.RecentQuery;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.document.document.model.DocumentQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

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


    String createOneDocument(@NotNull @Valid WikiDocument wikiDocument);

    String createConfluDocument(@NotNull @Valid WikiDocument wikiDocument);

    Integer getBrotherNum(String repositoryId, String categoryId);

    Integer getMaxSort(String repositoryId, String categoryId);

    /**
    * 更新文档内容与节点
    * @param wikiDocument
    */
    void updateDocument(@NotNull @Valid WikiDocument wikiDocument);


    /**
     * 更新
     * @param wikiDocument
     */
    void updateOneDocument(@NotNull @Valid WikiDocument wikiDocument);


    /**
    * 删除文档，以及关联关系
    * @param id
    */
    void deleteDocument(@NotNull String id);

    /**
     * 删除文档
     * @param id 文档ID
     */
    void deleteOneDocument(@NotNull String id);


    void deleteDocumentCondition(@NotNull @Valid DocumentQuery documentQuery);


    void batchDeleteDocument(DocumentQuery documentQuery);

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