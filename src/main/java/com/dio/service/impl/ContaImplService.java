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
    public void sacar(Long id , double valor) {
        Optional<Conta> banco =  contaRepository.findById(id);
        if(banco.isPresent()){
            double saldoRetirado = banco.get().getSaldo() - valor;
            banco.get().setSaldo(saldoRetirado);
            contaRepository.save(banco.get());
        }else{
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
    public void depositar(Long id , Double valor) {
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent()){
            double saldoDepositar = conta.get().getSaldo() + valor;
            conta.get().setSaldo(saldoDepositar);
            contaRepository.save(conta.get());
        }
    }

    @Override
    public void transferir(Double valor, Long id) {
        Optional<Conta> contaDestino = contaRepository.findById(id);
        if(contaDestino.isPresent()){
            double novoSaldo = contaDestino.get().getSaldo() + valor;
            contaDestino.get().setSaldo(novoSaldo);
            contaRepository.save(contaDestino.get());
        }else{
            System.out.println("Chave pix inválida!");
        }
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