INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type, status, archived_time, archived_user_id, archived_desc, recycle, recycle_time, recycle_user_id) VALUES ('043cd0188d3d', '事项页面介绍', '7904ebd674d2', 'e0937312d93b', '111111', '2024-11-26 11:17:09', '2024-11-26 11:18:19', 1, 2, 'e0937312d93b;', 'document', 'markdown', 'nomal', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type, status, archived_time, archived_user_id, archived_desc, recycle, recycle_time, recycle_user_id) VALUES ('e0937312d93b', '事项模块', '7904ebd674d2', NULL, '111111', '2023-12-28 17:33:41', '2024-11-26 11:16:40', 0, 1, NULL, 'category', NULL, 'nomal', NULL, NULL, NULL, '0', NULL, NULL);
INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type, status, archived_time, archived_user_id, archived_desc, recycle, recycle_time, recycle_user_id) VALUES ('1cc90b14f228', '工作周报', '7904ebd674d2', 'e0937312d93b', '111111', '2024-11-26 11:16:44', '2024-11-26 11:17:03', 0, 2, 'e0937312d93b;', 'document', 'document', 'nomal', NULL, NULL, NULL, '0', NULL, NULL);

INSERT INTO wiki_repository (id, name, type_id, master, limits, create_time, icon_url, description, status, archived_time, archived_user_id, archived_desc, recycle, recycle_time, recycle_user_id) VALUES ('7904ebd674d2', '示例知识库', '1', '111111', '0', '2022-07-29', 'repository3.png', '示例知识库', 'nomal', NULL, NULL, NULL, '0', NULL, NULL);

INSERT INTO wiki_category (id, name, repository_id, parent_category_id, master, update_time, icon_url, sort, dimension, tree_path) VALUES ('e0937312d93b', '目录1', '7904ebd674d2', NULL, '111111', '2023-12-28 17:33:41', NULL, 0, 1, NULL);





