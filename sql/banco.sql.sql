-- --------------------------------------------------------
-- ProAgende - Sistema de Agendamento de Serviços
-- Script de criação do banco de dados
-- --------------------------------------------------------

CREATE DATABASE IF NOT EXISTS proagende;
USE proagende;

-- --------------------------------------------------------
-- Tabelas
-- --------------------------------------------------------

CREATE TABLE profissional (
  id_Profissional INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id_Profissional)
);

CREATE TABLE cliente (
  id_Cliente INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id_Cliente)
);

CREATE TABLE servico (
  id_Servico INT NOT NULL AUTO_INCREMENT,
  id_Profissional INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  descricao TEXT,
  preco DECIMAL(10,2) NOT NULL,
  duracao_minutos INT DEFAULT NULL,
  PRIMARY KEY (id_Servico),
  FOREIGN KEY (id_Profissional) REFERENCES profissional(id_Profissional)
);

CREATE TABLE agendamento (
  id_Agendamento INT NOT NULL AUTO_INCREMENT,
  id_Servico INT NOT NULL,
  Id_Cliente INT NOT NULL,
  data_hora DATETIME NOT NULL,
  status ENUM('pendente','confirmado','cancelado') DEFAULT 'pendente',
  PRIMARY KEY (id_Agendamento),
  FOREIGN KEY (id_Servico) REFERENCES servico(id_Servico),
  FOREIGN KEY (Id_Cliente) REFERENCES cliente(id_Cliente)
);

-- --------------------------------------------------------
-- Dados de exemplo
-- --------------------------------------------------------

INSERT INTO profissional (nome, email, telefone) VALUES
('João Silva', 'joao@proagende.com', '11912345678'),
('Maria Oliveira', 'maria@proagende.com', '11988880000'),
('Carlos Souza', 'carlos@proagende.com', '11977770000'),
('Ana Lima', 'ana@proagende.com', '11966660000');

INSERT INTO cliente (nome, email, telefone) VALUES
('Lucas Costa', 'lucas@email.com', '11911112222'),
('Beatriz Ramos', 'beatriz@email.com', '11922223333'),
('Marcos Pereira', 'marcos@email.com', '11933334444'),
('Juliana Dias', 'juliana@email.com', '11944445555');

INSERT INTO servico (id_Profissional, nome, descricao, preco, duracao_minutos) VALUES
(1, 'Corte de Cabelo', 'Corte masculino padrão', 40.00, 30),
(2, 'Manicure', 'Serviço de manicure com esmaltação', 50.00, 45),
(3, 'Massagem Relaxante', 'Massagem corporal completa', 120.00, 60),
(4, 'Design de Sobrancelha', 'Modelagem com pinça', 40.00, 25);

INSERT INTO agendamento (id_Servico, Id_Cliente, data_hora, status) VALUES
(1, 1, '2025-07-01 10:00:00', 'confirmado'),
(2, 2, '2025-07-01 11:00:00', 'pendente'),
(3, 3, '2025-07-01 12:00:00', 'confirmado'),
(4, 4, '2025-07-01 13:00:00', 'pendente');