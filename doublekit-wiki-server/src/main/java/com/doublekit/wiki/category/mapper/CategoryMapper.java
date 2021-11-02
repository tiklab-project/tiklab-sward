package com.doublekit.wiki.category.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.category.entity.CategoryEntity;
import com.doublekit.wiki.category.model.Category;

@Mapper(source = Category.class,target = CategoryEntity.class)
public class CategoryMapper {
}
