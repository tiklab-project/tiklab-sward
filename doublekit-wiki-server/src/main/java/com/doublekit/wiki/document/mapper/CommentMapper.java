package com.doublekit.wiki.document.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.document.entity.CommentEntity;
import com.doublekit.wiki.document.model.Comment;

@Mapper(source = Comment.class,target = CommentEntity.class)
public class CommentMapper {
}
