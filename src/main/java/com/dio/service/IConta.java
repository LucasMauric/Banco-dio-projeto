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
     double sacar(Long id, double saldo);
     double depositar(Long id,double valor);
     double transferir(Long id,double valor);
     Banco inserirBanco(Banco banco);
     Conta getExtrato(long id);
}
