CREATE TABLE wiki_comment_version(
    id VARCHAR(12) PRIMARY KEY,
    version_name VARCHAR(64) ,
    document_id VARCHAR(12),
    text_document_id VARCHAR(32) ,
    user_id VARCHAR(64),
    create_time VARCHAR(64)
);


ALTER TABLE wiki_document
    ADD COLUMN IF NOT EXISTS version_id varchar(255);
