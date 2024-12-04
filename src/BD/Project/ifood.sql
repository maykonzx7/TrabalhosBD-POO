/*CREATE DATABASE IF NOT EXISTS projetoifood;
USE projetoifood;

CREATE TABLE IF NOT EXISTS `categorias` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL UNIQUE,
    `descricao` TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `produtos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `descricao` TEXT,
    `preco` DECIMAL(10, 2) NOT NULL,
    `categoria_id` INT NOT NULL,
    FOREIGN KEY (`categoria_id`) REFERENCES `categorias`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `clientes` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `telefone` VARCHAR(15),
    `endereco` TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `enderecos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `cliente_id` INT NOT NULL,
    `logradouro` VARCHAR(255),
    `numero` VARCHAR(10),
    `bairro` VARCHAR(100),
    `cidade` VARCHAR(100),
    `estado` VARCHAR(100),
    `cep` VARCHAR(10),
    FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pedidos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `cliente_id` INT NOT NULL,
    `data_pedido` DATETIME NOT NULL,
    `status` VARCHAR(50),
    `forma_pagamento` VARCHAR(50),
    FOREIGN KEY (`cliente_id`) REFERENCES `clientes`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `itens_pedido` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `pedido_id` INT NOT NULL,
    `produto_id` INT NOT NULL,
    `quantidade` INT NOT NULL,
    `preco_unitario` DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`pedido_id`) REFERENCES `pedidos`(`id`),
    FOREIGN KEY (`produto_id`) REFERENCES `produtos`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `pagamentos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `pedido_id` INT NOT NULL,
    `valor_pago` DECIMAL(10, 2) NOT NULL,
    `data_pagamento` DATETIME NOT NULL,
    `metodo_pagamento` VARCHAR(50) NOT NULL, 
    FOREIGN KEY (`pedido_id`) REFERENCES `pedidos`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `categorias` (`nome`, `descricao`) VALUES
('Fast Food', 'Lanches rápidos e saborosos'),
('Comida Japonesa', 'Sushi, temaki e mais'),
('Pizza', 'Pizzas de diversos sabores');

INSERT INTO `produtos` (`nome`, `descricao`, `preco`, `categoria_id`) VALUES
('Hamburguer', 'Hamburguer saboroso com carne bovina', 19.90, 1),
('Sushi', 'Sushi de atum e salmão', 29.90, 2),
('Pizza de Calabresa', 'Pizza com calabresa, cebola e azeitonas', 35.90, 3);

INSERT INTO `clientes` (`nome`, `email`, `telefone`, `endereco`) VALUES
('João Silva', 'joao@gmail.com', '123456789', 'Rua das Flores, 123'),
('Maria Oliveira', 'maria@gmail.com', '987654321', 'Rua dos Lírios, 456');

INSERT INTO `enderecos` (`cliente_id`, `logradouro`, `numero`, `bairro`, `cidade`, `estado`, `cep`) VALUES
(1, 'Rua das Flores', '123', 'Centro', 'São Paulo', 'SP', '12345-678'),
(2, 'Rua dos Lírios', '456', 'Vila Mariana', 'São Paulo', 'SP', '87654-321');

-- Janeiro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-01-01 12:00:00', 'Concluído', 'Cartão'),
(2, '2024-01-02 13:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-01-03 14:00:00', 'Concluído', 'Pix'),
(2, '2024-01-04 15:00:00', 'Concluído', 'Cartão'),
(1, '2024-01-05 16:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-01-06 17:00:00', 'Concluído', 'Pix'),
(1, '2024-01-07 18:00:00', 'Concluído', 'Cartão'),
(2, '2024-01-08 19:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-01-09 20:00:00', 'Concluído', 'Pix'),
(2, '2024-01-10 21:00:00', 'Concluído', 'Cartão');

-- Fevereiro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-02-01 12:00:00', 'Concluído', 'Pix'),
(2, '2024-02-02 13:00:00', 'Em andamento', 'Cartão'),
(1, '2024-02-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-02-04 15:00:00', 'Concluído', 'Pix'),
(1, '2024-02-05 16:00:00', 'Concluído', 'Cartão'),
(2, '2024-02-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-02-07 18:00:00', 'Concluído', 'Pix'),
(2, '2024-02-08 19:00:00', 'Concluído', 'Cartão'),
(1, '2024-02-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-02-10 21:00:00', 'Concluído', 'Pix');

-- Março
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-03-01 12:00:00', 'Concluído', 'Cartão'),
(2, '2024-03-02 13:00:00', 'Em andamento', 'Pix'),
(1, '2024-03-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-03-04 15:00:00', 'Concluído', 'Cartão'),
(1, '2024-03-05 16:00:00', 'Concluído', 'Pix'),
(2, '2024-03-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-03-07 18:00:00', 'Concluído', 'Cartão'),
(2, '2024-03-08 19:00:00', 'Concluído', 'Pix'),
(1, '2024-03-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-03-10 21:00:00', 'Concluído', 'Cartão');

-- Abril
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-04-01 12:00:00', 'Concluído', 'Pix'),
(2, '2024-04-02 13:00:00', 'Em andamento', 'Cartão'),
(1, '2024-04-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-04-04 15:00:00', 'Concluído', 'Pix'),
(1, '2024-04-05 16:00:00', 'Concluído', 'Cartão'),
(2, '2024-04-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-04-07 18:00:00', 'Concluído', 'Pix'),
(2, '2024-04-08 19:00:00', 'Concluído', 'Cartão'),
(1, '2024-04-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-04-10 21:00:00', 'Concluído', 'Pix');

-- Maio
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-05-01 12:00:00', 'Concluído', 'Cartão'),
(2, '2024-05-02 13:00:00', 'Em andamento', 'Pix'),
(1, '2024-05-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-05-04 15:00:00', 'Concluído', 'Cartão'),
(1, '2024-05-05 16:00:00', 'Concluído', 'Pix'),
(2, '2024-05-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-05-07 18:00:00', 'Concluído', 'Cartão'),
(2, '2024-05-08 19:00:00', 'Concluído', 'Pix'),
(1, '2024-05-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-05-10 21:00:00', 'Concluído', 'Cartão');

-- Junho
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-06-01 12:00:00', 'Concluído', 'Pix'),
(2, '2024-06-02 13:15:00', 'Em andamento', 'Cartão'),
(1, '2024-06-03 14:30:00', 'Concluído', 'Dinheiro'),
(2, '2024-06-04 15:45:00', 'Concluído', 'Pix'),
(1, '2024-06-05 16:15:00', 'Concluído', 'Cartão'),
(2, '2024-06-06 17:30:00', 'Concluído', 'Dinheiro'),
(1, '2024-06-07 18:00:00', 'Em andamento', 'Pix'),
(2, '2024-06-08 19:30:00', 'Concluído', 'Dinheiro'),
(1, '2024-06-09 20:15:00', 'Concluído', 'Cartão'),
(2, '2024-06-10 21:00:00', 'Concluído', 'Pix');

-- Julho
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-07-01 12:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-07-02 13:15:00', 'Em andamento', 'Pix'),
(1, '2024-07-03 14:30:00', 'Concluído', 'Cartão'),
(2, '2024-07-04 15:45:00', 'Concluído', 'Dinheiro'),
(1, '2024-07-05 16:15:00', 'Concluído', 'Pix'),
(2, '2024-07-06 17:30:00', 'Concluído', 'Cartão'),
(1, '2024-07-07 18:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-07-08 19:30:00', 'Concluído', 'Pix'),
(1, '2024-07-09 20:15:00', 'Concluído', 'Cartão'),
(2, '2024-07-10 21:00:00', 'Concluído', 'Dinheiro');

-- Agosto
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-08-01 12:00:00', 'Em andamento', 'Cartão'),
(2, '2024-08-02 13:30:00', 'Concluído', 'Pix'),
(1, '2024-08-03 14:45:00', 'Em andamento', 'Dinheiro'),
(2, '2024-08-04 15:15:00', 'Concluído', 'Cartão'),
(1, '2024-08-05 16:30:00', 'Em andamento', 'Pix'),
(2, '2024-08-06 17:00:00', 'Concluído', 'Dinheiro'),
(1, '2024-08-07 18:15:00', 'Concluído', 'Cartão'),
(2, '2024-08-08 19:45:00', 'Em andamento', 'Pix'),
(1, '2024-08-09 20:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-08-10 21:15:00', 'Concluído', 'Cartão');

-- Setembro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-09-01 12:00:00', 'Em andamento', 'Pix'),
(2, '2024-09-02 13:00:00', 'Concluído', 'Cartão'),
(1, '2024-09-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-09-04 15:00:00', 'Em andamento', 'Pix'),
(1, '2024-09-05 16:00:00', 'Concluído', 'Cartão'),
(2, '2024-09-06 17:00:00', 'Concluído', 'Dinheiro'),
(1, '2024-09-07 18:00:00', 'Concluído', 'Pix'),
(2, '2024-09-08 19:00:00', 'Em andamento', 'Cartão'),
(1, '2024-09-09 20:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-09-10 21:00:00', 'Concluído', 'Pix');

-- Outubro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-10-01 12:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-10-02 13:30:00', 'Em andamento', 'Pix'),
(1, '2024-10-03 14:45:00', 'Concluído', 'Cartão'),
(2, '2024-10-04 15:15:00', 'Concluído', 'Dinheiro'),
(1, '2024-10-05 16:30:00', 'Em andamento', 'Pix'),
(2, '2024-10-06 17:00:00', 'Concluído', 'Cartão'),
(1, '2024-10-07 18:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-10-08 19:00:00', 'Concluído', 'Pix'),
(1, '2024-10-09 20:00:00', 'Em andamento', 'Cartão'),
(2, '2024-10-10 21:00:00', 'Concluído', 'Dinheiro');

-- Novembro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-11-01 12:00:00', 'Concluído', 'Cartão'),
(2, '2024-11-02 13:00:00', 'Concluído', 'Pix'),
(1, '2024-11-03 14:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-11-04 15:00:00', 'Concluído', 'Cartão'),
(1, '2024-11-05 16:00:00', 'Concluído', 'Pix'),
(2, '2024-11-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-11-07 18:00:00', 'Concluído', 'Cartão'),
(2, '2024-11-08 19:00:00', 'Concluído', 'Pix'),
(1, '2024-11-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-11-10 21:00:00', 'Concluído', 'Cartão');

-- Dezembro
INSERT INTO `pedidos` (`cliente_id`, `data_pedido`, `status`, `forma_pagamento`) VALUES
(1, '2024-12-01 12:00:00', 'Concluído', 'Pix'),
(2, '2024-12-02 13:00:00', 'Em andamento', 'Cartão'),
(1, '2024-12-03 14:00:00', 'Concluído', 'Dinheiro'),
(2, '2024-12-04 15:00:00', 'Concluído', 'Pix'),
(1, '2024-12-05 16:00:00', 'Concluído', 'Cartão'),
(2, '2024-12-06 17:00:00', 'Em andamento', 'Dinheiro'),
(1, '2024-12-07 18:00:00', 'Concluído', 'Pix'),
(2, '2024-12-08 19:00:00', 'Concluído', 'Cartão'),
(1, '2024-12-09 20:00:00', 'Em andamento', 'Dinheiro'),
(2, '2024-12-10 21:00:00', 'Concluído', 'Pix');

-- Fevereiro
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(11, 2, 1, 29.90),
(11, 3, 1, 35.90),
(12, 3, 1, 35.90),
(12, 2, 1, 29.90),
(13, 2, 1, 29.90),
(13, 3, 1, 35.90),
(14, 2, 1, 29.90);

-- Março
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(21, 3, 1, 35.90),
(21, 2, 1, 29.90),
(22, 3, 1, 35.90),
(22, 2, 1, 29.90),
(23, 2, 1, 29.90),
(23, 3, 1, 35.90),
(24, 3, 1, 35.90);

-- Abril
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(31, 2, 1, 29.90),
(31, 3, 1, 35.90),
(32, 3, 1, 35.90),
(32, 2, 1, 29.90),
(33, 2, 1, 29.90),
(33, 3, 1, 35.90),
(34, 3, 1, 35.90);

-- Maio
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(41, 3, 1, 35.90),
(41, 2, 1, 29.90),
(42, 2, 1, 29.90),
(42, 3, 1, 35.90),
(43, 2, 1, 29.90),
(43, 3, 1, 35.90),
(44, 3, 1, 35.90);

-- Junho
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(51, 2, 1, 29.90),
(51, 3, 1, 35.90),
(52, 2, 1, 29.90),
(52, 3, 1, 35.90),
(53, 3, 1, 35.90),
(53, 2, 1, 29.90),
(54, 2, 1, 29.90);

-- Julho
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(61, 3, 1, 35.90),
(61, 2, 1, 29.90),
(62, 2, 1, 29.90),
(62, 3, 1, 35.90),
(63, 3, 1, 35.90),
(63, 2, 1, 29.90),
(64, 2, 1, 29.90);

-- Agosto
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(71, 2, 1, 29.90),
(71, 3, 1, 35.90),
(72, 2, 1, 29.90),
(73, 3, 1, 35.90),
(74, 2, 1, 29.90),
(74, 3, 1, 35.90),
(75, 3, 1, 35.90);

-- Setembro
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(81, 2, 1, 29.90),
(81, 3, 1, 35.90),
(82, 3, 1, 35.90),
(83, 2, 1, 29.90),
(83, 3, 1, 35.90),
(84, 3, 1, 35.90),
(85, 3, 1, 35.90);

-- Outubro
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(91, 2, 1, 29.90),
(91, 3, 1, 35.90),
(92, 2, 1, 29.90),
(93, 3, 1, 35.90),
(94, 2, 1, 29.90),
(94, 3, 1, 35.90),
(95, 3, 1, 35.90);

-- Novembro
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(101, 2, 1, 29.90),
(101, 3, 1, 35.90),
(102, 3, 1, 35.90),
(103, 2, 1, 29.90),
(103, 3, 1, 35.90),
(104, 3, 1, 35.90),
(105, 3, 1, 35.90);

-- Dezembro
INSERT INTO `itens_pedido` (`pedido_id`, `produto_id`, `quantidade`, `preco_unitario`) VALUES
(111, 2, 1, 29.90),
(111, 3, 1, 35.90),
(112, 3, 1, 35.90),
(113, 2, 1, 29.90),
(113, 3, 1, 35.90),
(114, 3, 1, 35.90),
(115, 3, 1, 35.90);

-- Janeiro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(1, 105.70, '2024-01-01', 'Cartão de Crédito'),
(2, 85.70, '2024-01-02', 'Dinheiro'),
(3, 85.70, '2024-01-03', 'Pix'),
(4, 29.90, '2024-01-04', 'Cartão de Crédito');

-- Fevereiro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(11, 85.70, '2024-02-01', 'Dinheiro'),
(12, 85.70, '2024-02-02', 'Pix'),
(13, 85.70, '2024-02-03', 'Cartão de Crédito'),
(14, 29.90, '2024-02-04', 'Dinheiro');

-- Março
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(21, 85.70, '2024-03-01', 'Pix'),
(22, 85.70, '2024-03-02', 'Cartão de Crédito'),
(23, 85.70, '2024-03-03', 'Dinheiro'),
(24, 35.90, '2024-03-04', 'Cartão de Crédito');

-- Abril
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(31, 85.70, '2024-04-01', 'Pix'),
(32, 85.70, '2024-04-02', 'Cartão de Crédito'),
(33, 85.70, '2024-04-03', 'Dinheiro'),
(34, 35.90, '2024-04-04', 'Pix');

-- Maio
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(41, 85.70, '2024-05-01', 'Cartão de Crédito'),
(42, 85.70, '2024-05-02', 'Dinheiro'),
(43, 85.70, '2024-05-03', 'Pix'),
(44, 35.90, '2024-05-04', 'Cartão de Crédito');

-- Junho
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(51, 85.70, '2024-06-01', 'Dinheiro'),
(52, 85.70, '2024-06-02', 'Pix'),
(53, 85.70, '2024-06-03', 'Cartão de Crédito'),
(54, 29.90, '2024-06-04', 'Dinheiro');

-- Julho
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(61, 85.70, '2024-07-01', 'Pix'),
(62, 85.70, '2024-07-02', 'Cartão de Crédito'),
(63, 85.70, '2024-07-03', 'Dinheiro'),
(64, 29.90, '2024-07-04', 'Pix');

-- Agosto
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(71, 85.70, '2024-08-01', 'Cartão de Crédito'),
(72, 49.80, '2024-08-02', 'Dinheiro'),
(73, 35.90, '2024-08-03', 'Pix'),
(74, 29.90, '2024-08-04', 'Cartão de Crédito');

-- Setembro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(81, 85.70, '2024-09-01', 'Pix'),
(82, 49.80, '2024-09-02', 'Cartão de Crédito'),
(83, 85.70, '2024-09-03', 'Dinheiro'),
(84, 35.90, '2024-09-04', 'Pix');

-- Outubro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(91, 85.70, '2024-10-01', 'Cartão de Crédito'),
(92, 49.80, '2024-10-02', 'Dinheiro'),
(93, 35.90, '2024-10-03', 'Pix'),
(94, 85.70, '2024-10-04', 'Pix');

-- Novembro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(101, 85.70, '2024-11-01', 'Dinheiro'),
(102, 49.80, '2024-11-02', 'Pix'),
(103, 85.70, '2024-11-03', 'Cartão de Crédito'),
(104, 35.90, '2024-11-04', 'Dinheiro');

-- Dezembro
INSERT INTO `pagamentos` (`pedido_id`, `valor_pago`, `data_pagamento`, `metodo_pagamento`) VALUES
(111, 85.70, '2024-12-01', 'Pix'),
(112, 49.80, '2024-12-02', 'Cartão de Crédito'),
(113, 85.70, '2024-12-03', 'Dinheiro'),
(114, 35.90, '2024-12-04', 'Pix');*/

