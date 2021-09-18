package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.RegistroCompra;
import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.repository.CartaoRepository;
import br.com.fiap.spring.repository.RegCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private RegCompraRepository regCompraRepository;

    public void verificarCartao(){}

    public void autorizarCompra(CompraDto compraDto){
        // verificar se o cartao existe;
        Optional<Cartao> cartao =
                cartaoRepository.getCartaoByNumero(compraDto.getNumCartao());
        if(cartao.isPresent()){
            // verificar se a senha esta correta;
            if(cartao.get().getSenha().equals(compraDto.getSenha())){
                // realizar compra;
                efetivarCompra(cartao.get(), compraDto);
            } else {
                // TODO tratar 'senha do cartao incorreta'
            }
        } else {
            // TODO tratar 'cartao nao existe'
        }

    }

    public void consultarCompraByCliente(){}

    private void efetivarCompra(Cartao cartao, CompraDto compraDto){
        RegistroCompra registroCompra = new RegistroCompra();

        registroCompra.setIdCartao(cartao);
        registroCompra.setEstabelecimento(compraDto.getEstabelecimento());
        registroCompra.setValor(compraDto.getValor());

        regCompraRepository.save(registroCompra);
    }

}
