package com.roota.passageirosapi.controller;

import com.roota.passageirosapi.dto.PassageiroRequestDTO;
import com.roota.passageirosapi.dto.PassageiroResponseDTO;
import com.roota.passageirosapi.service.PassageiroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passageiros")
public class PassageiroController {
    private final PassageiroService passageiroService;

    //Construtor
    public PassageiroController(PassageiroService passageiroService) {
        this.passageiroService = passageiroService;
    }

    //Salvar passageiros
    @PostMapping
    public PassageiroResponseDTO salvar(@Valid @RequestBody PassageiroRequestDTO dto) {
        return passageiroService.salvar(dto);
    }

    //Listar os passageiros
    @GetMapping
    public List<PassageiroResponseDTO> listarTodos() {
        return passageiroService.listarTodos();
    }

    //Buscar por Id
    @GetMapping("/{id}")
    public PassageiroResponseDTO buscarPorId(@PathVariable Long id) {
        return passageiroService.buscarPorID(id);
    }

    //Atualizar dados
    @PutMapping("/{id}")
    public PassageiroResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PassageiroRequestDTO dto) {
        return passageiroService.atualizar(id, dto);
    }

    //Deletar passageiro
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        passageiroService.deletar(id);
    }

    //Procurar por nome
    @GetMapping("/buscar")
    public List<PassageiroResponseDTO> buscarPorNome(@RequestParam String nome) {
        return passageiroService.buscarPorNome(nome);
    }
}
