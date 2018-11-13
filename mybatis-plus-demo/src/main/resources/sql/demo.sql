create table DEMO
(
  id          NUMBER(20) not null,
  name        VARCHAR2(30),
  age         NUMBER(11),
  type        NUMBER(11),
  phone       VARCHAR2(50),
  creste_time DATE
);
-- Add comments to the columns
comment on column DEMO.id
  is '主键Id';
comment on column DEMO.name
  is '名称';
comment on column DEMO.age
  is '年龄';
comment on column DEMO.type
  is '类型';
comment on column DEMO.phone
  is '手机号码';
comment on column DEMO.creste_time
  is '日期';
