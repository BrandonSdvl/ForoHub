ALTER TABLE topicos DROP COLUMN autor;

ALTER TABLE topicos
    ADD COLUMN id_autor bigint NOT NULL,
    ADD CONSTRAINT fk_id_autor FOREIGN KEY (id_autor) REFERENCES usuarios(id);

ALTER TABLE usuarios
    ADD CONSTRAINT nombre_unique UNIQUE (nombre);