package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.LikePo;
import com.doublekit.wiki.document.model.Like;

@Mapper(source = Like.class,target = LikePo.class)
public class LikeMapper {
}
