ALTER TABLE wiki_repository ADD COLUMN status VARCHAR(12);
UPDATE wiki_repository SET status = 'nomal';
ALTER TABLE wiki_repository ALTER COLUMN status DROP NOT NULL;
ALTER TABLE wiki_repository ADD COLUMN archived_time VARCHAR(32);
ALTER TABLE wiki_repository ADD COLUMN archived_user_id VARCHAR(12);
ALTER TABLE wiki_repository ADD COLUMN archived_desc VARCHAR(32);

ALTER TABLE wiki_node ADD COLUMN status VARCHAR(32);
UPDATE wiki_node SET status = 'nomal';
ALTER TABLE wiki_node ALTER COLUMN status DROP NOT NULL;
ALTER TABLE wiki_node ADD COLUMN archived_time VARCHAR(32);
ALTER TABLE wiki_node ADD COLUMN archived_user_id VARCHAR(32);
ALTER TABLE wiki_node ADD COLUMN archived_desc VARCHAR(32);

ALTER TABLE wiki_repository ADD COLUMN recycle VARCHAR(12);
UPDATE wiki_repository SET recycle = '0';
ALTER TABLE wiki_repository ALTER COLUMN recycle DROP NOT NULL;
ALTER TABLE wiki_repository ADD COLUMN recycle_time VARCHAR(32);
ALTER TABLE wiki_repository ADD COLUMN recycle_user_id VARCHAR(32);

ALTER TABLE wiki_node ADD COLUMN recycle VARCHAR(12);
UPDATE wiki_node SET recycle = '0';
ALTER TABLE wiki_node ALTER COLUMN recycle DROP NOT NULL;
ALTER TABLE wiki_node ADD COLUMN recycle_time VARCHAR(32);
ALTER TABLE wiki_node ADD COLUMN recycle_user_id VARCHAR(32);