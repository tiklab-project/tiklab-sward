package io.tiklab.sward.confluence.service;

import io.tiklab.sward.confluence.model.ConfluenceSpace;
import io.tiklab.sward.confluence.model.ConfluenceVersion;
import io.tiklab.sward.confluence.model.ImportStatus;

import java.io.InputStream;
import java.util.List;

/**
 * 导入第三方数据服务接口
 */
public interface ConfluenceImportService {

    /**
     * 导入jira的数据
     * @param confluenceAddress
     * @return
     */
    void importJiraData(String confluenceAddress);


    /**
     * 效验是否支持导入
     * @param confluenceAddress zip文件地址
     * @return 版本信息
     */
    ConfluenceVersion validConfluenceVersion(String confluenceAddress);

    /**
     * 解析导入的文件内容
     * @param confluenceAddress 文件地址
     */
    List<ConfluenceSpace> analysisEntityXml(String confluenceAddress);

    /**
     * 解析导入内容
     * @param confluenceAddress 文件地址
     */
    List<ConfluenceSpace> analysis(String confluenceAddress);

    /**
     * 获取状态
     */
    ImportStatus findCfInputSchedule();

}