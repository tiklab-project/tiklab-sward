package io.tiklab.sward.support.task.service;

import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.privilege.role.model.RoleQuery;
import io.tiklab.privilege.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitAuthority implements DsmProcessTask {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoleService roleService;

    @Override
    public void execute() {
        addSysRoleFunction();
    }

    private void addSysRoleFunction() {
        for (String sysRoleId : sysRoleIds) {
            String[] systemRoleIds = findSystemRoleIds(sysRoleId);
            execBatchInsert(systemRoleIds, sysRoleId);
        }
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setScope(2);
        roleQuery.setType("2");
        List<Role> roleList = roleService.findRoleList(roleQuery);

        for (Role role : roleList) {
            String[] systemRoleIds;
            String parentId = role.getParentId();
            if (!StringUtils.isEmpty(parentId)){
                systemRoleIds = findSystemRoleIds(parentId);
            }else {
                Integer businessType = role.getBusinessType();
                if (businessType.equals(2)){
                    systemRoleIds = domainAdminFunctionIds;
                }else {
                    systemRoleIds = domainAdminFunctionIds;
                }
            }
            execBatchInsert(systemRoleIds, role.getId());
        }
    }

    private String[] findSystemRoleIds(String id) {
        switch (id){
            case "111111","1" -> {return allSysFunctionIds;}
            case "sys_sward_user" -> {return sysFunctionIds;}

            // 项目
            case "pro_111111" -> {return domainAdminFunctionIds;}
            case "pro_sward_user" -> {return domainFunctionIds;}

            default -> {return new String[]{};}
        }
    }

    private final String[]  sysRoleIds = new String[]{"111111", "1","sys_sward_user","pro_111111","pro_sward_user"};


    private final String[] allSysFunctionIds = new String[]{
            "user","user_add_user","user_update_user","user_delete_user","user_update_user_password","user_update_user_recover",
            "orga","orga_add_orga","orga_update_orga","orga_delete_orga","orga_add_user","orga_delete_user",
            "user_group","user_add_group","user_update_group","user_delete_group","user_add_group_user","user_delete_group_user",
            "user_dir","user_dir_sync","user_dir_open","user_dir_config","user_dir_forbid",
            "permission","permission_role_add","permission_role_delete","permission_role_update","permission_role_user_add","permission_role_user_delete","permission_role_permission_update","permission_role_update_default",
            "message","message_update_send_way","message_update_plan_status","message_update_plan_send_way","message_plan_user_add","message_plan_delete",
            "openapi","openapi_add","openapi_delete","openapi_find",
            "backups_and_recover","backups_update_status","backups_create","recover_create",
            "log","log_find",
            "ip_whitelist","ip_whitelist_white","ip_whitelist_black",
            "licence","licence_import",
            "apply_limits","apply_limits_add_user","apply_limits_delete_user","apply_limits_open_user","apply_limits_close_user",
            "custom_logo","custom_logo_update_title","custom_logo_update_status","custom_logo_update_pic",

            // 知识库权限
            "wiki","wiki_create","wiki_update","wiki_delete","wiki_delete_share","wiki_find_share","wiki_setting",
            "wiki_service_integration","wiki_service_integration_kanass_add","wiki_service_integration_kanass_delete","wiki_service_integration_kanass_update","wiki_service_integration_onlyoffice_add","wiki_service_integration_onlyoffice_delete","wiki_service_integration_onlyoffice_update",
            "wiki_confluence","wiki_confluence_add",
            "wiki_archive","wiki_archive_recover","wiki_archive_delete",
            "wiki_recycle","wiki_recycle_recover","wiki_recycle_delete"
    };

    private final String[] sysFunctionIds = new String[]{
            // 知识库权限
            "wiki","wiki_create","wiki_update","wiki_delete","wiki_setting",
            "wiki_service_integration",
            "wiki_archive",
            "wiki_recycle",
    };


    private final String[] domainAdminFunctionIds = new String[]{
            "wi_overview","wi_overview_add_dir","wi_overview_add_doc","wi_overview_add_markdown","wi_overview_upload_local_file",
            "wi_doc","wi_doc_add_dir","wi_doc_update","wi_doc_preview", "wi_doc_download" ,"wi_doc_add_doc","wi_doc_add_markdown","wi_doc_upload_local_file","wi_doc_rename","wi_doc_delete","wi_doc_mv","wi_doc_share","wi_doc_archive","wi_doc_mv_recycle","wi_doc_commit_review","wi_doc_add_version","wi_doc_find_version","wi_doc_import_word","wi_doc_comment","wi_doc_offline",
            "wi_statistics","wi_statistics_delete","wi_statistics_rollback","wi_statistics_run","wi_statistics_contribute",
            "wi_setting","wi_setting_wiki_setting","wi_setting_wiki_update","wi_setting_wiki_archive","wi_setting_wiki_delete","wi_setting_wiki_mv_recycle","wi_setting_doc_share","wi_setting_doc_share_find","wi_setting_doc_share_delete","wi_setting_doc_review","wi_setting_doc_review_open","wi_setting_doc_review_add","wi_setting_doc_review_delete",
            "wi_setting_doc_archive","wi_setting_doc_archive_recover","wi_setting_doc_archive_delete","wi_setting_doc_recycle","wi_setting_doc_recycle_recover","wi_setting_doc_recycle_recover","wi_setting_doc_recycle_delete",

            "domain_user","domain_role","domain_message",
            // 系统全局设置权限
            "domain_user_add","domain_user_delete","domain_user_update",
            "domain_role_add","domain_role_delete","domain_role_update","domain_role_user_add","domain_role_user_delete","domain_role_permission_update","domain_role_update_default",
            "domain_message_status","domain_message_way","domain_message_user_add","domain_message_user_delete"
    };

    private final String[] domainFunctionIds = new String[]{
            "wi_overview","wi_overview_add_dir","wi_overview_add_doc","wi_overview_add_markdown","wi_overview_upload_local_file",
            "wi_doc","wi_doc_add_dir","wi_doc_update","wi_doc_preview", "wi_doc_download" ,"wi_doc_add_doc","wi_doc_add_markdown","wi_doc_upload_local_file","wi_doc_rename","wi_doc_delete","wi_doc_mv","wi_doc_share","wi_doc_archive","wi_doc_mv_recycle","wi_doc_commit_review","wi_doc_add_version","wi_doc_find_version","wi_doc_import_word","wi_doc_comment","wi_doc_offline",
            "wi_setting","wi_setting_wiki_setting","wi_setting_wiki_update",
    };


    private void execBatchInsert(String[] functionIds,String roleId) {

        String sql = "INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, Arrays.stream(functionIds).toList(), functionIds.length,
                (ps, functionId) -> {
                    ps.setString(1, UuidGenerator.getRandomIdByUUID(12));
                    ps.setString(2, roleId);
                    ps.setString(3, functionId);
                }
        );
    }


}
