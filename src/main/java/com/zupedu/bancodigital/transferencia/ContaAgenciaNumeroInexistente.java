package com.zupedu.bancodigital.transferencia;

public class ContaAgenciaNumeroInexistente extends RuntimeException{
    public ContaAgenciaNumeroInexistente() {
        super("Não foi possível encontrar a conta com a agência e número indicados.");
    }
}
