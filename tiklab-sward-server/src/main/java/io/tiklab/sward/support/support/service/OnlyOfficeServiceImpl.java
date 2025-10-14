package io.tiklab.sward.support.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.sward.support.support.dao.OnlyOfficeDao;
import io.tiklab.sward.support.support.entity.OnlyOfficeEntity;
import io.tiklab.sward.support.support.model.OnlyOffice;
import io.tiklab.sward.support.support.model.OnlyOfficeQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlyOfficeServiceImpl implements OnlyOfficeService {

    @Autowired
    OnlyOfficeDao OnlyOfficeDao;

    @Override
    public String createOnlyOffice(OnlyOffice onlyOffice) {
        OnlyOfficeEntity onlyOfficeEntity = BeanMapper.map(onlyOffice, OnlyOfficeEntity.class);
        return OnlyOfficeDao.createOnlyOffice(onlyOfficeEntity);
    }

    @Override
    public void deleteOnlyOffice(String id) {
        OnlyOfficeDao.deleteOnlyOffice(id);
    }

    @Override
    public void updateOnlyOffice(OnlyOffice onlyOffice) {
        OnlyOfficeEntity onlyOfficeEntity = BeanMapper.map(onlyOffice, OnlyOfficeEntity.class);
        OnlyOfficeDao.updateOnlyOffice(onlyOfficeEntity);
    }

    @Override
    public OnlyOffice findOnlyOffice(String id) {
        OnlyOfficeEntity onlyOfficeEntity = OnlyOfficeDao.findOnlyOffice(id);
        return BeanMapper.map(onlyOfficeEntity, OnlyOffice.class);
    }

    @Override
    public List<OnlyOffice> findAllOnlyOffice() {
        List<OnlyOfficeEntity> onlyOfficeEntityList = OnlyOfficeDao.findAllOnlyOffice();
        return BeanMapper.mapList(onlyOfficeEntityList, OnlyOffice.class);
    }

    @Override
    public List<OnlyOffice> findOnlyOfficeList(OnlyOfficeQuery query) {
        List<OnlyOfficeEntity> onlyOfficeEntityList = OnlyOfficeDao.findOnlyOfficeList(query);
        List<OnlyOffice> onlyOffices = BeanMapper.mapList(onlyOfficeEntityList, OnlyOffice.class);
        return onlyOffices;
    }

    @Override
    public List<OnlyOffice> findOnlyOfficeList(List<String> idList) {
        List<OnlyOfficeEntity> onlyOfficeEntityList = OnlyOfficeDao.findOnlyOfficeList(idList);
        return BeanMapper.mapList(onlyOfficeEntityList, OnlyOffice.class);
    }

    @Override
    public Pagination<OnlyOffice> findOnlyOfficePage(OnlyOfficeQuery query) {
        Pagination<OnlyOfficeEntity> onlyOfficeEntityPagination = OnlyOfficeDao.findOnlyOfficePage(query);
        List<OnlyOffice> OnlyOfficeList = BeanMapper.mapList(onlyOfficeEntityPagination.getDataList(), OnlyOffice.class);
        return PaginationBuilder.build(onlyOfficeEntityPagination, OnlyOfficeList);
    }



}
