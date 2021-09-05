### FIAP > trabalho de conclusao de materia > Spring

##### Como montar e subir o ambiente + projeto:

1. Clone o repositorio 
> git clone https://github.com/EvandroHiga/spring.git .

2. Após o clone do repo, entre no diretorio *docker* e rode os comandos:
> criar a imagem a partir do Dockerfile: **docker build -t mysql-img-car_cred_fiap:latest .**

> criar e subir o container a partir da imagem: **docker run -d -p 3306:3306 --rm --name mysql-car_cred_fiap mysql-img-car_cred_fiap:latest**

3. Importe e suba o projeto na IDE.
> se tudo estiver OK, o /actuator/health deve responder com um *status: UP* e o banco deve estar populado com o *lista_alunos.txt*.
