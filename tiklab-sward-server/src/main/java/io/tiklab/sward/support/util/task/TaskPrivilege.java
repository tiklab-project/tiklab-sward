package io.tiklab.sward.support.util.task;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.privilege.dmRole.model.DmRole;
import io.tiklab.privilege.dmRole.model.DmRoleQuery;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.privilege.role.model.RoleQuery;
import io.tiklab.privilege.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskPrivilege implements DsmProcessTask {

    @Autowired
    RoleService roleService;

    @Autowired
    DmRoleService dmRoleService;


    public void run() {
        String roleId = "2";
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setParentId(roleId);
        roleQuery.setType("2");
        roleQuery.setScope(2);
        List<Role> roleList = roleService.findRoleList(roleQuery);
        for (Role role : roleList) {
            DmRoleQuery dmRoleQuery = new DmRoleQuery();
            dmRoleQuery.setRoleId(role.getId());
            List<DmRole> dmRoleList = dmRoleService.findDmRoleListNoQuery(dmRoleQuery);

            for (DmRole dmRole : dmRoleList) {
                dmRoleService.deleteDmRole(dmRole.getId());
            }
        }
        roleService.deleteRole(roleId);
    }

    // @Override
    public void execute() {
        run();
    }
}


