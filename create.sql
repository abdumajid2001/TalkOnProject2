create table account (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, talk_coins int4, user_id varchar(255), primary key (id));
create table chats (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, mentee_id varchar(255), mentor_id varchar(255), primary key (id));
create table chats_messages (chats_id varchar(255) not null, messages_id varchar(255) not null);
create table favourite_lists (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, mentee_list jsonb, mentor_list jsonb, mentee_id varchar(255), primary key (id));
create table mentees (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, conversation_count int4, level varchar(255), user_id varchar(255), primary key (id));
create table mentors (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, about_media_link varchar(255) not null, about_text varchar(255) not null, experience int4 not null, rating_count int4, rating_value int4, user_id varchar(255) not null, primary key (id));
create table messages (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, body text, file_path varchar(255), is_read boolean not null, parent int4, chat_id varchar(255), from_id varchar(255), primary key (id));
create table reviews (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, body text, mentee_id varchar(255), mentor_id varchar(255), primary key (id));
create table schedules (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, cost int4, duration int4 not null, schedule_status varchar(255), start_date_time timestamp not null, mentee_id varchar(255), mentor_id varchar(255), primary key (id));
create table transactions (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, amount float8, pay_type varchar(255), account_id varchar(255), primary key (id));
create table users (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, data_of_birth date, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), phone_number varchar(255) not null, role varchar(255), time_zone int4 not null, username varchar(255), primary key (id));
create table video_calls (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, duration int4, end_date_time timestamp, start_date_time timestamp, video_call_status varchar(255), video_link varchar(255) not null, mentee_id varchar(255), mentor_id varchar(255), primary key (id));
create table voice_calls (id varchar(255) not null, created_at timestamp default now(), is_deleted NUMERIC default 0, status int2 not null, updated_at timestamp, duration int4, mentee1_id varchar(255), mentee2_id varchar(255), primary key (id));
alter table if exists chats add constraint UKeg7qxp2436297cxg1ivmn7exh unique (mentor_id, mentee_id);
alter table if exists chats_messages add constraint UK_dvq7vtmykmop3jsntw68spp3 unique (messages_id);
alter table if exists mentors add constraint UK_3y0yv2cy2egy1bl908vqnnhrv unique (user_id);
alter table if exists mentors add constraint UK_9on6ac4vhdbs5puko0aqdkbvd unique (about_media_link);
alter table if exists users add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table if exists account add constraint FKra7xoi9wtlcq07tmoxxe5jrh4 foreign key (user_id) references users;
alter table if exists chats add constraint FKc3jy3qamcfmbdvq4nx0j5euyg foreign key (mentee_id) references mentees;
alter table if exists chats add constraint FKr3ayygo9ifwbhx4xkptlcvou7 foreign key (mentor_id) references mentors;
alter table if exists chats_messages add constraint FKofq36r2soyfr1exsj0kih7n55 foreign key (messages_id) references messages;
alter table if exists chats_messages add constraint FKss8vyj7gos9tvhtpw7soteh55 foreign key (chats_id) references chats;
alter table if exists favourite_lists add constraint FKgmsdarwgll1oumg0krka51h67 foreign key (mentee_id) references mentees;
alter table if exists mentees add constraint FKp71v7q7aw3n9bjbuh82p2bexn foreign key (user_id) references users;
alter table if exists mentors add constraint FKmciiqqph74b3gqsw9615l9cwp foreign key (user_id) references users;
alter table if exists messages add constraint FK64w44ngcpqp99ptcb9werdfmb foreign key (chat_id) references chats;
alter table if exists messages add constraint FKjfnwjudm22scdltl4sgq6i86 foreign key (from_id) references users;
alter table if exists reviews add constraint FK5nkq8kyx44tvmylvdaa1o0r7b foreign key (mentee_id) references mentees;
alter table if exists reviews add constraint FKf2xx7u7d374ogfmjbv91md6fv foreign key (mentor_id) references mentors;
alter table if exists schedules add constraint FKfl77ei87bj9sx1po9tow9k851 foreign key (mentee_id) references mentees;
alter table if exists schedules add constraint FKkipvrlnco2b5aoue7oyi3vt3u foreign key (mentor_id) references mentors;
alter table if exists transactions add constraint FKfyn6ksis1qglevn2liiqirx75 foreign key (account_id) references account;
alter table if exists video_calls add constraint FKtfyucd91aeluy7rqp8gtaq61d foreign key (mentee_id) references mentees;
alter table if exists video_calls add constraint FKn56rcpfda736w16n52l9wy7xj foreign key (mentor_id) references mentors;
alter table if exists voice_calls add constraint FKdu3mau46xkujdgqcsgd7bdhs8 foreign key (mentee1_id) references mentees;
alter table if exists voice_calls add constraint FK74a92uyu2vu6jgunppfs1pgmf foreign key (mentee2_id) references mentees;