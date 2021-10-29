package com.doublekit.wiki.trilImport.service;

import java.io.InputStream;

public interface ImportDateService {


    /**
     * 导入Confluence的数据
     * @param inputStream
     * @return
     */
    String   importConfluenceDate(InputStream inputStream);
}
