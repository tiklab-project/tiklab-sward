

TRUNCATE TABLE pcs_prc_function;
TRUNCATE TABLE pcs_prc_role_function;
TRUNCATE TABLE pcs_prc_function_group;

ALTER TABLE pcs_prc_function MODIFY COLUMN name VARCHAR(256);

ALTER TABLE pcs_prc_function MODIFY COLUMN type VARCHAR(64);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('user', '用户', 'user', 'user,orga,user_group,user_dir', 1, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('permission', '权限', 'permission', 'permission', 2, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('message', '消息', 'message', 'message', 3, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wiki', '知识库', 'wiki', 'wiki', 5, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wiki_service', '集成开放', 'wiki_service', 'wiki_service_integration,wiki_confluence,openapi', 6, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('security', '安全', 'security', 'log,backups_and_recover,ip_whitelist,wiki_archive,wiki_recycle', 7, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('system', '系统', 'system', 'licence,apply_limits,custom_logo', 8, 1);


-- 用户
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user', '用户管理', 'user', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_add_user', '添加用户', 'user_add_user', 'user', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_update_user', '编辑用户', 'user_update_user', 'user', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_delete_user', '删除用户', 'user_delete_user', 'user', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_update_user_password', '修改用户密码', 'user_update_user_password', 'user', 4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_update_user_recover', '找回用户密码', 'user_update_user_recover', 'user', 4, '1');

--部门
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga', '部门管理', 'orga', NULL, 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga_add_orga', '添加组织', 'orga_add_orga', 'orga', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga_update_orga', '编辑组织信息', 'orga_update_orga', 'orga', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga_delete_orga', '删除组织', 'orga_delete_orga', 'orga', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga_add_user', '添加组织成员', 'orga_add_user', 'orga', 4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('orga_delete_user', '删除组织成员', 'orga_delete_user', 'orga', 5, '1');

--用户组
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_group', '用户组管理', 'user_group', NULL, 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_add_group', '添加用户组', 'user_add_group', 'user_group', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_update_group', '更新用户组信息', 'user_update_group', 'user_group', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_delete_group', '删除用户组', 'user_delete_group', 'user_group', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_add_group_user', '添加用户组成员', 'user_add_group_user', 'user_group', 4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_delete_group_user', '删除用户组成员', 'user_delete_group_user', 'user_group', 5, '1');

-- 用户目录
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_dir', '用户目录管理', 'user_dir', NULL, 4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_dir_sync', '同步数据', 'user_dir_sync', 'user_dir', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_dir_open', '开启', 'user_dir_open', 'user_dir', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_dir_config', '配置', 'user_dir_config', 'user_dir', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('user_dir_forbid', '禁止', 'user_dir_forbid', 'user_dir',4, '1');

--权限

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission', '权限管理', 'permission', NULL, 5, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_add', '添加角色', 'permission_role_add', 'permission',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_delete', '删除角色', 'permission_role_delete', 'permission',2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_update', '修改角色信息', 'permission_role_update', 'permission',3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_user_add', '添加角色成员', 'permission_role_user_add', 'permission',3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_user_delete', '删除角色成员', 'permission_role_user_delete', 'permission',3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_permission_update', '编辑角色权限', 'permission_role_permission_update', 'permission',3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('permission_role_update_default', '修改默认角色', 'permission_role_update_default', 'permission',4, '1');

-- 消息

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message', '消息管理', 'message', NULL, 6, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message_update_send_way', '配置消息发送方式', 'message_update_send_way', 'message',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message_update_plan_status', '修改通知方案状态', 'message_update_plan_status', 'message',2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message_update_plan_send_way', '修改通知方案发送方式', 'message_update_plan_send_way', 'message',3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message_plan_user_add', '添加通知方案通知成员', 'message_plan_user_add', 'message',4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('message_plan_delete', '删除通知方案通知成员', 'message_plan_delete', 'message',4, '1');



-- openapi
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('openapi', 'openapi管理', 'openapi', NULL, 100, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('openapi_add', '添加授权秘钥', 'openapi_add', 'openapi',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('openapi_delete', '删除授权秘钥', 'openapi_delete', 'openapi',2, '1');


-- 备份与恢复
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('backups_and_recover', '备份与恢复管理', 'backups_and_recover', NULL, 101, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('backups_update_status', '修改定时备份状态', 'backups_update_status', 'backups_and_recover',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('backups_create', '手动备份', 'backups_create', 'backups_and_recover',2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('recover_create', '恢复', 'recover_create', 'backups_and_recover',3, '1');

-- 日志
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('log', '操作日志管理', 'log', NULL, 102, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('log_find', '操作日志', 'log', 'log', 1, '1');

-- ip黑白名单
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('ip_whitelist', 'IP黑白名单管理', 'ip_whitelist', NULL,103, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('ip_whitelist_white', '配置白名单', 'ip_whitelist_white', 'ip_whitelist',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('ip_whitelist_black', '配置黑名单', 'ip_whitelist_black', 'ip_whitelist',2, '1');

-- 版本与许可证
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('licence', '版本与许可证管理', 'licence', NULL,104, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('licence_import', '导入Licence', 'licence_import', 'licence',1, '1');

--系统访问权限
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('apply_limits', '系统访问权限管理', 'apply_limits', NULL, 105, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('apply_limits_add_user', '添加授权用户', 'apply_limits_add_user', 'apply_limits', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('apply_limits_delete_user', '删除授权用户', 'apply_limits_delete_user', 'apply_limits', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('apply_limits_open_user', '激活授权', 'apply_limits_open_user', 'apply_limits', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('apply_limits_close_user', '取消授权', 'apply_limits_close_user', 'apply_limits', 4, '1');


-- 自定义Logo
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('custom_logo', '自定义Logo管理', 'custom_logo', NULL, 106, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('custom_logo_update_title', '修改标题', 'custom_logo_update_title', 'custom_logo',1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('custom_logo_update_status', '修改开启状态', 'custom_logo_update_status', 'custom_logo',2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('custom_logo_update_pic', '修改Logo图片', 'custom_logo_update_pic', 'custom_logo',3, '1');

-- 以下是知识库菜单

-- 知识库管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki', '知识库管理', 'wiki', NULL, 30, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_create', '创建知识库', 'wiki_create', 'wiki', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_update', '编辑知识库', 'wiki_update', 'wiki', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_setting', '知识库设置', 'wiki_setting', 'wiki', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_delete', '删除知识库', 'wiki_delete', 'wiki', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_find_share', '查看共享', 'wiki_find_share', 'wiki', 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_delete_share', '删除共享', 'wiki_delete_share', 'wiki', 6, '2');


-- 服务集成管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration', '服务集成管理', 'wiki_service_integration', NULL, 31, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_kanass_add', '新建Kanass服务集成', 'wiki_service_integration_kanass_add', 'wiki_service_integration', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_kanass_delete', '删除Kanass服务集成', 'wiki_service_integration_kanass_delete', 'wiki_service_integration', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_kanass_update', '编辑Kanass服务集成', 'wiki_service_integration_kanass_update', 'wiki_service_integration', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_onlyoffice_add', '新建OnlyOffice服务集成', 'wiki_service_integration_onlyoffice_add', 'wiki_service_integration', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_onlyoffice_delete', '删除OnlyOffice服务集成', 'wiki_service_integration_onlyoffice_delete', 'wiki_service_integration', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_service_integration_onlyoffice_update', '编辑OnlyOffice服务集成', 'wiki_service_integration_onlyoffice_update', 'wiki_service_integration', 4, '2');


-- Confluence导入管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_confluence', 'Confluence导入管理', 'wiki_confluence', NULL, 32, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_confluence_add', 'Confluence导入', 'wiki_confluence', 'wiki_confluence', 32, '2');


-- 归档管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_archive', '归档管理', 'wiki_archive', NULL, 33, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_archive_recover', '恢复归档知识库', 'wiki_archive_recover', 'wiki_archive', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_archive_delete', '删除归档知识库', 'wiki_archive_delete', 'wiki_archive', 2, '2');


-- 回收站管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_recycle', '回收站管理', 'wiki_recycle', NULL, 33, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_recycle_recover', '恢复回收站知识库', 'wiki_recycle_recover', 'wiki_recycle', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_recycle_delete', '删除回收站知识库', 'wiki_recycle_delete', 'wiki_recycle', 2, '2');



-- 以下是知识库项目域权限

-- 知识概况管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_overview', '知识库概况管理', 'wi_overview', NULL, 43, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_overview_add_dir', '创建目录', 'wi_overview_add_dir', 'wi_overview', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_overview_add_doc', '创建文档', 'wi_overview_add_doc', 'wi_overview', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_overview_add_markdown', '新建Markdown文档', 'wi_overview_add_markdown', 'wi_overview', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_overview_upload_local_file', '上传本地文件', 'wi_overview_upload_local_file', 'wi_overview', 4, '2');



-- 知识库文档管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc', '知识库文档管理', 'wi_doc', NULL, 43, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_add_dir', '创建目录', 'wi_doc_add_dir', 'wi_doc', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_add_doc', '创建文档', 'wi_doc_add_doc', 'wi_doc', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_add_markdown', '新建Markdown文档', 'wi_doc_add_markdown', 'wi_doc', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_upload_local_file', '上传本地文件', 'wi_doc_upload_local_file', 'wi_doc', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_rename', '重命名文档', 'wi_doc_rename', 'wi_doc', 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_delete', '删除文档', 'wi_doc_delete', 'wi_doc', 6, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_mv', '移动文档', 'wi_doc_mv', 'wi_doc', 7, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_update', '更新文档', 'wi_doc_update', 'wi_doc', 8, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_share', '分享文档', 'wi_doc_share', 'wi_doc', 8, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_archive', '归档文档', 'wi_doc_archive', 'wi_doc', 9, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_mv_recycle', '移动文档到回收站', 'wi_doc_mv_recycle', 'wi_doc', 10, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_commit_review', '文档提交评审', 'wi_doc_commit_review', 'wi_doc', 11, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_add_version', '文档添加版本', 'wi_doc_add_version', 'wi_doc', 12, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_find_version', '查看文档版本', 'wi_doc_find_version', 'wi_doc', 13, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_import_word', '文档导出Word', 'wi_doc_import_word', 'wi_doc', 14, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_comment', '评论文档', 'wi_doc_comment', 'wi_doc', 15, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_offline', '下线文档', 'wi_doc_offline', 'wi_doc', 16, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_download', '下载文档', 'wi_doc_download', 'wi_doc', 17, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_doc_preview', '预览文档', 'wi_doc_preview', 'wi_doc', 18, '2');


-- 知识库统计管理
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_statistics', '知识库统计管理', 'wi_statistics', NULL, 44, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_statistics_delete', '文档数量统计', 'wi_statistics_number', 'wi_statistics', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_statistics_rollback', '文档创作统计', 'wi_statistics_create', 'wi_statistics', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_statistics_run', '文档明细统计', 'wi_statistics_details', 'wi_statistics', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_statistics_contribute', '成员贡献统计', 'wi_statistics_contribute', 'wi_statistics', 4, '2');


-- 流水线测试报告
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting', '设置', 'wi_setting', NULL, 45, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_wiki_update', '修改知识库信息', 'wi_setting_wiki_update', 'wi_setting_wiki_setting', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_wiki_archive', '归档知识库', 'wi_setting_wiki_archive', 'wi_setting_wiki_setting', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_wiki_delete', '删除知识库', 'wi_setting_wiki_delete', 'wi_setting_wiki_setting', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_wiki_mv_recycle', '知识库移入回收站', 'wi_setting_wiki_mv_recycle', 'wi_setting_wiki_setting', 4, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_share_find', '查看共享文档', 'wi_setting_doc_share_find', 'wi_setting_doc_share', 6, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_share_delete', '删除文档共享', 'wi_setting_doc_share_delete', 'wi_setting_doc_share', 7, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_review_open', '开启关闭评审', 'wi_setting_doc_review_open', 'wi_setting_doc_review', 9, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_review_add', '添加评审', 'wi_setting_doc_review_add', 'wi_setting_doc_review', 10, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_review_delete', '删除评审', 'wi_setting_doc_review_delete', 'wi_setting_doc_review', 11, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_archive_recover', '恢复归档文档', 'wi_setting_doc_archive_recover', 'wi_setting_doc_archive', 13, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_archive_delete', '删除归档文档', 'wi_setting_doc_archive_delete', 'wi_setting_doc_archive', 14, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_recycle_recover', '恢复回收站文档', 'wi_setting_doc_recycle_recover', 'wi_setting_doc_recycle', 16, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_recycle_delete', '删除回收站文档', 'wi_setting_doc_recycle_delete', 'wi_setting_doc_recycle', 17, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_wiki_setting', '知识库信息', 'wi_setting_wiki_setting', 'wi_setting', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_user', '成员', 'domain_user', 'wi_setting', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role', '权限', 'domain_role', 'wi_setting', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_message', '消息', 'domain_message', 'wi_setting', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_share', '共享', 'wi_setting_doc_share', 'wi_setting', 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_review', '评审', 'wi_setting_doc_review', 'wi_setting', 6, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_archive', '归档', 'wi_setting_doc_archive', 'wi_setting', 7, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wi_setting_doc_recycle', '回收站', 'wi_setting_doc_recycle', 'wi_setting', 8, '2');


-- 此为公共的项目权限

-- 成员
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_user_add', '添加成员', 'domain_user_add', 'domain_user', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_user_delete', '删除流水线成员', 'domain_user_delete', 'domain_user', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_user_update', '修改流水线成员角色', 'domain_user_update', 'domain_user', 3, '2');

--角色
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_add', '添加角色', 'domain_role_add', 'domain_role', 7, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_delete', '删除角色', 'domain_role_delete', 'domain_role', 8, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_update', '修改角色信息', 'domain_role_update', 'domain_role',9, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_user_add', '添加角色成员', 'domain_role_user_add', 'domain_role', 10, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_user_delete', '删除角色成员', 'domain_role_user_delete', 'domain_role', 11, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_permission_update', '编辑角色权限', 'domain_role_permission_update', 'domain_role',12, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_role_update_default', '修改默认角色', 'domain_role_update_default', 'domain_role',13, '2');


--消息
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_message_status', '配置消息通知方案开启状态', 'domain_message_status', 'domain_message', 14, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_message_way', '配置消息通知方案通知方式', 'domain_message_way', 'domain_message', 15, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_message_user_add', '配置消息通知方案通知对象', 'domain_message_user_add', 'domain_message', 16, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('domain_message_user_delete', '删除消息通知方案通知对象', 'domain_message_user_delete', 'domain_message', 17, '2');



INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wi_overview', '概况', 'wi_overview', 'wi_overview', 1, 2);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wi_doc', '文档', 'wi_doc', 'wi_doc', 2, 2);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wi_statistics', '统计', 'wi_statistics', 'wi_statistics', 3, 2);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wi_setting', '设置', 'wi_setting', 'wi_setting_wiki_setting,domain_user,domain_role,domain_message,wi_setting_doc_share,wi_setting_doc_review,wi_setting_doc_archive,wi_setting_doc_recycle', 4, 2);
