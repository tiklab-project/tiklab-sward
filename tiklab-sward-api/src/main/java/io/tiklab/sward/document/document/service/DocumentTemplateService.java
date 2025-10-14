package io.tiklab.sward.document.document.service;

import io.tiklab.sward.document.document.model.DocumentTemplate;
import io.tiklab.sward.document.document.model.DocumentTemplateQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

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
    * @param documentTemplate 模版信息
    * @return 结果
    */
    String createDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate);

    /**
    * 更新
    * @param documentTemplate 模版信息
    */
    void updateDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate);

    /**
    * 删除
    * @param id id
    */
    void deleteDocumentTemplate(@NotNull String id);

    @FindOne
    DocumentTemplate findOne(@NotNull String id);

    @FindList
    List<DocumentTemplate> findList(List<String> idList);

    /**
    * 查找
    * @param id id
    * @return 结果
    */
    DocumentTemplate findDocumentTemplate(@NotNull String id);

    /**
    * 查找所有
    * @return 结果
    */
    @FindAll
    List<DocumentTemplate> findAllDocumentTemplate();

    /**
    * 查询列表
    * @param documentTemplateQuery 查询条件
    * @return 结果
    */
    List<DocumentTemplate> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery);

    /**
    * 按分页查询
    * @param documentTemplateQuery 查询条件
    * @return 结果
    */
    Pagination<DocumentTemplate> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery);

}