package io.tiklab.kanass.support.service;

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



    public HashMap<String, List<Object>> searchWikiDocumentForTop(String keyword);

    void initIndex();
}