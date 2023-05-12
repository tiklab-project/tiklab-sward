
package io.tiklab.kanass.repository.service;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.repository.dao.UpdateMySqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* 图标服务
*/
@Service
public class UpdateMySqlServiceImpl implements UpdateMySqlService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    UpdateMySqlDao updateMySqlDao;
    @Override
    public void updateAllData() {
        updateMySqlDao.updateId();
    }



}