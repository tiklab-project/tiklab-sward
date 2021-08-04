CREATE TABLE wiki_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        master VARCHAR(32) NOT NULL,
        description VARCHAR(64)
);

CREATE TABLE wiki_category(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        parent_category_id VARCHAR(32) ,
        sort int
);

CREATE TABLE wiki_repository_detail(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        category_id VARCHAR (32) ,
        details VARCHAR(320)


);