INSERT INTO wiki_document (id, name, repository_id, type_id, category_id, master, update_time, details, detail_text, sort, dimension, tree_path) VALUES ('1cc90b14f228', NULL, NULL, NULL, NULL, NULL, NULL, '[{"type":"paragraph","children":[{"text":"部门：运营部"}]},{"type":"paragraph","children":[{"text":"汇报人： @成员"}]},{"type":"paragraph","children":[{"text":"时间："},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":" - "},{"type":"date","dateValue":"2024-05-30","children":[{"text":""}]},{"text":""}]},{"type":"head","children":[{"text":" ","backgroundColor":"#FF5630"},{"backgroundColor":"#FFFFFF","text":" "},{"text":"本周工作内容"}],"head":"h1","id":"1huhjldb"},{"type":"table","children":[{"type":"table-colgroup","key":"row_713b4342-f9b0-4155-8ae4-79aae44890ec","data":{},"children":[{"type":"table-col","key":"row_21f2bac9-07eb-48a9-9f1c-a5a9a036cd34","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_9c5a5f89-4954-4c41-98a3-3592c58b7aa9","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_ef8208ad-b8b9-402c-8ce2-71ce617390d0","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_0492b59d-d4bf-42b8-8ade-e44cbe920619","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_3143386f-79cb-45e9-ab0c-a76b65cf55c6","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_f5ba6e13-8ccf-447f-8ddf-f4d7a2652979","data":{},"children":[{"type":"table-cell","key":"cell_ba107d53-6851-477b-9456-b28b4bebd1af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"时间"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_ca407a5e-1a23-4a16-8bdc-cf4df1ff3885","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"重要工作"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_a30d6cc9-9603-4628-a4b0-76090ab21da8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"工作进展"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_9cbf9af3-0903-4bea-9009-4b4c2e2d6fd1","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"风险及应对措施"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_79f5302c-5cac-45c1-9bf8-eeb7ccf59095","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"说明"}]}]}],"width":"100px","height":"44px","head":true}]},{"type":"table-row","key":"row_244fb233-4bbe-4478-9986-51cd8f68331b","data":{},"children":[{"type":"table-cell","key":"cell_2c3b1a21-15c8-4487-9688-db95d10ee253","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_4d7848cb-26cb-4ea5-a849-20cf69e547d8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_dad25f8a-96a0-49cf-a214-cb3c9aea5735","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ef9fb1dc-19c5-4b8e-ac3b-626a2b4aa6af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_365efdfd-95be-4e3b-874c-80206c71c25a","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_b1b22943-45fc-4266-9328-2443208c9fc1","data":{},"children":[{"type":"table-cell","key":"cell_d88ec320-deb5-455e-a5a2-009ce403c654","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_076afd7b-ae30-4181-a57a-6375afaf1711","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_2fa50198-2c53-412f-9913-0eea04eabdc4","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_2db93251-4a72-4998-b4b7-c29b74c85a02","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_407041e4-366e-4cba-8f2f-0a8412edc11f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_e6b7a617-1acf-4b22-a932-87e2ea45050c","data":{},"children":[{"type":"table-cell","key":"cell_3d44b786-26da-48cc-a6f4-65f441b27929","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_e34b8dbb-724b-4a34-ac7f-08ee728bcd39","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ded04085-d994-4a69-8183-fa2653c60559","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_f4ee16ff-4a22-4b17-b259-dab17cb7764f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_5ca74343-8104-4b28-ad39-161edd5086a0","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_ae67aead-b2c9-4929-8c47-c3eb94ee4e71","data":{},"children":[{"type":"table-cell","key":"cell_45100ed2-e5cd-43ed-b420-06e2bf636376","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_0ecf35ff-3fd8-4ffc-ba15-39102d2107e2","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_8210bf53-3b69-4c95-b83e-55a307ecbf08","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ae9dbf99-e095-47ff-8a38-1c5d80191d77","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_6eaa114d-fde2-407b-afe6-a52ebe43b411","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_c58f8559-99d1-4566-8dd2-59f0d092de0e","data":{},"children":[{"type":"table-cell","key":"cell_a260f83a-a979-4463-a6c7-3f7d43e44967","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_11d1b05e-2ff2-4923-a9e5-7a8fb14caea0","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_bd6ebf72-db9e-486a-bc8c-dfcc4551901b","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_40709aeb-7926-4a99-be93-492032af5736","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_4b44ec79-3818-4831-a9fe-32c524615d19","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{},"head":1},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" ","color":"#FFFFFF","backgroundColor":"#FF5630"},{"text":" 本周工作总结"}],"head":"h1","id":"1huhjp6u"},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"填写对本周工作的总结"}]},{"type":"list-item","children":[{"text":"……"}]},{"type":"list-item","children":[{"text":"……"}]}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" ","backgroundColor":"#FF5630"},{"text":" 下周工作计划"}],"head":"h1","id":"1huhjrua"},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"具体描述下周工作内容或项目，并在行末插入任务截止时间"}]},{"type":"list-item","children":[{"text":"……"}]},{"type":"list-item","children":[{"text":"……"}]}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]}]', '部门：运营部

汇报人： @成员

时间：
﻿
 -
﻿
﻿

  本周工作内容

时间



重要工作



工作进展



风险及应对措施



说明




﻿
﻿
﻿




﻿




﻿




﻿




﻿





﻿
﻿
﻿




﻿




﻿




﻿




﻿





﻿
﻿
﻿




﻿




﻿




﻿




﻿





﻿
﻿
﻿




﻿




﻿




﻿




﻿





﻿
﻿
﻿




﻿




﻿




﻿




﻿


﻿


  本周工作总结
填写对本周工作的总结
……
……

﻿


  下周工作计划
具体描述下周工作内容或项目，并在行末插入任务截止时间
……
……

﻿


﻿


﻿
', NULL, NULL, NULL);
INSERT INTO wiki_document (id, name, repository_id, type_id, category_id, master, update_time, details, detail_text, sort, dimension, tree_path) VALUES ('043cd0188d3d', NULL, NULL, NULL, NULL, NULL, NULL, '[{"type":"paragraph","children":[{"text":"1. 事项列表"}]},{"type":"paragraph","children":[{"text":"2. 事项详情"}]},{"type":"paragraph","children":[{"text":"3. 事项类型"}]}]', '1. 事项列表', NULL, NULL, NULL);

INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('1a85ea2556e7', '事项模块', 'category', 'e0937312d93b', '111111', '7904ebd674d2', '2024-11-26 11:20:34.705');
INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('6437743afcb0', '事项页面介绍', 'document', '043cd0188d3d', '111111', '7904ebd674d2', '2024-11-26 11:20:35.576');
INSERT INTO wiki_recent (id, name, model, model_id, master_id, repository_id, recent_time) VALUES ('85819b6f5676', '工作周报', 'document', '1cc90b14f228', '111111', '7904ebd674d2', '2024-11-26 11:20:35.913');

INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('bbd22724c949', 'repository1.png', 'repository1.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('0c1faf0d2ec6', 'repository2.png', 'repository2.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('b5952fb1da49', 'repository3.png', 'repository3.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('f253b41fa064', 'repository4.png', 'repository4.png', 'repository');

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('SWARD_LOGTYPE_DOCUMENTADD', '添加文档', 'sward');


INSERT INTO pcs_ucc_dm_user (id, domain_id, user_id, type) VALUES ('bfd4660ddbb9', '7904ebd674d2', '111111', 0);
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

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d2b6f6d084e7', '111111', 'oug5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('32a4d0eed003', '111111', '1f777ba063f7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c24fc146f68c', '111111', '7ff4702ebe64');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d444c8920ebb', '111111', '321751dfd3a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('64a5bbd4bc6a', '111111', '63fd58715558');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('879506ff42fe', '111111', 'f8b5c661b0fc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3bae1d616b8d', '111111', '0c73e628fd54');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ff4ecc41dcca', '111111', '64bdf62686a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b7d770a6a28c', '111111', '9633d9475886');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('76117ac5975d', '111111', 'dd81bdbb52bc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d733a441f6b7', '111111', '57a3bcd1e5fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5057e8eb3f0a', '111111', '428be660dea3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('78f31d9fb554', '111111', '5fb7863b09a8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c580fa7417e5', '111111', '043e412151db');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1869d09f7baa', '111111', '925371be8ec6');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6d3b90a99b6e', '111111', '447d9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c1e2234d43dd', '111111', '890e7d41decf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('555b4438ffd6', '111111', '585d26bcbdf3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('977b83a02277', '111111', 'e8bf9843bc9d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5ab4228748bd', '111111', 'cb954a7c0be3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1363746a2ce1', '111111', '9c99b8a096c8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7fcbf04ad6f6', '111111', '325c2503007f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d4fa405ed6b3', '111111', '6b61fbe5091a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b013fc145ae6', '111111', 'wqre9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('59e81503e67a', '111111', '43e7d41decf7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3d43bf23cef2', '111111', 'hfg5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ad40263be4aa', '111111', 'hf43e412151e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3601eaf9a6d0', '111111', '4235d2624bdf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ebd031405c51', '111111', '138654cdc36c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1ff041d83714', '111111', '746c3becb86f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c4cc7bedb237', '111111', 'a11ca9e4559e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d9b85595468e', '111111', '47fb980c2919');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('73ec6efe7f56', '111111', 'f9e27dd6f76e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d1aafd9a35ce', '111111', '5d5b64c3d172');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7ab376aa258a', '111111', 'f569dfa96880');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('58c597adf054', '111111', '48717b977129');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('77da43188035', '111111', '49e12c2b8fca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cadf134301a0', '111111', 'c5af706628c2');


INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d515760eaadb', 'pro_111111', '4dfb4eb2c40d');

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
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('353377bd43d9', 'c7e77e842563', '4dfb4eb2c40d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e47a9b3e4dc4', 'f0763af198e8', '4dfb4eb2c40d');

INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('9e973dbe3ecf', 'bfa59d952c64', '7904ebd674d2', '111111');

INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('bfa59d952c64', '7904ebd674d2', '1fac7297bd6c', 0);
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('e5f12d1372da', '7904ebd674d2', '070c9ac2734a', 0);
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id, business_type) VALUES ('ffcb15cc5283', '7904ebd674d2', 'c7e77e842563', 2);


