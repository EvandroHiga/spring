package br.com.fiap.spring.service;

import br.com.fiap.spring.model.Cartao;
import br.com.fiap.spring.model.RegistroCompra;
import br.com.fiap.spring.model.dto.CompraDto;
import br.com.fiap.spring.model.dto.ConsultaCompraDto;
import br.com.fiap.spring.repository.RegCompraRepository;
import br.com.fiap.spring.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.fiap.spring.utils.MessageConstants.*;

@Service
public class CompraService {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegCompraRepository regCompraRepository;

    public String autorizarCompra(CompraDto compraDto){
        Cartao cartao = cartaoService.getCartaoByNumero(compraDto.getNumCartao());

        if(cartao != null){
            if(cartao.getSenha().equals(compraDto.getSenha())){
                try{
                    efetivarCompra(cartao, compraDto);
                    return COMPRA_SUCESSO;
                } catch (MailSendException e){
                    return COMPRA_SUCESSO_ERRO_COMPROVANTE;
                }
            } else {
                return CARTAO_SENHA_INCORRETA;
            }
        } else {
            return CARTAO_NAO_EXISTE;
        }
    }

    public List<ConsultaCompraDto> consultarComprasByClienteId(Long id){
        List<ConsultaCompraDto> consultaCompraDtoList = regCompraRepository.findComprasByClienteId(id);
        if(consultaCompraDtoList.isEmpty()){
            return null;
        } else {
            return consultaCompraDtoList;
        }
    }

    private void efetivarCompra(Cartao cartao, CompraDto compraDto) throws MailSendException {
        RegistroCompra registroCompra = new RegistroCompra();

        registroCompra.setCartao(cartao);
        registroCompra.setEstabelecimento(compraDto.getEstabelecimento());
        registroCompra.setValor(compraDto.getValor());

        regCompraRepository.save(registroCompra);

        if(compraDto.getEmail() != null){
            try{
                emailService.sendGmailSimpleMail(
                        compraDto.getEmail(),
                        "[COMPROVANTE] Cartao de Credito FIAP ",
                        EmailUtils.comprovanteCompraParser(registroCompra));
            } catch(MailSendException e){
                throw e;
            }
        }
    }
}
