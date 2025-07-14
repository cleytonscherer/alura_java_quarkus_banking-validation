create table if not exists endereco(
    id serial primary key,
    rua text not null,
    logradouro text not null,
    complemento text not null,
    numero int not null
);

create table if not exists agencia(
    id serial primary key,
    nome text not null,
    razao_social text not null,
    cnpj text not null,
    situacao_cadastral text not null,
    endereco_id int references endereco
);

insert into endereco (rua, logradouro, complemento, numero)
     values ('Rua Frederico Maurer','Hauer','Schwan Cosmetics', '149');

insert into agencia (nome, razao_social, cnpj, situacao_cadastral, endereco_id)
    values ('Agencia BSB', 'Asa Norte AGENCIA BSB', '15130254000100', 'ATIVO', 1);