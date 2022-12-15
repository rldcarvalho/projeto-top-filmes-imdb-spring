# Top Filmes IMDb

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=2.7.4&color=blue&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Java&message=17&color=red&style=for-the-badge&logo=JAVA"/>
  <img src="http://img.shields.io/static/v1?label=Maven&message=4.0.0&color=red&style=for-the-badge&logo=Apache%20Maven"/>
  <img src="http://img.shields.io/static/v1?label=TESTES&message=5%20passed&color=GREEN&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge"/>
</p>

<p align="center">
  <img width="205" height="331" src="https://user-images.githubusercontent.com/28987245/207752664-23bfbb98-95d6-4828-be01-1df47b67decc.png">
</p>

### Descrição do Projeto

Esse projeto consiste em uma aplicação criada através do Spring Boot utilizando a linguagem Java que, de posse de uma Acess Key, realiza uma requisição a API do IMDb e obtém um Json contendo informações do Top 250 filmes da plataforma. Os dados obtidos são extraídos, modelados e agrupados em uma lista, possibilitando a geração de um código HTML contendo todos os 250 filmes, ou fazer uma seleção de filmes favoritos antes de imprimir essa listagem. Para fazer a requisição do Json à API do IMDb e criar os endpois dessa aplicação foi utilizado o módulo Spring WEB. Testes de integração foram elaborados utilizando o JUnit 5 a fim de cobrir todas as requisições.

## A aplicação

### Filme

Contém os seguintes campos:

```json
"id", //Id sequencial do filme gerado ao formar a lista de 250 filmes
"title", //Título do Filme
"urlImage", //URL da imagem de cartaz do filme
"rating", //Nota do filme na plataforma do ImDB
"year", //Ano de lançamento do filme
```
### Endpoints

Os endpoints da aplicação só podem ser acessados mediante autenticação via API Key. Para obter a key é necessário realizar cadastro no [site do IMDb](https://imdb-api.com/api), que fornecerá uma Access Key ID. Essa Key obtida deverá ser eviada através da variável de ambiente {api.key}.

A aplicação suporta das seguintes funcionalidades:

- GET ```/top250``` 
Retorna os 250 melhores filmes do IMDb classificados por nota.

- GET ```/top250?tittle=palavraBuscadaNoTituloDosFilmes```
Consulta no top 250 filmes os filmes que contem as palavras enviadas no parametro contido na URL.

- POST ```/favorito/{filmeId}```
Insere em uma base de filmes favoritos o filme cujo id foi passado na URL.

- GET ```/favoritos```
Retorna uma lista com todos os filmes adicionados aos favoritos.


## Premissa do Projeto

Projeto elaborado durante o desafio 7daysOfCode da Alura. A cada dia da semana era enviada uma tarefa para progredir com a construção da aplicação.

Dia 1: Criar o projeto com o Spring Initializr com a dependencia Spring Web e enviar uma requisição HTTP do tipo GET o endpoint da API do IMDb e obter o Json com o top 250 filmes.

Dia 2: Elaborar um teste de integração para verificar o recebimento do Json.

Dia 3: Criar uma classe que modela cada filme separadamente.

Dia 4: Escrever um método que gere um código HTML mostrando todos os 250 filmes.

Dia 5: Encapsular o código seguindo boas práticas de programação.

Dia 6: Possibilitar a pesquisa de um titulo de filme enviando uma palavra pela URL; Criar uma lista em memória e adicionar o campo de ID aos 250 filmes.

Dia 7: Criar uma requisição do tipo POST para adicionar filmes favoritos passando o ID deles; Implementar uma requisição para listar os filmes favoritos e por fim criar testes para essas novas requisições.

## Licença

The [MIT License](https://github.com/rldcarvalho/projeto-top-filmes-imdb-spring/blob/main/LICENSE) (MIT)

Copyright :copyright: 2022 - Top Filmes IMDb
