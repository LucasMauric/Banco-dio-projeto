package com.dio.service.impl;
import com.dio.model.*;
import com.dio.service.IConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.random.RandomGenerator;
@Service
public class ContaImplService implements IConta {

    @Autowired
    ContaRepository contaRepository;
    @Autowired
    BancoRepository bancoRepository;
    @Override
    public String gerarChavePix() {
        return RandomGenerator.getDefault().toString();
    }

    @Override
    public Conta inserirConta(Conta conta) {
        if(!contaRepository.existsById(conta.id()))
            conta.setChavePix(gerarChavePix());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public double sacar(Long id , double valor) {
        Optional<Conta> banco =  contaRepository.findById(id);
        double saldoRetirado = 0D;
        if(banco.isPresent()){
            saldoRetirado = banco.get().getSaldo() - valor;
            banco.get().setSaldo(saldoRetirado);
            contaRepository.save(banco.get());
        }else{
            System.out.println("Saldo insuficiente!");
        }
        return saldoRetirado;
    }

    @Override
    public double depositar(Long id , double valor) {
        Optional<Conta> conta = contaRepository.findById(id);
        double saldoDepositar = 0D;
        if(conta.isPresent()){
            saldoDepositar = conta.get().getSaldo() + valor;
            conta.get().setSaldo(saldoDepositar);
            contaRepository.save(conta.get());
        }
        return saldoDepositar;
    }

    @Override
    public double transferir(Long id,double valor, Long idOrigem ) {
        Optional<Conta> contaDestino = contaRepository.findById(id);
        Optional<Conta> contaOrigem = contaRepository.findById(idOrigem);
        double novoSaldo = 0d;
        if(contaDestino.isPresent()){
            double valorContaOrigem = contaOrigem.get().getSaldo() - valor;
            novoSaldo = contaDestino.get().getSaldo() + valor;
            contaOrigem.get().setSaldo(valorContaOrigem);
            contaDestino.get().setSaldo(novoSaldo);
            contaRepository.save(contaDestino.get());
        }else{
            System.out.println("Chave pix inválida!");
        }
        return novoSaldo;
    }

    @Override
    public Banco inserirBanco(Banco banco) {
        Optional<Banco> verificarBanco = bancoRepository.findById(banco.getId());
        return bancoRepository.save(banco);
    }

    @Override
    public Conta getExtrato(long id) {
        Optional<Conta> conta = contaRepository.findById(id);
        return conta.get();
    }
}