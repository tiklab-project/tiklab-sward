
DROP TABLE IF EXISTS wiki_approve_rule, wiki_approve_rpy,wiki_approve_doc,wiki_approve_details;

CREATE TABLE wiki_approve(
  id VARCHAR(12) PRIMARY KEY,
  status VARCHAR(64) ,
  wiki_document_ids VARCHAR(64) ,
  user_ids VARCHAR(256) ,
  create_time timestamp ,
  user_id VARCHAR(64) ,
  rpy_id VARCHAR(64) ,
  name VARCHAR(256) ,
  type VARCHAR(64)
);

CREATE TABLE wiki_approve_doc(
  id VARCHAR(12) PRIMARY KEY,
  approve_id VARCHAR(12) ,
  status VARCHAR(12) ,
  document_id VARCHAR(12)
);

CREATE TABLE wiki_approve_user(
  id VARCHAR(12) PRIMARY KEY,
  approve_id VARCHAR(64) ,
  create_time timestamp ,
  update_time timestamp ,
  status VARCHAR(64) ,
  user_id VARCHAR(64) ,
  message VARCHAR(600)
);


CREATE TABLE wiki_approve_rpy(
  id VARCHAR(12) PRIMARY KEY,
  rpy_id VARCHAR(64) ,
  create_time timestamp
);


