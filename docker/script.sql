CREATE DATABASE IF NOT EXISTS car_cred_fiap;

USE car_cred_fiap;

CREATE TABLE usuarios (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255),
	role VARCHAR(5) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT chk_role CHECK(role='USER' OR role='ADMIN')
);

INSERT INTO usuarios (username, password, role)
    VALUES ('root', '$2a$10$1B6VMXGSzPjVFNXFRKlZ3.BLjhlpndmhyvtVWnDdQeZ9mUnxvYp2m', 'ADMIN');

CREATE TABLE alunos (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255),
	rm VARCHAR(7),
	cod VARCHAR(6),
	PRIMARY KEY (id)
);

CREATE TABLE cartoes(
	id BIGINT NOT NULL AUTO_INCREMENT,
	id_aluno BIGINT,
	numero VARCHAR(16) NOT NULL,
	senha VARCHAR(6) NOT NULL,
	cod_seg VARCHAR(3) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE registro_compras(
	id BIGINT NOT NULL AUTO_INCREMENT,
	id_cartao BIGINT NOT NULL,
	estabelecimento VARCHAR(255) NOT NULL,
	valor DOUBLE NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE cartoes ADD FOREIGN KEY (id_aluno) REFERENCES alunos(id);
ALTER TABLE registro_compras ADD FOREIGN KEY (id_cartao) REFERENCES cartoes(id);
