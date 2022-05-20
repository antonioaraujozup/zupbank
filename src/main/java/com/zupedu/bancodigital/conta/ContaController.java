package com.zupedu.bancodigital.conta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    Logger logger = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    public ContaRepository contaRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inserir(@RequestBody ContaRequest request){
        logger.info("Cadastrando Conta (agencia = {}, numero = {})", request.getAgencia(), request.getNumero());
        var conta = request.toModel();

        if(contaRepository.findByDocumentoTitular(conta.getDocumentoTitular()).isPresent()){
            logger.warn("Não foi possível cadastrar a conta (agencia = {}, numero = {}) pois já existe outra conta com o CPF informado", request.getAgencia(), request.getNumero());
            return ResponseEntity.badRequest().body("Já existe uma conta com mesmo CPF!");

        }else{
            conta = contaRepository.save(conta);

            logger.info("Conta cadastrada (agencia = {}, numero = {})", request.getAgencia(), request.getNumero());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ContaResponse.from(conta));
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        var conta = contaRepository.findById(id).orElseThrow(ContaIdInexistenteException::new);

        logger.info("Excluindo conta (agencia = {}, numero = {})", conta.getAgencia(), conta.getNumero());

        contaRepository.delete(conta);

        logger.info("Conta (agencia = {}, numero = {}) excluída com sucesso", conta.getAgencia(), conta.getNumero());
    }
}
