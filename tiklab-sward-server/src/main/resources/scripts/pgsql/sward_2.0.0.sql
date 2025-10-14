

--  消息类型
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_UPDATE', '更新知识库', '更新知识库', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_RESTORE', '恢复知识库', '恢复知识库', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_DELETE', '删除知识库', '删除知识库', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_CREATE', '创建知识库', '创建知识库', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_ARCHIVE', '归档知识库', '归档知识库', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_RPY_RECYCLE', '移入回收站', '移入回收站', 'sward');


INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_REVIVW_REVOKE', '撤销评审', '撤销评审', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_REVIVW_REVIVW', '评审文档', '评审文档', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_REVIVW_CREATE', '创建评审', '创建评审', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_DOC_RESTORE', '恢复文档', '恢复文档', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_DOC_DELETE', '删除文档', '删除文档', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_DOC_CREATE', '创建文档', '创建文档', 'sward');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('SW_DOC_ARCHIVE', '归档文档', '归档文档', 'sward');



-- 系统角色
INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_CREATE', '知识库成员', '知识库成员', 1, 'SW_RPY_CREATE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_DELETE', '知识库成员', '知识库成员', 1, 'SW_RPY_DELETE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_ARCHIVE', '知识库成员', '知识库成员', 1, 'SW_RPY_ARCHIVE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_UPDATE', '知识库成员', '知识库成员', 1, 'SW_RPY_UPDATE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_RESTORE', '知识库成员', '知识库成员', 1, 'SW_RPY_RESTORE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_RPY_RECYCLE', '知识库成员', '知识库成员', 1, 'SW_RPY_RECYCLE');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_CREATE', 'SW_RPY_CREATE', 1, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_DELETE', 'SW_RPY_DELETE', 1, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_ARCHIVE', 'SW_RPY_ARCHIVE', 1, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_UPDATE', 'SW_RPY_UPDATE', 1, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_RESTORE', 'SW_RPY_RESTORE', 1, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_RPY_RECYCLE', 'SW_RPY_RECYCLE', 1, 'sward', 'site,email,qywechat', 1, 'true');

