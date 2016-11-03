drop table if exists R_role_power;

drop table if exists answer;

drop table if exists bug;

drop table if exists comment;

drop table if exists loginUser;

drop table if exists menu;

drop table if exists power;

drop table if exists question;

drop table if exists reply;

drop table if exists role;

drop table if exists userInfo;

/*==============================================================*/
/* Table: R_role_power                                          */
/*==============================================================*/
create table R_role_power
(
   id                   integer not null auto_increment,
   r_id                 integer,
   p_id                 integer,
   primary key (id)
);

/*==============================================================*/
/* Table: answer                                                */
/*==============================================================*/
create table answer
(
   a_id                 integer not null auto_increment,
   a_content            varchar(10000),
   a_author             varchar(40),
   a_publishTime        date,
   a_likeNum            integer,
   a_hateNum            integer,
   primary key (a_id)
);

/*==============================================================*/
/* Table: bug                                                   */
/*==============================================================*/
create table bug
(
   b_id                 integer not null auto_increment,
   b_title              varchar(200),
   b_describe           varchar(200),
   b_reason             varchar(400),
   b_method             varchar(10000),
   b_publishTime        date,
   b_author             varchar(40),
   b_likeNum            integer,
   b_hateNum            integer,
   primary key (b_id)
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   Column_1             integer not null auto_increment,
   c_content            varchar(10000),
   c_publishTime        date,
   c_author             varchar(40),
   c_likeNum            integer,
   c_hateNum            integer,
   primary key (Column_1)
);

/*==============================================================*/
/* Table: loginUser                                             */
/*==============================================================*/
create table loginUser
(
   l_name               varchar(30),
   l_password           varchar(40),
   l_email              varchar(30),
   l_id                 integer not null auto_increment,
   r_id                 integer,
   primary key (l_id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   m_id                 integer not null auto_increment,
   m_name               varchar(40),
   m_url                varchar(40),
   primary key (m_id)
);

/*==============================================================*/
/* Table: power                                                 */
/*==============================================================*/
create table power
(
   p_id                 integer not null auto_increment,
   p_name               varchar(20),
   primary key (p_id)
);

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   q_id                 integer not null auto_increment,
   q_title              varchar(200),
   q_descirbe           varchar(200),
   q_publishTime        date,
   q_author             varchar(40),
   q_likeNum            integer,
   q_hateNum            integer,
   primary key (q_id)
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   r_id                 integer not null auto_increment,
   r_content            varchar(10000),
   r_publishTime        date,
   r_author             varchar(40),
   r_likeNum            integer,
   r_hateNum            integer,
   primary key (r_id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   r_id                 integer not null auto_increment,
   r_name               varchar(40),
   primary key (r_id)
);

/*==============================================================*/
/* Table: userInfo                                              */
/*==============================================================*/
create table userInfo
(
   u_id                 integer not null auto_increment,
   u_birthday           date,
   u_headPortrait       varchar(40),
   u_sex                varchar(2),
   u_registTime         date,
   u_describe           varchar(1000),
   primary key (u_id)
);
