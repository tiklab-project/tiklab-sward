package com.doublekit.wiki.category.mapper;

import com.doublekit.beans.annotation.Mapper;
import com.doublekit.wiki.category.entity.CategoryPo;
import com.doublekit.wiki.category.model.Category;

@Mapper(source = Category.class,target = CategoryPo.class)
public class CategoryMapper {
}
