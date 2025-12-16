create table veiculo(
	id bigint not null auto_increment,
	marca varchar(20) not null,
	modelo varchar(20) not null,
	placa varchar(7) not null unique,
	status varchar(20) not null,
	data_cadastro datetime not null,
	data_apreensao datetime,
	proprietario_id bigint not null,
	constraint pk_veiculo primary key(id),
	constraint fk_proprietario foreign key(proprietario_id) references proprietario(id)
);