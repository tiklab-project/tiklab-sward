package io.tiklab.sward.support.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.support.model.OnlyOffice;
import io.tiklab.sward.support.support.model.OnlyOfficeQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;

import java.util.List;

public interface OnlyOfficeService {

    /**
     * 创建onlyOffice
     * @param onlyOffice onlyOffice信息
     * @return onlyOfficeID
     */
    String createOnlyOffice(OnlyOffice onlyOffice);

    /**
     * 删除onlyOffice
     * @param id onlyOfficeID
     */
    void deleteOnlyOffice(String id);

    /**
     * 更新onlyOffice
     * @param onlyOffice onlyOffice信息
     */
    void updateOnlyOffice(OnlyOffice onlyOffice);

    /**
     * 查询onlyOffice
     * @param id onlyOfficeID
     * @return onlyOffice信息
     */
    @FindOne
    OnlyOffice findOnlyOffice(String id);

    /**
     * 查询所有onlyOffice
     * @return onlyOffice列表
     */
    @FindAll
    List<OnlyOffice> findAllOnlyOffice();

    /**
     * 查询onlyOffice列表
     * @param query 查询条件
     * @return onlyOffice列表
     */
    @FindList
    List<OnlyOffice> findOnlyOfficeList(OnlyOfficeQuery query);

    /**
     * 批量查询onlyOffice
     * @param idList onlyOfficeID列表
     * @return onlyOffice列表
     */
    List<OnlyOffice> findOnlyOfficeList(List<String> idList);

    /**
     * 分页查询onlyOffice
     * @param query 查询条件
     * @return onlyOffice列表
     */
    Pagination<OnlyOffice> findOnlyOfficePage(OnlyOfficeQuery query);

}
