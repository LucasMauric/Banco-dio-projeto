package com.dio.model;
import com.dio.service.impl.ContaImplService;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public abstract class Banco  {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nomeDoBanco;
        @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Conta> contas = new ArrayList<>();
        public Banco(){

        }

    public List<Conta> getConta() {
        return contas;
    }

    public void setConta(Conta conta) {
        conta.setBanco(conta);
        this.contas.add(conta);
    }
    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
