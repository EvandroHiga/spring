package br.com.fiap.spring.utils;

public class MessageConstants {
    public static final String TITULO_LISTA_ALUNOS_INSERIDOS = "::: Lista de alunos inseridos no banco de dados :::";

    // TODO Apos conserto da insercao, mandar msg de conclusao.
    public static final String LISTA_INSERIDA_COM_SUCESSO = "::: Lista de alunos inserida com sucesso :::";

    public static final String NAO_HA_NOVA_LISTA_ALUNOS = "::: Nao ha uma nova lista de alunos a ser inserida :::";

    public static final String TOKEN_JWT_AUSENTE = "Token nao encontrado.";
    public static final String TOKEN_JWT_FORA_PADRAO_BEARER = "Token encontrado, porem fora do padrao Bearer.";

    public static final String ERRO_PROCESSAR_ARQUIVO_ALUNOS = "Erro ao processar o arquivo lista_alunos.txt. Verifique.";
    public static final String ERRO_TOKEN_PARSE = "Erro ao fazer parse do token";
    public static final String ERRO_TOKEN_EXPIRADO = "Token expirado";
}
