package io.thoughtware.sward.repository.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.logging.service.LoggingByTempService;
import io.thoughtware.sward.repository.dao.WikiRepositoryDao;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;
import io.thoughtware.user.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* RepositoryServiceImpl
*/
@Service
@Exporter
public class WikiArchivedRepositoryServiceImpl implements WikiArchivedRepositoryService {
    private static Logger logger = LoggerFactory.getLogger(WikiArchivedRepositoryServiceImpl.class);
    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

   @Autowired
    WikiRepositoryService wikiRepositoryService;


    @Autowired
    LoggingByTempService loggingByTemplService;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public void archivedRepository(WikiRepository wikiRepository) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        wikiRepository.setArchivedTime(format);

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        wikiRepository.setArchivedUser(user);

        wikiRepositoryService.updateRepository(wikiRepository);
    }

    @Override
    public void recoverArchivedRepository(WikiRepository wikiRepository) {
        wikiRepository.setArchivedTime("nullstring");

        User user = new User();
        user.setId("nullstring");
        wikiRepository.setArchivedUser(user);
        wikiRepositoryService.updateRepository(wikiRepository);
    }

    @Override
    public List<WikiRepository> findArchivedRepository(WikiRepositoryQuery wikiRepositoryQuery) {
        List<WikiRepository> repositoryList = wikiRepositoryService.findRepositoryList(wikiRepositoryQuery);
        return repositoryList;
    }


}