/*
-- Produto que Mais Vendeu
SELECT p.id AS produto_id, p.nome AS produto_nome, SUM(i.quantidade) AS total_vendido
FROM produtos p
INNER JOIN itens_pedido i ON p.id = i.produto_id
GROUP BY p.id
ORDER BY total_vendido DESC
LIMIT 1;
*/
/*
-- Produto que Menos Vendeu
SELECT p.id AS produto_id, p.nome AS produto_nome, SUM(i.quantidade) AS total_vendido
FROM produtos p
INNER JOIN itens_pedido i ON p.id = i.produto_id
GROUP BY p.id
ORDER BY total_vendido ASC
LIMIT 1;
*/
/*
-- Mês do Ano com Mais Vendas
SELECT EXTRACT(MONTH FROM pd.data_pedido) AS mes, SUM(i.quantidade) AS total_vendido
FROM pedidos pd
INNER JOIN itens_pedido i ON pd.id = i.pedido_id
GROUP BY mes
ORDER BY total_vendido DESC
LIMIT 1;
*/
/*
--Forma de Pagamento Mais Usada
SELECT pa.metodo_pagamento, COUNT(*) AS total_pagamentos
FROM pagamentos pa
GROUP BY pa.metodo_pagamento
ORDER BY total_pagamentos DESC
LIMIT 1;
*/

