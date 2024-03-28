INSERT INTO wiki_document (id, name, repository_id, type_id, category_id, master, update_time, details, detail_text, sort, dimension, tree_path) VALUES ('8f1889bcfe9b', '文档', '43ef04320e51', 'document', NULL, '111111', '2023-12-28 17:33:22.377+08', '[{"type":"paragraph","children":[{"text":"附文字"}]}]', '附文字', 1, 1, NULL);
INSERT INTO wiki_document (id, name, repository_id, type_id, category_id, master, update_time, details, detail_text, sort, dimension, tree_path) VALUES ('81d30924eb59', '测试文档', '7904ebd674d2', 'document', NULL, '111111', '2023-12-28 17:33:52.717+08', '[{"type":"paragraph","children":[{"text":"测试文档"}]}]', '测试文档', 1, 1, NULL);
INSERT INTO wiki_category (id, name, repository_id, parent_category_id, master, update_time, icon_url, sort, dimension, tree_path) VALUES ('b1bdd8940001', '目录1', '43ef04320e51', NULL, '111111', '2023-12-28 17:33:01.46+08', NULL, 0, 1, NULL);
INSERT INTO wiki_category (id, name, repository_id, parent_category_id, master, update_time, icon_url, sort, dimension, tree_path) VALUES ('e0937312d93b', '目录1', '7904ebd674d2', NULL, '111111', '2023-12-28 17:33:41.898+08', NULL, 0, 1, NULL);

INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type) VALUES ('b1bdd8940001', '目录1', '43ef04320e51', NULL, '111111', '2023-12-28 17:33:01.46+08', '2023-12-28 17:33:01.46+08', 0, 1, NULL, 'category', NULL);
INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type) VALUES ('e0937312d93b', '目录1', '7904ebd674d2', NULL, '111111', '2023-12-28 17:33:41.898+08', '2023-12-28 17:33:41.898+08', 0, 1, NULL, 'category', NULL);
INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type) VALUES ('8f1889bcfe9b', '文档', '43ef04320e51', NULL, '111111', '2023-12-28 17:33:22.377+08', '2023-12-28 17:33:22.377+08', 1, 1, NULL, 'document', 'document');
INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type) VALUES ('81d30924eb59', '测试文档', '7904ebd674d2', NULL, '111111', '2023-12-28 17:33:52.717+08', '2023-12-28 17:33:52.717+08', 1, 1, NULL, 'document', 'document');


INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('b6369775d80b', '知识库1', 'repository', '43ef04320e51', '111111', '43ef04320e51', '2023-12-28 17:36:16.354');
INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('99284518de42', '文档', 'document', '8f1889bcfe9b', '111111', '43ef04320e51', '2023-12-28 17:36:17.537');
INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('32f9adce051b', '知识库2', 'repository', '7904ebd674d2', '111111', '7904ebd674d2', '2023-12-28 17:36:21.767');
INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('9a80bbe7e1da', '测试文档', 'document', '81d30924eb59', '111111', '7904ebd674d2', '2023-12-28 17:36:22.704');
INSERT INTO wiki_repository (id, name, type_id, master, limits, create_time, description, icon_url) VALUES ('43ef04320e51', '知识库1', '1', '111111', '0', '2022-07-29', '知识库1', '/image/c9724904fc5de37c');
INSERT INTO wiki_repository (id, name, type_id, master, limits, create_time, description, icon_url) VALUES ('7904ebd674d2', '知识库2', '1', '111111', '0', '2022-07-29', '知识库2', '/image/ec95cf5ee02e0437');

