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
import java.util.Date;
import java.util.List;

/**
* RepositoryServiceImpl
*/
@Service
@Exporter
public class WikiRecycleRepositoryServiceImpl implements WikiRecycleRepositoryService {
    private static Logger logger = LoggerFactory.getLogger(WikiRecycleRepositoryServiceImpl.class);
    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

   @Autowired
    WikiRepositoryService wikiRepositoryService;


    @Autowired
    LoggingByTempService loggingByTemplService;

    @Value("${base.url:null}")
    String baseUrl;


    @Override
    public void recycleRepository(WikiRepository wikiRepository) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        wikiRepository.setRecycleTime(format);

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        wikiRepository.setRecycleUser(user);

        wikiRepositoryService.updateRepository(wikiRepository);
    }

    @Override
    public void recoverRecycleRepository(WikiRepository wikiRepository) {
        wikiRepository.setRecycleTime("nullstring");

        User user = new User();
        user.setId("nullstring");
        wikiRepository.setRecycleUser(user);
        wikiRepositoryService.updateRepository(wikiRepository);
    }

    @Override
    public List<WikiRepository> findRecycleRepository(WikiRepositoryQuery wikiRepositoryQuery) {
        wikiRepositoryQuery.setStatus(null);
        List<WikiRepository> repositoryList = wikiRepositoryService.findRepositoryList(wikiRepositoryQuery);
        return repositoryList;
    }
}