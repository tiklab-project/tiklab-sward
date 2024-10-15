package io.thoughtware.sward.repository.service;

import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryService
*/

public interface WikiArchivedRepositoryService {

    /**
    * 创建
    * @param wikiRepository
    * @return
    */
    void archivedRepository(@NotNull @Valid WikiRepository wikiRepository);


    void recoverArchivedRepository(WikiRepository wikiRepository);

    List<WikiRepository> findArchivedRepository(WikiRepositoryQuery wikiRepositoryQuery);
}