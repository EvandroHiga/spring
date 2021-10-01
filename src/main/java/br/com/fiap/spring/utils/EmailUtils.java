package br.com.fiap.spring.utils;

import br.com.fiap.spring.model.RegistroCompra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailUtils {
    public static String comprovanteCompraParser(RegistroCompra compraDto){
        return new StringBuilder()
                .append("[ DADOS DA COMPRA ]")
                .append(System.getProperty("line.separator"))
                .append("CARTAO: ")
                .append(compraDto.getCartao().getNumero())
                .append(System.getProperty("line.separator"))
                .append("ESTABELECIMENTO: ")
                .append(compraDto.getEstabelecimento())
                .append(System.getProperty("line.separator"))
                .append("VALOR: R$ ")
                .append(compraDto.getValor())
                .append(System.getProperty("line.separator"))
                .append("DATA / HORA: ")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .append(System.getProperty("line.separator")).toString();
    }
}
