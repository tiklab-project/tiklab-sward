ALTER TABLE kanass_share_relation ALTER COLUMN document_id type VARCHAR(12);
ALTER TABLE kanass_share_relation ALTER COLUMN document_id DROP NOT NULL;
ALTER TABLE kanass_share_relation ALTER COLUMN category_id DROP NOT NULL;