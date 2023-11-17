ALTER TABLE kanass_document_template ADD COLUMN icon_url VARCHAR(255) ;
UPDATE kanass_document_template SET icon_url = '/image/19496bd1cf0e309f' WHERE icon_url IS NULL;
ALTER TABLE kanass_document_template ALTER COLUMN icon_url set NOT NULL;

