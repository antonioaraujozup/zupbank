ALTER TABLE transferencia
ADD CONSTRAINT FK_CONTA_ORIGEM_ID_TRANSFERENCIA
FOREIGN KEY (conta_origem_id) REFERENCES conta(id);