package io.thoughtware.sward.document.service;

import io.thoughtware.sward.document.model.DocumentTemplate;
import io.thoughtware.sward.document.model.DocumentTemplateQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentTemplateService
*/
@JoinProvider(model = DocumentTemplate.class)
public interface DocumentTemplateService {

    /**
    * 创建
    * @param documentTemplate
    * @return
    */
    String createDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate);

    /**
    * 更新
    * @param documentTemplate
    */
    void updateDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate);

    /**
    * 删除
    * @param id
    */
    void deleteDocumentTemplate(@NotNull String id);

    @FindOne
    DocumentTemplate findOne(@NotNull String id);

    @FindList
    List<DocumentTemplate> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    DocumentTemplate findDocumentTemplate(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<DocumentTemplate> findAllDocumentTemplate();

    /**
    * 查询列表
    * @param documentTemplateQuery
    * @return
    */
    List<DocumentTemplate> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery);

    /**
    * 按分页查询
    * @param documentTemplateQuery
    * @return
    */
    Pagination<DocumentTemplate> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery);

}