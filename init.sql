SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

DROP DATABASE IF EXISTS integrate_farm_db;
CREATE DATABASE integrate_farm_db;
USE integrate_farm_db;

SET time_zone = "+03:00";


CREATE TABLE `tb_clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `rg` varchar(30) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_clientes` (`id`, `nome`, `rg`, `cpf`, `email`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
(9, 'charlie', '22.222.222', '111.111.111-11', 'charlie@charliegmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv raimundinho', 1, 'casa', 'trançador', 'caxambu', 'MG'),
(12, 'joao da silva', '11.111.111', '111.111.111-13', 'charlie@charliegmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv raimundinho', 1, 'casa', 'trançador', 'caxambu', 'MG'),
(13, ' charlie rodrigues da silva', '11.111.111', '111.111.111-12', 'charlie@charliegmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv raimundinho', 1, 'casa', 'trançador', 'caxambu', 'MG'),
(14, 'ffbfgbfg', '00.000.000', '111.111.111-14', '0000', '(11)1111-1111', '(99)9 9999-9999', '00000-000', '0000', 0, '00', 'vila verde', '0000', 'AC');

CREATE TABLE `tb_fornecedores` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `cnpj` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_fornecedores` (`id`, `nome`, `cnpj`, `email`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
(1, 'sang sung', '77.777.777/7777-77', 'ss@gmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv raimundinho', 44, 'mobile', 'trançadoor', 'caxambu', 'CE'),
(3, 'moto ', '22.222.222/2222-22', 'G@gmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv do beco2', 22, 'mobile', '2', 'caxambu2', 'MG'),
(4, 'motorola ', '00.000.000/0000-00', 'ss@gmail.com', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'tv do beco', 44, 'mobile', 'jardim das naçoes', 'cambuquira', 'MS'),
(5, 'magazine luiza', '99.999.999/9999-99', 'magazine@yahoo.com.br', '(11)1111-1111', '(99)9 9999-9999', '11111-111', 'joaquim dos santos', 78, 'empresa', 'vila verde', 'caxambu', 'CE');


CREATE TABLE `tb_funcionarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `rg` varchar(30) DEFAULT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `senha` varchar(10) DEFAULT NULL,
  `cargo` varchar(100) DEFAULT NULL,
  `nivel_acesso` varchar(50) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_funcionarios` (`id`, `nome`, `rg`, `cpf`, `email`, `senha`, `cargo`, `nivel_acesso`, `telefone`, `celular`, `cep`, `endereco`, `numero`, `complemento`, `bairro`, `cidade`, `estado`) VALUES
(4, 'ricardo azarias', '11.111.111', '111.111.111-11', 'helio@gmail.com.br', '000', 'entregador', 'Usuário', '(11)1111-1111', '(99)9 9999-9999', '37440-000', 'rua das flores mais lindas', 55, 'alameda1', 'da pedra1', 'cerranos1', 'MT'),
(6, 'charlie abraao', '11.111.111', '111.111.111-11', 'helio@gmail.com', '000', 'entregador', 'Administrador', '(11)1111-1111', '(99)9 9999-9999', '37440-000', 'rua das flores mais lindas', 55, 'alameda', 'da pedra', 'cerranos', 'MT');


CREATE TABLE `tb_itensvendas` (
  `id` int(11) NOT NULL,
  `venda_id` int(11) DEFAULT NULL,
  `produto_id` int(11) DEFAULT NULL,
  `qtd` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_itensvendas` (`id`, `venda_id`, `produto_id`, `qtd`, `subtotal`) VALUES
(198, 233, 9, 2, '200.00'),
(199, 235, 9, 1, '100.00'),
(200, 239, 8, 1, '16000.00'),
(201, 241, 7, 1, '1100.00'),
(202, 242, 9, 1, '100.00'),
(203, 243, 9, 1, '100.00'),
(204, 244, 9, 1, '100.00'),
(205, 245, 9, 1, '100.00'),
(206, 247, 9, 1, '100.00'),
(207, 249, 9, 1, '100.00'),
(208, 250, 6, 1, '155.00'),
(209, 251, 5, 1, '1500.00'),
(210, 252, 9, 1, '100.00'),
(211, 253, 9, 1, '100.00'),
(212, 254, 9, 1, '100.00'),
(213, 255, 9, 1, '100.00'),
(214, 256, 9, 2, '200.00'),
(215, 256, 5, 2, '3000.00');


CREATE TABLE `tb_parcelas` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `data_venda` datetime DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `parcela` decimal(10,2) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `num_parcelas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_parcelas` (`id`, `cliente_id`, `data_venda`, `total`, `parcela`, `obs`, `num_parcelas`) VALUES
(46, 12, '2020-11-02 00:00:00', '240.00', '24.00', '', 5),
(47, 14, '2020-11-02 00:00:00', '16000.00', '1000.00', 'maior que quinze mil', 14),
(48, 14, '2020-11-02 00:00:00', '1100.00', '100.00', '', 9),
(49, 9, '2020-11-02 00:00:00', '100.00', '10.00', '', 10);

CREATE TABLE `tb_produtos` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `preco` decimal(10,2) DEFAULT NULL,
  `qtd_estoque` int(11) DEFAULT NULL,
  `for_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_produtos` (`id`, `descricao`, `preco`, `qtd_estoque`, `for_id`) VALUES
(5, 'tablet', '1500.00', 17, 3),
(6, 'relogio', '155.00', 0, 1),
(7, 'celular', '1100.00', 27, 1),
(8, 'computador sang sung', '16000.00', 11, 5),
(9, 'teclado de pc', '100.00', 72, 1);


CREATE TABLE `tb_vendas` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `data_venda` datetime DEFAULT NULL,
  `total_venda` decimal(10,2) DEFAULT NULL,
  `observacoes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tb_vendas` (`id`, `cliente_id`, `data_venda`, `total_venda`, `observacoes`) VALUES
(233, 12, '2020-11-02 00:00:00', NULL, ''),
(234, 9, '2020-11-02 00:00:00', '48.00', ''),
(235, 9, '2020-11-02 00:00:00', '100.00', ''),
(236, 9, '2020-11-02 00:00:00', '24.00', ''),
(237, 9, '2020-11-02 00:00:00', '24.00', ''),
(238, 12, '2020-11-02 00:00:00', '24.00', ''),
(239, 14, '2020-11-02 00:00:00', NULL, ''),
(240, 14, '2020-11-02 00:00:00', '2000.00', 'maior que quinze mil'),
(241, 14, '2020-11-02 00:00:00', NULL, ''),
(242, 9, '2020-11-02 00:00:00', '100.00', 'tentando'),
(243, 9, '2020-11-02 00:00:00', '100.00', 'tentando'),
(244, 9, '2020-11-02 00:00:00', '100.00', 'tentando'),
(245, 9, '2020-11-02 00:00:00', '100.00', ''),
(246, 14, '2020-11-02 00:00:00', '100.00', ''),
(247, 9, '2020-11-02 00:00:00', NULL, ''),
(248, 9, '2020-11-02 00:00:00', '100.00', ''),
(249, 12, '2020-11-02 00:00:00', NULL, ''),
(250, 9, '2023-11-09 22:31:20', '155.00', ''),
(251, 9, '2023-11-13 21:56:17', '1500.00', ''),
(252, 9, '2023-11-13 22:00:33', '100.00', 'teste'),
(253, 9, '2023-11-13 22:15:28', '100.00', 'sem troco'),
(254, 9, '2023-11-13 22:18:06', '100.00', 'teste'),
(255, 9, '2023-11-13 22:28:23', '100.00', 'teste'),
(256, 9, '2023-11-13 22:46:40', '3200.00', 'teste final');


ALTER TABLE `tb_clientes`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `tb_fornecedores`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `tb_funcionarios`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `tb_itensvendas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `venda_id` (`venda_id`),
  ADD KEY `produto_id` (`produto_id`);

ALTER TABLE `tb_parcelas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`);

ALTER TABLE `tb_produtos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `for_id` (`for_id`);

ALTER TABLE `tb_vendas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`);

