package io.thoughtware.sward.document.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.sward.document.model.ShareRelation;
import io.thoughtware.sward.document.model.ShareRelationQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ShareRelationService
*/
public interface ShareRelationService {

    /**
    * 创建
    * @param shareRelation
    * @return
    */
    String createShareRelation(@NotNull @Valid ShareRelation shareRelation);

    void createShareDocumentCategory(@NotNull @Valid ShareRelation shareRelation);

    /**
    * 更新
    * @param shareRelation
    */
    void updateShareRelation(@NotNull @Valid ShareRelation shareRelation);

    /**
    * 删除
    * @param id
    */
    void deleteShareRelation(@NotNull String id);

    ShareRelation findOne(@NotNull String id);

    List<ShareRelation> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ShareRelation findShareRelation(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ShareRelation> findAllShareRelation();

    /**
    * 查询列表
    * @param shareRelationQuery
    * @return
    */
    List<ShareRelation> findShareRelationList(ShareRelationQuery shareRelationQuery);

    /**
    * 按分页查询
    * @param shareRelationQuery
    * @return
    */
    Pagination<ShareRelation> findShareRelationPage(ShareRelationQuery shareRelationQuery);


}