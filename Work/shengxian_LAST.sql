/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2020/7/12 4:27:59                            */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_ADDRESS_RELATIONS_USERLIST') then
    alter table Address
       delete foreign key FK_ADDRESS_RELATIONS_USERLIST
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COUPON_RELATIONS_USERLIST') then
    alter table Coupon
       delete foreign key FK_COUPON_RELATIONS_USERLIST
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MENU_SUG_RELATIONS_MENU') then
    alter table Menu_suggestion
       delete foreign key FK_MENU_SUG_RELATIONS_MENU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRODUCT_RELATIONS_PRODUCT_') then
    alter table Product
       delete foreign key FK_PRODUCT_RELATIONS_PRODUCT_
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PRODUCT__RELATIONS_PRODUCT') then
    alter table Product_suggestion
       delete foreign key FK_PRODUCT__RELATIONS_PRODUCT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SYSTEMUS_RELATIONS_SYSTEMUS') then
    alter table SystemUser_buy
       delete foreign key FK_SYSTEMUS_RELATIONS_SYSTEMUS
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DISTCOUN_RELATIONS_PRODUCT') then
    alter table distcount_time
       delete foreign key FK_DISTCOUN_RELATIONS_PRODUCT
end if;

drop index if exists Address.Relationship_4_FK;

drop index if exists Address.Address_PK;

drop table if exists Address;

drop index if exists Coupon.Relationship_8_FK;

drop index if exists Coupon.Coupon_PK;

drop table if exists Coupon;

drop index if exists Menu.Menu_PK;

drop table if exists Menu;

drop index if exists Menu_suggestion.Relationship_2_FK;

drop table if exists Menu_suggestion;

drop index if exists Order_product.Order_info_PK;

drop table if exists Order_product;

drop index if exists Product.Relationship_1_FK;

drop index if exists Product.Product_PK;

drop table if exists Product;

drop index if exists Product_Evaluation.Product_Evaluation_PK;

drop table if exists Product_Evaluation;

drop index if exists Product_suggestion.Relationship_3_FK;

drop table if exists Product_suggestion;

drop index if exists Product_type.Product_type_PK;

drop table if exists Product_type;

drop index if exists SystemUser.SystemUser_PK;

drop table if exists SystemUser;

drop index if exists SystemUser_buy.Relationship_7_FK;

drop index if exists SystemUser_buy.SystemUser_buy_PK;

drop table if exists SystemUser_buy;

drop index if exists UserList.UserList_PK;

drop table if exists UserList;

drop index if exists distcount_time.Relationship_6_FK;

drop index if exists distcount_time.distcount_time_PK;

drop table if exists distcount_time;

drop index if exists full_distcount.full_distcount_PK;

drop table if exists full_distcount;

/*==============================================================*/
/* Table: Address                                               */
/*==============================================================*/
create table Address 
(
   Address_id           integer                        not null,
   User_id              varchar(20)                    null,
   Address_province     varchar(25)                    null,
   Address_city         varchar(25)                    null,
   Address_area         varchar(25)                    null,
   Address_person       varchar(25)                    null,
   Address_tel          varchar(25)                    null,
   constraint PK_ADDRESS primary key (Address_id)
);

/*==============================================================*/
/* Index: Address_PK                                            */
/*==============================================================*/
create unique index Address_PK on Address (
Address_id ASC
);

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/
create index Relationship_4_FK on Address (
User_id ASC
);

/*==============================================================*/
/* Table: Coupon                                                */
/*==============================================================*/
create table Coupon 
(
   Coupon_id            integer                        not null,
   User_id              varchar(20)                    null,
   Coupon_statement     varchar(255)                   null,
   Coupon_between       float                          null,
   Coupon_sub           float                          null,
   Coupon_start_time    timestamp                      null,
   Coupon_end_time      timestamp                      null,
   Coupon_used_time     timestamp                      null,
   constraint PK_COUPON primary key (Coupon_id)
);

/*==============================================================*/
/* Index: Coupon_PK                                             */
/*==============================================================*/
create unique index Coupon_PK on Coupon (
Coupon_id ASC
);

/*==============================================================*/
/* Index: Relationship_8_FK                                     */
/*==============================================================*/
create index Relationship_8_FK on Coupon (
User_id ASC
);

/*==============================================================*/
/* Table: Menu                                                  */
/*==============================================================*/
create table Menu 
(
   Menu_id              integer                        not null,
   Menu_name            varchar(20)                    null,
   Menu_product         varchar(255)                   null,
   Menu_step            varchar(255)                   null,
   Menu_imag            long binary                    null,
   constraint PK_MENU primary key (Menu_id)
);

