package io.tiklab.sward.starter.task;


import io.tiklab.eam.client.author.config.TiklabApplicationRunner;
import io.tiklab.sward.support.support.DssInitIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskInitDss implements TiklabApplicationRunner {

    @Autowired
    DssInitIndex initIndex;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        initIndex.execute();
    }




}

















