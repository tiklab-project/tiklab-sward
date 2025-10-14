package io.tiklab.sward.support.task.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.message.message.model.MessageDmNotice;
import io.tiklab.message.message.model.MessageDmNoticeQuery;
import io.tiklab.message.message.model.MessageNoticePatch;
import io.tiklab.message.message.service.MessageDmNoticeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.user.util.util.CodeFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitMessageTask implements DsmProcessTask {

    @Autowired
    MessageDmNoticeService messageDmNoticeService;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Value("${app.name:sward}")
    String appName;

    @Override
    public void execute() {
        initMessage();
    }

    public void initMessage() {

        List<WikiRepository> allRepository = wikiRepositoryService.findAllRepository();

        allRepository.forEach(wikiRepository -> {
            MessageDmNoticeQuery messageDmNoticeQuery = new MessageDmNoticeQuery();
            messageDmNoticeQuery.setMessageNoticeId(appName);
            List<MessageDmNotice> messageDmNoticeList = messageDmNoticeService.findMessageDmNoticeList(messageDmNoticeQuery);
            if (!messageDmNoticeList.isEmpty()) {
                return;
            }

            MessageNoticePatch messageNoticePatch = new MessageNoticePatch();
            messageNoticePatch.setDomainId(wikiRepository.getId());
            messageNoticePatch.setUserList(List.of(CodeFinal.ROOT_ID_CODE));
            messageDmNoticeService.initMessageDmNotice(messageNoticePatch);
        });
    }
}


