-- X Endereço que Mais Teve Entregas
/*
-- Venda com Mais Produtos
SELECT pd.id AS pedido_id, COUNT(i.id) AS total_produtos
FROM pedidos pd
INNER JOIN itens_pedido i ON pd.id = i.pedido_id
GROUP BY pd.id
ORDER BY total_produtos DESC
LIMIT 1;
*/
/*
-- Vendas do Mês Atual por Pagamento
SELECT pa.metodo_pagamento, SUM(i.quantidade * i.preco_unitario) AS total_vendido
FROM pedidos pd
INNER JOIN itens_pedido i ON pd.id = i.pedido_id
INNER JOIN pagamentos pa ON pd.id = pa.pedido_id
WHERE EXTRACT(MONTH FROM pd.data_pedido) = EXTRACT(MONTH FROM CURRENT_DATE)
AND EXTRACT(YEAR FROM pd.data_pedido) = EXTRACT(YEAR FROM CURRENT_DATE)
GROUP BY pa.metodo_pagamento;
*/
/*
-- Vendas do Mês Anterior por Pagamento
SELECT pa.metodo_pagamento, SUM(i.quantidade * i.preco_unitario) AS total_vendido
FROM pedidos pd
INNER JOIN itens_pedido i ON pd.id = i.pedido_id
INNER JOIN pagamentos pa ON pd.id = pa.pedido_id
WHERE EXTRACT(MONTH FROM pd.data_pedido) = EXTRACT(MONTH FROM CURRENT_DATE) - 1
AND EXTRACT(YEAR FROM pd.data_pedido) = EXTRACT(YEAR FROM CURRENT_DATE)
GROUP BY pa.metodo_pagamento;
*/