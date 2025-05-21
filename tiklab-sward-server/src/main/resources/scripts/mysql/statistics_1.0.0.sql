

CREATE TABLE wiki_document_statistics(
    id VARCHAR(12) PRIMARY KEY,
    type VARCHAR(64) ,
    rpy_id VARCHAR(12),
    document_id VARCHAR(32) ,
    user_id VARCHAR(32) ,
    create_time VARCHAR(64)
);