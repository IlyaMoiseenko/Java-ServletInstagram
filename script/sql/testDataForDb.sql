insert into country values (1, 'Беларусь');

insert into city values (1, 'Гомель', 1);
insert into city values (2, 'Брест', 1);
insert into city values (3, 'Могилев', 1);
insert into city values (4, 'Минск', 1);

insert into human values (1, 'Ilya', 'Moiseenko', 'IlyaMoiseenko', null, 'IlyaMoiseenko@gmail.com', 'testPassword', 1, 1);
insert into human values (2, 'Marina', 'Maslowa', 'MarinaMaslowa', null, 'MarinaMaslowa@gmail.com', 'testPassword', 1, 2);
insert into human values (3, 'Kolya', 'Pinchuk', 'KolyaPinchuk', null, 'KolyaPinchuk@gmail.com', 'testPassword', 1, 2);
insert into human values (4, 'Karina', 'Mashanova', 'KarinaMashanova', null, 'KarinaMashanova@gmail.com', 'testPassword', 1, 3);

insert into post values (1, 1, null, 'Test description by post #1');
insert into post values (2, 1, null, 'Test description by post #2');
insert into post values (3, 2, null, 'Test description by post #3');
insert into post values (4, 3, null, 'Test description by post #4');

insert into post_like values (1, 1);
insert into post_like values (1, 2);
insert into post_like values (3, 3);
insert into post_like values (4, 4);

insert into comment values (1, 1);
insert into comment values (2, 2);
insert into comment values (2, 1);
insert into comment values (3, 3);