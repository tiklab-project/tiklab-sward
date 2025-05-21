
CREATE TABLE wiki_repository(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        type_id VARCHAR(8) NOT NULL,
        master VARCHAR(12) NOT NULL,
        limits VARCHAR(8) NOT NULL,
        create_time VARCHAR(32) NOT NULL,
        icon_url VARCHAR(64),
        status VARCHAR(12),
        archived_time VARCHAR(32),
        archived_user_id VARCHAR(12),
        archived_desc VARCHAR(32),
        recycle VARCHAR(12),
        recycle_time VARCHAR(32),
        recycle_user_id VARCHAR(32),
        description VARCHAR(64)
);


CREATE TABLE wiki_category(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) ,
        repository_id VARCHAR(12),
        parent_category_id VARCHAR(12) ,
        master VARCHAR(12),
        update_time VARCHAR(64),
        icon_url VARCHAR(64),
        sort int,
        dimension int,
        tree_path TEXT
);


CREATE TABLE wiki_document(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64),
        repository_id VARCHAR(12),
        type_id VARCHAR(32),
        category_id VARCHAR (12) ,
        master VARCHAR(12),
        update_time  VARCHAR(64),
        details TEXT,
        detail_text TEXT,
        sort int,
        dimension int,
        tree_path TEXT
);

CREATE TABLE wiki_node(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        parent_id VARCHAR(12),
        master VARCHAR(12) NOT NULL,
        create_time VARCHAR(64),
        update_time VARCHAR(64),
        sort int NOT NULL,
        dimension int NOT NULL,
        tree_path TEXT,
        type VARCHAR(12),
        status VARCHAR(32),
        archived_time VARCHAR(32),
        archived_user_id VARCHAR(32),
        archived_desc VARCHAR(32),
        recycle VARCHAR(12),
        recycle_time VARCHAR(32),
        recycle_user_id VARCHAR(32),
        document_type VARCHAR(12)
);


CREATE TABLE wiki_document_attach(
        id VARCHAR(12) PRIMARY KEY,
        document_id VARCHAR(12) NOT NULL,
        attachment VARCHAR(256) NOT NULL,
        sort int
);


CREATE TABLE wiki_document_template(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        description VARCHAR (64),
        details TEXT,
        detail_text TEXT,
        sort int,
        icon_url VARCHAR(255)
);


CREATE TABLE wiki_comment(
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


CREATE TABLE wiki_like(
        id VARCHAR(12) PRIMARY KEY,
        to_whom_id varchar (12) not null,
        like_user varchar(12) not null,
        like_type varchar(8) not null,
        create_time timestamp
);

CREATE TABLE wiki_share(
        id VARCHAR(12) PRIMARY KEY,
        auth_code varchar(6),
        create_time timestamp,
        limits VARCHAR(8)
);


CREATE TABLE wiki_recent(
        id VARCHAR(12) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(12) NOT NULL,
        master_id VARCHAR(12) NOT NULL,
        repository_id VARCHAR(12) NOT NULL,
        recent_time timestamp NOT NULL
);

CREATE TABLE wiki_repository_focus(
        id VARCHAR(12) PRIMARY KEY,
        repository_id VARCHAR(12),
        master_id VARCHAR(12),
        sort int
);

CREATE TABLE wiki_share_relation(
        id VARCHAR(12) PRIMARY KEY,
        type VARCHAR(8) NOT NULL,
        document_id VARCHAR(12),
        category_id VARCHAR(12),
        node_id VARCHAR(12),
        share_id VARCHAR(12) NOT NULL
);

CREATE TABLE wiki_system_url(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        system_url VARCHAR(64) NOT NULL,
        web_url VARCHAR(64)
);

CREATE TABLE wiki_icon(
        id VARCHAR(12) PRIMARY KEY,
        icon_name VARCHAR(64) NOT NULL,
        icon_url VARCHAR(64) NOT NULL,
        icon_type VARCHAR(32)
);
CREATE TABLE wiki_document_focus(
        id VARCHAR(12) PRIMARY KEY,
        document_id VARCHAR(12),
        master_id VARCHAR(12),
        focus_time VARCHAR(32),
        repository_id VARCHAR(12),
        sort int
);



