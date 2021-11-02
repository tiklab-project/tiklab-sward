package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.ShareEntity;
import com.doublekit.wiki.document.model.Share;

@Mapper(source = Share.class,target = ShareEntity.class)
public class ShareMapper {
}
