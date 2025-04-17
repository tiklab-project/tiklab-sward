package io.tiklab.sward.document.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.model.DocumentVersion;
import io.tiklab.sward.document.model.DocumentVersionQuery;
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
@JoinProvider(model = DocumentVersion.class)
public interface DocumentVersionService {

    /**
    * 创建
    * @param version
    * @return
    */
    String createDocumentVersion(@NotNull @Valid DocumentVersion version);

    /**
    * 更新
    * @param version
    */
    void updateDocumentVersion(@NotNull @Valid DocumentVersion version);

    /**
    * 删除
    * @param id
    */
    void deleteDocumentVersion(@NotNull String id);

    /**
     * 回滚版本
     * @param id
     */
    void callbackDocument(String id);


    @FindOne
    DocumentVersion findDocumentVersion(@NotNull String id);


    @FindList
    List<DocumentVersion> findDocumentVersionList(List<String> idList);

    /**
     * 查询列表
     * @param documentQuery
     * @return
     */
    List<DocumentVersion> findDocumentVersionList(DocumentVersionQuery documentQuery);



    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<DocumentVersion> findAllDocumentVersion();


    /**
    * 按分页查询
    * @param documentQuery
    * @return
    */
    Pagination<DocumentVersion> findDocumentVersionPage(DocumentVersionQuery documentQuery);

}