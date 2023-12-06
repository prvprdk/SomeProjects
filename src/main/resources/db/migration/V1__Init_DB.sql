create sequence access_seq start with 1 increment by 50;
create sequence client_seq start with 1 increment by 50;
create sequence site_seq start with 1 increment by 50;
create sequence usr_seq start with 1 increment by 50;


create table access (
check_access boolean not null,
id bigint not null, site_id bigint,
login varchar(255),
password varchar(255),
type_access varchar(255),
url varchar(255),
primary key (id));


create table client (
author_id bigint,
id bigint not null,
company varchar(255),
email varchar(255),
name varchar(255),
phone varchar(255),
site varchar(255),
primary key (id));

create table client_contract (
client_id bigint not null,
 contract_set varchar(255) check (contract_set in ('FIX','FACT')));

create table site (
id bigint not null,
name_site varchar(255),
primary key (id));

create table usr (id bigint not null,
password varchar(255),
username varchar(255),
primary key (id));

alter table if exists access add constraint FK5xa96780pvqfedpkpee0frnyf foreign key (site_id) references site ON DELETE CASCADE;
alter table if exists client add constraint FK37yfonmyncdpwcpbbs8kojrn0 foreign key (author_id) references usr;
