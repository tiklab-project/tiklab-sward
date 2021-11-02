package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.DocumentAttachEntity;
import com.doublekit.wiki.document.model.DocumentAttach;

@Mapper(source = DocumentAttach.class,target = DocumentAttachEntity.class)
public class DocumentAttachMapper {
}
