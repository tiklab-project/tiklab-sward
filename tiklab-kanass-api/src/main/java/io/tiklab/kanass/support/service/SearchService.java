package io.tiklab.kanass.support.service;



import io.tiklab.dss.common.document.model.CountResponse;
import io.tiklab.dss.common.document.model.PageCondition;
import io.tiklab.dss.common.document.model.PageResponse;
import io.tiklab.dss.common.document.model.TopResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 用户服务接口
*/
public interface SearchService {

    /**
     * 重新构建索引
     */
    void rebuild();

    /**
     * 添加记录
     * @param entity
     * @param <T>
     */
    <T> void save(T entity);

    /**
     * 根据ID查找记录
     * @param <T>
     * @param entityClass
     * @param id
     * @return
     */
    <T> Map<String, Object> get(Class<T> entityClass, String id);

    /**
     * 根据关键字搜索top结果
     * @param <T>
     * @param entityClass
     * @param keyword
     * @return
     */
    <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword);

    /**
     * 统计搜索结果
     * @param entityClass
     * @param keyword
     * @param <T>
     * @return
     */
    <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword);

    /**
     * 根据关键字按分页搜索
     * @param entityClass
     * @param keyword
     * @param pageCondition
     * @param <T>
     * @return
     */
    <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition);

    public HashMap<String, List<Object>> searchWikiDocumentForTop(String keyword);
}