INSERT INTO pcs_ucc_dm_user (id, domain_id, user_id, type) VALUES ('bfd4660ddbb9', '7904ebd674d2', '111111', 0);
INSERT INTO pcs_ucc_dm_user (id, domain_id, user_id, type) VALUES ('d19fe2d0083a', '43ef04320e51', '111111', 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('1', 'admin', '管理员', 'system', '1', 1, 1);

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0674973ba9e6', '1', 'oug5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('088232502709', '1', '49e12c2b8fca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0a6b0735512a', '1', '746c3becb86f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0f252a84bf28', '1', '585d26bcbdf3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1b36154503ad', '1', 'e8bf9843bc9d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('22812022e97f', '1', 'f9e27dd6f76e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2d13eaa367d4', '1', '138654cdc36c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('334f561f9c7c', '1', '1f777ba063f7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('372b92672536', '1', '325c2503007f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('38e1a4b17302', '1', '4235d2624bdf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('439aee3f4884', '1', '925371be8ec6');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4577fab27e46', '1', '6b61fbe5091a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('484ef66818cb', '1', '64bdf62686a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4d5003c74262', '1', '57a3bcd1e5fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5a7f18ed6188', '1', '043e412151db');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5e0bf237dbb1', '1', '890e7d41decf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('769973d32f61', '1', '321751dfd3a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('76b21914d1e8', '1', 'hfg5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('77290abce8a7', '1', 'c5af706628c2');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7b98c1c65654', '1', 'dd81bdbb52bc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7ec416e4315c', '1', '9633d9475886');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('83af615f3214', '1', 'f8b5c661b0fc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('848b35f7df25', '1', 'wqre9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9390fb9924f1', '1', '47fb980c2919');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('adb47ff1f1ad', '1', '428be660dea3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b2921ddf758e', '1', '63fd58715558');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b489c5281a79', '1', 'a11ca9e4559e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b6f4b6789a19', '1', '5d5b64c3d172');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('be458e04e471', '1', '5fb7863b09a8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c869512db472', '1', 'cb954a7c0be3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cc0cc7b9e593', '1', '48717b977129');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cc43a9f9c85a', '1', '43e7d41decf7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d2479f3b3d48', '1', '9c99b8a096c8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e090bc966540', '1', '447d9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e3ce4060ad57', '1', '7ff4702ebe64');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e5e89123743e', '1', 'hf43e412151e');


INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('0c73e628fd54', '插件', 'SysPlugin', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('138654cdc36c', '消息发送方式', 'SysMessageSendType', '47fb980c2919', 41, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('1f777ba063f7', '系统角色管理', 'SysRoleSys', '63fd58715558', 48, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('321751dfd3a5', '项目角色管理', 'SysRoleProject', '63fd58715558', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('3e642d400783', '日志模板', 'SysLogTemplate', '5d2518f49057', 50, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('47fb980c2919', '消息中心', 'SysMessage', NULL, NULL, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('48717b977129', '待办', 'SysTodo', NULL, 52, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('49e12c2b8fca', '权限', 'SysPermission', '63fd58715558', 47, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('4f3307200787', '操作日志', 'SysLogList', '5d2518f49057', 45, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('5d5b64c3d172', '待办类型', 'SysTodoList', '48717b977129', 54, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('63fd58715558', '权限中心', 'SysPriviliege', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('746c3becb86f', '消息类型', 'SysMessageType', '47fb980c2919', 49, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('7ff4702ebe64', '项目功能管理', 'SysFeatrueProject', '63fd58715558', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('86db94ef62a6', '用户管理', 'SysUser', '1465dc3ae3c4', NULL, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('97c93ffe00c9', '组织管理', 'SysOrga', '1465dc3ae3c4', NULL, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('a11ca9e4559e', '消息通知方案', 'SysMessageNotice', '47fb980c2919', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('c5af706628c2', '系统功能管理', 'SysFeatrueSys', '63fd58715558', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('c9efe0b7e166', '日志类型', 'SysLogType', '5d2518f49057', 51, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f8b5c661b0fc', '产品授权', 'SysProductAuth', NULL, 46, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f9e27dd6f76e', '待办管理', 'SysSetting', '48717b977129', 53, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f569dfa96880', '版本与许可证', 'SysVersion', '48717b977129', 53, '1');

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a65b3d3aae57', '087db62560dc', 'email', '知识库添加通知', '<div style="display: flex;            align-items: center;            font-size: 14px;            justify-content: space-between;">        <div style="display: flex;align-items: center;">            <div  style="width: 25px;                   height: 25px;                   line-height: 25px;                   background-color: #ccc;                   border-radius: 32px;                   text-align: center;                   color: #fff;">${createUserIcon}</div>            <div style="display: flex;                    flex-direction: column;                    padding: 0 20px;                ">                <div>                    <span style="padding-right: 10px;">                        ${master}                    </span>                    创建了知识库                </div>                <div style="line-height: 40px;                        display: flex;                        align-items: center;                        height: 40px;">                    <div style="                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;                            margin-right: 10px;">                        <img src="${repositoryIcon}" alt="" width="16px" height="16px">                    </div>                    <div style="color: #5d70ea;                            cursor: pointer;                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;">                        ${repositoryName}                    </div>                </div>            </div>        </div>        <div style="font-size: 13px;">            ${updateTime}        </div>    </div>', '/index/repositorydetail/${repositoryId}/survey', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b962aa598e0b', '087db62560dc', 'site', '知识库添加', '<div style="display: flex;            align-items: center;            font-size: 14px;            justify-content: space-between;">        <div style="display: flex;align-items: center;">            <div  style="width: 25px;                   height: 25px;                   line-height: 25px;                   background-color: #ccc;                   border-radius: 32px;                   text-align: center;                   color: #fff;">${createUserIcon}</div>            <div style="display: flex;                    flex-direction: column;                    padding: 0 20px;                ">                <div>                    <span style="padding-right: 10px;">                        ${master}                    </span>                    创建了知识库                </div>                <div style="line-height: 40px;                        display: flex;                        align-items: center;                        height: 40px;">                    <div style="                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;                            margin-right: 10px;">                        <img src="${repositoryIcon}" alt="" width="16px" height="16px">                    </div>                    <div style="color: #5d70ea;                            cursor: pointer;                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;">                        ${repositoryName}                    </div>                </div>            </div>        </div>        <div style="font-size: 13px;">            ${updateTime}        </div>    </div>', '/index/repositorydetail/${repositoryId}/survey', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('7e1a8c427737', '087db62560dc', 'dingding', '知识库添加通知', '**知识库添加通知**${master} 添加了知识库 <font color="info"> ${repositoryName}</font>状态![图片](${{repositoryIcon})', NULL, 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('3cd3ae6efa80', '087db62560dc', 'qywechat', NULL, '**知识库添加通知**${master} 添加了知识库 <font color="info"> ${repositoryName}</font>状态![图片](${{repositoryIcon})', NULL, 'kanass', NULL);

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('087db62560dc', '知识库添加', '知识库添加', 'kanass');


INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('56b4489ba64a', '目录添加', '<div style="display: flex;            align-items: center;            font-size: 14px;            justify-content: space-between;">        <div style="display: flex;align-items: center;">            <div  style="width: 25px;                   height: 25px;                   line-height: 25px;                   background-color: #ccc;                   border-radius: 32px;                   text-align: center;                   color: #fff;">${createUserIcon}</div>            <div style="display: flex;                    flex-direction: column;                    padding: 0 20px;                ">                <div>                    <span style="padding-right: 10px;">                        ${master}                    </span>                    创建了目录                </div>                <div style="line-height: 40px;                        display: flex;                        align-items: center;                        height: 40px;">                    <div style="                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;                            margin-right: 10px;">                        <img src="images/file.png" alt="" width="16px" height="16px">                    </div>                    <div style="color: #5d70ea;                            cursor: pointer;                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;">                        ${categoryName}                    </div>                </div>            </div>        </div>        <div style="font-size: 13px;">            ${updateTime}        </div>    </div>', '/index/repositorydetail/${repositoryId}/folder/${categoryId}', 'kanass', '目录添加');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('c18ca29f3d32', '知识库添加', '<div style="display: flex;            align-items: center;            font-size: 14px;            justify-content: space-between;">        <div style="display: flex;align-items: center;">            <div  style="width: 25px;                   height: 25px;                   line-height: 25px;                   background-color: #ccc;                   border-radius: 32px;                   text-align: center;                   color: #fff;">${createUserIcon}</div>            <div style="display: flex;                    flex-direction: column;                    padding: 0 20px;                ">                <div>                    <span style="padding-right: 10px;">                        ${master}                    </span>                    创建了知识库                </div>                <div style="line-height: 40px;                        display: flex;                        align-items: center;                        height: 40px;">                    <div style="                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;                            margin-right: 10px;">                        <img src="${repositoryIcon}" alt="" width="16px" height="16px">                    </div>                    <div style="color: #5d70ea;                            cursor: pointer;                            font-size: 12px;                            height: 15px;                            line-height: 15px;                            border-radius: 5px;">                        ${repositoryName}                    </div>                </div>            </div>        </div>        <div style="font-size: 13px;">            ${updateTime}        </div>    </div>', '/index/repositorydetail/${repositoryId}/survey', 'kanass', '知识库添加');


INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('777da699a3d9', '知识库添加', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('c23cabd39186', '目录添加', 'kanass');

INSERT INTO pcs_mec_message_notice_connect_user (id, message_notice_id, user_id) VALUES ('fbaffc35f451', '300e1bce86d1', '111111');
INSERT INTO pcs_mec_message_notice_connect_user (id, message_notice_id, user_id) VALUES ('fc896ffe46d5', '097d6164bd56', '111111');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id) VALUES ('097d6164bd56', '087db62560dc', 1, 'kanass', 'dingding,email,qywechat,site');


INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('4dfb4eb2c40d', '知识库删除', 'RepositoryDelete', NULL, 55, '2');

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('389ab5c54692', '1fac7297bd6c', '4dfb4eb2c40d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4d23b6a02e3c', '2', '4dfb4eb2c40d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('84ca038f8f75', '2e018b908ac1', '4dfb4eb2c40d');

INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('2628fa355f74', '4763791e9126', '43ef04320e51', '111111');
INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('9e973dbe3ecf', 'bfa59d952c64', '7904ebd674d2', '111111');

INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('4763791e9126', '43ef04320e51', '2e018b908ac1', 0);
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('5ea808caadc2', '43ef04320e51', '67c91d87afd7', 0);
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('bfa59d952c64', '7904ebd674d2', '1fac7297bd6c', 0);
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('e5f12d1372da', '7904ebd674d2', '070c9ac2734a', 0);

INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('070c9ac2734a', '普通用户', '普通用户', 'system', '2', 2, 1, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('1af472d3e62d', '普通用户', '普通用户', 'system', '2', 1, 0, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('1fac7297bd6c', 'admin', '管理员', 'system', '2', 2, 0, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('2', 'admin', '管理员', 'system', '2', 1, 1, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('2e018b908ac1', 'admin', '管理员', 'system', '2', 2, 0, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type, default_role) VALUES ('67c91d87afd7', '普通用户', '普通用户', 'system', '2', 2, 0, 0);


INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('bbd22724c949', 'repository1.png', '/image/5fa65aea26863f2b', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('0c1faf0d2ec6', 'repository2.png', '/image/66f8f49794e65ec7', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('b5952fb1da49', 'repository3.png', '/image/ec95cf5ee02e0437', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('f253b41fa064', 'repository4.png', '/image/8fc1ee4be3865cd4', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('7488124b1b12', 'repository5.png', '/image/52eed62513ef3b3b', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('55fde63c707e', 'repository6.png', '/image/c9724904fc5de37c', 'repository');

