CREATE TABLE kanass_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        master VARCHAR(32) NOT NULL,
        limits VARCHAR(32) NOT NULL,
        create_time VARCHAR(32) NOT NULL,
        description VARCHAR(64)
);

CREATE TABLE kanass_category(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        parent_category_id VARCHAR(32) ,
        master VARCHAR(32) NOT NULL,
        sort int
);

CREATE TABLE kanass_document(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        category_id VARCHAR (32) ,
        master VARCHAR(32) NOT NULL,
        update_time  VARCHAR(64),
        details longtext
);

CREATE TABLE kanass_document_attach(
        id VARCHAR(32) PRIMARY KEY,
        document_id VARCHAR(32) NOT NULL,
        attachment VARCHAR(256) NOT NULL,
        sort int
);

CREATE TABLE kanass_document_template(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        description VARCHAR (64),
        details longtext,
        sort int
);
CREATE TABLE kanass_comment(
        id VARCHAR(32) PRIMARY KEY,
        document_id VARCHAR(32) NOT NULL,
        parent_comment_id VARCHAR (32),
        first_one_comment_id varchar(32),
        details longtext,
        user varchar(32),
        aim_at_user varchar(32),
        create_time timestamp,
        update_time timestamp
);
CREATE TABLE kanass_like(
        id VARCHAR(32) PRIMARY KEY,
        to_whom_id varchar (32) not null,
        like_user varchar(32) not null,
        like_type varchar(8) not null,
        create_time timestamp
);

CREATE TABLE kanass_share(
        id VARCHAR(32) PRIMARY KEY,
        document_id VARCHAR(32) NOT NULL,
        share_link  varchar(64) not null,
        auth_code varchar(6),
        create_time timestamp
);
CREATE TABLE kanass_document_recent(
        id VARCHAR(32) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(64) NOT NULL,
        master_id VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        recent_time timestamp NOT NULL
);
CREATE TABLE kanass_document_recent(
        id VARCHAR(32) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(64) NOT NULL,
        master_id VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        recent_time timestamp NOT NULL
);




