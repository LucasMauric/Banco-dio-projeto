package com.dio.service;

import com.dio.model.Banco;
import com.dio.model.Conta;
import com.dio.service.impl.ContaImplService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public interface IConta {
     String gerarChavePix();
     Conta inserirConta(Conta conta);
     void sacar(Long id, double saldo);
     void depositar(Long id,Double valor);
     void transferir(Double valor, Long id);
     Banco inserirBanco(Banco banco);
     Conta getExtrato(long id);


}
