-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1000"
-- ----------------------------
CREATE TABLE kanass_repository(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        master VARCHAR(32) NOT NULL,
        limits VARCHAR(32) NOT NULL,
        create_time VARCHAR(32) NOT NULL,
        icon_url VARCHAR(64),
        description VARCHAR(64)
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1001"
-- ----------------------------
CREATE TABLE kanass_category(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        parent_category_id VARCHAR(32) ,
        master VARCHAR(32) NOT NULL,
        update_time VARCHAR(64),
        icon_url VARCHAR(64),
        sort int
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1002"
-- ----------------------------
CREATE TABLE kanass_document(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        type_id VARCHAR(32) NOT NULL,
        category_id VARCHAR (32) ,
        master VARCHAR(32) NOT NULL,
        update_time  VARCHAR(64),
        details TEXT
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1003"
-- ----------------------------
CREATE TABLE kanass_document_attach(
        id VARCHAR(32) PRIMARY KEY,
        document_id VARCHAR(32) NOT NULL,
        attachment VARCHAR(256) NOT NULL,
        sort int
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1004"
-- ----------------------------
CREATE TABLE kanass_document_template(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        description VARCHAR (64),
        details TEXT,
        sort int
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1005"
-- ----------------------------
CREATE TABLE kanass_comment(
        id VARCHAR(32) PRIMARY KEY,
        document_id VARCHAR(32) NOT NULL,
        parent_comment_id VARCHAR (32),
        first_one_comment_id varchar(32),
        details TEXT,
        user_id varchar(32),
        aim_at_user varchar(32),
        create_time timestamp,
        update_time timestamp
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1006"
-- ----------------------------
CREATE TABLE kanass_like(
        id VARCHAR(32) PRIMARY KEY,
        to_whom_id varchar (32) not null,
        like_user varchar(32) not null,
        like_type varchar(8) not null,
        create_time timestamp
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1007"
-- ----------------------------
CREATE TABLE kanass_share(
        id VARCHAR(32) PRIMARY KEY,
        auth_code varchar(6),
        create_time timestamp,
        limits VARCHAR(64)
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1008"
-- ----------------------------
CREATE TABLE kanass_document_recent(
        id VARCHAR(32) NOT NULL PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        model VARCHAR(64) NOT NULL,
        model_id VARCHAR(64) NOT NULL,
        master_id VARCHAR(64) NOT NULL,
        repository_id VARCHAR(32) NOT NULL,
        recent_time timestamp NOT NULL
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1009"
-- ----------------------------
CREATE TABLE kanass_repository_focus(
        id VARCHAR(32) PRIMARY KEY,
        repository_id VARCHAR(64),
        master_id VARCHAR(64),
        sort int
);

-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1010"
-- ----------------------------
CREATE TABLE kanass_share_relation(
        id VARCHAR(32) PRIMARY KEY,
        type VARCHAR(64) NOT NULL,
        document_id VARCHAR(64) NOT NULL,
        share_id VARCHAR(64) NOT NULL
);



