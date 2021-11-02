package com.doublekit.wiki.repository.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.repository.entity.RepositoryEntity;
import com.doublekit.wiki.repository.model.Repository;

@Mapper(source = Repository.class,target = RepositoryEntity.class)
public class RepositoryMapper {
}
