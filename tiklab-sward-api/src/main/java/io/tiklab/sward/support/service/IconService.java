package io.tiklab.sward.support.service;



import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.model.Icon;
import io.tiklab.sward.support.model.IconQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 图标服务接口
*/
public interface IconService {

    /**
    * 创建图标
    * @param icon
    * @return
    */
    String createIcon(@NotNull @Valid Icon icon);

    /**
    * 更新图标
    * @param icon
    */
    void updateIcon(@NotNull @Valid Icon icon);

    /**
    * 根据id删除图标
    * @param id
    */
    void deleteIcon(@NotNull String id);

    Icon findOne(@NotNull String id);

    List<Icon> findList(List<String> idList);

    /**
    * 根据id 查找图标
    * @param id
    * @return
    */
    Icon findIcon(@NotNull String id);

    /**
    * 查找所有图标
    * @return
    */
    List<Icon> findAllIcon();

    /**
    * 查询图标列表
    * @param iconQuery
    * @return
    */
    List<Icon> findIconList(IconQuery iconQuery);

    /**
    * 按分页查询图标
    * @param iconQuery
    * @return
    */
    Pagination<Icon> findIconPage(IconQuery iconQuery);

}