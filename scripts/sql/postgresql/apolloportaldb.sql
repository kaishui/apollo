CREATE SEQUENCE app_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE app
(
    id                 BIGINT    NOT NULL DEFAULT nextval('app_id_seq') PRIMARY KEY,
    app_id             TEXT      NOT NULL DEFAULT  '',
    app_name           TEXT      NOT NULL DEFAULT  '',
    org_id             TEXT      NOT NULL DEFAULT  '',
    org_name           TEXT      NOT NULL DEFAULT  '',
    owner_name         TEXT      NOT NULL DEFAULT  '',
    owner_email        TEXT      NOT NULL DEFAULT  '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX app_app_id_index
    ON app (app_id);
CREATE
INDEX app_app_name_index
    ON app (app_name);
CREATE
INDEX app_last_modified_time_index
    ON app (last_modified_time);

CREATE SEQUENCE app_namespace_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE app_namespace
(
    id                 BIGINT    NOT NULL DEFAULT nextval('app_namespace_id_seq') PRIMARY KEY,
    namespace_name     TEXT      NOT NULL DEFAULT '',
    app_id             TEXT      NOT NULL DEFAULT '',
    format             TEXT      NOT NULL DEFAULT '' ,
    shared             BOOLEAN   NOT NULL DEFAULT FALSE,
    comment            TEXT      NOT NULL DEFAULT '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX app_namespace_app_id_index
    ON app_namespace (app_id);
CREATE
INDEX app_namespace_namespace_name_app_id_index
    ON app_namespace (namespace_name, app_id);
CREATE
INDEX app_namespace_last_modified_time_index
    ON app_namespace (last_modified_time);

CREATE SEQUENCE consumer_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE consumer
(
    id                 BIGINT    NOT NULL DEFAULT nextval('consumer_id_seq') PRIMARY KEY,
    app_id             TEXT      NOT NULL DEFAULT  '',
    consumer_name      TEXT      NOT NULL DEFAULT  '',
    org_id             TEXT      NOT NULL DEFAULT  '',
    org_name           TEXT      NOT NULL DEFAULT  '',
    owner_name         TEXT      NOT NULL DEFAULT  '',
    owner_email        TEXT      NOT NULL DEFAULT  '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX consumer_app_id_index
    ON consumer (app_id);
CREATE
INDEX consumer_last_modified_time_index
    ON consumer (last_modified_time);

CREATE SEQUENCE consumer_audit_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE consumer_audit
(
    id                 BIGINT    NOT NULL DEFAULT nextval('consumer_audit_id_seq') PRIMARY KEY,
    consumer_id        BIGINT,
    uri                TEXT      NOT NULL DEFAULT '',
    method             TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX consumer_audit_consumer_id_index
    ON consumer_audit (consumer_id);
CREATE
INDEX consumer_audit_last_modified_time_index
    ON consumer_audit (last_modified_time);

CREATE SEQUENCE consumer_role_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE consumer_role
(
    id                 BIGINT    NOT NULL DEFAULT nextval('consumer_role_id_seq') PRIMARY KEY,
    consumer_id        BIGINT,
    role_id            BIGINT,
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX consumer_role_consumer_id_role_id_index
    ON consumer_role (consumer_id, role_id);
CREATE
INDEX consumer_role_role_id_index
    ON consumer_role (role_id);
CREATE
INDEX consumer_role_last_modified_time_index
    ON consumer_role (last_modified_time);

CREATE SEQUENCE consumer_token_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE consumer_token
(
    id                 BIGINT    NOT NULL DEFAULT nextval('consumer_token_id_seq') PRIMARY KEY,
    consumer_id        BIGINT,
    token              TEXT      NOT NULL DEFAULT '',
    expires            TIMESTAMP NOT NULL DEFAULT '2099-12-31 23:59:59':: TIMESTAMP,
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now(),
    UNIQUE (token)
);
CREATE
INDEX consumer_token_last_modified_time_index
    ON consumer_token (last_modified_time);

CREATE SEQUENCE favorite_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE favorite
(
    id                 BIGINT    NOT NULL DEFAULT nextval('favorite_id_seq') PRIMARY KEY,
    user_id            TEXT      NOT NULL DEFAULT  '',
    app_id             TEXT      NOT NULL DEFAULT  '',
    favorite_position  BIGINT    NOT NULL DEFAULT 10000,
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX favorite_user_id_index
    ON favorite (user_id);
CREATE
INDEX favorite_app_id_index
    ON favorite (app_id);
CREATE
INDEX favorite_last_modified_time_index
    ON favorite (last_modified_time);

CREATE SEQUENCE permission_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE permission
(
    id                 BIGINT    NOT NULL DEFAULT nextval('permission_id_seq') PRIMARY KEY,
    permission_type    TEXT      NOT NULL DEFAULT '',
    target_id          TEXT      NOT NULL DEFAULT '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX permission_permission_type_target_id_index
    ON permission (permission_type, target_id);
CREATE
INDEX permission_last_modified_time_index
    ON permission (last_modified_time);

CREATE SEQUENCE role_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE role
(
    id                 BIGINT    NOT NULL DEFAULT nextval('role_id_seq') PRIMARY KEY,
    role_name          TEXT      NOT NULL DEFAULT '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX role_role_name_index
    ON role (role_name);
CREATE
INDEX role_last_modified_time_index
    ON role (last_modified_time);

CREATE SEQUENCE role_permission_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE role_permission
(
    id                 BIGINT    NOT NULL DEFAULT nextval('role_permission_id_seq') PRIMARY KEY,
    role_id            BIGINT,
    permission_id      BIGINT,
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX role_permission_role_id_index
    ON role_permission (role_id);
CREATE
INDEX role_permission_permission_id_index
    ON role_permission (permission_id);
CREATE
INDEX role_permission_last_modified_time_index
    ON role_permission (last_modified_time);

CREATE SEQUENCE server_config_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE server_config
(
    id                 BIGINT    NOT NULL DEFAULT nextval('server_config_id_seq') PRIMARY KEY,
    key                TEXT      NOT NULL DEFAULT  '',
    value              TEXT      NOT NULL DEFAULT  '',
    comment            TEXT               DEFAULT '',
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT  '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX server_config_key_index
    ON server_config (key);
CREATE
INDEX server_config_last_modified_time_index
    ON server_config (last_modified_time);

CREATE SEQUENCE user_role_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE user_role
(
    id                 BIGINT    NOT NULL DEFAULT nextval('user_role_id_seq') PRIMARY KEY,
    user_id            TEXT               DEFAULT '',
    role_id            BIGINT,
    deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
    created_by         TEXT      NOT NULL DEFAULT '',
    created_time       TIMESTAMP NOT NULL DEFAULT now(),
    last_modified_by   TEXT               DEFAULT '',
    last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE
INDEX user_role_user_id_role_id_index
    ON user_role (user_id, role_id);
CREATE
INDEX user_role_role_id_index
    ON user_role (role_id);
CREATE
INDEX user_role_last_modified_time_index
    ON user_role (last_modified_time);

CREATE SEQUENCE users_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE users
(
    id       BIGINT  NOT NULL DEFAULT nextval('users_id_seq') PRIMARY KEY,
    username TEXT    NOT NULL DEFAULT  '',
    password TEXT    NOT NULL DEFAULT  '',
    email    TEXT    NOT NULL DEFAULT  '',
    enabled  BOOLEAN NOT NULL
);

CREATE SEQUENCE authorities_id_seq
    INCREMENT 1
    START 1;
CREATE TABLE authorities
(
    id        BIGINT NOT NULL DEFAULT nextval('authorities_id_seq') PRIMARY KEY,
    username  TEXT   NOT NULL,
    authority TEXT   NOT NULL
);

---------------------------------------------------------------------------------------------


-- Config
-- ------------------------------------------------------------
INSERT INTO server_config (key, value, comment)
VALUES
('apollo.portal.envs', 'dev', '可支持的环境列表'),
('organizations', '[{"orgId":"TEST1","orgName":"样例部门1"},{"orgId":"TEST2","orgName":"样例部门2"}]', '部门列表'),
('superAdmin', 'apollo', 'Portal超级管理员'),
('api.readTimeout', '10000', 'http接口read timeout'),
('consumer.token.salt', 'someSalt', 'consumer token salt'),
('admin.createPrivateNamespace.switch', 'true', '是否允许项目管理员创建私有namespace'),
('configView.memberOnly.envs', 'dev', '只对项目成员显示配置信息的环境列表，多个env以英文逗号分隔');

INSERT INTO users (username, password, email, enabled)
VALUES
('apollo', '$2a$10$kDeSc8CPoIFBRvkRY0VDau62.4yACXfuc9Z1Gg2CfEBg63zJmg.xG', 'apollo@acme.com', 1);

INSERT INTO authorities (username, authority) values ('apollo', 'ROLE_user');

-- Sample Data
-- ------------------------------------------------------------
INSERT INTO app (app_id, app_name, org_id, org_name, owner_name, owner_email)
VALUES
('SampleApp', 'Sample App', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com');

INSERT INTO app_namespace (namespace_name, app_id, format, shared, comment)
VALUES
('application', 'SampleApp', 'properties', FALSE, 'default app namespace');

INSERT INTO permission (id, permission_type, target_id)
VALUES
(1, 'CreateCluster', 'SampleApp'),
(2, 'CreateNamespace', 'SampleApp'),
(3, 'AssignRole', 'SampleApp'),
(4, 'ModifyNamespace', 'SampleApp+application'),
(5, 'ReleaseNamespace', 'SampleApp+application');

INSERT INTO role (id, role_name)
VALUES
(1, 'Master+SampleApp'),
(2, 'ModifyNamespace+SampleApp+application'),
(3, 'ReleaseNamespace+SampleApp+application');

INSERT INTO role_permission (role_id, permission_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(3, 5);

INSERT INTO user_role (user_id, role_id)
VALUES
('apollo', 1),
('apollo', 2),
('apollo', 3);


