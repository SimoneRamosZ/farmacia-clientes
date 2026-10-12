package com.farmacia.api_clientes.service;

import com.farmacia.api_clientes.exception.CpfJaCadastradoException;
import com.farmacia.api_clientes.exception.ClienteNaoEncontradoException;
import com.farmacia.api_clientes.model.Cliente;
import com.farmacia.api_clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new CpfJaCadastradoException(cliente.getCpf());
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente dadosAtualizados) {
        Cliente clienteExistente = buscarPorId(id);

        if (clienteRepository.existsByCpfAndIdNot(dadosAtualizados.getCpf(), id)) {
            throw new CpfJaCadastradoException(dadosAtualizados.getCpf());
        }

        clienteExistente.setNome(dadosAtualizados.getNome());
        clienteExistente.setCpf(dadosAtualizados.getCpf());
        clienteExistente.setTelefone(dadosAtualizados.getTelefone());
        clienteExistente.setEmail(dadosAtualizados.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id); // garante que o cliente existe antes de deletar
        clienteRepository.deleteById(id);
    }
}
