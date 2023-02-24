package net.tiklab.kanass.repository.service;

import java.io.InputStream;

public interface CfImportDataService {


    /**
     * 导入Confluence的数据
     * @param inputStream
     * @return
     */
    String importConfluenceData(InputStream inputStream);
}
