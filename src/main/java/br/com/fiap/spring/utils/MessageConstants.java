package br.com.fiap.spring.utils;

public class MessageConstants {

    public static final String ALUNOS_INSERIDOS = "::: [Alunos inseridos] :::";
    public static final String LISTA_ALUNOS_INSERIDA_SUCESSO = "::: [lista_alunos.txt finalizado com sucesso] :::";
    public static final String NAO_HA_NOVA_LISTA_ALUNOS = "::: [Nao ha um novo lista_alunos.txt para ser inserido] :::";
    public static final String ERRO_PROCESSAR_ARQUIVO_ALUNOS = "::: [Erro ao processar o arquivo lista_alunos.txt => Verifique] :::";

    public static final String TOKEN_JWT_AUSENTE = "Token nao encontrado.";
    public static final String TOKEN_JWT_FORA_PADRAO_BEARER = "Token encontrado, porem fora do padrao Bearer.";

    public static final String ERRO_TOKEN_PARSE = "Erro ao fazer parse do token.";
    public static final String TOKEN_EXPIRADO = "Token expirado. Realize um novo login.";
}
