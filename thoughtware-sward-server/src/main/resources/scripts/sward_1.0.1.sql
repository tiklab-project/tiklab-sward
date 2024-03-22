ALTER TABLE wiki_document ALTER COLUMN name DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN repository_id DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN type_id DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN master DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN category_id DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN sort DROP NOT NULL;
ALTER TABLE wiki_document ALTER COLUMN dimension DROP NOT NULL;


ALTER TABLE wiki_category ALTER COLUMN name DROP NOT NULL;
ALTER TABLE wiki_category ALTER COLUMN master DROP NOT NULL;
ALTER TABLE wiki_category ALTER COLUMN repository_id DROP NOT NULL;
ALTER TABLE wiki_category ALTER COLUMN sort DROP NOT NULL;
ALTER TABLE wiki_category ALTER COLUMN dimension DROP NOT NULL;


UPDATE "public"."pcs_op_log_type" SET "name" = '修改文档名称', "bgroup" = 'sward' WHERE "id" = 'SWARD_LOGTYPE_DOCUMENTUPDATENAME';