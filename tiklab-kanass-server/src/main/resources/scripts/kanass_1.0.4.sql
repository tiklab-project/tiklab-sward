ALTER TABLE kanass_document ADD COLUMN sort int ;

UPDATE kanass_document
SET sort = COALESCE(sort, 0)
WHERE sort IS NULL;

ALTER TABLE kanass_document ALTER COLUMN sort set NOT NULL;

UPDATE kanass_category
SET sort = COALESCE(sort, 0)
WHERE sort IS NULL;

ALTER TABLE kanass_category ALTER COLUMN sort set NOT NULL;

