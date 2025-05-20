package io.tiklab.sward.confluence.config;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.PutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;

@Component
public class ConfluenceDataUpload {

    @Autowired(required=false)
    DfsClient dfsClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String upload(File file){
        try {
            // 获取文件名
            String fileName = file.getName();

            // 获取文件扩展名
            String fileType = "";
            int lastIndex = fileName.lastIndexOf(".");
            if (lastIndex != -1) {
                fileType = fileName.substring(lastIndex + 1);
            }

            FileInputStream fis = new FileInputStream(file);
            logger.info("upload dfs file {}", file.getAbsolutePath());
            PutRequest request = new PutRequest( fileName, fileType,fis);
            String objectId = dfsClient.put(request);

            fis.close();
            return objectId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("附件导入失败！");
        }
    }

}













