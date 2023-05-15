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