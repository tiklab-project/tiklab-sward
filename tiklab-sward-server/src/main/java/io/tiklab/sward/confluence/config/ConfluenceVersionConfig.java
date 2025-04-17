package io.tiklab.sward.confluence.config;

import io.tiklab.core.exception.SystemException;
import io.tiklab.sward.confluence.model.ConfluenceVersion;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfluenceVersionConfig {


    public static ConfluenceVersion parseExportDescriptor(File file){
        if (!file.exists() || !file.isFile()) {
            throw new SystemException("无法找到导出文件的版本信息!");
        }

        Properties props = new Properties();
        try (InputStream input = new FileInputStream(file)) {
            props.load(input);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("解析Confluence版本信息失败!");
        }

        ConfluenceVersion version = new ConfluenceVersion();
        version.setVersionNumber(props.getProperty("createdByVersionNumber"));
        version.setExportType(props.getProperty("exportType"));
        version.setServerType(props.getProperty("source"));
        version.setBackupAttachments(Boolean.parseBoolean(props.getProperty("backupAttachments", "false")));
        version.setBuildNumber(props.getProperty("createdByBuildNumber"));
        version.setSpaceKey(props.getProperty("spaceKey"));

        return version;
    }


}
