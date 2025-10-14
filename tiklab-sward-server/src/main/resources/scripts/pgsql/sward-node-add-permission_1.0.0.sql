
CREATE TABLE wiki_node_permission (
   id VARCHAR(12) PRIMARY KEY,
   node_id VARCHAR(32),
   permission_type int,
   user_ids TEXT
);

