package com.dio.model;

import jakarta.persistence.*;

@Entity
public class Conta extends Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String numeroConta;
    private String agencia;
    private double saldo;
    private String chavePix;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    public void setId(long id) {
        this.id = id;
    }
    public long id(){
        return this.id;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
