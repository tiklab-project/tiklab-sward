CREATE TABLE wiki_document_recent(
        id VARCHAR(32) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(64) NOT NULL,
        master_id VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        recent_time timestamp NOT NULL
);