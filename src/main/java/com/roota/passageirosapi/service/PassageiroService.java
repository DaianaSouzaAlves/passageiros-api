package com.roota.passageirosapi.service;

import com.roota.passageirosapi.dto.PassageiroRequestDTO;
import com.roota.passageirosapi.dto.PassageiroResponseDTO;
import com.roota.passageirosapi.entity.Passageiro;
import com.roota.passageirosapi.repository.PassageiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassageiroService {
    //Armazenar o acesso ao banco
    private final PassageiroRepository repository;

    //Construtor
    public PassageiroService(PassageiroRepository repository) {
        this.repository = repository;
    }


    //Receber os dados e salvar
    public PassageiroResponseDTO salvar(PassageiroRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Passageiro passageiro = new Passageiro();

        passageiro.setNome(dto.getNome());
        passageiro.setEmail(dto.getEmail());
        passageiro.setMatricula(dto.getMatricula());

        Passageiro salvo = repository.save(passageiro);

        return new PassageiroResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getMatricula()
        );
    }


    //Buscar dados e listar
    public List<PassageiroResponseDTO> listarTodos() {

        List<Passageiro> passageiros = repository.findAll();

        return passageiros.stream()
                .map(passageiro -> new PassageiroResponseDTO(
                        passageiro.getId(),
                        passageiro.getNome(),
                        passageiro.getEmail(),
                        passageiro.getMatricula()
                ))
                .collect(Collectors.toList());
    }


    //Procurar pessoas por Id
    public PassageiroResponseDTO buscarPorID(Long id) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        return new PassageiroResponseDTO(
                passageiro.getId(),
                passageiro.getNome(),
                passageiro.getEmail(),
                passageiro.getMatricula()
        );
    }


    //Deletar por Id
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Passageiro não encontrado");
        }
        repository.deleteById(id);
    }

    //Atualizar dados
    public PassageiroResponseDTO atualizar(Long id, PassageiroRequestDTO dto) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        passageiro.setNome(dto.getNome());
        passageiro.setEmail(dto.getEmail());
        passageiro.setMatricula(dto.getMatricula());

        Passageiro atualizado = repository.save(passageiro);

        return new PassageiroResponseDTO(
                atualizado.getId(),
                passageiro.getNome(),
                passageiro.getEmail(),
                passageiro.getMatricula()
        );
    }


    //Procurar por nome
    public List<PassageiroResponseDTO> buscarPorNome(String nome) {

        List<Passageiro> passageiros =
                repository.findByNomeContainingIgnoreCase(nome);
        if (passageiros.isEmpty()) {
            throw new RuntimeException("Passageiro não encontrado");
        }
        return passageiros.stream()
                .map(passageiro -> new PassageiroResponseDTO(
                        passageiro.getId(),
                        passageiro.getNome(),
                        passageiro.getEmail(),
                        passageiro.getMatricula()
                ))
                .collect(Collectors.toList());
    }
}
