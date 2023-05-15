package io.tiklab.kanass.repository.dao;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class UpdateMySqlDao {

    @Autowired
    JpaTemplate jpaTemplate;

    public void updateId(){
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        String sql="SHOW TABLES";
        List<String> stringList = jdbcTemplate.queryForList(sql, String.class);

        for (String tableName:stringList ){
            if (tableName.startsWith("kanass")){
                if (("kanass_repository").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"type_id", 8);
                    exeSql(tableName,"master", 12);
                    exeSql(tableName,"limits", 8);
                    exeSql(tableName,"icon_url", 32);
                }

                if (("kanass_category").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"repository_id", 12);
                    exeSql(tableName,"parent_category_id", 12);
                    exeSql(tableName,"master", 12);
                    exeSql(tableName,"icon_url", 32);
                }

                if (("kanass_document").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"repository_id", 12);
                    exeSql(tableName,"category_id", 12);
                    exeSql(tableName,"master", 12);
                }

                if (("kanass_document_attach").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"document_id", 12);
                }

                if (("kanass_document_template").equals(tableName)){
                    exeSql(tableName,"id", 8);
                }

                if (("kanass_comment").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"document_id", 12);
                    exeSql(tableName,"parent_comment_id", 12);
                    exeSql(tableName,"first_one_comment_id", 12);
                    exeSql(tableName,"aim_at_user", 12);
                    exeSql(tableName,"user_id", 12);
                }

                if (("kanass_like").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"to_whom_id", 12);
                    exeSql(tableName,"like_user", 12);
                }

                if (("kanass_share").equals(tableName)){
                    exeSql(tableName,"id", 12);
                }


                if (("kanass_document_recent").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"model_id", 12);
                    exeSql(tableName,"master_id", 12);
                    exeSql(tableName,"repository_id", 12);
                }
                if (("kanass_repository_focus").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"repository_id", 12);
                    exeSql(tableName,"master_id", 12);
                }
                if (("kanass_share_relation").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"document_id", 12);
                    exeSql(tableName,"share_id", 12);
                }

            }
            

            if(tableName.startsWith("pcs_mec")){
                if (("pcs_mec_mail_cfg").equals(tableName)){
                    exeSql(tableName,"id", 12);
                }
                if (("pcs_mec_message").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_type_id", 12);
                }
                if (("pcs_mec_message_dispatch_item").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_id", 12);
                    exeSql(tableName,"receiver", 12);
                    exeSql(tableName,"send_type", 12);
                    exeSql(tableName,"message_type_id", 12);
                }
                if (("pcs_mec_message_notice").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_type_id", 12);
                }
                if (("pcs_mec_message_notice_connect_orga").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_notice_id", 12);
                    exeSql(tableName,"orga_id", 12);
                }
                if (("pcs_mec_message_notice_connect_orga").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_notice_id", 12);
                    exeSql(tableName,"role_id", 12);
                }

                if (("pcs_mec_message_notice_connect_user").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_notice_id", 12);
                    exeSql(tableName,"user_id", 12);
                }
                if (("pcs_mec_message_notice_connect_usergroup").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_notice_id", 12);
                    exeSql(tableName,"usergroup_id", 12);
                }
                if (("pcs_mec_message_receiver").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"message_id", 12);
                    exeSql(tableName,"receiver", 12);
                }
                if (("pcs_mec_message_send_type").equals(tableName)){
                    exeSql(tableName,"id", 12);
                }
                if (("pcs_mec_message_template").equals(tableName)){
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"msg_type_id", 12);
                    exeSql(tableName,"msg_send_type_id", 12);
                }
                if (("pcs_mec_message_type").equals(tableName)){
                    exeSql(tableName,"id", 12);
                }
                if (("pcs_mec_webhook").equals(tableName)){
                    exeSql(tableName,"id", 12);
                }
            }

            if(tableName.startsWith("pcs_op_log")) {
                if (("pcs_op_log").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"user", 12);
                    exeSql(tableName,"actionType", 12);
                }

                if (("pcs_op_log_template").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_op_log_type").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }
            }

            if(tableName.startsWith("pcs_prc")) {
                if (("pcs_prc_dm_role").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"domain_id", 12);
                    exeSql(tableName,"role_id", 12);
                }

                if (("pcs_prc_dm_role_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"dmRole_id", 12);
                    exeSql(tableName,"domain_id", 12);
                    exeSql(tableName,"user_id", 12);
                }

                if (("pcs_prc_dm_role_user_group").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"dmRole_id", 12);
                    exeSql(tableName,"domain_id", 12);
                }

                if (("pcs_prc_dm_role_vuser").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"dmRole_id", 12);
                    exeSql(tableName,"domain_id", 12);
                    exeSql(tableName,"vuser_id", 12);
                }
                if (("pcs_prc_function").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"parent_function_id", 12);
                }
                if (("pcs_prc_product_authorization").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"user_id", 12);
                }
                if (("pcs_prc_role").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_prc_role_function").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"role_id", 12);
                    exeSql(tableName,"function_id", 12);
                }

                if (("pcs_prc_role_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"role_id", 12);
                    exeSql(tableName,"user_id", 12);
                }

                if (("pcs_prc_role_user_group").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"role_id", 12);
                    exeSql(tableName,"group_id", 12);
                }
                if (("pcs_prc_role_vuser").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"role_id", 12);
                    exeSql(tableName,"group_id", 12);
                }
            }

            if(tableName.startsWith("pcs_todo")) {
                if (("pcs_todo_task").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"create_user", 12);
                    exeSql(tableName,"assign_user", 12);
                    exeSql(tableName,"todo_type", 12);
                }

                if (("pcs_todo_task_template").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }
                if (("pcs_todo_task_type").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }
            }
            if (("pcs_tool_app_link").equals(tableName)) {
                exeSql(tableName,"id", 12);
            }

            if(tableName.startsWith("pcs_ucc")) {
                if (("pcs_ucc_dingding_directory_config").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_dm_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"domain_id", 12);
                    exeSql(tableName,"user_id", 12);

                }

                if (("pcs_ucc_dm_vuser").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"domain_id", 12);
                    exeSql(tableName,"vuser_id", 12);
                }

                if (("pcs_ucc_ldap_directory_config").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_ldap_directory_org_config").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_ldap_directory_user_config").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_orga").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"parent_orga_id", 12);
                }

                if (("pcs_ucc_orga_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"user_id", 12);
                    exeSql(tableName,"orga_id", 12);
                }

                if (("pcs_ucc_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_user_directory").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_usergroup").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_usergroup_user").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                    exeSql(tableName,"group_id", 12);
                    exeSql(tableName,"user_id", 12);
                }

                if (("pcs_ucc_vuser").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }

                if (("pcs_ucc_wechat_directory_config").equals(tableName)) {
                    exeSql(tableName,"id", 12);
                }
            }

        }
    }

    public void exeSql(String tableName,String fields, Integer num ){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable(){
            @Override
            public void run() {
                JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
                String findSql="SELECT "+ fields+ " FROM "+tableName;
                List<String> stringList = jdbcTemplate.queryForList(findSql, String.class);
                System.out.println(tableName);
                try {
                    for (String field:stringList){
                        if (StringUtils.isNotEmpty(field)&&field.length()>=12){
                            String newId = field.substring(0, num);
                            String updateSql="UPDATE "+tableName+" SET "+fields+"='"+newId+"' WHERE " +fields+"= '"+field+"'";
                            jdbcTemplate.execute(updateSql);
                        }
                    }

                }catch (Exception e){
                    throw new ApplicationException(2000,"更新失败" + e.getMessage() + "   sql   " + findSql);
                }
            }
        });


    }
}

