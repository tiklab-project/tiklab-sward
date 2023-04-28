INSERT INTO `tiklab_kanass`.`pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('4dfb4eb2c40d03f0b330aee3f4a88182', '知识库删除', 'RepositoryDelete', NULL, 55, '2');

INSERT INTO `tiklab_kanass`.`pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('389ab5c546929fe53e48eae64b693f8e', '1fac7297bd6c47ad9601374c4db1c3b8', '4dfb4eb2c40d03f0b330aee3f4a88182');
INSERT INTO `tiklab_kanass`.`pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('4d23b6a02e3ca5d9158455a57c2c1828', '2', '4dfb4eb2c40d03f0b330aee3f4a88182');
INSERT INTO `tiklab_kanass`.`pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('84ca038f8f75f1d86a710894ef263039', '2e018b908ac1c2248d3141a07db98c56', '4dfb4eb2c40d03f0b330aee3f4a88182');

INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role_user` (`id`, `dmRole_id`, `domain_id`, `user_id`) VALUES ('2628fa355f748a5915bb369681d87125', '4763791e9126a22486bb02a14d1c9a20', '43ef04320e5169789bc3e483e232b6e0', '111111');
INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role_user` (`id`, `dmRole_id`, `domain_id`, `user_id`) VALUES ('9e973dbe3ecfd4050452aa5cabd3e344', 'bfa59d952c6433688866da973b43a341', '7904ebd674d28c65b11ae47017a783c0', '111111');

INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('4763791e9126a22486bb02a14d1c9a20', '43ef04320e5169789bc3e483e232b6e0', '2e018b908ac1c2248d3141a07db98c56', 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('5ea808caadc24bdbfacc9c1d4bfc772b', '43ef04320e5169789bc3e483e232b6e0', '67c91d87afd71dd51b2c838299cde09f', 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('bfa59d952c6433688866da973b43a341', '7904ebd674d28c65b11ae47017a783c0', '1fac7297bd6c47ad9601374c4db1c3b8', 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('e5f12d1372da7f43b92ca0f5a348dd4f', '7904ebd674d28c65b11ae47017a783c0', '070c9ac2734a1aadc42aa03090210df1', 0);

INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('070c9ac2734a1aadc42aa03090210df1', '普通用户', '普通用户', 'system', '2', 2, 1, 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('1af472d3e62d02562c025b9d7a5ce4cf', '普通用户', '普通用户', 'system', '2', 1, 0, 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('1fac7297bd6c47ad9601374c4db1c3b8', 'admin', '管理员', 'system', '2', 2, 0, 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('2', 'admin', '管理员', 'system', '2', 1, 1, 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('2e018b908ac1c2248d3141a07db98c56', 'admin', '管理员', 'system', '2', 2, 0, 0);
INSERT INTO `tiklab_kanass`.`pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('67c91d87afd71dd51b2c838299cde09f', '普通用户', '普通用户', 'system', '2', 2, 0, 0);