ALTER TABLE `tb_clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

ALTER TABLE `tb_fornecedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `tb_funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

ALTER TABLE `tb_itensvendas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=216;

ALTER TABLE `tb_parcelas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

ALTER TABLE `tb_produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

ALTER TABLE `tb_vendas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=257;

ALTER TABLE `tb_itensvendas`
  ADD CONSTRAINT `tb_itensvendas_ibfk_1` FOREIGN KEY (`venda_id`) REFERENCES `tb_vendas` (`id`),
  ADD CONSTRAINT `tb_itensvendas_ibfk_2` FOREIGN KEY (`produto_id`) REFERENCES `tb_produtos` (`id`);

ALTER TABLE `tb_parcelas`
  ADD CONSTRAINT `tb_parcelas_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`);

ALTER TABLE `tb_produtos`
  ADD CONSTRAINT `tb_produtos_ibfk_1` FOREIGN KEY (`for_id`) REFERENCES `tb_fornecedores` (`id`);

ALTER TABLE `tb_vendas`
  ADD CONSTRAINT `tb_vendas_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `tb_clientes` (`id`);

CREATE TABLE cargo (
    id_cargo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    id_cargo INT NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    email VARCHAR(300) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(300) NOT NULL,
    contatos VARCHAR(100) NOT NULL,
    cep CHAR(8) NOT NULL,
    numero INT NOT NULL,
    ativo INT(1) NOT NULL,
    CONSTRAINT FK_USUARIO_CARGO FOREIGN KEY (id_cargo) REFERENCES cargo (id_cargo)
);

