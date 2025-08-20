select * from medicamento;
select * from paciente;

select * from prescricao;


-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS remediar DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE remediar;

-- Tabela: paciente
CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    historico_clinico TEXT
);

-- Tabela: medicamento
CREATE TABLE IF NOT EXISTS medicamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dosagem VARCHAR(50),
    finalidade VARCHAR(100),
    fabricante VARCHAR(100),
    quantidade_estoque INT DEFAULT 0
);
ALTER TABLE medicamento ADD forma_administracao VARCHAR(50);


-- Tabela: prescricao
CREATE TABLE IF NOT EXISTS prescricao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medicamento_id INT NOT NULL,
    dosagem VARCHAR(50),
    frequencia VARCHAR(50),
    horario VARCHAR(50),
    medico_responsavel VARCHAR(100),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id) ON DELETE CASCADE,
    FOREIGN KEY (medicamento_id) REFERENCES medicamento(id) ON DELETE CASCADE
);
ALTER TABLE prescricao ADD duracao VARCHAR(50);

ALTER TABLE prescricao ADD COLUMN observacoes TEXT;

-- Tabela: administracao
CREATE TABLE IF NOT EXISTS administracao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prescricao_id INT NOT NULL,
    data_hora DATETIME NOT NULL,
    responsavel VARCHAR(100),
    observacoes TEXT,
    FOREIGN KEY (prescricao_id) REFERENCES prescricao(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha_hash VARCHAR(64) NOT NULL
);



DELETE FROM usuario;
INSERT INTO usuario (login, senha_hash)
VALUES ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

UPDATE paciente
SET data_nascimento = '2021-01-01'
WHERE data_nascimento = '210101';

UPDATE paciente
SET data_nascimento = '2021-01-01'
WHERE data_nascimento = '210101';

UPDATE paciente
SET data_nascimento = '2021-01-01'
WHERE data_nascimento = '21213';

SELECT * FROM administracao;