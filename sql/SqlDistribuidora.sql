CREATE DATABASE IF NOT EXISTS distribuidora CHARACTER SET utf8mb4;
CREATE USER IF NOT EXISTS 'aluno_cd'@'localhost' IDENTIFIED BY 'aluno_pw';
GRANT ALL PRIVILEGES ON distribuidora.* TO 'aluno_cd'@'localhost';
FLUSH PRIVILEGES;

USE distribuidora;

CREATE TABLE IF NOT EXISTS peca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    marca VARCHAR(40) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade INT NOT NULL,
    aplicacao VARCHAR(80)
);

USE distribuidora;

INSERT INTO peca (codigo, descricao, marca, preco, quantidade, aplicacao) VALUES
('FLT001', 'Filtro de oleo',        'Mahle',        24.90,  40, 'Motor 1.0/1.6'),
('FLT002', 'Filtro de ar',          'Mahle',        35.50,  25, 'Linha GM'),
('FLT003', 'Filtro de combustivel', 'Bosch',        42.00,  18, 'Motor 1.8/2.0'),
('PST001', 'Pastilha de freio',     'Bosch',        89.90,  15, 'Dianteira - linha VW'),
('DIS001', 'Disco de freio',        'Brembo',      210.00,  10, 'Par dianteiro'),
('COR001', 'Correia dentada',       'Continental', 120.00,  12, 'Motor 8v'),
('BAT001', 'Bateria 60Ah',          'Moura',       399.90,   8, 'Uso geral'),
('VEL001', 'Vela de ignicao',       'NGK',          18.50,  60, 'Motor 1.0'),
('AMO001', 'Amortecedor dianteiro', 'Monroe',      265.00,  10, 'Par - linha Fiat'),
('EMB001', 'Kit de embreagem',      'Valeo',       780.00,   5, 'Motor 1.6');

Select * From peca;
