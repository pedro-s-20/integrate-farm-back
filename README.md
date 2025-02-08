
# 💊 Integrate Farma

Este é um projeto que feito para um trabalho final da disciplina de Sistemas Distribuídos. Consiste em um sistema backend para farmácias, em que estas poderão anunciar seus produtos e agendamentos de serviços com terceiros (algo como uma farmácia que possibilita consultas de profissionais especializados).

O objetivo do trabalho era criar sistemas distribuídos que se comunicassem via RMI em redes distintas. Então esse projeto foi pensando para que possibilitasse a comunicação com um outro sistema interno, via RMI, mas que também permitisse a exposição das funcionalidades via REST, que foram consumidas por uma aplicação Angular.

Algumas ressalvas: 
- Devido a questões de tempo para entrega do projeto, alguns endpoints foram projetados visando somente demonstrar a funcionalidade e não contam com o devido cuidado com questões de boas práticas e até de segurança no código.
- Contei com boa parte de código já desenvolvido anteriormente em um trabalho em grupo de quando fiz estágio, link do projeto: [Safety Soft - um sistema para agendamento de consultas médicas.](https://github.com/pedro-s-20/vemser-trabalho-final-spring-security-mockito-mongo-kafka)

### 🛠 Tecnologias e padrões de projeto
<ul>
    <li>Java 17</li>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>Spring Data</li>
    <li>Comunicação RMI</li>
    <li>Banco de dados MySQL</li>
    <li>Docker</li>
    <li>Swagger</li>
    <li>JavaMail com templates FreeMarker</li>
    <li>Abordagem MVC</li>
    <li>Padrão de projeto DTO</li>
</ul>

## 🐳 Deploy

#### Sigas os passos: 

Primeiramente, precisamos configurar o banco MySQL em um container Docker. (Mas caso usar o banco local, pode ignorar essa etapa)
- Tendo o Docker instalado, no PowerShell execute o comando: 
```bash
  docker run --name mysql-container `
  --env MYSQL_ROOT_PASSWORD=root `
  --env MYSQL_DATABASE=integrate_farm_db `
  -v "$PWD/init.sql:/docker-entrypoint-initdb.d/init.sql" `
  -p 3307:3306 `
  -d mysql:8.0.29
```

- Copie o arquivo init.sql para a pasta C:// (para ficar mais fácil de acessar), em seguida, execute o comando: 
```bash
  docker exec -i mysql-container mysql -u root -proot < /init.sql
```
Dica: baixe o gerenciador de Banco de Dados DBeaver e conecte ao banco de dados que acabamos de configurar para mais facilmente visualizar e gerenciar os dados.

#### Agora vamos fazer o deploy do ambiente Spring Boot.

Atualize o arquivo .env com suas credenciais.
<br> 
Observações: as credenciais MYSQLDB_LOCAL_ADDRESS e RMI_HOST, são o ip do localhost, para saber qual seu ip, digite no terminal do Windows o comando abaixo e copie a informação de IPv4.: 
```bash
  ipconfig
```
Para as informações referente a e-mail, pode-se criar uma conta no [Mailtrap](https://mailtrap.io/signin) e gerar as credenciais por lá.

Em seguidarode o comando abaixo:

```bash
  docker-compose up
```

Pronto! Será criado a imagem e container docker. Dica: Utilize o Docker Desktop para visualizar e gerenciar o container mais fácilmente.

Obs.: os endpoints de Funcionário e os de Produto (com exceção do de listagem paginado) dependem do servidor RMI para se conectarem, então, caso não queira implementar um servidor seguindo as interfaces e classes contidas no projeto, esses endpoints não funcionarão.
