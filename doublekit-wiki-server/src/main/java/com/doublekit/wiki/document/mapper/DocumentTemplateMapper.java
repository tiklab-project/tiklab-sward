package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.DocumentTemplatePo;
import com.doublekit.wiki.document.model.DocumentTemplate;

@Mapper(source = DocumentTemplate.class,target = DocumentTemplatePo.class)
public class DocumentTemplateMapper {
}
