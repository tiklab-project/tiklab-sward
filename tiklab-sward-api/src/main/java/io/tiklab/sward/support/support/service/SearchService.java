package io.tiklab.sward.support.support.service;

import io.tiklab.sward.document.document.model.DocumentQuery;
import io.tiklab.sward.document.document.model.WikiDocument;

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
     * 搜索
     * @param documentQuery 关键字
     * @return 结果
     */
    HashMap<String, List<Object>> searchWikiDocumentForTop(DocumentQuery documentQuery);


    List<WikiDocument> searchRepositoryDocument(DocumentQuery documentQuery);

    void initIndex();
}