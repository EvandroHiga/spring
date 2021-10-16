## Java 11+ e Spring Framework

![#009933](https://via.placeholder.com/15/009933/000000?text=+) Concluído

### Sobre
> Projeto de conclusão da matéria de *Spring* do MBA ***Full Stack Development - Design, Engineering and Deployment*** na [FIAP](https://www.fiap.com.br/). API RESTful que simula uma aplicação que recebe e administra pagamentos via cartão de crédito próprio.

### O que foi utilizado
> Java 11+, [Spring](https://spring.io/projects) Boot, Web, Security, Data JPA, Actuator e Mail. MySQL 8 em um container [Docker](https://www.docker.com/). Em conjunto com o Spring Security, foi utilizado tokens JWT para o controle de authorizations/authentications na utilização dos endpoints.

### Como subir
> 1. Clone o repo com: `git clone https://github.com/EvandroHiga/spring.git`
> 2. Após o clone, entre no diretorio _spring/docker_ e rode:
> - p/ criar a imagem a partir do Dockerfile: `docker build -t mysql-img-car_cred_fiap:latest .`
> - p/ criar e subir o container a partir da imagem: `docker run -d -p 3306:3306 --rm --name mysql-car_cred_fiap mysql-img-car_cred_fiap:latest`
> 3. Inicie-o. Na primeira vez, o arquivo *lista_alunos.txt* será consumido para popular a tabela _alunos_. Para testar, chame _/actuator/health_ e espere por um _status: UP_. **Obs.**: para utilizar o _gmail_ como remetente no _Spring Mail_, é necessário vir [aqui](https://www.google.com/settings/security/lesssecureapps) e ativar _'permitir aplicativos menos seguros'_.

### Casos de teste e como utilizá-los
- **POST : /usuarios/login**: Necessário para obtenção do token JWT. As roles são _ADMIN_, com acesso total ao sistema, e _USER_, com permissão para consultas.
Esta request não necessita de um token. Para fins de testes, o usuário abaixo já foi inserido no banco como _ADMIN_.
```
Ex. de _payload_: 

{
    "username":"root",
    "password":"root"
}
```

- **GET : /usuarios/<id_do_usuario>**: Traz os dados do usuário pesquisado. Não exibe a senha descriptografada. Necessário um Bearer token _ADMIN_ no _Authorization_ do header. 
```
Ex. de _response_:

{
    "id": 1,
    "username": "root",
    "password": "$2a$10$1B6VMXGSzPjVFNXFRKlZ3.BLjhlpndmhyvtVWnDdQeZ9mUnxvYp2m",
    "role": "ADMIN"
}
```

- **POST : /usuarios**: Cria um novo usuário. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _payload_:

{
    "username":"carlos",
    "password":"carlos",
    "role":"USER"
}
```

- **POST : /compras**: Payload com os dados da compra. Caso os dados estejam corretos, a compra é aprovada. Se um email válido for informado, o comprovante da compra será enviado.
Não é necessário um token para esta requisição.
```
Ex. de _payload_:

{
    "numCartao":"0000999977773333",
    "senha":"654321",
    "estabelecimento":"RESTAURANTE VILA PRUDENTE",
    "valor": 175.55,
    "email":"mail@mail.com"
}
```

- **GET : /compras/aluno/<id_do_aluno>**: Array contendo todas as compras realizadas por aquele cliente/aluno. Necessário um _Bearer_ token no _Authorization_ header.
```
Ex. de _response_:

[
    {
        "nome": "ARTHUR RYUU GUNJI",
        "rm": "1005714",
        "cartao": "0000999977773333",
        "estabelecimento": "MECANICO",
        "valor": 1235.5
    },
    {
        "nome": "ARTHUR RYUU GUNJI",
        "rm": "1005714",
        "cartao": "0000999977773333",
        "estabelecimento": "FEIRA",
        "valor": 75.4
    }
]
```

- **GET : /cartoes/<numero_do_cartao>**: Traz os dados do cartao e o ID do aluno/cliente ao qual pertence. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _response_:

{
    "id": 2,
    "idAluno": 1003,
    "numero": "0000999977773333",
    "senha": "654321",
    "codSeg": "999"
}
```

- **POST : /cartoes**: Payload contendo os dados do novo cartão a ser criado e o ID do usuario ao qual pertencerá. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _payload_:

{
    "id": 2,
    "idAluno": 1003,
    "numero": "0000999977773333",
    "senha": "654321",
    "codSeg": "999"
}
```

- **DELETE : /cartoes/<id_do_cartao>**: Exclui o cartao. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Não há exemplos de _payload_ e _response_
```

- **GET : /alunos/all**: Traz um array contendo todos os alunos/clientes cadastrados. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _response_:

[
    {
        "id": 1,
        "nome": "AARON FELIPE GRASSMANN",
        "rm": "3095564",
        "cod": "100-11"
    },
    {
        "id": 2,
        "nome": "AARON PAPA DE MORAIS",
        "rm": "8610833",
        "cod": "160-26"
    },
    ...
]
```

- **GET : /alunos/<id_do_aluno>**: Traz os dados do aluno solicitado, pesquisando-o pelo ID. Necessário um _Bearer_ token no _Authorization_ header.
```
Ex. de _response_:

{
    "id": 2,
    "nome": "AARON PAPA DE MORAIS",
    "rm": "8610833",
    "cod": "160-26"
}
```

- **GET : /alunos?nome=<nome_do_aluno>**: Traz os dados do aluno solicitado, pesquisando-o pelo nome. Necessário um _Bearer_ token no _Authorization_ header.
```
Ex. de _response_:

{
    "id": 2,
    "nome": "AARON PAPA DE MORAIS",
    "rm": "8610833",
    "cod": "160-26"
}
```

- **POST : /alunos**: Cria um novo aluno. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _payload_:

{
    "nome":"Jose da Silva",
    "rm":"123456",
    "cod":"666-66"
}
```

- **PUT : /alunos/<id_do_aluno>**: Atualiza todas as informacoes do aluno conforme o payload enaviado. Necessário um _Bearer_ token _ADMIN_ no _Authorization_ header.
```
Ex. de _payload_:

{
    "nome":"Jose da Silva",
    "rm":"123456",
    "cod":"666-66"
}
```
