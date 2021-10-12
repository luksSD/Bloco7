drop database if exists bloco7;

CREATE DATABASE bloco7 WITH OWNER = postgres
ENCODING = 'UTF8' CONNECTION LIMIT = -1;


--Criando tabela cidade
CREATE TABLE cidade
(
    id BIGSERIAL primary key,
    estado VARCHAR (50) not null,
    nome VARCHAR (50)  not null
    check (nome in ('Pouso Alegre' , 'Santa Rita do Sapucai' , 'Poços de Caldas' , 'Itajuba'))
);

--Colocando UK entre estado e nome
alter table cidade add constraint uk1_cidade unique(estado, nome);



--Criando tabela usuario
CREATE TABLE usuario
(
    id BIGSERIAL primary key,
    senha VARCHAR (50) not null,
    email VARCHAR (50) unique not null,
    celular VARCHAR (20) unique not null
);

--Criando tabela pessoa
CREATE TABLE pessoa
(
    usuario_id integer primary key,
    cpf VARCHAR (20) unique not null,
    nome VARCHAR (200) not null,
    logradouro VARCHAR (200),
    numero VARCHAR (10),
    bairro VARCHAR (100),
    cep VARCHAR (15),
    cidade_id integer

);

--Tornando cidade_id/usuario_id uma FK de pessoa
alter table pessoa add constraint fk1_pessoa foreign key (cidade_id) references cidade(id) on update cascade on delete cascade;
alter table pessoa add constraint fk2_pessoa foreign key (usuario_id) references usuario(id) on update cascade on delete cascade;






--Criando tabela anuncio
CREATE TABLE anuncio
(
    id BIGSERIAL primary key,
    descricao VARCHAR (50) not null,
    quartos integer not null,
    banheiros integer not null,
    vaga_garagem integer,
    tipo_propriedade VARCHAR (50) not null,
    status VARCHAR (50) not null,
    area integer,
    preco real not null,
    endereco VARCHAR (200) not null,
    bairro VARCHAR (20) not null,
    cep VARCHAR (20) not null,
    cidade_id integer  not null,
    usuario_anunciante_id integer not null
);

--Tornando cidade_id/usuario_anunciante_id uma FK de anuncio
alter table anuncio add constraint fk1_anuncio foreign key (cidade_id) references cidade(id) on update cascade on delete cascade;
alter table anuncio add constraint fk2_anuncio foreign key (usuario_anunciante_id) references usuario(id) on update cascade on delete cascade;


--Criando tabela anuncio_foto
CREATE TABLE anuncio_foto
(
    id BIGSERIAL primary key,
    foto_path VARCHAR not null,
    anuncio_id integer not null
    
);
--Colocando UK entre anuncio_id e foto_path
alter table anuncio_foto add constraint uk1_anuncio_foto unique(foto_path, anuncio_id);

--Tornando anuncio_id uma FK de anuncio_foto
alter table anuncio_foto add constraint fk1_anuncio_foto foreign key (anuncio_id) references anuncio(id) on update cascade on delete cascade;


--Criando tabela chat
CREATE TABLE chat
(
    id BIGSERIAL primary key,
    datahora TIMESTAMP not null,
    anuncio_id integer not null,
    usuario_interessado_id integer not null
    
);

--Colocando UK entre anuncio_id e usuario_interessado_id
alter table chat add constraint uk1_chat unique(anuncio_id, usuario_interessado_id);

--Tornando usuario_interessado_id/anuncio_id uma FK de chat
alter table chat add constraint fk1_chat foreign key (usuario_interessado_id) references usuario(id) on update cascade on delete cascade;
alter table chat add constraint fk2_chat foreign key (anuncio_id) references anuncio(id) on update cascade on delete cascade;



--Criando tabela mensagem
CREATE TABLE mensagem
(
    id BIGSERIAL primary key,
    datahora TIMESTAMP not null,
    texto text not null,
    usuario_remetente_id integer not null,
    chat_id integer not null
    
);

--Tornando usuario_remetente_id/chat_id uma FK de chat
alter table mensagem add constraint fk1_mensagem foreign key (usuario_remetente_id) references usuario(id) on update cascade on delete cascade;
alter table mensagem add constraint fk2_mensagem foreign key (chat_id) references chat(id) on update cascade on delete cascade;


--Adicionando cidades que o site vai atender inicialmente
----------------------
INSERT INTO cidade (id , estado, nome) 
values ('1' , 'Minas Gerais' , 'Pouso Alegre');

INSERT INTO cidade (id , estado, nome) 
values ('2' , 'Minas Gerais' , 'Itajuba');

INSERT INTO cidade (id , estado, nome) 
values ('3' , 'Minas Gerais' , 'Poços de Caldas');

INSERT INTO cidade (id , estado, nome) 
values ('4' , 'Minas Gerais' , 'Santa Rita do Sapucai');



--Adicionando valores para teste

INSERT INTO usuario (id , senha, email, celular)
 values ('1' , '123' , 'teste@gmail.com' , '999999999');

INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id ) 
values ('1' , '123.456.789.11' , 'Vinicius Teste' , 'Rua Francisco' , '27' , 'Centro' , '37550000' , '1');


INSERT INTO usuario (id , senha, email, celular)
 values ('2' , '123' , 'teste02@hotmail.com' , '333333333333');

INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id )
values ('2' , '111.111.111.11' , 'Lucas Teste' , 'Rua Joao' , '46' , 'Fatima' , '37550000' , '2');


INSERT INTO usuario (id , senha, email, celular)
 values ('3' , '123' , 'teste03@hotmail.com' , '444444444');

 INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id )
values ('3' , '222.222.222.22' , 'Matheus Teste' , 'Rua Roberto' , '46' , 'Bento' , '37550000' , '3');


 INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id)
 values ('1' , 'Linda casa em bairro familiar' , '4' , '2' , '2' , 'Casa' , 'Alugar' , '1000', '2000' ,'Rua Francisco Nº 30' , 'Centro' , '37550-000' , '1' , '1');


INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id)
 values ('2' , 'Ap. para solteiro' , '1' , '1' , '0' , 'Apartamento' , 'Alugar' , '100', '500', 'Rua João Nº 60' , 'Fatima' , '37550-000' , '2' , '2');

 INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id)
 values ('3' , 'Alugo casa que aceita animais' , '2' , '1' , '1' , 'Casa' , 'Alugar' , '500', '1500' ,'Rua Roberto Nº 145' , 'Bento' , '37550-000' , '3' , '3');


