package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.DocumentTemplateEntity;
import com.doublekit.wiki.document.model.DocumentTemplate;

@Mapper(source = DocumentTemplate.class,target = DocumentTemplateEntity.class)
public class DocumentTemplateMapper {
}
