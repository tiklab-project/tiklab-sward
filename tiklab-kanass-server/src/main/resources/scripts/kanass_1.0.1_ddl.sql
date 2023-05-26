-- ---------------------------
-- kanass表
-- @dsm.cmd.id="1000"
-- ----------------------------
CREATE TABLE kanass_system_url(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        system_url VARCHAR(64) NOT NULL,
        web_url VARCHAR(64) NOT NULL
);

