
CREATE TABLE kanass_repository(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        type_id VARCHAR(8) NOT NULL,
        master VARCHAR(12) NOT NULL,
        limits VARCHAR(8) NOT NULL,
        create_time VARCHAR(32) NOT NULL,
        icon_url VARCHAR(64),
        description VARCHAR(64)
);


CREATE TABLE kanass_category(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        parent_category_id VARCHAR(12) ,
        master VARCHAR(12) NOT NULL,
        update_time VARCHAR(64),
        icon_url VARCHAR(64),
        sort int
);


CREATE TABLE kanass_document(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        category_id VARCHAR (12) ,
        master VARCHAR(12) NOT NULL,
        update_time  VARCHAR(64),
        details TEXT
);


CREATE TABLE kanass_document_attach(
        id VARCHAR(12) PRIMARY KEY,
        document_id VARCHAR(12) NOT NULL,
        attachment VARCHAR(256) NOT NULL,
        sort int
);


CREATE TABLE kanass_document_template(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        description VARCHAR (64),
        details TEXT,
        sort int
);


CREATE TABLE kanass_comment(
        id VARCHAR(12) PRIMARY KEY,
        document_id VARCHAR(12) NOT NULL,
        parent_comment_id VARCHAR (12),
        first_one_comment_id varchar(12),
        details TEXT,
        user_id varchar(12),
        aim_at_user varchar(12),
        create_time timestamp,
        update_time timestamp
);


CREATE TABLE kanass_like(
        id VARCHAR(12) PRIMARY KEY,
        to_whom_id varchar (12) not null,
        like_user varchar(12) not null,
        like_type varchar(8) not null,
        create_time timestamp
);

CREATE TABLE kanass_share(
        id VARCHAR(12) PRIMARY KEY,
        auth_code varchar(6),
        create_time timestamp,
        limits VARCHAR(8)
);


CREATE TABLE kanass_document_recent(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(12) NOT NULL,
        master_id VARCHAR(12) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        recent_time timestamp NOT NULL
);

CREATE TABLE kanass_repository_focus(
        id VARCHAR(12) PRIMARY KEY,
        repository_id VARCHAR(12),
        master_id VARCHAR(12),
        sort int
);

CREATE TABLE kanass_share_relation(
        id VARCHAR(12) PRIMARY KEY,
        type VARCHAR(8) NOT NULL,
        document_id VARCHAR(64) NOT NULL,
        category_id VARCHAR(12) NOT NULL,
        share_id VARCHAR(12) NOT NULL
);

CREATE TABLE kanass_system_url(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        system_url VARCHAR(64) NOT NULL,
        web_url VARCHAR(64)
);




