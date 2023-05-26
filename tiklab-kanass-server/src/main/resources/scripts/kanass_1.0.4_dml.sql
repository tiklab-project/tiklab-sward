
ALTER TABLE kanass_repository ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_repository ALTER COLUMN type_id SET DATA TYPE varchar(8);
ALTER TABLE kanass_repository ALTER COLUMN master SET DATA TYPE varchar(12);
ALTER TABLE kanass_repository ALTER COLUMN limits SET DATA TYPE varchar(8);
ALTER TABLE kanass_repository ALTER COLUMN icon_url SET DATA TYPE varchar(32);

ALTER TABLE kanass_category ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_category ALTER COLUMN repository_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_category ALTER COLUMN parent_category_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_category ALTER COLUMN master SET DATA TYPE varchar(12);
ALTER TABLE kanass_category ALTER COLUMN icon_url SET DATA TYPE varchar(32);

ALTER TABLE kanass_document ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document ALTER COLUMN repository_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document ALTER COLUMN category_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document ALTER COLUMN master SET DATA TYPE varchar(12);


ALTER TABLE kanass_document_attach ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document_attach ALTER COLUMN document_id SET DATA TYPE varchar(12);

ALTER TABLE kanass_document_template ALTER COLUMN id SET DATA TYPE varchar(8);

alter table kanass_comment rename "user" to user_id;
ALTER TABLE kanass_comment ALTER COLUMN id  SET DATA TYPE varchar(12);
ALTER TABLE kanass_comment ALTER COLUMN document_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_comment ALTER COLUMN parent_comment_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_comment ALTER COLUMN first_one_comment_id SET DATA TYPE varchar(12);

ALTER TABLE kanass_comment ALTER COLUMN aim_at_user SET DATA TYPE varchar(12);
ALTER TABLE kanass_comment ALTER COLUMN user_id SET DATA TYPE varchar(12);


ALTER TABLE kanass_like ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_like ALTER COLUMN to_whom_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_like ALTER COLUMN like_user SET DATA TYPE varchar(12);

ALTER TABLE kanass_share ALTER COLUMN id SET DATA TYPE varchar(12);


ALTER TABLE kanass_document_recent ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document_recent ALTER COLUMN model_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document_recent ALTER COLUMN master_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_document_recent ALTER COLUMN repository_id SET DATA TYPE varchar(12);

ALTER TABLE kanass_repository_focus ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_repository_focus ALTER COLUMN repository_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_repository_focus ALTER COLUMN master_id SET DATA TYPE varchar(12);

ALTER TABLE kanass_share_relation ALTER COLUMN id SET DATA TYPE varchar(12);
ALTER TABLE kanass_share_relation ALTER COLUMN document_id SET DATA TYPE varchar(12);
ALTER TABLE kanass_share_relation ALTER COLUMN share_id SET DATA TYPE varchar(12);



