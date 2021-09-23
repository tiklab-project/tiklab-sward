package com.doublekit.wiki.repository.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.repository.entity.RepositoryPo;
import com.doublekit.wiki.repository.model.Repository;

@Mapper(source = Repository.class,target = RepositoryPo.class)
public class RepositoryMapper {
}
