create table access (
id bigint not null,
site_id bigint,
login varchar(255),
password varchar(255),
type_access varchar(255),
url varchar(255),
primary key (id)) engine=InnoDB;

create table access_seq (next_val bigint) engine=InnoDB;
insert into access_seq values ( 1 );

create table client (author_id bigint,
id bigint not null,
company varchar(255),
email varchar(255),
name varchar(255),
phone varchar(255),
site varchar(255),
primary key (id)) engine=InnoDB;


create table client_contract (client_id bigint not null,
contract_set varchar (255)) engine=InnoDB;

create table client_seq (next_val bigint) engine=InnoDB;
insert into client_seq values ( 1 );

create table site (id bigint not null,
name_site varchar(255),
primary key (id)) engine=InnoDB;

create table site_seq (next_val bigint) engine=InnoDB;
insert into site_seq values ( 1 );

create table usr (id bigint not null,
password varchar(255),
username varchar(255),
primary key (id)) engine=InnoDB;

create table usr_seq (next_val bigint) engine=InnoDB;
insert into usr_seq values ( 1 );

alter table access add constraint FK3sjvtgcf10cfw85a61rvypsdu foreign key (site_id) references site (id) ON DELETE CASCADE;
alter table client add constraint FK37yfonmyncdpwcpbbs8kojrn0 foreign key (author_id) references usr (id);
alter table client_contract add constraint FKngsgd8kqw3bb1ahyqwkdi0mcr foreign key (client_id) references client (id);
