DROP TABLE IF EXISTS wiki_share;
DROP TABLE IF EXISTS wiki_share_relation;


CREATE TABLE wiki_share(
    id VARCHAR(12) PRIMARY KEY,
    name VARCHAR(256) ,
    create_time TIMESTAMP ,
    rpy_id VARCHAR(256) ,
    user_id VARCHAR(256) ,
    password VARCHAR(256) ,
    share_ids VARCHAR(1024) ,
    user_ids VARCHAR(1024) ,
    type int,
    auth_type int
);


ALTER TABLE wiki_node
    ADD COLUMN IF NOT EXISTS node_status int;