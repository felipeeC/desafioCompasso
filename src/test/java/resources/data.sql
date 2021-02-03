INSERT INTO Filme (descricao, nome, comentario, data_lancamento, estudio, diretor, elenco)
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
		),(
		'tt',
		'calculadora',
		'ttttt',
		'1999-05-12',
		'Hollywood',
		'felipe alves',
		'Leonardo Da vinci');


INSERT INTO Pessoa (nome, aniversario, email)
	VALUES 
		('Guilherme', '1996-11-22', 'guilhermecald96@gmail.com'),
		('Rafael', '1999-05-22', 'rafaelcalderaro@gmail.com'),
		('Gabriel', '1993-04-17', 'gabrielcalderaro@gmail.com');
    
INSERT INTO Categoria(nome)
     VALUES
		('Suspense'), ('Comédia'), ('Ação'),
		('Aventura'), ('Infantil'), ('Romance'),
		('Ficção'), ('Terror'), ('Mistério'),
		('Drama');
     
INSERT INTO FILME_PESSOAS (FILMES_ID, PESSOAS_ID)
	VALUES (2,2), (1,2);