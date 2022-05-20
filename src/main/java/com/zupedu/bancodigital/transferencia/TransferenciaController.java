package com.zupedu.bancodigital.transferencia;

import com.zupedu.bancodigital.conta.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    Logger logger = LoggerFactory.getLogger(TransferenciaController.class);

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    public TransferenciaResponse inserir(@Valid @RequestBody TransferenciaRequest request){
        logger.info("Inserindo transferência: Origem [agencia = {}, numero = {}], Destino [agencia = {}, numero = {}], Valor: {}",
                request.getContaOrigem().getAgencia(), request.getContaOrigem().getNumero(),
                request.getContaDestino().getAgencia(), request.getContaDestino().getNumero(),
                request.getValor());

        var transferencia = request.toModel(contaRepository);

        transferenciaRepository.save(transferencia);

        logger.info("Transferência realizada com sucesso: Origem [agencia = {}, numero = {}], Destino [agencia = {}, numero = {}], Valor: {}",
                request.getContaOrigem().getAgencia(), request.getContaOrigem().getNumero(),
                request.getContaDestino().getAgencia(), request.getContaDestino().getNumero(),
                request.getValor());

        return TransferenciaResponse.from(transferencia);
    }
}
