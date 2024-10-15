package io.thoughtware.sward.repository.service;

import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryService
*/

public interface WikiRecycleRepositoryService {

    /**
    * 创建
    * @param wikiRepository
    * @return
    */
    void recycleRepository(@NotNull @Valid WikiRepository wikiRepository);


    void recoverRecycleRepository(WikiRepository wikiRepository);

    List<WikiRepository> findRecycleRepository(WikiRepositoryQuery wikiRepositoryQuery);
}