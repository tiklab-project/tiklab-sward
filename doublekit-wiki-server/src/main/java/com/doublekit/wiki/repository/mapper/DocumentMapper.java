package com.doublekit.wiki.repository.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.entity.RepositoryPo;
import com.doublekit.wiki.repository.model.Document;
import com.doublekit.wiki.repository.model.Repository;

@Mapper(source = Document.class,target = DocumentPo.class)
public class DocumentMapper {
}
