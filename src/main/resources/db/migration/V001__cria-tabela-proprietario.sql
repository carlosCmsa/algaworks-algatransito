create table proprietario(
	id bigint not null auto_increment,
	nome varchar(200) not null,
	email varchar(255) not null unique,
	telefone varchar(20) not null,
	constraint pk_proprietario primary key(id)
);