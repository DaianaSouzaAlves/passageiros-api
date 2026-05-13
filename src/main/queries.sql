//Listar passageiros
SELECT *
FROM passageiro
ORDER BY nome;

//Contar quantos emails gmail
SELECT COUNT(*)
FROM passageiro
WHERE email LIKE '%@gmail.com';

//Nomes com Silva
SELECT *
FROM passageiro
WHERE nome LIKE '%silva%';

//Contar total de passageiros
SELECT COUNT(*)
FROM passageiro;

//Listar só nome e email
SELECT nome, email
FROM passageiro;

//Passageiros ordenados do mais antigo
SELECT *
FROM passageiro
ORDER BY id DESC;