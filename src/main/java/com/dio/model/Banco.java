package com.dio.model;
import jakarta.persistence.*;

@Entity
public class Banco  {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nomeDoBanco;
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
