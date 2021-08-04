CREATE TABLE wiki_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        description VARCHAR(64)
);