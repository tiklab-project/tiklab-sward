ALTER TABLE kanass_document ADD COLUMN sort int ;
UPDATE kanass_document SET sort = COALESCE(sort, 0) WHERE sort IS NULL;
ALTER TABLE kanass_document ALTER COLUMN sort set NOT NULL;

UPDATE kanass_category SET sort = COALESCE(sort, 0) WHERE sort IS NULL;
ALTER TABLE kanass_category ALTER COLUMN sort set NOT NULL;


ALTER TABLE kanass_document ADD COLUMN dimension int ;
UPDATE kanass_document SET dimension = COALESCE(dimension, 0) WHERE dimension IS NULL;
ALTER TABLE kanass_document ALTER COLUMN dimension set NOT NULL;

ALTER TABLE kanass_category ADD COLUMN dimension int ;
UPDATE kanass_category SET dimension = COALESCE(dimension, 0) WHERE dimension IS NULL;
ALTER TABLE kanass_category ALTER COLUMN dimension set NOT NULL;
