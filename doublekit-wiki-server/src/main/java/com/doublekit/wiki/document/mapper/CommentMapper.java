package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.CommentPo;
import com.doublekit.wiki.document.model.Comment;

@Mapper(source = Comment.class,target = CommentPo.class)
public class CommentMapper {
}
