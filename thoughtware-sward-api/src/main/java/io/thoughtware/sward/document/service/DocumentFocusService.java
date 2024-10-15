package io.thoughtware.sward.document.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.sward.document.model.DocumentFocus;
import io.thoughtware.sward.document.model.DocumentFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentFocusService
*/
public interface DocumentFocusService {

    /**
    * 创建
    * @param documentFocus
    * @return
    */
    String createDocumentFocus(@NotNull @Valid DocumentFocus documentFocus);

    /**
    * 更新
    * @param documentFocus
    */
    void updateDocumentFocus(@NotNull @Valid DocumentFocus documentFocus);

    /**
    * 删除
    * @param id
    */
    void deleteDocumentFocus(@NotNull String id);


    void deleteDocumentFocusByCondition(@NotNull @Valid DocumentFocusQuery documentFocusQuery);

    DocumentFocus findOne(@NotNull String id);

    List<DocumentFocus> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    DocumentFocus findDocumentFocus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<DocumentFocus> findAllDocumentFocus();

    /**
    * 查询列表
    * @param documentFocusQuery
    * @return
    */
    List<DocumentFocus> findDocumentFocusList(DocumentFocusQuery documentFocusQuery);

    /**
    * 按分页查询
    * @param documentFocusQuery
    * @return
    */
    Pagination<DocumentFocus> findDocumentFocusPage(DocumentFocusQuery documentFocusQuery);

}