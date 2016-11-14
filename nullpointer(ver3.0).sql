drop table if exists R_role_power;

drop table if exists answer;

drop table if exists bug;

drop table if exists comment;

drop table if exists loginUser;

drop table if exists menu;

drop table if exists power;

drop table if exists question;

drop table if exists role;

drop table if exists tag;

drop table if exists userInfo;

/*==============================================================*/
/* Table: R_role_power                                          */
/*==============================================================*/
create table R_role_power
(
   id                   integer not null auto_increment,
   roleId               integer,
   powerId              integer,
   primary key (id)
);

/*==============================================================*/
/* Table: answer                                                */
/*==============================================================*/
create table answer
(
   answerId             integer not null auto_increment,
   answerContent        longtext,
   answerAuthorId       integer,
   answerPublishTime    date,
   answerLikeNum        integer,
   answerHateNum        integer,
   questionId           integer,
   answerParentId       integer,
   primary key (answerId)
);

/*==============================================================*/
/* Table: bug                                                   */
/*==============================================================*/
create table bug
(
   bugId                integer not null auto_increment,
   bugTitle             varchar(200),
   bugDescribe          varchar(200),
   bugReason            varchar(400),
   bugMethod            longtext,
   bugPublishTime       date,
   bugAuthorId          integer,
   bugLikeNum           integer,
   bugHateNum           integer,
   bugPageviews         integer,
   primary key (bugId)
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   Column_1             integer not null auto_increment,
   commentContent       longtext,
   commentPublishTime   date,
   commentAuthorId      integer,
   commentLikeNum       integer,
   commentHateNum       integer,
   bugId                integer,
   commentParentId      integer,
   primary key (Column_1)
);

/*==============================================================*/
/* Table: loginUser                                             */
/*==============================================================*/
create table loginUser
(
   loginName            varchar(30),
   loginPassword        varchar(40),
   loginEmail           varchar(30),
   loginUserId          integer not null auto_increment,
   roleId               integer,
   primary key (loginUserId)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   menuId               integer not null auto_increment,
   menuName             varchar(40),
   menuUrl              varchar(40),
   primary key (menuId)
);

/*==============================================================*/
/* Table: power                                                 */
/*==============================================================*/
create table power
(
   powerId              integer not null auto_increment,
   powerName            varchar(20),
   primary key (powerId)
);

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   questionId           integer not null auto_increment,
   questionTitle        varchar(200),
   questionDescirbe     longtext,
   questionPublishTime  date,
   questionAuthorId     integer,
   questionLikeNum      integer,
   questionHateNum      integer,
   questionAnswerCount  integer,
   primary key (questionId)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   roleId               integer not null auto_increment,
   roleName             varchar(40),
   primary key (roleId)
);

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   tagId                integer not null,
   tagName              varchar(40),
   questionId           integer,
   bugId                integer,
   primary key (tagId)
);

/*==============================================================*/
/* Table: userInfo                                              */
/*==============================================================*/
create table userInfo
(
   userInfoId           integer not null auto_increment,
   userInfoBirthday     date,
   userInfoHeadPortrait varchar(40),
   userInfoSex          varchar(2),
   userInfoRegistTime   date,
   userInfoDescribe     varchar(200),
   userInfoHonorCount   integer,
   primary key (userInfoId)
);
