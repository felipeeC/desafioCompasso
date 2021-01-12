INSERT INTO Filme(descricao, nome, comentario, data_lancamento, estudio, diretor, elenco)
	VALUES (
		'A bordo',
		'Titanic',
		'Filme trágico',
		'1997-05-23',
		'Hollywood',
		'James Cameron',
		'Leonardo Dicaprio'
		), (
		'An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.',
		'Harry Potter e a Pedra Filosofal',
		'',
		'2001-11-23',
		'Warner Bros.',
		'Chris Columbus',
		'Daniel Radcliffe'
		);

INSERT INTO Pessoa(nome, idade)
	VALUES 
		('Guilherme', 24),
		('Rafael', 21),
		('Gabriel', 27);
    
INSERT INTO Categoria(nome)
     VALUES ('Suspense'), ('Comédia'), ('Ação'), ('Aventura'), ('Infantil'), ('Romance'), ('Ficção'), ('Terror'), ('Mistério'), ('Drama');