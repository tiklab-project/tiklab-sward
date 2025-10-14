package io.tiklab.sward.support.task.service;

import io.tiklab.dsm.support.DsmProcessTask;
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
