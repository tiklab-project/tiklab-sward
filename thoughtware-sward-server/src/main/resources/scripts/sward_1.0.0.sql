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
        document_type VARCHAR(12)
);

INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type)
SELECT id, name,repository_id, parent_category_id, master, update_time, update_time, sort, dimension, tree_path, 'category', NULL
FROM wiki_category;

INSERT INTO wiki_node (id, name, repository_id, parent_id, master, create_time, update_time, sort, dimension, tree_path, type, document_type)
SELECT  id, name, repository_id, category_id, master, update_time,update_time, sort,dimension,tree_path, 'document', type_id
FROM wiki_document;