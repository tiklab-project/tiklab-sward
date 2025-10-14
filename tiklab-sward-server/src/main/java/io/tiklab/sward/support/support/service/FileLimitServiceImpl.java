package io.tiklab.sward.support.support.service;

import io.tiklab.sward.support.support.model.SwardFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileLimitServiceImpl implements FileLimitService {

    @Value("${sward.doc.limit:0}")
    private Integer fileLimit;


    @Override
    public SwardFile findFileLimit() {
        SwardFile swardFile = new SwardFile();
        swardFile.setDocFileSize(fileLimit);
        if (fileLimit == 0){
            swardFile.setDocFileSize(20);
        }
        return swardFile;
    }
}
