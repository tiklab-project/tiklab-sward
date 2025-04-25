package io.tiklab.sward.confluence.config;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.object.model.PutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;

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

            // 读取文件内容到字节数组
            byte[] content = Files.readAllBytes(file.toPath());

            logger.info("upload dfs file {}", file.getAbsolutePath());

            PutRequest request = new PutRequest(content, fileName, fileType);
            return dfsClient.put(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("附件超过最大限制，附件导入失败！");
        }
    }

}













