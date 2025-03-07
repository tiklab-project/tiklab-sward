INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('eac6b9855f61', '111111', 'soular', 'USER_LOGIN', NULL, '2024-11-26 11:14:44.026', 'soular', '{"logType":"USER_LOGIN","name":"admin","userName":"管理员","message":""}', NULL, NULL, 'admin');
INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('abceb6c38ae2', '111111', 'document', 'SWARD_LOGTYPE_DOCUMENTADD', NULL, '2024-11-26 11:16:44.514', 'sward', '{"documentType":"document","createUserIcon":"a","repositoryId":"7904ebd674d2","documentId":"1cc90b14f228","createUser":"admin","updateTime":"2024-11-26","documentName":"未命名文档"}', 'http://192.168.10.7:8060/#/repositorydetail/7904ebd674d2/doc/1cc90b14f228', NULL, '未命名文档');
INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('d292b24bd59f', '111111', 'document', 'SWARD_LOGTYPE_DOCUMENTUPDATENAME', NULL, '2024-11-26 11:16:56.508', 'sward', '{"newValue":"工作周报","createUserId":"111111","documentType":"document","createTime":"2024-11-26","createUserIcon":"管","repositoryId":"7904ebd674d2","documentId":"1cc90b14f228","createUserName":"admin","oldValue":"未命名文档"}', 'http://192.168.10.7:8060/#/repositorydetail/7904ebd674d2/doc/1cc90b14f228', NULL, '工作周报');
INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('e007c1826600', '111111', 'document', 'SWARD_LOGTYPE_DOCUMENTADD', NULL, '2024-11-26 11:17:10.4', 'sward', '{"documentType":"markdown","createUserIcon":"a","repositoryId":"7904ebd674d2","documentId":"043cd0188d3d","createUser":"admin","updateTime":"2024-11-26","documentName":"未命名文档"}', 'http://192.168.10.7:8060/#/repositorydetail/7904ebd674d2/markdownView/043cd0188d3d', NULL, '未命名文档');
INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('e1f770955a96', '111111', 'document', 'SWARD_LOGTYPE_DOCUMENTUPDATENAME', NULL, '2024-11-26 11:17:29.587', 'sward', '{"newValue":"事项模块文档","createUserId":"111111","documentType":"markdown","createTime":"2024-11-26","createUserIcon":"管","repositoryId":"7904ebd674d2","documentId":"043cd0188d3d","createUserName":"admin","oldValue":"未命名文档"}', 'http://192.168.10.7:8060/#/repositorydetail/7904ebd674d2/doc/043cd0188d3d', NULL, '事项模块文档');
INSERT INTO pcs_op_log (id, user_id, module, action_type, content, create_time, bgroup, data, link, abstract_content, action) VALUES ('933a599fb9b2', '111111', 'document', 'SWARD_LOGTYPE_DOCUMENTUPDATENAME', NULL, '2024-11-26 11:17:57.96', 'sward', '{"newValue":"事项页面介绍","createUserId":"111111","documentType":"markdown","createTime":"2024-11-26","createUserIcon":"管","repositoryId":"7904ebd674d2","documentId":"043cd0188d3d","createUserName":"admin","oldValue":"事项模块文档"}', 'http://192.168.10.7:8060/#/repositorydetail/7904ebd674d2/doc/043cd0188d3d', NULL, '事项页面介绍');

INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('070c9ac2734a', '普通用户', '普通用户', 'system', '2', 1, 0, 2, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('1af472d3e62d', '普通用户', '普通用户', 'system', '2', 0, 0, 1, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('1fac7297bd6c', 'admin', '管理员', 'system', '2', 0, 0, 2, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('2', 'admin', '管理员', 'system', '2', 1, 0, 1, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('2e018b908ac1', 'admin', '管理员', 'system', '2', 0, 0, 2, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('67c91d87afd7', '普通用户', '普通用户', 'system', '2', 0, 0, 2, NULL);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('c7e77e842563', '项目超级管理员', '项目超级管理员角色,只能存在一个。', 'system', '2', 2, 2, 2, 'pro_111111');
INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('f0763af198e8', '项目超级管理员', '项目超级管理员角色,只能存在一个。', 'system', '2', 2, 2, 2, 'pro_111111');


INSERT INTO wiki_document_template (id, name, description, details, sort, icon_url, detail_text)  VALUES ('69a50100', '工作周报（通用版）', NULL, '[{"type":"paragraph","children":[{"text":"部门：运营部"}]},{"type":"paragraph","children":[{"text":"汇报人： @成员"}]},{"type":"paragraph","children":[{"text":"时间："},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":" - "},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]},{"type":"head","children":[{"text":" ","backgroundColor":"#FF5630"},{"backgroundColor":"#FFFFFF","text":" "},{"text":"本周工作内容"}],"head":"h1","id":"1huhjldb"},{"type":"table","children":[{"type":"table-colgroup","key":"row_713b4342-f9b0-4155-8ae4-79aae44890ec","data":{},"children":[{"type":"table-col","key":"row_21f2bac9-07eb-48a9-9f1c-a5a9a036cd34","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_9c5a5f89-4954-4c41-98a3-3592c58b7aa9","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_ef8208ad-b8b9-402c-8ce2-71ce617390d0","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_0492b59d-d4bf-42b8-8ade-e44cbe920619","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_3143386f-79cb-45e9-ab0c-a76b65cf55c6","width":"20.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_f5ba6e13-8ccf-447f-8ddf-f4d7a2652979","data":{},"children":[{"type":"table-cell","key":"cell_ba107d53-6851-477b-9456-b28b4bebd1af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"时间"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_ca407a5e-1a23-4a16-8bdc-cf4df1ff3885","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"重要工作"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_a30d6cc9-9603-4628-a4b0-76090ab21da8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"工作进展"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_9cbf9af3-0903-4bea-9009-4b4c2e2d6fd1","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"风险及应对措施"}]}]}],"width":"100px","height":"44px","head":true},{"type":"table-cell","key":"cell_79f5302c-5cac-45c1-9bf8-eeb7ccf59095","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"说明"}]}]}],"width":"100px","height":"44px","head":true}]},{"type":"table-row","key":"row_244fb233-4bbe-4478-9986-51cd8f68331b","data":{},"children":[{"type":"table-cell","key":"cell_2c3b1a21-15c8-4487-9688-db95d10ee253","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_4d7848cb-26cb-4ea5-a849-20cf69e547d8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_dad25f8a-96a0-49cf-a214-cb3c9aea5735","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ef9fb1dc-19c5-4b8e-ac3b-626a2b4aa6af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_365efdfd-95be-4e3b-874c-80206c71c25a","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_b1b22943-45fc-4266-9328-2443208c9fc1","data":{},"children":[{"type":"table-cell","key":"cell_d88ec320-deb5-455e-a5a2-009ce403c654","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_076afd7b-ae30-4181-a57a-6375afaf1711","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_2fa50198-2c53-412f-9913-0eea04eabdc4","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_2db93251-4a72-4998-b4b7-c29b74c85a02","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_407041e4-366e-4cba-8f2f-0a8412edc11f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_e6b7a617-1acf-4b22-a932-87e2ea45050c","data":{},"children":[{"type":"table-cell","key":"cell_3d44b786-26da-48cc-a6f4-65f441b27929","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_e34b8dbb-724b-4a34-ac7f-08ee728bcd39","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ded04085-d994-4a69-8183-fa2653c60559","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_f4ee16ff-4a22-4b17-b259-dab17cb7764f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_5ca74343-8104-4b28-ad39-161edd5086a0","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_ae67aead-b2c9-4929-8c47-c3eb94ee4e71","data":{},"children":[{"type":"table-cell","key":"cell_45100ed2-e5cd-43ed-b420-06e2bf636376","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_0ecf35ff-3fd8-4ffc-ba15-39102d2107e2","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_8210bf53-3b69-4c95-b83e-55a307ecbf08","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_ae9dbf99-e095-47ff-8a38-1c5d80191d77","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_6eaa114d-fde2-407b-afe6-a52ebe43b411","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_c58f8559-99d1-4566-8dd2-59f0d092de0e","data":{},"children":[{"type":"table-cell","key":"cell_a260f83a-a979-4463-a6c7-3f7d43e44967","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_11d1b05e-2ff2-4923-a9e5-7a8fb14caea0","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_bd6ebf72-db9e-486a-bc8c-dfcc4551901b","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_40709aeb-7926-4a99-be93-492032af5736","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_4b44ec79-3818-4831-a9fe-32c524615d19","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{},"head":1},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" ","color":"#FFFFFF","backgroundColor":"#FF5630"},{"text":" 本周工作总结"}],"head":"h1","id":"1huhjp6u"},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"填写对本周工作的总结"}]},{"type":"list-item","children":[{"text":"……"}]},{"type":"list-item","children":[{"text":"……"}]}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" ","backgroundColor":"#FF5630"},{"text":" 下周工作计划"}],"head":"h1","id":"1huhjrua"},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"具体描述下周工作内容或项目，并在行末插入任务截止时间"}]},{"type":"list-item","children":[{"text":"……"}]},{"type":"list-item","children":[{"text":"……"}]}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]}]', NULL, '/image/00be262435139f96', '部门：运营部

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
');
INSERT INTO wiki_document_template (id, name, description, details, sort, icon_url, detail_text)  VALUES ('562158f9', '待办工作清单', NULL, '[{"type":"paragraph","children":[{"text":"部门:                                     职位：                                   姓名：@成员                    时间： "},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"divider","children":[{"text":""}]},{"type":"head","children":[{"text":" 紧急且重要 ","backgroundColor":"#FFC400"}],"head":"h1","id":"1huhts9u"},{"type":"check-list-item","children":[{"text":"一级合作伙伴周会"}]},{"type":"check-list-item","children":[{"text":"一级供应商合作洽谈"}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" 紧急但不重要 ","backgroundColor":"#FFC400"}],"head":"h1","id":"1huhu94e"},{"type":"check-list-item","children":[{"text":"续约合同签订"}]},{"type":"check-list-item","children":[{"text":"……"}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" 不紧急但重要 ","backgroundColor":"#FFC400"}],"head":"h1","id":"1huhu6hd"},{"type":"check-list-item","children":[{"text":"确认合作伙伴端午贺卡"}]},{"type":"check-list-item","children":[{"text":"……"}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":" 不紧急不重要 ","backgroundColor":"#FFC400"}],"head":"h1","id":"1huhu7i9"},{"type":"check-list-item","children":[{"text":"办公电脑性能优化"}]},{"type":"check-list-item","children":[{"text":"……"}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]}]', NULL, '/image/494908cf1638e43e', '部门:                                     职位：                                   姓名：@成员                    时间：
﻿
﻿

﻿


﻿

 紧急且重要
一级合作伙伴周会
一级供应商合作洽谈

﻿


 紧急但不重要
续约合同签订
……

﻿


 不紧急但重要
确认合作伙伴端午贺卡
……

﻿


 不紧急不重要
办公电脑性能优化
……

﻿


﻿


﻿


﻿
');
INSERT INTO wiki_document_template (id, name, description, details, sort, icon_url, detail_text)  VALUES ('c9f11532', '项目运营计划', NULL, '[{"type":"head","children":[{"text":"项目成员","color":"#0747A6","lineHeight":3}],"head":"h3","id":"1huhmhl8"},{"type":"paragraph","children":[{"text":"负责人：@成员"}]},{"type":"paragraph","children":[{"text":"团队成员：@成员"}]},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":"项目背景描述","lineHeight":3,"color":"#0747A6"}],"head":"h3","id":"1huhmheq"},{"type":"paragraph","children":[{"text":"如：为了提升用户满意度，所以此次立项对哪些功能进行改造","color":"#97A0AF"}]},{"type":"paragraph","children":[{"color":"#97A0AF","text":""}]},{"type":"paragraph","children":[{"color":"#97A0AF","text":""}]},{"type":"head","children":[{"color":"#0747A6","text":"目标","lineHeight":3}],"head":"h3","id":"1huhmj5l"},{"type":"paragraph","children":[{"text":"如：达成目标用到的方式和方法","color":"#97A0AF"}],"head":"h3","id":"1huhmjkh"},{"type":"paragraph","head":"h3","id":"1huhmjkh","children":[{"color":"#97A0AF","text":""}]},{"type":"paragraph","head":"h3","id":"1huhmjkh","children":[{"color":"#97A0AF","text":""}]},{"type":"head","head":"h3","id":"1huhmkak","children":[{"color":"#0747A6","text":"具体执行方案","lineHeight":3}]},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"策略1："}]}]},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"策略2："}]}]},{"type":"bulleted-list","children":[{"type":"list-item","children":[{"text":"策略3："}]}]},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":"执行计划","color":"#0747A6","lineHeight":3}],"head":"h3","id":"1huhmmio"},{"type":"paragraph","children":[{"text":"项目里程碑："}]},{"type":"table","children":[{"type":"table-colgroup","key":"row_9a13ed2d-73e6-4d8a-b475-74ef2b6d749f","data":{},"children":[{"type":"table-col","key":"row_0640c4b6-c62f-44dc-9796-bedc0ad31428","width":"25.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_8cd453ff-a180-4ded-a262-8856a4efdc77","width":"25.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_ed4b7960-3f32-437e-9ab5-a066a9b3a224","width":"25.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_c437dcee-0749-478f-9d14-c150cc445d5d","width":"25.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_ae59d7a2-a52f-46c9-9342-db0a26fff67c","data":{},"children":[{"type":"table-cell","key":"cell_d3aee366-6752-4381-bc8a-14a7022634fd","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_306d317c-d95a-4fc3-b74e-a820172e94d8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_b90e29cf-7833-493b-8b67-291c9b003ada","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_03fa7a08-57f1-44de-b8db-d82a117ea480","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_d81095e5-b5f2-4f5f-bdec-1482a99641d2","data":{},"children":[{"type":"table-cell","key":"cell_adbd5447-e98b-4403-a454-501b14ba2eb2","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_9d60f266-06e6-47c1-af5b-528aa8278341","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_adedc707-2c97-4e78-86fb-811e6e1be623","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_479e634d-9474-43cf-96c0-40beb48f353f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_2ec32fa6-d46d-4bf3-8214-9161b2e70e5a","data":{},"children":[{"type":"table-cell","key":"cell_3ba2d802-9c74-44b6-b194-08d254b9fe15","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_2bebc784-fb27-41d5-86d8-b0f949c09627","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_48b0f0df-94df-44d1-93af-74b7f886d4af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_3bda4d01-aaec-4a30-8699-a04e791af393","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_51f9eda5-f963-49ad-933d-bcfaff2db9de","data":{},"children":[{"type":"table-cell","key":"cell_31281292-693c-489d-8477-58f11f9e6bc9","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_83449175-beb9-48b2-bb16-794a7294abee","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_71119dda-5eb5-46a2-8455-5bbf9561f2d8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_fb86e1e5-8784-494f-bf64-e7d8a6a160e9","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{}},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":"项目预算","color":"#0747A6","lineHeight":3}],"head":"h3","id":"1huhmo4t"},{"type":"paragraph","children":[{"text":"如：预算1"}]},{"type":"table","children":[{"type":"table-colgroup","key":"row_a104b4fc-dbc9-43a4-9d7f-5296c934d660","data":{},"children":[{"type":"table-col","key":"row_7db9b2eb-5169-4f18-aca7-e7be921c9fb0","width":"50.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_6c4607c9-0570-4580-ae48-69658e0f6819","width":"50.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_ee43c4ed-0f7c-45b3-906e-3ad8c623a28d","data":{},"children":[{"type":"table-cell","key":"cell_4400ba89-8574-4a6c-86da-43b35dab402b","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"预算1方案名称"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_acfb7cbb-060c-4f9c-ab67-74204de2a749","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"老用户回访计划"}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_041fba79-57c7-4e35-832f-fd6db5a500c2","data":{},"children":[{"type":"table-cell","key":"cell_30ee45a0-dede-417d-8947-4c31af3522bc","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"方案时间"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_7a50b0a0-9f9d-4ed1-bb91-5d25b70953d0","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":" -   "},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_5bd5d7b8-27e6-4c90-bec5-edc2a3d12cc9","data":{},"children":[{"type":"table-cell","key":"cell_3ecc7c72-0ca0-49ab-84aa-d3aae73ef7bd","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"方案目标"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_1546eec9-9b1a-422e-9cbb-8adf68c2d53b","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"10万老用户回访"}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_3e1deb30-3094-4a2b-97df-e70788bed8d6","data":{},"children":[{"type":"table-cell","key":"cell_027c4789-69b9-423a-8f1c-e98647267792","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"方案预计服务老用户数"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_fe607176-7797-42b4-aa10-7a77437401f5","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_87f8e798-dc6a-4163-a2ab-430a4ce90cd1","data":{},"children":[{"type":"table-cell","key":"cell_13e7b37d-2ef6-466d-bbe5-f0d081927186","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"平均单用户奖励金额"}]}]}]}],"width":"100px","height":"44px","colspan":1},{"type":"table-cell","key":"cell_5c6f30f3-4d7b-4f1c-8127-c36614d5b22c","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px","colspan":1}]},{"type":"table-row","key":"row_42694910-8f11-4311-b318-49f46cae764f","data":{},"children":[{"type":"table-cell","key":"cell_7ca71770-d67c-4ddb-b51c-f65204b7cf9f","children":[{"type":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"总计需要花费"}]}]}]}],"width":"100px","height":"44px","colspan":1},{"type":"table-cell","key":"cell_ea0d6ea0-f198-46c5-ad38-060d3cefc029","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px","colspan":1}]}],"data":{}},{"type":"paragraph","children":[{"text":""}]}]', NULL, '/image/fa6ae73e9b12becf', '项目成员

负责人：@成员

团队成员：@成员

﻿


﻿


项目背景描述

如：为了提升用户满意度，所以此次立项对哪些功能进行改造

﻿


﻿


目标

如：达成目标用到的方式和方法

﻿


﻿


具体执行方案
策略1：
策略2：
策略3：

﻿


执行计划

项目里程碑：

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


项目预算

如：预算1

预算1方案名称



老用户回访计划




方案时间



﻿
﻿
 -
﻿
﻿




方案目标



10万老用户回访




方案预计服务老用户数



﻿





平均单用户奖励金额



﻿





总计需要花费



﻿


﻿
');
INSERT INTO wiki_document_template (id, name, description, details, sort, icon_url, detail_text)  VALUES ('46a8d3a8', '项目规划和执行', NULL, '[{"type":"block-quote","children":[{"text":"适合在项目启动前，规划编辑项目方案"}]},{"type":"head","children":[{"text":"一、项目背景"}],"head":"h1","id":"1huhl6u1"},{"type":"paragraph","children":[{"text":"介绍项目成立的背景","color":"#97A0AF"}]},{"type":"paragraph","children":[{"color":"#97A0AF","text":""}]},{"type":"head","children":[{"color":"#000000","text":"二、项目目标"}],"head":"h1","id":"1huhl7t6"},{"type":"paragraph","children":[{"text":"目标1：","backgroundColor":"#FFFFFF"}]},{"type":"paragraph","children":[{"backgroundColor":"#FFFFFF","text":"目标2："}]},{"type":"paragraph","children":[{"backgroundColor":"#FFFFFF","text":""}]},{"type":"head","children":[{"backgroundColor":"#FFFFFF","text":"三、核心成员"}],"head":"h1","id":"1huhl9k6"},{"type":"table","children":[{"type":"table-colgroup","key":"row_1f234b2b-60c6-4dc1-8f8c-5eb4c24f3dd2","data":{},"children":[{"type":"table-col","key":"row_bd662196-c79b-4c67-b39f-6e466d10af18","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_6b5e5619-54c7-484e-9cef-4d2fe320e639","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_bfe1e7b3-c3ea-43b3-a98f-e461772dec4c","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_fdf7840a-b3ed-4454-b666-3c3ca98ee002","data":{},"children":[{"type":"table-cell","key":"cell_690ebe85-de2e-4c52-9141-64e0597c988d","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"成员"}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_fb8cc0bd-30e2-46dc-8391-437710289d15","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"部门"}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_1aa15e75-9300-4b68-8a1c-b3ec0f8d58dd","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"角色"}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_a1fa9328-62d7-4655-89d2-15741a8a6f5f","data":{},"children":[{"type":"table-cell","key":"cell_35b63021-32d2-458d-bbea-ceda46f36e3c","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":"@成员","backgroundColor":"#FFFFFF"}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_dc4642f4-6041-4994-bee0-f7fdc93cb073","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_adf82d94-b052-4976-9ab1-a5fa0bfa2d63","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_1b41dadd-2e1d-4246-a932-a69e8acb3f06","data":{},"children":[{"type":"table-cell","key":"cell_9d2c76b9-9e9d-4ed9-8746-f05c04833521","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_d1a729e5-aa98-4256-a2f5-4ac49a011f2a","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_1ffb80aa-0935-42c6-9d85-34bbbb9d924e","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_9f443621-9ed3-4673-b0da-53b99ffeb10d","data":{},"children":[{"type":"table-cell","key":"cell_0be72c00-991d-420a-8590-c027a66aab77","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_d0049778-17ea-49d9-876b-64691b0f50f5","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_d30d87a5-a2d6-4e6f-9a87-011d1011b6c8","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{}},{"type":"paragraph","children":[{"text":""}]},{"type":"paragraph","children":[{"text":"项目群二维码："}]},{"type":"paragraph","children":[{"text":"左上角插入“图片”，可添加二维码图片","color":"#97A0AF"}]},{"type":"head","children":[{"text":"四、项目计划表"}],"head":"h1","id":"1huhlgog"},{"type":"paragraph","children":[{"text":""}]},{"type":"table","children":[{"type":"table-colgroup","key":"row_4b9613d7-8b63-40de-a1d9-696baf290d67","data":{},"children":[{"type":"table-col","key":"row_77756278-c4c2-4ef1-a6f5-528243a9cbb6","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_f9f1f3c2-0201-4a8d-8446-a9dc57185672","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_9d87589d-02c3-417d-88ee-5b791754e13c","width":"33.33%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","key":"row_c7d5efc4-f1b0-4209-8878-2055ad34aafc","data":{},"children":[{"type":"table-cell","key":"cell_0633accd-b36d-4d30-b88e-182cb7d4378f","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_f92fa471-30cb-4243-a833-1d40c34d487d","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_cd52ce41-9f91-40ed-9328-0fbada94b7ba","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_c47e3d9a-251a-4d27-94c6-96810452f763","data":{},"children":[{"type":"table-cell","key":"cell_2b44336b-0f12-4087-b07e-1f4a24b8b109","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_6cc7fcd8-987e-439f-b976-809964fe21b1","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_b3852241-82f7-450f-87a4-b022073c9580","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","key":"row_073603d1-d98f-4a97-a382-a847a5c8e9bb","data":{},"children":[{"type":"table-cell","key":"cell_e80f999b-634a-4e4a-9e03-86d47cba9fcf","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_4f7fa18a-ffc5-4978-b827-2afd5cfff1af","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","key":"cell_886b1305-6751-4407-b2ad-ff717db8f704","children":[{"type":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{}},{"type":"paragraph","children":[{"text":""}]},{"type":"head","children":[{"text":"五、核心任务"}],"head":"h1","id":"1huhlt2h"},{"type":"check-list-item","children":[{"text":"填写具体的任务","lineHeight":3},{"type":"date","dateValue":"2024-05-23","children":[{"text":""}]},{"text":" @成员","lineHeight":3}]},{"type":"check-list-item","children":[{"lineHeight":3,"text":"@成员"}]},{"type":"head","children":[{"lineHeight":1,"text":"六、项目资料集合"}],"head":"h1","id":"1huhlv4u"},{"type":"paragraph","children":[{"text":"左上角可选择插入“文档”、“文件”，关联项目资料","color":"#97A0AF"}]},{"type":"paragraph","children":[{"color":"#97A0AF","text":""}]},{"type":"head","children":[{"color":"#000000","text":"七、项目会议"}],"head":"h1","id":"1huhm0n1"},{"type":"paragraph","children":[{"text":""}]}]', NULL, '/image/e118cb3ebd63c81c', '适合在项目启动前，规划编辑项目方案
一、项目背景

介绍项目成立的背景

﻿


二、项目目标

目标1：

目标2：

﻿


三、核心成员

成员



部门



角色




@成员



﻿




﻿





﻿




﻿




﻿





﻿




﻿




﻿


﻿


项目群二维码：

左上角插入“图片”，可添加二维码图片

四、项目计划表

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


五、核心任务
填写具体的任务
﻿
 @成员
@成员
六、项目资料集合

左上角可选择插入“文档”、“文件”，关联项目资料

﻿


七、项目会议

﻿
');
INSERT INTO wiki_document_template (id, name, description, details, sort, icon_url, detail_text)  VALUES ('32f87df4', '周报模版', NULL, '[{"type":"paragraph","children":[{"text":""}]},{"type":"table","childrenType":"table","children":[{"type":"table-colgroup","key":"row_dc7f9902-49e3-4bee-802c-35568fa3268b","data":{},"children":[{"type":"table-col","key":"row_73dc973f-5020-49fd-b371-5966ea385624","width":"50.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]},{"type":"table-col","key":"row_050de6db-fbda-4edc-a3e1-76b6f58c6ae2","width":"50.00%","data":{},"children":[{"type":"paragraph","children":[{"text":""}]}]}]},{"type":"table-row","childrenType":"table-row","key":"row_7ec03d5c-1e5a-4190-b6c2-498bfb2c1891","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_0beb2732-6c08-497d-a62f-0a0f9be41cfe","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"姓名"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_6169ffe9-eaa3-4340-9cda-913b7097b52b","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":""}]}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_b81c719d-8c24-4994-94e0-68b451ae76ce","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_2decb82c-6692-462d-991c-96ded214b58c","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"部门"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_6259cec5-3ca5-4d1e-badf-eb0814f9f3aa","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"技术研发部"}]}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_57ea921c-ceb3-43bc-af45-b0a1ae2b0452","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_a0a58407-7827-4dbc-952c-b3e5ca118a85","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"时间"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_c54c4713-c824-429f-a23a-39a15cae4dd1","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"2023.11.20"}]}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_652e7b62-8b84-4cb4-a46c-b9f7ab05ee59","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_154c6075-2dad-4d31-894f-b4ac87206664","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"上周工作内容"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_1af0a626-80a1-4fd3-8dce-2b4792f63f55","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":""}]}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_cb2a0f60-6fde-4eac-aa29-4050802d1616","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_5ccbe44e-4e0d-4549-9892-3572a55ae3ee","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"本周工作计划"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_e4f06488-04c4-47bc-986f-3b55301bcfdf","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"1. 解决缺陷"}]},{"type":"paragraph","children":[{"text":"2. 验证，发布版本"}]}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_1fb5c9c1-fb35-4aa4-b730-52514562b29a","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_e6ebb4e0-7f21-494d-b0b6-4bbedf573889","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"问题"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_f87a1c96-0f22-4bbf-8a4c-1fb2d95ffd42","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]},{"type":"table-row","childrenType":"table-row","key":"row_42f01eae-d3a1-44ba-8a52-17bed78f65b9","data":{},"children":[{"type":"table-cell","childrenType":"table-cell","key":"cell_c228e6af-feb2-4f2c-91c0-e2755d8d8060","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"align","align":"left","children":[{"type":"paragraph","children":[{"text":"风险"}]}]}]}],"width":"100px","height":"44px"},{"type":"table-cell","childrenType":"table-cell","key":"cell_76514318-96b2-4746-b7f4-c602868c9d68","children":[{"type":"table-content","childrenType":"table-content","children":[{"type":"paragraph","children":[{"text":""}]}]}],"width":"100px","height":"44px"}]}],"data":{}},{"type":"paragraph","children":[{"text":""}]}]', NULL, '/image/5994fa146ed7e4be', '﻿


姓名



﻿





部门



技术研发部




时间



2023.11.20




上周工作内容



﻿





本周工作计划



1. 解决缺陷

2. 验证，发布版本




问题



﻿





风险



﻿


﻿
');