CREATE TABLE especialidade (
	id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
	valor DECIMAL(10,2) NOT NULL,
	nome VARCHAR(60) NOT NULL
);

CREATE TABLE prestador_servico (
	id_prestador_servico INT PRIMARY KEY AUTO_INCREMENT,
	crm CHAR(13) UNIQUE,
	id_especialidade INT NOT NULL,
	id_usuario INT NOT NULL UNIQUE,
	CONSTRAINT FK_PRESTADOR_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
	CONSTRAINT FK_PRESTADOR_ESPECIALIDADE FOREIGN KEY (id_especialidade) REFERENCES especialidade (id_especialidade)
);

CREATE TABLE agendamento (
    id_agendamento INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_prestador_servico INT NOT NULL,
    tratamento VARCHAR(40),
    exame VARCHAR(40),
    data_horario DATETIME NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    CONSTRAINT FK_AGENDAMENTO_PRESTADOR FOREIGN KEY (id_prestador_servico) REFERENCES prestador_servico (id_prestador_servico),
    CONSTRAINT FK_AGENDAMENTO_CLIENTE FOREIGN KEY (id_cliente) REFERENCES tb_clientes (id)
);

ALTER TABLE tb_clientes
  ADD COLUMN id_usuario INT;

ALTER TABLE tb_clientes
  ADD CONSTRAINT FK_CLIENTE_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario);

INSERT INTO cargo (id_cargo, nome)
     VALUES (1, 'ROLE_ADM');

INSERT INTO cargo (id_cargo, nome)
     VALUES (2, 'ROLE_PRESTADOR');

INSERT INTO cargo (id_cargo, nome)
     VALUES (3, 'ROLE_CLIENTE');

INSERT INTO especialidade (id_especialidade, nome, valor)
	 VALUES (1, 'NUTRICIONISTA', 500.00);

ALTER TABLE agendamento
  ADD COLUMN status VARCHAR(40);

ALTER TABLE tb_vendas
  ADD COLUMN qnt_parcelas INT;

ALTER TABLE tb_produtos
  ADD COLUMN link_imagem VARCHAR(255);

DELETE FROM tb_parcelas;
DELETE FROM tb_itensvendas;
DELETE FROM tb_vendas;
DELETE FROM tb_produtos;
DELETE FROM tb_funcionarios;
DELETE FROM tb_fornecedores;
DELETE FROM agendamento;
DELETE FROM tb_clientes;
DELETE FROM prestador_servico;
DELETE FROM usuario;
DELETE FROM especialidade;
DELETE FROM cargo;

-- Inserindo dados na tabela cargo
INSERT INTO cargo (id_cargo, nome)
     VALUES (1, 'ROLE_ADM'),
			(2, 'ROLE_PRESTADOR'),
			(3, 'ROLE_CLIENTE');

-- Inserindo dados na tabela especialidade
INSERT INTO especialidade (id_especialidade, valor, nome) VALUES
(1, 150.00, 'Cardiologia'),
(2, 100.00, 'Dermatologia'),
(3, 200.00, 'Nutricionista');

-- Inserindo dados na tabela usuario
INSERT INTO usuario (id_usuario, id_cargo, cpf, email, nome, senha, contatos, cep, numero, ativo) VALUES
(1, 2, '12345678901', 'medico1@exemplo.com', 'Dr. João Silva', '$2a$10$4gX0PV4.66EmzZgOpAauseXfR4UCWZFs/qdSOgFYkRls2gpNaj8PK', '31999999999', '37500000', 10, 1),
(2, 3, '98765432109', 'cleyton@exemplo.com', 'Cleyton Costa', '$2a$10$4gX0PV4.66EmzZgOpAauseXfR4UCWZFs/qdSOgFYkRls2gpNaj8PK', '31988888888', '37500001', 20, 1),
(3, 3, '14426283381', 'geraldo@exemplo.com', 'Geraldo Júnior', '$2a$10$4gX0PV4.66EmzZgOpAauseXfR4UCWZFs/qdSOgFYkRls2gpNaj8PK', '31988888888', '37500001', 20, 1),
(4, 1, '11122233344', 'admin@exemplo.com', 'Ana Paula', '$2a$10$4gX0PV4.66EmzZgOpAauseXfR4UCWZFs/qdSOgFYkRls2gpNaj8PK', '31977777777', '37500002', 30, 1);

