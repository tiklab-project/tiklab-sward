
ALTER TABLE kanass_repository MODIFY id varchar(12);
ALTER TABLE kanass_repository MODIFY type_id varchar(8);
ALTER TABLE kanass_repository MODIFY master varchar(12);
ALTER TABLE kanass_repository MODIFY limits varchar(8);
ALTER TABLE kanass_repository MODIFY icon_url varchar(32);

ALTER TABLE kanass_category MODIFY id varchar(12);
ALTER TABLE kanass_category MODIFY repository_id varchar(12);
ALTER TABLE kanass_category MODIFY parent_category_id varchar(12);
ALTER TABLE kanass_category MODIFY master varchar(12);
ALTER TABLE kanass_category MODIFY icon_url varchar(32);

ALTER TABLE kanass_document MODIFY id varchar(12);
ALTER TABLE kanass_document MODIFY repository_id varchar(12);
ALTER TABLE kanass_document MODIFY category_id varchar(12);
ALTER TABLE kanass_document MODIFY master varchar(12);


ALTER TABLE kanass_document_attach MODIFY id varchar(12);
ALTER TABLE kanass_document_attach MODIFY document_id varchar(12);

ALTER TABLE kanass_document_template MODIFY id varchar(8);


ALTER TABLE kanass_comment MODIFY id varchar(12);
ALTER TABLE kanass_comment MODIFY document_id varchar(12);
ALTER TABLE kanass_comment MODIFY parent_comment_id varchar(12);
ALTER TABLE kanass_comment MODIFY first_one_comment_id varchar(12);
ALTER TABLE kanass_comment MODIFY aim_at_user varchar(12);


ALTER TABLE kanass_like MODIFY id varchar(12);
ALTER TABLE kanass_like MODIFY to_whom_id varchar(12);
ALTER TABLE kanass_like MODIFY like_user varchar(12);

ALTER TABLE kanass_share MODIFY id varchar(12);


ALTER TABLE kanass_document_recent MODIFY id varchar(12);
ALTER TABLE kanass_document_recent MODIFY model_id varchar(12);
ALTER TABLE kanass_document_recent MODIFY master_id varchar(12);
ALTER TABLE kanass_document_recent MODIFY repository_id varchar(12);

ALTER TABLE kanass_repository_focus MODIFY id varchar(12);
ALTER TABLE kanass_repository_focus MODIFY repository_id varchar(12);
ALTER TABLE kanass_repository_focus MODIFY master_id varchar(12);

ALTER TABLE kanass_share_relation MODIFY id varchar(12);
ALTER TABLE kanass_share_relation MODIFY document_id varchar(12);
ALTER TABLE kanass_share_relation MODIFY share_id varchar(12);



