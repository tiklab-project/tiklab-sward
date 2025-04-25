CREATE TABLE wiki_approve_rule(
    id VARCHAR(12) PRIMARY KEY,
    title VARCHAR(64) ,
    type VARCHAR(12),
    create_time VARCHAR(64) ,
    description VARCHAR(256)
);

CREATE TABLE wiki_approve_rpy(
    id VARCHAR(12) PRIMARY KEY,
    rpy_id VARCHAR(12) ,
    create_time VARCHAR(64) ,
    rule_id VARCHAR(12)

);


CREATE TABLE wiki_approve_doc(
    id VARCHAR(12) PRIMARY KEY,
    title VARCHAR(64) ,
    status VARCHAR(64) ,
    document_id VARCHAR(64) ,
    user_ids VARCHAR(256) ,
    create_time VARCHAR(64) ,
    user_id VARCHAR(64) ,
    rpy_id VARCHAR(64) ,
    type VARCHAR(64)
);

CREATE TABLE wiki_approve_details(
    id VARCHAR(12) PRIMARY KEY,
    approve_id VARCHAR(64) ,
    create_time VARCHAR(64) ,
    status VARCHAR(64) ,
    user_id VARCHAR(64) ,
    describe VARCHAR(64)
);


INSERT INTO wiki_approve_rule (id, title, type, create_time, description)
    VALUES ('allPass', '全部通过才算通过', 'allPass', '2025-04-24 09:13:16', '全部通过才算通过');


INSERT INTO wiki_approve_rule (id, title, type, create_time, description)
    VALUES ('allReject', '全部不通过才算不通过', 'allReject', '2025-04-24 09:13:16', '全部不通过才算不通过');

INSERT INTO wiki_approve_rule (id, title, type, create_time, description)
    VALUES ('pass', '存在通过就算通过', 'pass', '2025-04-24 09:13:16', '存在通过就算通过');

INSERT INTO wiki_approve_rule (id, title, type, create_time, description)
    VALUES ('reject', '存在不通过就算不通过', 'reject', '2025-04-24 09:13:16', '存在不通过就算不通过');

ALTER TABLE wiki_node
    ADD COLUMN IF NOT EXISTS approve varchar(255) default 'complete';







