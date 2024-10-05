package com.dio.service.impl;
import com.dio.model.Banco;
import com.dio.model.BancoRepository;
import com.dio.model.Conta;
import com.dio.model.ContaRepository;
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
        if(!contaRepository.existsById(conta.getNumeroConta()))
            conta.setChavePix(gerarChavePix());
        contaRepository.save(conta);
        return conta;
    }

    @Override
    public void sacar(String numeroConta, double valor) {
        Optional<Conta> banco =  contaRepository.findById(numeroConta);
        if(banco.isPresent()){
            double saldoRetirado = banco.get().getSaldo() - valor;
            banco.get().setSaldo(saldoRetirado);
            contaRepository.save(banco.get());
        }else{
            System.out.println("Saldo insuficiente!");
        }

    }

    @Override
    public void depositar(String  numeroConta , Double valor) {
        Optional<Conta> conta = contaRepository.findById(numeroConta);
        if(conta.isPresent()){
            double saldoDepositar = conta.get().getSaldo() + valor;
            conta.get().setSaldo(saldoDepositar);
            contaRepository.save(conta.get());
        }
    }

    @Override
    public void transferir(Double valor, String chavePix) {
        Optional<Conta> contaDestino = contaRepository.findById(chavePix);
        if(contaDestino.isPresent()){
            double novoSaldo = contaDestino.get().getSaldo() + valor;
            contaDestino.get().setSaldo(novoSaldo);
            contaRepository.save(contaDestino.get());
        }else{
            System.out.println("Chave pix inválida!");
        }
    }

    @Override
    public void inserirBanco(Banco banco, long id, Conta conta) {
        Optional<Banco> verificarBanco = bancoRepository.findById(id);
        if( verificarBanco.isEmpty()){
            Conta novaConta = inserirConta(conta);
            banco.setConta(novaConta);
            bancoRepository.save(banco);
        }
    }

    @Override
    public void getExtrato() {

    }

}