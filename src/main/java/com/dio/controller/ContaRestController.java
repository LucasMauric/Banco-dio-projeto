package com.dio.controller;
import com.dio.model.Banco;
import com.dio.model.Conta;
import com.dio.service.IConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conta")
public class ContaRestController {
    @Autowired
    private IConta contaService;
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getExtrato(@PathVariable long id){
        return  ResponseEntity.ok(contaService.getExtrato(id));
    }

    @PostMapping
    public ResponseEntity<Conta> inserirConta(@RequestBody Conta conta){
        return ResponseEntity.ok( contaService.inserirConta(conta));
    }

    @PostMapping("/banco")
    public ResponseEntity<Banco> inserirBanco(@RequestBody Banco banco){
        return  ResponseEntity.ok(contaService.inserirBanco(banco));
    }
}