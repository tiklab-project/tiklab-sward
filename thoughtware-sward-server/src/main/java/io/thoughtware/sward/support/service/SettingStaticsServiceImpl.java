package io.thoughtware.sward.support.service;



import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.user.directory.service.UserDirService;
import io.thoughtware.user.orga.service.OrgaService;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.user.usergroup.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SettingStaticsServiceImpl implements SettingStaticsService{
    @Autowired
    UserService userService;

    @Autowired
    OrgaService orgaService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserDirService userDirService;



    @Override
    public HashMap<String, Integer> findOrgaNum() {
        HashMap<String, Integer> numMap = new HashMap<>();
        Integer userNumber = userService.findUserNumber();
        Integer orgaNumber = orgaService.findOrgaNumber();
        Integer userGroupNumber = userGroupService.findUserGroupNumber();
        Integer roleNumber = roleService.findRoleNumber();
        Integer userDirNumber = userDirService.findUserDirNumber();

        numMap.put("user", userNumber);
        numMap.put("orga", orgaNumber);
        numMap.put("userGroup", userGroupNumber);
        numMap.put("role", roleNumber);
        numMap.put("userDir", userDirNumber);
        return numMap;
    }
}
