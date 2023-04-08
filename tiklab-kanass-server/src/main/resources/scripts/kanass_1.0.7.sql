ALTER TABLE kanass_share DROP COLUMN document_id;
ALTER TABLE kanass_share DROP COLUMN share_link;
ALTER TABLE kanass_share ADD limits VARCHAR(64);
CREATE TABLE kanass_share_relation(
        id VARCHAR(32) PRIMARY KEY,
        type VARCHAR(64) NOT NULL,
        document_id VARCHAR(64) NOT NULL,
        share_id VARCHAR(64) NOT NULL
);