package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.SharePo;
import com.doublekit.wiki.document.model.Share;

@Mapper(source = Share.class,target = SharePo.class)
public class ShareMapper {
}
