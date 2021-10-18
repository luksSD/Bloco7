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
    descricao VARCHAR (1000) not null,
    quartos integer not null,
    banheiros integer not null,
    vaga_garagem integer,
    tipo_propriedade VARCHAR (50) not null,
    status VARCHAR (50) not null,
    area integer,
    preco VARCHAR (200) not null,
    endereco VARCHAR (200) not null,
    bairro VARCHAR (20) not null,
    cep VARCHAR (20) not null,
    cidade_id integer  not null,
    usuario_anunciante_id integer not null,
    foto1 VARCHAR(200) not null,
    foto2 VARCHAR(200),
    foto3 VARCHAR(200),
    foto4 VARCHAR(200),
    foto5 VARCHAR(200)
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
 values ('1' , '123' , 'vinicius@gmail.com' , '(35)99757-8408');

INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id ) 
values ('1' , '123.456.789.11' , 'Vinicius de Almeida Gonçalves' , 'Rua Lourdes de Souza Santos' , '90' , 'Coline Verde' , '37550000' , '1');


INSERT INTO usuario (id , senha, email, celular)
 values ('2' , '123' , 'lucas@gmail.com' , '(35)99873-8536');

INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id )
values ('2' , '123.456.789.12' , 'Lucas Silva e Dias' , 'Avenida Barão do Rio Branco' , '46' , 'Centro' , '37540-000' , '4');


INSERT INTO usuario (id , senha, email, celular)
 values ('3' , '123' , 'matheus@gmail.com' , '(35)99114-3152');

 INSERT INTO pessoa (usuario_id , cpf, nome, logradouro, numero, bairro, cep, cidade_id )
values ('3' , '123.456.789.13' , 'Matheus Felipe de Souza' , 'Rua Crescêncio Ribeiro' , '14' , 'Fernandes' , '37540-000' , '4');


 INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id, foto1, foto2, foto3)
 values ('1' , 'Apartamento em frente a receita federal. Região central. Rápido acesso a bancos e lotéricas. Condomínio com segurança. Sala ampla, cozinha com armários. Possui elevador' 
    , '4' , '2' , '1' , 'Apartamento' , 'Aluguel' , '100', '870' ,'Rua Francisco Nº 30' , 'Centro' , '37550-000' , '1' , '1', '/resources/img/post-single-1.jpg', '/resources/img/post-single-2.jpg', '/resources/img/post-6.jpg');


INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id, foto1, foto2, foto3)
 values ('2' , 'Casa sede em madeira com 140m a/c, 2 amplos dormitórios, sala em 2 ambientes, ampla cozinha e despensa. Na parte externa, piscina 4x8 com deck em pedra mineira, jardim, espaço gourmet completo, churrasqueira, campo de futebol. Linda Vista para desfrutar do que a natureza tem de melhor!!!

' , '2' , '3' , '2' , 'Chácara' , 'Venda' , '1000', '500000', 'Rua Costinha Nº 79' , 'Zona Rural' , '37540-000' , '4' , '2', '/resources/img/post-single-1.jpg', '/resources/img/post-single-2.jpg', '/resources/img/post-6.jpg');

 INSERT INTO anuncio
 (id , descricao, quartos, banheiros, vaga_garagem , tipo_propriedade , status, area, preco , endereco, bairro, cep, cidade_id, usuario_anunciante_id, foto1, foto2, foto3)
 values ('3' , 'Maravilhosa casa, com um excelente construção, isso sem falar da área de laser..., casa em 2 pavimentos com uma escada bem generosa, ampla, clara e muito arejada, no primeiro pavimento com um amplo jardim na frente da casa, varandão e as vagas de garagem, existe um Soto em baixo da casa, piscina, área livre, uma academia, sauna, bar gourmet, sala de tv, quarto e um banheiro , essa é a parte de fora da casa, ainda no primeiro pavimento, salão em L quarto que é uma suíte, copa cozinha, lavabo, dispensa e lavanderia, casa com energia solar. Segundo pavimento- 3 quartos, todos com varanda, uma suíte e um banheiro social. Cômodos todos com armários.' 
    , '4' , '3' , '2' , 'Casa' , 'Aluguel' , '400', '7000' ,'Rua Roberto Nº 145' , 'Jardim Paraiso' , '37550-000' , '4' , '3', '/resources/img/post-single-1.jpg', '/resources/img/post-single-2.jpg', '/resources/img/post-6.jpg');

