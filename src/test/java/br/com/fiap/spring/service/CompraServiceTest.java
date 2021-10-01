package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.repository.RegCompraRepository;
import br.com.fiap.spring.utils.MessageConstants;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CompraServiceTest {

    @InjectMocks
    private CompraService compraService;

    @Mock
    private CartaoService cartaoService;

    @Mock
    private RegCompraRepository regCompraRepository;


    @Test
    public void whenAutorizarCompra_CARTAO_NAO_EXISTE() {

        Cartao cartao = cartaoService.validarCartao(getCompraCCInvalido().getNumCartao());
        Assert.assertFalse(MessageConstants.CARTAO_NAO_EXISTE,Objects.isNull(cartao.getNumero()));

    }

    public CompraDto getCompraDto() {
        CompraDto compraDto = new CompraDto();

        compraDto.setNumCartao("1234.4567");
        compraDto.setSenha("1234");
        compraDto.setCodSeg("012");
        compraDto.setEstabelecimento("Teste Razão Estabelecimento");
        compraDto.setValor(250F);
        return compraDto;
    }

    public CompraDto getCompraCCInvalido() {
        CompraDto compraDto = new CompraDto();

        compraDto.setNumCartao("");
        compraDto.setSenha("1234");
        compraDto.setCodSeg("012");
        compraDto.setEstabelecimento("Teste Razão Estabelecimento");
        compraDto.setValor(250F);
        return compraDto;
    }

}
