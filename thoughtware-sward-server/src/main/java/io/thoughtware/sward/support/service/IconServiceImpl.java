package io.thoughtware.sward.support.service;

import io.thoughtware.sward.support.model.Icon;
import io.thoughtware.sward.support.model.IconQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.thoughtware.sward.support.dao.IconDao;
import io.thoughtware.sward.support.entity.IconEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 图标服务
*/
@Service
public class IconServiceImpl implements IconService {

    @Autowired
    IconDao iconDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createIcon(@NotNull @Valid Icon icon) {
        IconEntity iconEntity = BeanMapper.map(icon, IconEntity.class);

        return iconDao.createIcon(iconEntity);
    }

    @Override
    public void updateIcon(@NotNull @Valid Icon icon) {
        IconEntity iconEntity = BeanMapper.map(icon, IconEntity.class);

        iconDao.updateIcon(iconEntity);
    }

    @Override
    public void deleteIcon(@NotNull String id) {
        iconDao.deleteIcon(id);
    }

    @Override
    public Icon findOne(String id) {
        IconEntity iconEntity = iconDao.findIcon(id);

        Icon icon = BeanMapper.map(iconEntity, Icon.class);
        return icon;
    }

    @Override
    public List<Icon> findList(List<String> idList) {
        List<IconEntity> iconEntityList =  iconDao.findIconList(idList);

        List<Icon> iconList =  BeanMapper.mapList(iconEntityList,Icon.class);
        return iconList;
    }

    @Override
    public Icon findIcon(@NotNull String id) {
        Icon icon = findOne(id);

        joinTemplate.joinQuery(icon);

        return icon;
    }

    @Override
    public List<Icon> findAllIcon() {
        List<IconEntity> iconEntityList =  iconDao.findAllIcon();

        List<Icon> iconList =  BeanMapper.mapList(iconEntityList,Icon.class);

        joinTemplate.joinQuery(iconList);

        return iconList;
    }

    @Override
    public List<Icon> findIconList(IconQuery iconQuery) {
        List<IconEntity> iconEntityList = iconDao.findIconList(iconQuery);

        List<Icon> iconList = BeanMapper.mapList(iconEntityList,Icon.class);

        joinTemplate.joinQuery(iconList);

        return iconList;
    }

    @Override
    public Pagination<Icon> findIconPage(IconQuery iconQuery) {
        Pagination<IconEntity>  pagination = iconDao.findIconPage(iconQuery);

        List<Icon> iconList = BeanMapper.mapList(pagination.getDataList(),Icon.class);

        joinTemplate.joinQuery(iconList);

        return PaginationBuilder.build(pagination,iconList);
    }
}