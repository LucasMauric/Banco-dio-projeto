package com.dio.service;

import com.dio.model.Banco;
import com.dio.model.Conta;
import com.dio.service.impl.ContaImplService;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface IConta {
     String gerarChavePix();
     Conta inserirConta(Conta conta);
     void sacar(String numeroConta, double saldo);
     void depositar(String numeroConta,Double valor);
     void transferir(Double valor, String chavePix);
     void inserirBanco(Banco banco, long id,Conta conta);

     void getExtrato();


}
