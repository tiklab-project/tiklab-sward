ALTER TABLE kanass_repository RENAME TO wiki_repository;
ALTER TABLE kanass_category RENAME TO wiki_category;
ALTER TABLE kanass_document RENAME TO wiki_document;
ALTER TABLE kanass_document_attach RENAME TO wiki_document_attach;
ALTER TABLE kanass_document_template RENAME TO wiki_document_template;
ALTER TABLE kanass_comment RENAME TO wiki_comment;
ALTER TABLE kanass_like RENAME TO wiki_like;
ALTER TABLE kanass_share RENAME TO wiki_share;
ALTER TABLE kanass_document_recent RENAME TO wiki_document_recent;
ALTER TABLE kanass_repository_focus RENAME TO wiki_repository_focus;
ALTER TABLE kanass_share_relation RENAME TO wiki_share_relation;

ALTER TABLE kanass_system_url RENAME TO wiki_system_url;
ALTER TABLE kanass_icon RENAME TO wiki_icon;