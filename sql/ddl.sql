create table if not exists wifi
(
    id                     integer primary key autoincrement,
    management_no          text,
    borough                text,
    name                   text,
    road_name_address      text,
    detail_address         text,
    floor                  integer,
    install_type           text,
    install_institution    text,
    service_classification text,
    network_type           text,
    installed_year         integer,
    inout                  text,
    connection_env         text,
    lat                    real,
    lnt                    real,
    work_datetime          text
);


create table if not exists location_history
(
    id           integer primary key autoincrement,
    lat          real,
    lnt          real,
    inquiry_date timestamp DATE DEFAULT (datetime('now','localtime'))
);
