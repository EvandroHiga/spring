## Java 11+ e Spring Framework

![#ffff00](https://via.placeholder.com/10/ffff00/000000?text=+) Em andamento...

**Sobre**
> Projeto de conclusão da matéria de *Spring* do MBA ***Full Stack Development - Design, Engineering and Deployment*** na [FIAP](https://www.fiap.com.br/). API RESTful que simula uma aplicação que recebe e administra pagamentos via cartão de crédito próprio.

**O que foi utilizado**
> Java 11+, [Spring](https://spring.io/projects) Boot, Web, Security, Data JPA, Actuator e Mail. MySQL 8 em um container [Docker](https://www.docker.com/). Em conjunto com o Spring Security, foi utilizado tokens JWT para o controle de authorizations/authentications na utilização dos endpoints.


**Como rodar**
> 1. Clone o repo com: _git clone https://github.com/EvandroHiga/spring.git_
> 2. Após o clone, entre no diretorio _spring/docker_ e rode:
> - p/ criar a imagem a partir do Dockerfile: _docker build -t mysql-img-car_cred_fiap:latest ._
> - p/ criar e subir o container a partir da imagem: _docker run -d -p 3306:3306 --rm --name mysql-car_cred_fiap mysql-img-car_cred_fiap:latest_
> 3. Inicie-o. Na primeira vez, o arquivo *lista_alunos.txt* será consumido para popular a tabela _alunos_. Para testar, chame _/actuator/health_ e espere por um _status: UP_. **Obs.**: para utilizar o _gmail_ como remetente no _Spring Mail_, é necessário vir [aqui](https://www.google.com/settings/security/lesssecureapps) e ativar _'permitir aplicativos menos seguros'_.