/*==============================================================*/
/* Index: Menu_PK                                               */
/*==============================================================*/
create unique index Menu_PK on Menu (
Menu_id ASC
);

/*==============================================================*/
/* Table: Menu_suggestion                                       */
/*==============================================================*/
create table Menu_suggestion 
(
   Menu_id              integer                        null,
   Menu_suggestion      varchar(255)                   null
);

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on Menu_suggestion (
Menu_id ASC
);

/*==============================================================*/
/* Table: Order_product                                         */
/*==============================================================*/
create table Order_product 
(
   Oder_info_Order_id   integer                        not null,
   Order_info_product_id integer                        null,
   Oder_info_User_id    varchar(20)                    null,
   Order_info_count     integer                        null,
   Order_info_discount_time_id integer                        null,
   Oder_info_begin_price float                          null,
   Oder_info_end_price  float                          null,
   Oder_info_coupon_id  integer                        null,
   Oder_info_fulldiscount_ID integer                        null,
   Oder_info_plantime   timestamp                      null,
   Order_info_finishtime timestamp                      null,
   Oder_info_address_id integer                        null,
   Oder_info_statement  varchar(255)                   null,
   Order_info_evatime   integer                        null,
   constraint PK_ORDER_PRODUCT primary key (Oder_info_Order_id)
);

