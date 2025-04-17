package io.tiklab.sward.confluence.config;

import io.tiklab.core.exception.SystemException;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.object.model.PutRequest;
import io.tiklab.dfs.common.support.StreamConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ConfluenceDataUpload {

    @Autowired(required=false)
    DfsClient dfsClient;


    public String upload(File file){
        try {
            String fileName = file.getName();
            int lastIndex = fileName.lastIndexOf(".");
            String fileType = fileName.substring(lastIndex + 1);
            InputStream stream = new FileInputStream(file);
            byte[] content = StreamConvert.toByteArray(stream);

            return dfsClient.put(new PutRequest(content,fileName,fileType));
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

}













