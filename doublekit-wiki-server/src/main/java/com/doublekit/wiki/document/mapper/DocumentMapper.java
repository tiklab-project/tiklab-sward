package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.DocumentPo;
import com.doublekit.wiki.document.model.Document;

@Mapper(source = Document.class,target = DocumentPo.class)
public class DocumentMapper {
}