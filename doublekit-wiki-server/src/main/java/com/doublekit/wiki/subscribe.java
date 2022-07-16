package com.doublekit.wiki;

import com.doublekit.rpc.annotation.Reference;
import com.doublekit.subscribe.subscribe.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class subscribe {


    @Autowired
    @Reference(address = "${homes.address}")
    SubscribeService subscribeService;
}
