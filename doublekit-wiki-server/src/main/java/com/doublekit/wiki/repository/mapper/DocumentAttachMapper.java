package com.doublekit.wiki.repository.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.repository.entity.DocumentAttachPo;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.model.Document;
import com.doublekit.wiki.repository.model.DocumentAttach;

@Mapper(source = DocumentAttach.class,target = DocumentAttachPo.class)
public class DocumentAttachMapper {
}
