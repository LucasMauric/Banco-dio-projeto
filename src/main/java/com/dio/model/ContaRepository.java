package com.dio.model;


import com.dio.service.impl.ContaImplService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends CrudRepository<Conta,Long>{
}