-- 通知成员
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('0905e7249b05', 'SW_RPY_CREATE', 'SW_RPY_CREATE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('f018f473d827', 'SW_RPY_DELETE', 'SW_RPY_DELETE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('732e5f4f7a96', 'SW_RPY_ARCHIVE', 'SW_RPY_ARCHIVE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('278f538aad27', 'SW_RPY_UPDATE', 'SW_RPY_UPDATE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('11aace809399', 'SW_RPY_RESTORE', 'SW_RPY_RESTORE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('usjeksiensho', 'SW_RPY_RECYCLE', 'SW_RPY_RECYCLE');


-- 项目角色
INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_DOC_CREATE', '知识库管理员', '知识库管理员', 2, 'SW_DOC_CREATE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_DOC_DELETE', '知识库管理员', '知识库管理员', 2, 'SW_DOC_DELETE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_DOC_ARCHIVE', '知识库管理员', '知识库管理员', 2, 'SW_DOC_ARCHIVE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_DOC_RESTORE', '知识库管理员', '知识库管理员', 2, 'SW_DOC_RESTORE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_REVIVW_REVIVW', '评审成员', '评审成员', 2, 'SW_REVIVW_REVIVW');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_REVIVW_REVOKE', '评审成员', '评审成员', 2, 'SW_REVIVW_REVOKE');

INSERT INTO pcs_prc_vrole (id, name, des, type, role_group)
    VALUES ('SW_REVIVW_CREATE', '评审成员', '评审成员', 2, 'SW_REVIVW_CREATE');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_DOC_CREATE', 'SW_DOC_CREATE', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_DOC_DELETE', 'SW_DOC_DELETE', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_DOC_ARCHIVE', 'SW_DOC_ARCHIVE', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_REVIVW_REVIVW', 'SW_REVIVW_REVIVW', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_REVIVW_REVOKE', 'SW_REVIVW_REVOKE', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_REVIVW_CREATE', 'SW_REVIVW_CREATE', 2, 'sward', 'site,email,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('SW_DOC_RESTORE', 'SW_DOC_RESTORE', 2, 'sward', 'site,email,qywechat', 1, 'true');

--通知虚拟用户
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('a1f93b72c4d1', 'SW_DOC_CREATE', 'SW_DOC_CREATE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('b8c62f9d7e3a', 'SW_DOC_DELETE', 'SW_DOC_DELETE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('c3d57e1a9f84', 'SW_DOC_ARCHIVE', 'SW_DOC_ARCHIVE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('d4e68f2b0a95', 'SW_DOC_RESTORE', 'SW_DOC_RESTORE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('e5f79a3c1b06', 'SW_REVIVW_REVOKE', 'SW_REVIVW_REVOKE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('g7b91c5e3d28', 'SW_REVIVW_CREATE', 'SW_REVIVW_CREATE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('h8c02d6f4e39', 'SW_DOC_RESTORE', 'SW_DOC_RESTORE');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('47562ss7c4as', 'SW_REVIVW_REVIVW', 'SW_REVIVW_REVIVW');



INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('24f05302cc05', 'SW_RPY_UPDATE', 'email', '更新知识库', '${userName}更新知识库${rpyName}基本信息', '/#/repository/${rpyId}/set/basicInfo', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a9af52b884bd', 'SW_RPY_DELETE', 'email', '删除知识库', '${userName}删除知识库${rpyName}', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('vrth2sase4fv', 'SW_RPY_RECYCLE', 'email', '移入回收站知识库', '${userName}将知识库${rpyName}移入回收站', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('5095e646e7e7', 'SW_RPY_RESTORE', 'email', '恢复知识库', '${userName}恢复知识库${rpyName}', '/#/repository/${rpyId}/overview', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e3a38397d5c4', 'SW_RPY_ARCHIVE', 'email', '归档知识库', '${userName}归档知识库${rpyName}', '/#/setting/archived', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0c2382d46999', 'SW_RPY_CREATE', 'email', '创建知识库', '${userName}创建知识库${rpyName}', '/#/repository/${rpyId}/overview', 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('37ae01bd8ccd', 'SW_DOC_RESTORE', 'email', '恢复文档', '${userName}恢复了知识库${rpyName}的文档${docName}', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('48175eba222c', 'SW_DOC_ARCHIVE', 'email', '归档文档', '${userName}归档知识库${rpyName}的文档${docName}', '/#/repository/${rpyId}/set/archivedBin', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('8140d5205942', 'SW_DOC_DELETE', 'email', '删除文档', '${userName}删除知识库${rpyName}的文档${docName}', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4d92e8f75fc0', 'SW_DOC_CREATE', 'email', '创建文档', '${userName}创建知识库${rpyName}的文档${docName}', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('33c87ccdd93d', 'SW_REVIVW_REVIVW', 'email', '评审文档', '${userName}评审知识库${rpyName}的文档${docName} 状态：${status}', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4613bf23af2e', 'SW_REVIVW_REVOKE', 'email', '撤销评审', '${userName}撤销评审${reviewName}', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4209a128a9df', 'SW_REVIVW_CREATE', 'email', '创建评审', '${userName}创建评审${reviewName}', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('6ccf0d036a08', 'SW_RPY_CREATE', 'site', '创建知识库', '${userName}创建知识库${rpyName}', '/#/repository/${rpyId}/overview', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d363fe4d16f1', 'SW_RPY_UPDATE', 'site', '更新知识库', '${userName}更新知识库${rpyName}基本信息', '/#/repository/${rpyId}/set/basicInfo', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0a93d8537612', 'SW_RPY_ARCHIVE', 'site', '归档知识库', '${userName}归档知识库${rpyName}', '/#/setting/archived', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('785423sesae5', 'SW_RPY_RECYCLE', 'site', '移入回收站知识库', '${userName}将知识库${rpyName}移入回收站', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4fd76580f5e2', 'SW_RPY_RESTORE', 'site', '恢复知识库', '${userName}恢复知识库${rpyName}', '/#/repository/${rpyId}/overview', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('9a5fc0279bf0', 'SW_RPY_DELETE', 'site', '删除知识库', '${userName}删除知识库${rpyName}', NULL, 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d6df3c82001e', 'SW_REVIVW_CREATE', 'site', '创建评审', '${userName}创建评审${reviewName}', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d77bc0a6208d', 'SW_DOC_RESTORE', 'site', '恢复文档', '${userName}恢复文档${docName}', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4ceeb15fe096', 'SW_REVIVW_REVOKE', 'site', '撤销评审', '${userName}撤销评审${reviewName}', NULL, 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e6054fb84d41', 'SW_DOC_ARCHIVE', 'site', '归档了文档', '${userName}归档知识库${rpyName}的文档${docName}', '/#/repository/${rpyId}/set/archivedBin', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('3283308ac517', 'SW_DOC_DELETE', 'site', '删除文档', '${userName}删除知识库${rpyName}的文档${docName}', '', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('5e775b50efa3', 'SW_DOC_CREATE', 'site', '创建文档', '${userName}创建知识库${rpyName}的文档${docName}', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a7a83657f5cf', 'SW_REVIVW_REVIVW', 'site', '评审文档', '${userName}评审知识库${rpyName}的文档${docName} 状态：${status}', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('13b62e8ad717', 'SW_RPY_CREATE', 'qywechat', NULL, E'## 知识库创建消息 \n> 知识库：<font color=info>**[${rpyName}](${qywxurl})**</font> \n> 创建人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/overview', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('30f5a5d553b7', 'SW_RPY_DELETE', 'qywechat', NULL, E'## 删除知识库消息 \n> 知识库：<font color=warning>**${rpyName}**</font> \n> 操作人：<font color=comment>${userName}</font>', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('ade4s2e58a6x', 'SW_RPY_RECYCLE', 'qywechat', NULL, E'## 移入回收站消息 \n> 知识库：<font color=warning>**${rpyName}**</font> \n> 操作人：<font color=comment>${userName}</font>', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c4df74e436d2', 'SW_RPY_UPDATE', 'qywechat', NULL, E'## 更新知识库消息 \n> 知识库：<font color=info>**[${rpyName}](${qywxurl})**</font> \n> 操作人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/set/basicInfo', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e4af0d92977f', 'SW_RPY_ARCHIVE', 'qywechat', NULL, E'## 归档知识库消息 \n> 知识库：<font color=warning>**[${rpyName}](${qywxurl})**</font> \n> 操作人：<font color=comment>${userName}</font>', '/#/setting/archived', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('06503349c874', 'SW_RPY_RESTORE', 'qywechat', NULL, E'## 恢复知识库消息 \n> 知识库：<font color=info>**[${rpyName}](${qywxurl})**</font> \n> 操作人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/overview', 'sward', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('bc4ba0ea31d8', 'SW_DOC_ARCHIVE', 'qywechat', NULL, E'## 归档文档消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 文档：<font color=warning>**[${docName}](${qywxurl})**</font> \n> 操作人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/set/archivedBin', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('712fc4e7c998', 'SW_DOC_RESTORE', 'qywechat', NULL, E'## 恢复文档消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 文档：<font color=info>**[${docName}](${qywxurl})**</font> \n> 操作人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('450d89b980a2', 'SW_REVIVW_REVOKE', 'qywechat', NULL, E'## 撤销评审消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 评审单：<font color=warning>**[${docName}](${qywxurl})**</font> \n> 创建人：<font color=comment>${userName}</font>', NULL, 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('6ecc2bdab1cb', 'SW_REVIVW_REVIVW', 'qywechat', NULL, E'## 评审文档消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 文档：<font color=info>**[${reviewName}](${qywxurl})**</font> \n> 评审人：<font color=comment>${userName}</font> \n> 状态：<font color=${reviewColor}>${status}</font>', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0f79708eb54b', 'SW_DOC_CREATE', 'qywechat', NULL, E'## 文档创建消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 文档：<font color=info>**[${docName}](${qywxurl})**</font> \n> 创建人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/doc/rich/${nodeId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b813e326a8e6', 'SW_REVIVW_CREATE', 'qywechat', NULL, E'## 评审创建消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 评审单：<font color=info>**[${reviewName}](${qywxurl})**</font> \n> 创建人：<font color=comment>${userName}</font>', '/#/repository/${rpyId}/set/review/${reviewId}', 'sward', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e9947454f4bd', 'SW_DOC_DELETE', 'qywechat', NULL, E'## 删除文档消息 \n> 知识库：<font color=comment>**${rpyName}**</font> \n> 文档：<font color=warning>**${docName}**</font> \n> 操作人：<font color=comment>${userName}</font>', NULL, 'sward', NULL);




