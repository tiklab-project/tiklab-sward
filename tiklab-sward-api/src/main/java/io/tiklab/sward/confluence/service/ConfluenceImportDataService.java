package io.tiklab.sward.confluence.service;

import java.io.InputStream;
import java.util.Map;

/**
 * 导入第三方数据服务接口
 */
public interface ConfluenceImportDataService {

    /**
     * 导入jira的数据
     *
     * @param inputStream
     * @return
     */
    void importJiraData(InputStream inputStream);

    Map<String, Object> findCfInputSchedule();

}