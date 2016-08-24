create table test_junktable (
  id                        number(10) not null,
  name                      varchar2(255),
  description               varchar2(255),
  constraint pk_test_junktable primary key (id))
;

create table j_job_requirement (
  job_requirement_id        number(10) not null,
  job_requirement_employer  varchar2(255),
  job_requirement_exp       varchar2(255),
  job_requirement_title     varchar2(255),
  job_requirement_desciption varchar2(255),
  j_gender                  varchar2(6),
  job_requirement_minSalary number(10),
  job_requirement_maxSalary number(10),
  constraint ck_j_job_requirement_j_gender check (j_gender in ('Male','Female')),
  constraint pk_j_job_requirement primary key (job_requirement_id))
;

create table j_language (
  language_id               number(10) not null,
  language_name             varchar2(255),
  constraint uq_j_language_language_name unique (language_name),
  constraint pk_j_language primary key (language_id))
;

create table j_location (
  location_id               number(10) not null,
  location_name             varchar2(255),
  constraint uq_j_location_location_name unique (location_name),
  constraint pk_j_location primary key (location_id))
;

create table j_role (
  role_id                   number(10) not null,
  role_name                 varchar2(255),
  constraint uq_j_role_role_name unique (role_name),
  constraint pk_j_role primary key (role_id))
;

create table j_skill (
  skill_id                  number(10) not null,
  skill_name                varchar2(255),
  constraint uq_j_skill_skill_name unique (skill_name),
  constraint pk_j_skill primary key (skill_id))
;


create table language_detail (
  job_requirement_id             number(10) not null,
  language_id                    number(10) not null,
  constraint pk_language_detail primary key (job_requirement_id, language_id))
;

create table skill_detail (
  job_requirement_id             number(10) not null,
  skill_id                       number(10) not null,
  constraint pk_skill_detail primary key (job_requirement_id, skill_id))
;
create sequence test_junktable_seq;

create sequence j_job_requirement_seq;

create sequence j_language_seq;

create sequence j_location_seq;

create sequence j_role_seq;

create sequence j_skill_seq;

alter table j_job_requirement add constraint fk_j_job_requirement_locatio_1 foreign key (job_requirement_id) references j_location (location_id);
create index ix_j_job_requirement_locatio_1 on j_job_requirement (job_requirement_id);
alter table j_job_requirement add constraint fk_j_job_requirement_role_2 foreign key (job_requirement_id) references j_role (role_id);
create index ix_j_job_requirement_role_2 on j_job_requirement (job_requirement_id);



alter table language_detail add constraint fk_language_detail_j_job_re_01 foreign key (job_requirement_id) references j_job_requirement (job_requirement_id);

alter table language_detail add constraint fk_language_detail_j_langua_02 foreign key (language_id) references j_language (language_id);

alter table skill_detail add constraint fk_skill_detail_j_job_requi_01 foreign key (job_requirement_id) references j_job_requirement (job_requirement_id);

alter table skill_detail add constraint fk_skill_detail_j_skill_02 foreign key (skill_id) references j_skill (skill_id);
