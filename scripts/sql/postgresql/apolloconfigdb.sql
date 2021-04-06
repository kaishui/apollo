
CREATE SEQUENCE app_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE app (
                     id                 BIGINT    NOT NULL DEFAULT nextval('app_id_seq'),
                     app_id             TEXT      NOT NULL DEFAULT 'default',
                     app_name           TEXT      NOT NULL DEFAULT 'default',
                     org_id             TEXT      NOT NULL DEFAULT 'default',
                     org_name           TEXT      NOT NULL DEFAULT 'default',
                     owner_name         TEXT      NOT NULL DEFAULT 'default',
                     owner_email        TEXT      NOT NULL DEFAULT 'default',
                     deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                     created_by         TEXT      NOT NULL DEFAULT 'default',
                     created_time       TIMESTAMP NOT NULL DEFAULT now(),
                     last_modified_by   TEXT               DEFAULT '',
                     last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX app_app_id_index
  ON app (app_id);
CREATE INDEX app_app_name_index
  ON app (app_name);
CREATE INDEX app_last_modified_time_index
  ON app (last_modified_time);

CREATE SEQUENCE app_namespace_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE app_namespace (
                               id                 BIGINT    NOT NULL DEFAULT nextval('app_namespace_id_seq') PRIMARY KEY,
                               namespace_name     TEXT      NOT NULL DEFAULT '',
                               app_id             TEXT      NOT NULL DEFAULT '',
                               format             TEXT      NOT NULL DEFAULT 'properties',
                               shared             BOOLEAN   NOT NULL DEFAULT FALSE,
                               comment            TEXT      NOT NULL DEFAULT '',
                               deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                               created_by         TEXT      NOT NULL DEFAULT '',
                               created_time       TIMESTAMP NOT NULL DEFAULT now(),
                               last_modified_by   TEXT               DEFAULT '',
                               last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX app_namespace_app_id_index
  ON app_namespace (app_id);
CREATE INDEX app_namespace_namespace_name_app_id_index
  ON app_namespace (namespace_name, app_id);
CREATE INDEX app_namespace_last_modified_time_index
  ON app_namespace (last_modified_time);

CREATE SEQUENCE audit_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE audit (
                       id                 BIGINT    NOT NULL DEFAULT nextval('audit_id_seq') PRIMARY KEY,
                       entity_name        TEXT      NOT NULL DEFAULT 'default',
                       entity_id          BIGINT,
                       op_name            TEXT      NOT NULL DEFAULT 'default',
                       comment            TEXT,
                       deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                       created_by         TEXT      NOT NULL DEFAULT 'default',
                       created_time       TIMESTAMP NOT NULL DEFAULT now(),
                       last_modified_by   TEXT               DEFAULT '',
                       last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX audit_last_modified_time_index
  ON audit (last_modified_time);

CREATE SEQUENCE cluster_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE cluster (
                         id                 BIGINT    NOT NULL DEFAULT nextval('cluster_id_seq') PRIMARY KEY,
                         cluster_name       TEXT      NOT NULL DEFAULT '',
                         app_id             TEXT      NOT NULL DEFAULT '',
                         parent_cluster_id  BIGINT    NOT NULL DEFAULT 0,
                         deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                         created_by         TEXT      NOT NULL DEFAULT '',
                         created_time       TIMESTAMP NOT NULL DEFAULT now(),
                         last_modified_by   TEXT               DEFAULT '',
                         last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX cluster_app_id_cluster_name_index
  ON cluster (app_id, cluster_name);
CREATE INDEX cluster_parent_cluster_id_index
  ON cluster (parent_cluster_id);
CREATE INDEX cluster_last_modified_time_index
  ON cluster (last_modified_time);

CREATE SEQUENCE commit_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE commit (
                        id                 BIGINT    NOT NULL DEFAULT nextval('commit_id_seq') PRIMARY KEY,
                        change_sets        TEXT      NOT NULL,
                        app_id             TEXT      NOT NULL DEFAULT 'default',
                        cluster_name       TEXT      NOT NULL DEFAULT 'default',
                        namespace_name     TEXT      NOT NULL DEFAULT 'default',
                        comment            TEXT,
                        deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                        created_by         TEXT      NOT NULL DEFAULT 'default',
                        created_time       TIMESTAMP NOT NULL DEFAULT now(),
                        last_modified_by   TEXT               DEFAULT '',
                        last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX commit_app_id_index
  ON commit (app_id);
CREATE INDEX commit_cluster_name_index
  ON commit (cluster_name);
CREATE INDEX commit_namespace_name_index
  ON commit (namespace_name);
CREATE INDEX commit_last_modified_time_index
  ON commit (last_modified_time);

CREATE SEQUENCE gray_release_rule_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE gray_release_rule (
                                   id                 BIGINT    NOT NULL DEFAULT nextval('gray_release_rule_id_seq') PRIMARY KEY,
                                   app_id             TEXT      NOT NULL DEFAULT 'default',
                                   cluster_name       TEXT      NOT NULL DEFAULT 'default',
                                   namespace_name     TEXT      NOT NULL DEFAULT 'default',
                                   branch_name        TEXT      NOT NULL DEFAULT 'default',
                                   rules              TEXT      NOT NULL DEFAULT '[]',
                                   release_id         BIGINT    NOT NULL DEFAULT 0,
                                   branch_status      SMALLINT           DEFAULT 1,
                                   deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                                   created_by         TEXT      NOT NULL DEFAULT 'default',
                                   created_time       TIMESTAMP NOT NULL DEFAULT now(),
                                   last_modified_by   TEXT               DEFAULT '',
                                   last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX gray_release_rule_app_id_cluster_name_namespace_name_index
  ON gray_release_rule (app_id, cluster_name, namespace_name);
CREATE INDEX gray_release_rule_last_modified_time_index
  ON gray_release_rule (last_modified_time);

CREATE SEQUENCE instance_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE instance (
                          id                 BIGINT    NOT NULL DEFAULT nextval('instance_id_seq') PRIMARY KEY,
                          app_id             TEXT      NOT NULL DEFAULT 'default',
                          cluster_name       TEXT      NOT NULL DEFAULT 'default',
                          data_center        TEXT      NOT NULL DEFAULT 'default',
                          ip                 TEXT      NOT NULL DEFAULT '',
                          created_time       TIMESTAMP NOT NULL DEFAULT now(),
                          last_modified_time TIMESTAMP          DEFAULT now(),
                          UNIQUE (app_id, cluster_name, ip, data_center)
);
CREATE INDEX instance_ip_index
  ON instance (ip);
CREATE INDEX instance_last_modified_time_index
  ON instance (last_modified_time);

CREATE SEQUENCE instance_config_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE instance_config (
                                 id                    BIGINT    NOT NULL DEFAULT nextval('instance_config_id_seq') PRIMARY KEY,
                                 instance_id           BIGINT,
                                 config_app_id         TEXT      NOT NULL DEFAULT 'default',
                                 config_cluster_name   TEXT      NOT NULL DEFAULT 'default',
                                 config_namespace_name TEXT      NOT NULL DEFAULT 'default',
                                 release_key           TEXT      NOT NULL DEFAULT '',
                                 release_delivery_time TIMESTAMP,
                                 created_time          TIMESTAMP NOT NULL DEFAULT now(),
                                 last_modified_time    TIMESTAMP          DEFAULT now(),
                                 UNIQUE (instance_id, config_app_id, config_namespace_name)
);
CREATE INDEX instance_config_config_app_id_config_cluster_name_config_namespace_name_last_modified_time_index
  ON instance_config (config_app_id, config_cluster_name, config_namespace_name, last_modified_time);
CREATE INDEX instance_config_release_key_index
  ON instance_config (release_key);
CREATE INDEX instance_config_last_modified_time_index
  ON instance_config (last_modified_time);

CREATE SEQUENCE item_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE item (
                      id                 BIGINT    NOT NULL DEFAULT nextval('item_id_seq') PRIMARY KEY,
                      namespace_id       BIGINT    NOT NULL DEFAULT 0,
                      key                TEXT      NOT NULL DEFAULT 'default',
                      value              TEXT      NOT NULL,
                      comment            TEXT               DEFAULT '',
                      line_num           BIGINT             DEFAULT 0,
                      deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                      created_by         TEXT      NOT NULL DEFAULT 'default',
                      created_time       TIMESTAMP NOT NULL DEFAULT now(),
                      last_modified_by   TEXT               DEFAULT '',
                      last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX item_namespace_id_index
  ON item (namespace_id);
CREATE INDEX item_last_modified_time_index
  ON item (last_modified_time);

CREATE SEQUENCE namespace_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE namespace (
                           id                 BIGINT    NOT NULL DEFAULT nextval('namespace_id_seq') PRIMARY KEY,
                           app_id             TEXT      NOT NULL DEFAULT 'default',
                           cluster_name       TEXT      NOT NULL DEFAULT 'default',
                           namespace_name     TEXT      NOT NULL DEFAULT 'default',
                           deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                           created_by         TEXT      NOT NULL DEFAULT 'default',
                           created_time       TIMESTAMP NOT NULL DEFAULT now(),
                           last_modified_by   TEXT               DEFAULT '',
                           last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX namespace_app_id_cluster_name_namespace_name_index
  ON namespace (app_id, cluster_name, namespace_name);
CREATE INDEX namespace_namespace_name_index
  ON namespace (namespace_name);
CREATE INDEX namespace_last_modified_time_index
  ON namespace (last_modified_time);

CREATE SEQUENCE namespace_lock_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE namespace_lock (
                                id                 BIGINT    NOT NULL DEFAULT nextval('namespace_lock_id_seq') PRIMARY KEY,
                                namespace_id       BIGINT    NOT NULL DEFAULT 0,
                                deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                                created_by         TEXT      NOT NULL DEFAULT 'default',
                                created_time       TIMESTAMP NOT NULL DEFAULT now(),
                                last_modified_by   TEXT               DEFAULT 'default',
                                last_modified_time TIMESTAMP          DEFAULT now(),
                                UNIQUE (namespace_id)
);
CREATE INDEX namespace_lock_last_modified_time_index
  ON namespace_lock (last_modified_time);

CREATE SEQUENCE release_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE release (
                         id                 BIGINT    NOT NULL DEFAULT nextval('release_id_seq') PRIMARY KEY,
                         release_key        TEXT      NOT NULL DEFAULT '',
                         release_name       TEXT      NOT NULL DEFAULT 'default',
                         comment            TEXT,
                         app_id             TEXT      NOT NULL DEFAULT 'default',
                         cluster_name       TEXT      NOT NULL DEFAULT 'default',
                         namespace_name     TEXT      NOT NULL DEFAULT 'default',
                         configurations     TEXT      NOT NULL,
                         abandoned          BOOLEAN   NOT NULL DEFAULT FALSE,
                         deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                         created_by         TEXT      NOT NULL DEFAULT 'default',
                         created_time       TIMESTAMP NOT NULL DEFAULT now(),
                         last_modified_by   TEXT               DEFAULT '',
                         last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX release_release_key_index
  ON release (release_key);
CREATE INDEX release_app_id_cluster_name_namespace_name_index
  ON release (app_id, cluster_name, namespace_name);
CREATE INDEX release_last_modified_time_index
  ON release (last_modified_time);

CREATE SEQUENCE release_history_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE release_history (
                                 id                  BIGINT    NOT NULL DEFAULT nextval('release_history_id_seq') PRIMARY KEY,
                                 app_id              TEXT      NOT NULL DEFAULT 'default',
                                 cluster_name        TEXT      NOT NULL DEFAULT 'default',
                                 namespace_name      TEXT      NOT NULL DEFAULT 'default',
                                 branch_name         TEXT      NOT NULL DEFAULT 'default',
                                 release_id          BIGINT    NOT NULL DEFAULT 0,
                                 previous_release_id BIGINT    NOT NULL DEFAULT 0,
                                 operation           SMALLINT  NOT NULL DEFAULT 0,
                                 operation_context   TEXT      NOT NULL,
                                 deleted             BOOLEAN   NOT NULL DEFAULT FALSE,
                                 created_by          TEXT      NOT NULL DEFAULT 'default',
                                 created_time        TIMESTAMP NOT NULL DEFAULT now(),
                                 last_modified_by    TEXT               DEFAULT '',
                                 last_modified_time  TIMESTAMP          DEFAULT now()
);
CREATE INDEX release_history_app_id_cluster_name_namespace_name_branch_name_index
  ON release_history (app_id, cluster_name, namespace_name, branch_name);
CREATE INDEX release_history_release_id_index
  ON release_history (release_id);
CREATE INDEX release_history_last_modified_time_index
  ON release_history (last_modified_time);

CREATE SEQUENCE release_message_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE release_message (
                                 id                 BIGINT NOT NULL DEFAULT nextval('release_message_id_seq') PRIMARY KEY,
                                 message            TEXT   NOT NULL DEFAULT '',
                                 last_modified_time TIMESTAMP       DEFAULT now()
);
CREATE INDEX release_message_message_index
  ON release_message (message);
CREATE INDEX release_message_last_modified_time_index
  ON release_message (last_modified_time);

CREATE SEQUENCE server_config_id_seq
    INCREMENT 1
  START 1;
CREATE TABLE server_config (
                               id                 BIGINT    NOT NULL DEFAULT nextval('server_config_id_seq') PRIMARY KEY,
                               key                TEXT      NOT NULL DEFAULT 'default',
                               cluster            TEXT      NOT NULL DEFAULT 'default',
                               value              TEXT      NOT NULL DEFAULT 'default',
                               comment            TEXT               DEFAULT '',
                               deleted            BOOLEAN   NOT NULL DEFAULT FALSE,
                               created_by         TEXT      NOT NULL DEFAULT 'default',
                               created_time       TIMESTAMP NOT NULL DEFAULT now(),
                               last_modified_by   TEXT               DEFAULT '',
                               last_modified_time TIMESTAMP          DEFAULT now()
);
CREATE INDEX server_config_key_index
  ON server_config (key);
CREATE INDEX server_config_last_modified_time_index
  ON server_config (last_modified_time);

-- Dump of table accesskey
-- ------------------------------------------------------------

CREATE SEQUENCE access_key_id_seq
    INCREMENT 1
  START 1;

CREATE TABLE access_key (
   id BIGINT NOT NULL DEFAULT nextval('access_key_id_seq') PRIMARY KEY,
  app_id text NOT NULL DEFAULT 'default',
  secret text NOT NULL DEFAULT '' ,
  enabled BOOLEAN NOT NULL DEFAULT FALSE ,
   deleted BOOLEAN NOT NULL DEFAULT FALSE ,
   created_by         TEXT      NOT NULL DEFAULT 'default',
   created_time       TIMESTAMP NOT NULL DEFAULT now(),
   last_modified_by   TEXT               DEFAULT '',
   last_modified_time TIMESTAMP          DEFAULT now()

);
CREATE INDEX access_key_index
  ON access_key (app_id);
CREATE INDEX access_key_last_modified_time_index
  ON access_key (last_modified_time);

-- Config
-- ------------------------------------------------------------
INSERT INTO server_config (key, cluster, value, comment)
VALUES
    ('eureka.service.url', 'default', 'http://localhost:8080/eureka/', 'Eureka服务Url，多个service以英文逗号分隔'),
    ('namespace.lock.switch', 'default', 'false', '一次发布只能有一个人修改开关'),
    ('item.value.length.limit', 'default', '20000', 'item value最大长度限制'),
    ('config-service.cache.enabled', 'default', 'false', 'ConfigService是否开启缓存，开启后能提高性能，但是会增大内存消耗！'),
    ('item.key.length.limit', 'default', '128', 'item key 最大长度限制');

-- Sample Data
-- ------------------------------------------------------------
INSERT INTO app (app_id, app_name, org_id, org_name, owner_name, owner_email)
VALUES
	('SampleApp', 'Sample App', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com');

INSERT INTO app_namespace (namespace_name, app_id, format, shared, comment)
VALUES
	('application', 'SampleApp', 'properties', FALSE, 'default app namespace');

INSERT INTO cluster (cluster_name, app_id)
VALUES
	('default', 'SampleApp');

INSERT INTO namespace (id, app_id, cluster_name, namespace_name)
VALUES
	(1, 'SampleApp', 'default', 'application');


INSERT INTO item (namespace_id, key, value, comment, line_num)
VALUES
	(1, 'timeout', '100', 'sample timeout配置', 1);

INSERT INTO release (release_key, release_name, comment, app_id, cluster_name, namespace_name, configurations)
VALUES
	('20161009155425-d3a0749c6e20bc15', '20161009155424-release', 'Sample发布', 'SampleApp', 'default', 'application', '{\"timeout\":\"100\"}');

INSERT INTO release_history (app_id, cluster_name, namespace_name, branch_name, release_id, previous_release_id, operation, operation_context, created_by, last_modified_by)
VALUES
  ('SampleApp', 'default', 'application', 'default', 1, 0, 0, '{}', 'apollo', 'apollo');

insert into release_message (message)
VALUES
  ('SampleApp+default+application');

