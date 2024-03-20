truncate table roles cascade;

insert into roles (role_id, name)
values (1, 'ADMIN'),
       (2, 'ANONYMOUS'),
       (3, 'CUSTOMER'),
       (4, 'OPERATOR');