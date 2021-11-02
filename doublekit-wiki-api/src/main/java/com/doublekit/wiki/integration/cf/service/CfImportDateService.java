package com.doublekit.wiki.integration.cf.service;

import java.io.InputStream;

public interface CfImportDateService {


    /**
     * 导入Confluence的数据
     * @param inputStream
     * @return
     */
    String   importConfluenceDate(InputStream inputStream);
}
