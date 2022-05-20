CREATE TABLE transferencia (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valor decimal(18,2) NOT NULL,
    realizado_em timestamp NOT NULL,
    conta_origem_id bigint NOT NULL,
    conta_destino_id bigint NOT NULL
);