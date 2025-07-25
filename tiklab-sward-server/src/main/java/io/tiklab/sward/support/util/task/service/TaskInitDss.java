package io.tiklab.sward.support.util.task.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.sward.support.support.service.DssInitIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskInitDss implements DsmProcessTask {

    @Autowired
    DssInitIndex initIndex;


    // @Override
    public void execute() {
        initIndex.execute();
    }
}
