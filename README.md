<h1 align="center" style="font-weight: bold;">Postal Service 📫 </h1>

<p align="center">
    <b>A aplicação permite buscar qualquer CEP brasileiro </b>
</p>


<h2 id="technologies"> Conceito </h2>
Esta aplicação consiste em um serviço de busca de endereços brasileiros baseado em CEP, desenvolvido como uma API REST que interage com um banco de dados MySQL para armazenar e consultar informações. A arquitetura é containerizada usando Docker, permitindo que a aplicação seja facilmente configurada e executada em diferentes ambientes.


<h2 id="technologies"> Tecnologias </h2>

- Java 17
- Spring Boot
- Spring Data JPA
- Spring MVC
- MySQL 
- Docker Compose 


<h2 id="started">Práticas adotadas</h2>

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências 
- Exceptions 

<h2 id="started">Testes</h2>
Cobertura de testes de integrações, utilizando: 

- MockServer 
- H2 Database 

<h2 id="started">Como executar</h2>
É necessário clonar o repositório do projeto

<h3>Clonar</h3>

```bash
git clone your-project-url-in-github
```

<h3>Executar</h3>

```bash
    docker-compose up
```
  <b> Importante: </b> Ao buildar e iniciar a API, o processo pode levar de 3 a 5 minutos para completar a configuração inicial. Isso acontece porque, nesse tempo, a API estará baixando todos os CEPs (Códigos de Endereçamento Postal) necessários e inserindo esses dados no banco de dados MySQL.

Durante esse período, enquanto a API ainda está configurando esses dados, qualquer tentativa de acessá-la resultará em um erro 503. Esse erro significa que o serviço não está disponível no momento, pois o sistema ainda está se instalando.

<h3>Exemplos de testes</h3>
<b> 200 OK </b>

```json
{
    "zipcode": "03358150",
    "street": "Rua Ituri",
    "district": "Vila Formosa",
    "state": "SP",
    "city": "São Paulo"
}
```

<b> 503 Service Unavailable </b>

```json
{
    {
    "timestamp": "2023-01-16T23:27:34.962+00:00",
    "status": 503,
    "error": "Service Unavailable",
    "message": "This service is being installed, please wait a few moments.",
    "path": "/zip/03358150"
}
```

204 No-Content
```bash
    curl http://localhost:6868/zip/9999999
```
