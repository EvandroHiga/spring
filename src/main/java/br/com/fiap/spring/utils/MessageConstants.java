package br.com.fiap.spring.utils;

public class MessageConstants {

    public static final String ALUNOS_INSERIDOS = "::: [Alunos inseridos] :::";
    public static final String LISTA_ALUNOS_INSERIDA_SUCESSO = "::: [lista_alunos.txt finalizado com sucesso] :::";
    public static final String NAO_HA_NOVA_LISTA_ALUNOS = "::: [Nao ha um novo lista_alunos.txt para ser inserido] :::";
    public static final String ERRO_PROCESSAR_ARQUIVO_ALUNOS = "::: [Erro ao processar o arquivo lista_alunos.txt => Verifique] :::";

    public static final String ERRO_USERNAME_PASSWD = "Authorization error. Favor verificar username/password inserido.";
    public static final String ERRO_CRIAR_USUARIO = "Erro ao tentar criar o usuario.";

    public static final String TOKEN_JWT_AUSENTE = "Token nao encontrado.";
    public static final String TOKEN_JWT_FORA_PADRAO_BEARER = "Token encontrado, porem fora do padrao Bearer.";

    public static final String ERRO_TOKEN_PARSE = "Erro ao fazer parse do token.";
    public static final String TOKEN_EXPIRADO = "Token expirado. Realize um novo login.";

    public static final String COMPRA_SUCESSO = "Compra realizada com sucesso.";
    public static final String COMPRA_SUCESSO_ERRO_COMPROVANTE = "Compra efetivada, mas nao foi possivel enviar comprovante por email.";
    public static final String CARTAO_NAO_EXISTE = "Cartao inexistente na base de dados.";
    public static final String CARTAO_SENHA_INCORRETA = "Senha do cartao incorreta.";
    public static final String ERRO_CRIAR_CARTAO = "Erro ao tentar criar o cartao. Verifique se o mesmo ja existe.";

    public static final String ERRO_CLIENTE_INEXISTENTE = "Aluno/Cliente inexistente.";
}