/*==============================================================*/
/* Index: Order_info_PK                                         */
/*==============================================================*/
create unique index Order_info_PK on Order_product (
Oder_info_Order_id ASC
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product 
(
   Product_id           integer                        not null,
   Product_type_id      integer                        null,
   Product_name         varchar(20)                    null,
   Product_price        float                          null,
   Product_vip_price    float                          null,
   Product_stock        integer                        null,
   Product_format       varchar(20)                    null,
   Product_statement    varchar(255)                   null,
   constraint PK_PRODUCT primary key (Product_id)
);

/*==============================================================*/
/* Index: Product_PK                                            */
/*==============================================================*/
create unique index Product_PK on Product (
Product_id ASC
);

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/
create index Relationship_1_FK on Product (
Product_type_id ASC
);

/*==============================================================*/
/* Table: Product_Evaluation                                    */
/*==============================================================*/
create table Product_Evaluation 
(
   Product_Evaluation_id integer                        not null,
   Product_Evaluation_user_id varchar(20)                    null,
   Product_Evaluation_product_id integer                        null,
   Product_Evaluation_time timestamp                      null,
   Product_Evaluation_level integer                        null,
   Product_Evaluation_imag varchar(255)                   null,
   constraint PK_PRODUCT_EVALUATION primary key (Product_Evaluation_id)
);

/*==============================================================*/
/* Index: Product_Evaluation_PK                                 */
/*==============================================================*/
create unique index Product_Evaluation_PK on Product_Evaluation (
Product_Evaluation_id ASC
);

/*==============================================================*/
/* Table: Product_suggestion                                    */
/*==============================================================*/
create table Product_suggestion 
(
   Product_id           integer                        null,
   Product_suggestion   varchar(255)                   null
);

/*==============================================================*/
/* Index: Relationship_3_FK                                     */
/*==============================================================*/
create index Relationship_3_FK on Product_suggestion (
Product_id ASC
);

/*==============================================================*/
/* Table: Product_type                                          */
/*==============================================================*/
create table Product_type 
(
   Product_type_id      integer                        not null,
   Product_type_name    varchar(50)                    null,
   Product_type_statement varchar(255)                   null,
   constraint PK_PRODUCT_TYPE primary key (Product_type_id)
);

/*==============================================================*/
/* Index: Product_type_PK                                       */
/*==============================================================*/
create unique index Product_type_PK on Product_type (
Product_type_id ASC
);

/*==============================================================*/
/* Table: SystemUser                                            */
/*==============================================================*/
create table SystemUser 
(
   SystemUser_id        varchar(20)                    not null,
   SystemUser_name      varchar(20)                    null,
   SystemUser_pwd       varchar(50)                    null,
   constraint PK_SYSTEMUSER primary key (SystemUser_id)
);

/*==============================================================*/
/* Index: SystemUser_PK                                         */
/*==============================================================*/
create unique index SystemUser_PK on SystemUser (
SystemUser_id ASC
);

/*==============================================================*/
/* Table: SystemUser_buy                                        */
/*==============================================================*/
create table SystemUser_buy 
(
   SystemUser_buy_id    integer                        not null,
   SystemUser_id        varchar(20)                    null,
   SystemUser_buy_product_id integer                        null,
   SystemUser_buy_count integer                        null,
   SystemUser_buy_statement varchar(20)                    null,
   constraint PK_SYSTEMUSER_BUY primary key (SystemUser_buy_id)
);

/*==============================================================*/
/* Index: SystemUser_buy_PK                                     */
/*==============================================================*/
create unique index SystemUser_buy_PK on SystemUser_buy (
SystemUser_buy_id ASC
);

/*==============================================================*/
/* Index: Relationship_7_FK                                     */
/*==============================================================*/
create index Relationship_7_FK on SystemUser_buy (
SystemUser_id ASC
);

/*==============================================================*/
/* Table: UserList                                              */
/*==============================================================*/
create table UserList 
(
   User_id              varchar(20)                    not null,
   User_name            varchar(20)                    null,
   User_sex             varchar(20)                    null,
   User_pwd             varchar(20)                    null,
   User_tel             varchar(11)                    null,
   User_email           varchar(50)                    null,
   User_city            varchar(20)                    null,
   User_reg_time        timestamp                      null,
   User_end_time        timestamp                      null,
   User_vip             integer                        null,
   User_sumpay          float                          null,
   constraint PK_USERLIST primary key (User_id)
);

/*==============================================================*/
/* Index: UserList_PK                                           */
/*==============================================================*/
create unique index UserList_PK on UserList (
User_id ASC
);

/*==============================================================*/
/* Table: distcount_time                                        */
/*==============================================================*/
create table distcount_time 
(
   distcount_time_id    integer                        not null,
   Product_id           integer                        null,
   distcount_time_price float                          null,
   distcount_time_count integer                        null,
   distcount_time_begin_time timestamp                      null,
   distcount_time_end_time timestamp                      null,
   constraint PK_DISTCOUNT_TIME primary key (distcount_time_id)
);

/*==============================================================*/
/* Index: distcount_time_PK                                     */
/*==============================================================*/
create unique index distcount_time_PK on distcount_time (
distcount_time_id ASC
);

/*==============================================================*/
/* Index: Relationship_6_FK                                     */
/*==============================================================*/
create index Relationship_6_FK on distcount_time (
Product_id ASC
);

/*==============================================================*/
/* Table: full_distcount                                        */
/*==============================================================*/
create table full_distcount 
(
   full_distcount_id    integer                        not null,
   full_distcount_product_id integer                        null,
   full_distcount_between float                          null,
   full_distcount_disctount float                          null,
   full_distcount_begin_time timestamp                      null,
   full_distcount_end_time timestamp                      null,
   full_distcount_STATEMENT varchar(255)                   null,
   constraint PK_FULL_DISTCOUNT primary key (full_distcount_id)
);

/*==============================================================*/
/* Index: full_distcount_PK                                     */
/*==============================================================*/
create unique index full_distcount_PK on full_distcount (
full_distcount_id ASC
);

alter table Address
   add constraint FK_ADDRESS_RELATIONS_USERLIST foreign key (User_id)
      references UserList (User_id)
      on update restrict
      on delete restrict;

alter table Coupon
   add constraint FK_COUPON_RELATIONS_USERLIST foreign key (User_id)
      references UserList (User_id)
      on update restrict
      on delete restrict;

alter table Menu_suggestion
   add constraint FK_MENU_SUG_RELATIONS_MENU foreign key (Menu_id)
      references Menu (Menu_id)
      on update restrict
      on delete restrict;

alter table Product
   add constraint FK_PRODUCT_RELATIONS_PRODUCT_ foreign key (Product_type_id)
      references Product_type (Product_type_id)
      on update restrict
      on delete restrict;

alter table Product_suggestion
   add constraint FK_PRODUCT__RELATIONS_PRODUCT foreign key (Product_id)
      references Product (Product_id)
      on update restrict
      on delete restrict;

alter table SystemUser_buy
   add constraint FK_SYSTEMUS_RELATIONS_SYSTEMUS foreign key (SystemUser_id)
      references SystemUser (SystemUser_id)
      on update restrict
      on delete restrict;

alter table distcount_time
   add constraint FK_DISTCOUN_RELATIONS_PRODUCT foreign key (Product_id)
      references Product (Product_id)
      on update restrict
      on delete restrict;