-- Inserindo dados na tabela prestador_servico
INSERT INTO prestador_servico (id_prestador_servico, crm, id_especialidade, id_usuario) VALUES
(1, 'CRM12345', 1, 1);

-- Inserindo dados na tabela tb_clientes
INSERT INTO tb_clientes (id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, id_usuario) VALUES
(1, 'Cliente A', '1234567', '11122233344', 'clienteA@exemplo.com', '3133333333', '31999999999', '37500003', 'Rua Exemplo', 40, 'Apto 1', 'Centro', 'São Paulo', 'SP', 2),
(2, 'Cliente B', '7654321', '55566677788', 'clienteB@exemplo.com', '3144444444', '31988888888', '37500004', 'Avenida Teste', 50, null, 'Liberdade', 'Rio de Janeiro', 'RJ', 3);

-- Inserindo dados na tabela agendamento
INSERT INTO agendamento (id_agendamento, id_cliente, id_prestador_servico, tratamento, exame, data_horario, valor, status) VALUES
(1, 1, 1, 'Consulta Cardiologia', null, '2024-03-15 10:00:00', 200.00, 'Agendado'),
(2, 2, 1, null, 'Exame Dermatológico', '2024-03-20 14:00:00', 150.00, 'Concluído');

-- Inserindo dados na tabela tb_fornecedores
INSERT INTO tb_fornecedores (id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES
(1, 'Fornecedor X', '12345678901234', 'fornecedorX@exemplo.com', '3155555555', '31977777777', '37500005', 'Rua Alfa', 60, null, 'Boa Vista', 'Belo Horizonte', 'MG'),
(2, 'Fornecedor Y', '98765432109876', 'fornecedorY@exemplo.com', '3166666666', '31966666666', '37500006', 'Avenida Beta', 70, 'Sala 1', 'Centro', 'Contagem', 'MG');

-- Inserindo dados na tabela tb_funcionarios
INSERT INTO tb_funcionarios (id, nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES
(1, 'Funcionário 1', '111222333', '12312312312', 'funcionario1@exemplo.com', '000', 'Atendente', 'Administrador', '3177777777', '31955555555', '37500007', 'Rua Gama', 80, null, 'São Francisco', 'Betim', 'MG'),
(2, 'Funcionário 2', '444555666', '45645645645', 'funcionario2@exemplo.com', '000', 'Auxiliar', 'Administrador', '3188888888', '31944444444', '37500008', 'Avenida Delta', 90, 'Apto 2', 'Horto', 'Sete Lagoas', 'MG');

-- Inserindo dados na tabela tb_produtos
INSERT INTO tb_produtos (id, descricao, preco, qtd_estoque, for_id, link_imagem) VALUES
(1, 'Produto A', 10.00, 100, 1, 'link_imagem_produto_a.jpg'),
(2, 'Produto B', 20.00, 50, 2, 'link_imagem_produto_b.jpg');

-- Inserindo dados na tabela tb_vendas
INSERT INTO tb_vendas (id, cliente_id, data_venda, total_venda, observacoes, qnt_parcelas) VALUES
(1, 1, '2024-03-10 09:00:00', 50.00, 'Venda online', 1),
(2, 2, '2024-03-12 15:00:00', 100.00, 'Venda física', 2);

-- Inserindo dados na tabela tb_itensvendas
INSERT INTO tb_itensvendas (venda_id, produto_id, qtd, subtotal) VALUES
(1, 1, 5, 50.00),
(2, 2, 2, 40.00),
(2,1,1,10.00);

-- Inserindo dados na tabela tb_parcelas
INSERT INTO tb_parcelas (cliente_id, data_venda, total, parcela, obs, num_parcelas) VALUES
(1, '2024-03-10 09:00:00', 50.00, 50.00, 'Parcela única', 1),
(2, '2024-03-12 15:00:00', 100.00, 50.00, 'Parcela 1/2', 2),
(2, '2024-03-12 15:00:00', 100.00, 50.00, 'Parcela 2/2', 2);
