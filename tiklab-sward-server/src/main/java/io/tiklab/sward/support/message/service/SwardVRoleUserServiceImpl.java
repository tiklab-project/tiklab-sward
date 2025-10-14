package io.tiklab.sward.support.message.service;

import io.tiklab.privilege.dmRole.model.DmRole;
import io.tiklab.privilege.dmRole.model.DmRoleQuery;
import io.tiklab.privilege.dmRole.model.DmRoleUser;
import io.tiklab.privilege.dmRole.model.DmRoleUserQuery;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.dmRole.service.DmRoleUserService;
import io.tiklab.privilege.vRole.model.VRole;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.privilege.vRole.service.VRoleUserService;
import io.tiklab.sward.document.approve.model.ApproveUser;
import io.tiklab.sward.document.approve.model.ApproveUserQuery;
import io.tiklab.sward.document.approve.service.ApproveUserService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Primary
@Service
public class SwardVRoleUserServiceImpl implements VRoleUserService {

    @Autowired
    DmUserService dmUserService;

    @Autowired
    UserProcessor userService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DmRoleUserService dmRoleUserService;

    @Autowired(required = false)
    ApproveUserService approveUserService;

    @Override
    public List<User> findDmVRoleUser(VRoleDomain vRoleDomain) {
        String modelId = vRoleDomain.getModelId();
        String domainId = vRoleDomain.getDomainId();
        List<String> userIdList = new ArrayList<>();
        if (StringUtils.isEmpty(modelId)){
            DmRoleQuery dmRoleQuery = new DmRoleQuery();
            dmRoleQuery.setDomainId(domainId);
            List<DmRole> dmRoleList = dmRoleService.findDmRoleListNoQuery(dmRoleQuery);
            for (DmRole dmRole : dmRoleList) {
                Integer businessType = dmRole.getBusinessType();
                if (businessType.equals(0)) {
                    continue;
                }
                DmRoleUserQuery dmUserQuery = new DmRoleUserQuery();
                dmUserQuery.setDmRoleId(dmRole.getId());
                List<DmRoleUser> dmUserList = dmRoleUserService.findDmRoleUserList(dmUserQuery);
                userIdList = dmUserList.stream().map(DmRoleUser::getUserId)
                        .distinct()
                        .collect(Collectors.toList());

            }
        } else {
            if (!Objects.isNull(approveUserService)){
                ApproveUserQuery approveUserQuery = new ApproveUserQuery();
                approveUserQuery.setApproveId(modelId);
                List<ApproveUser> approveUserList = approveUserService.findApproveUserList(approveUserQuery);

                userIdList = approveUserList.stream()
                        .map(ApproveUser::getUser)
                        .map(User::getId)
                        .distinct()
                        .collect(Collectors.toList());
            }

        }
        return new ArrayList<>(userService.findList(userIdList));
    }

    @Override
    public List<User> findVRoleUser(List<VRole> vRoleList,String domainId) {
        List<User> userList = new ArrayList<>();
        for (VRole vRole : vRoleList) {
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setDomainId(domainId);
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
            List<String> userIdList = new ArrayList<>();
            for (DmUser dmUser : dmUserList) {
                userIdList.add(dmUser.getUser().getId());
            }
            List<User> list = userService.findList(userIdList);
            userList.addAll(list);
        }
        return userList;
    }


}
















