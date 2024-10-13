package com.dio.service.impl;

import com.dio.model.Cliente;
import com.dio.model.ClienteRepository;
import com.dio.model.Endereco;
import com.dio.model.EnderecoRepository;
import com.dio.service.ClienteService;
import com.dio.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteImplService implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> buscar = clienteRepository.findById(id);
        return buscar.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        SalvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> buscar = clienteRepository.findById(id);
        if(buscar.isPresent()){
            SalvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void SalvarClienteComCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        }) ;
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}