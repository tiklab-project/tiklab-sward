UPDATE wiki_repository SET icon_url = 'repository' ||  floor(random() * 4 + 1)::int || '.png';
DELETE FROM wiki_icon WHERE icon_type = 'repository';

INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('bbd22724c949', 'repository1.png', 'repository1.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('0c1faf0d2ec6', 'repository2.png', 'repository2.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('b5952fb1da49', 'repository3.png', 'repository3.png', 'repository');
INSERT INTO wiki_icon (id, icon_name, icon_url, icon_type) VALUES ('f253b41fa064', 'repository4.png', 'repository4.png', 'repository');