package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.RegistroCompra;
import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import br.com.fiap.spring.repository.RegCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.fiap.spring.utils.MessageConstants.*;

@Service
public class CompraService {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private RegCompraRepository regCompraRepository;

    public String autorizarCompra(CompraDto compraDto){
        Cartao cartao = cartaoService.validarCartao(compraDto.getNumCartao());

        if(cartao != null){
            if(cartao.getSenha().equals(compraDto.getSenha())){
                efetivarCompra(cartao, compraDto);
                return COMPRA_SUCESSO;
            } else {
                return CARTAO_SENHA_INCORRETA;
            }
        } else {
            return CARTAO_NAO_EXISTE;
        }

    }

    public List<ConsultaCompraDto> consultarComprasByClienteId(Long id){
        List<ConsultaCompraDto> consultaCompraDtoList = regCompraRepository.findComprasByClienteId(id);

        // TODO Apenas 'caminho feliz' implementado. Implementar o resto.

        return consultaCompraDtoList;
    }

    private void efetivarCompra(Cartao cartao, CompraDto compraDto){
        RegistroCompra registroCompra = new RegistroCompra();

        registroCompra.setCartao(cartao);
        registroCompra.setEstabelecimento(compraDto.getEstabelecimento());
        registroCompra.setValor(compraDto.getValor());

        regCompraRepository.save(registroCompra);

        // TODO Implementar o envio de comprovante por email
    }

}
