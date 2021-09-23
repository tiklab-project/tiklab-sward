package com.doublekit.wiki.repository.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.entity.DocumentTemplatePo;
import com.doublekit.wiki.repository.model.Document;
import com.doublekit.wiki.repository.model.DocumentTemplate;

@Mapper(source = DocumentTemplate.class,target = DocumentTemplatePo.class)
public class DocumentTemplateMapper {
}
