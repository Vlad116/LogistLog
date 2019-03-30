create table if not exists logistic_service_user(
  id bigserial not null
    constraint logistic_service_user_pkey
    primary key,
  email varchar(50),
  hash_password varchar(200),
  first_name varchar(30),
  last_name varchar(30),
  address varchar(50),
  phone_number varchar(15),
  login varchar(30)
);

alter table logistic_service_user owner to postgres;

create table if not exists customer_company(
  id bigserial not null
    constraint customers_pkey
    primary key,
  company_id bigint
);

create table if not exists requisite(
  id bigserial not null
    constraint requisites_pkey
    primary key,
  full_company_name varchar(50),
  legal_address varchar(50),
  actual_address varchar(50),
  inn varchar(10),
  ogrn varchar(13),
  kpp varchar(9),
  okpo varchar(8),
  current_bank_account varchar(20),
  full_name_of_the_bank varchar(50),
  correspondent_account_of_the_bank varchar(20),
  bic varchar(15),
  company_id bigint
);

create table if not exists carrier_company(
  id bigserial not null
    constraint carrier_company_pkey
    primary key,
  the_number_of_trucks integer,
  company_id bigint
);

create table if not exists application(
  id bigserial not null
    constraint applications_pkey
    primary key,
  date timestamp,
  loading_address varchar(50),
  unloading_address varchar(50),
  weight integer,
  payment integer,
  customer_id bigint
    constraint applications_customer_id_fkey
    references customer_company,
  carrier_id bigint
    constraint applications_application_carrier_id_fkey
    references carrier_company,
  loading_type loading_type
);

create table if not exists cargo(
  id bigserial not null
    constraint cargo_pkey
    primary key,
  weight integer,
  type varchar(50),
  application_id bigint
    constraint cargo_application_id_fkey
    references application,
  cargo_name varchar(100),
  length integer,
  width integer,
  height integer
);

create table if not exists driver(
  id bigserial not null
    constraint driver_pkey
    primary key,
  name varchar(50),
  surname varchar(50),
  phone_number varchar(50),
  age smallint,
  driver_expirience smallint,
  carrier_company_id bigint
    constraint drivers_driver_carrier_id_fkey
    references carrier_company
);

create table if not exists tractor(
  id bigserial not null
    constraint tractor_pkey
    primary key,
  registration_number varchar(20),
  years_of_exploitation smallint,
  mileage integer,
  carrier_company_id bigint
    constraint tractor_carrier_company_fkey
    references carrier_company,
  driver_id bigint
    constraint tractor_driver_id_fkey
    references driver,
  model varchar(50),
  mark tractor_mark,
  type tractor_type
);

create table if not exists trailer(
  id bigserial not null
    constraint trailer_pkey
    primary key,
  registration_number varchar(20),
  years_of_exploitation smallint,
  mileage integer,
  tonnage integer,
  carrier_company_id bigint
    constraint trailer_carrier_company_fkey
    references carrier_company,
  volume_in_cubic_meters integer,
  mark trailer_mark,
  type trailer_type,
  inner_length integer,
  inner_width integer,
  inner_height integer
);

create table if not exists company(
  id bigserial not null
    constraint companies_pkey
    primary key,
  company_name varchar(50),
  director_name varchar(50),
  phone_number varchar(15),
  requisite_id bigint
    constraint companies_requisite_id_fkey
    references requisite,
  user_id bigint
    constraint companies_user_id_fkey
    references logistic_service_user,
  carrier_id bigint
    constraint companies_carrier_fk
    references carrier_company,
  customer_id bigint
    constraint companies_customer_id_fkey
    references customer_company,
  company_role company_role
);

alter table customer_company
  add constraint customers_company_id_fk
foreign key (company_id) references company;

alter table requisite
  add constraint requisites_company_id_fkey
foreign key (company_id) references company;

create table if not exists expense(
  id bigserial not null
    constraint expenses_pkey
    primary key,
  company_id bigint
    constraint expenses_company_id_fkey
    references company,
  expense_item varchar(50),
  spent integer,
  date timestamp
);

alter table carrier_company
  add constraint carrier_companies_company_id_fk
foreign key (company_id) references company;

create table if not exists wagon(
  id bigint not null
    constraint wagon_pkey
    primary key,
  tractor_id bigint
    constraint wagon_tractor_id_fkey
    references tractor,
  trailer_id bigint
    constraint wagon_trailer_id_fkey
    references trailer,
  driver_id bigint
    constraint wagon_driver_id_fkey
    references driver,
  carrier_company_id bigint
    constraint carrier_company_id_fkey
    references carrier_company
);

create table if not exists basket(
  id bigint not null
    constraint basket_pkey
    primary key,
  user_id bigint
    constraint fk_logistic_service_user_id
    references logistic_service_user
);

alter table basket owner to postgres;

create unique index if not exists basket_pk
  on basket (id);

create table if not exists product
(
  id serial not null
    constraint product_pkey
    primary key,
  product_name varchar(50),
  price numeric not null,
  description varchar(600)
);

alter table product owner to postgres;

create table if not exists basket_product(
  basket_id bigint not null
    constraint fk_basket_id
    references basket,
  product_id bigint not null
    constraint fk_product_id
    references product
);

alter table basket_product owner to postgres;

create table if not exists auth_cookie(
  id serial not null
    constraint auth_cookie_pkey
    primary key,
  cookie_value varchar(100),
  user_id integer
    constraint auth_cookie_user_id_fk
    references logistic_service_user
);

alter table auth_cookie owner to postgres;