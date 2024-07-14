create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique ,
    mensaje varchar(200) not null unique ,
    autor varchar(100) not null,
    curso varchar(100) not null,
    fecha datetime not null,
    status boolean not null,

    primary key(id)
)