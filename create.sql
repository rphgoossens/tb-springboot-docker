create table beer (id bigint not null, beer_type varchar(255) not null, name varchar(255) not null, brewery_id bigint, primary key (id)) engine=MyISAM
create table brewery (id bigint not null, country varchar(255), name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
alter table beer add constraint FK5p78eficyvoadx99wd9owh0kf foreign key (brewery_id) references brewery (id)
