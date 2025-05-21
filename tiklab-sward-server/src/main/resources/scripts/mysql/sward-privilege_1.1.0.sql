UPDATE pcs_prc_role SET name = '超级管理员', description = '系统超级管理员角色,拥有系统的全部权限，无法自定义权限。',
    grouper = 'system', type = '1', business_type = 2, default_role = 2, scope = 1, parent_id = NULL
    WHERE id = '111111';

UPDATE pcs_prc_role SET name = '管理员', description = '管理员，拥有系统的大部分权限，可以自定义权限。',
    grouper = 'system', type = '1', business_type = 1, default_role = 0, scope = 1, parent_id = NULL
    WHERE id = '1';

INSERT INTO pcs_prc_role (
    id, name, description, grouper, type, business_type, default_role, scope, parent_id
)
VALUES (
           'sys_sward_user', '普通用户', '普通用户，拥有系统的小部分权限，可以自定义权限。',
           'system', '1', 1, 1, 1, NULL
       )
    ON DUPLICATE KEY UPDATE id = id;


UPDATE pcs_prc_role SET name = '知识库管理员', description = '管理员角色,拥有系统的全部权限，无法自定义权限。',
     grouper = 'system', type = '2', business_type = 2, default_role = 2, scope = 1, parent_id = NULL WHERE id = 'pro_111111';

UPDATE pcs_prc_role SET name = '知识库成员', description = '知识库普通用户，拥有系统的小部分权限，可以自定义权限。',
     grouper = 'system', type = '2', business_type = 0, default_role = 1, scope = 1, parent_id = NULL WHERE id = '1af472d3e62d';


UPDATE pcs_prc_role SET name = '知识库管理员', description = '项目管理员角色。', grouper = 'system', type = '2', business_type = 2, default_role = 2, scope = 2, parent_id = 'pro_111111' WHERE id = 'f0763af198e8';
UPDATE pcs_prc_role SET name = '知识库管理员', description = '项目管理员角色。', grouper = 'system', type = '2', business_type = 2, default_role = 2, scope = 2, parent_id = 'pro_111111' WHERE id = 'c7e77e842563';


UPDATE pcs_prc_role SET  default_role = 1, scope = 2, parent_id = NULL WHERE id = '1af472d3e62d';


INSERT INTO pcs_prc_role (id, name, description, grouper, type, business_type, default_role, scope, parent_id) VALUES ('pro_sward_user', '知识库成员', '知识库普通用户，拥有系统的小部分权限，可以自定义权限。', 'system', '2', 0, 1, 1, NULL);








