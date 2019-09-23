create database virtualdoorman;
use virtualdoorman;

create table condominio (
	cnpj varchar(20) not null,
	nome varchar(50) not null, 
	apartamentosNaCobertura int not null,
	andares int not null, 
	apartamentosPorAndar int not null, 
	numerosDeBlocos int not null,
	primary key(cnpj),
	constraint uk_nome unique(nome)
);

create table condominioEndereco (
	codCondEnd int auto_increment,
	endereco varchar(255) not null,
	cep char(10) not null,
	bairro varchar(50) not null,
	uf char(2) not null,
	numero varchar(5) not null,
	cnpj varchar(20) not null,
	primary key(codCondEnd),
	constraint uk_cnpj unique(cnpj),
	constraint fk_cnpj foreign key(cnpj) references condominio(cnpj)
);

create table apartamentos (
	apartamento int not null,
	andar int not null,
	final int not null,
	bloco char(10) not null,
	cnpj varchar(20) not null,
	primary key(apartamento),
	constraint uk_cnpjApartamentos unique(cnpj),
	constraint fk_cnpjApartamentos foreign key(cnpj) references condominio(cnpj)
);

create table pessoas (
	cpf char(15) not null,
	nome varchar(50) not null,
	rg char(15),
	telefoneDdd char(4),
	telefone char(11),
	email varchar(50),
	primary key(cpf),
	constraint uk_rgPessoas unique(rg)
);

create table apartamentoPessoa (
	apartamento int not null,
	cpf char(15) not null,
	primary key(apartamento, cpf),
	constraint fk_apartamentoPessoa foreign key(apartamento) references apartamentos(apartamento),
	constraint fk_cpfApartamentoPessoa foreign key(cpf) references pessoas(cpf)
);

create table areasComuns (
	codArea int auto_increment,
	nome varchar(50) not null,
	cnpj varchar(20) not null,
	primary key(codArea),
	constraint uk_nomeAreasComuns unique(nome),
	constraint fk_cnpjAreasComuns foreign key(cnpj) references condominio(cnpj)
);

create table ModeloAluguel (
    cnpj varchar(20),
    descricao varchar(50) not null,
    preco float not null,
    primary key(cnpj),
    constraint fk_ModeloAluguel_cnpj foreign key(cnpj) references condominio(cnpj)
);

create table Alugado (
    dataAluguel date,
    cpf char(15) not null,
    cnpj varchar(20) not null,
    primary key(dataAluguel),
    constraint fk_Alugado_cpf foreign key(cpf) references pessoas (cpf),
    constraint fk_Alugado_cnpj foreign key(cnpj) references condominio (cnpj)
);