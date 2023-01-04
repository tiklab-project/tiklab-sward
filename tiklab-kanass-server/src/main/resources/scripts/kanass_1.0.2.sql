CREATE TABLE kanass_repository_focus(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(64),
        master_id VARCHAR(64),
        sort int
);