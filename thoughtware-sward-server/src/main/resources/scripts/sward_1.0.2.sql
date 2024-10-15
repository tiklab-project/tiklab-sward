--INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('SWARD_LOGTYPE_DOCUMENTUPDATENAME', '修改文档名称', 'sward');
--
ALTER TABLE wiki_document_template ADD COLUMN detail_text TEXT;