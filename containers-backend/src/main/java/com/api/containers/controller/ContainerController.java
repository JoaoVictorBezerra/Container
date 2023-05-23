package com.api.containers.controller;

import com.api.containers.dtos.ContainerDTO;
import com.api.containers.model.ContainerModel;
import com.api.containers.service.ContainerService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/containers")
public class ContainerController {
    final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping
    public ResponseEntity<Object> saveContainer(@RequestBody @Valid ContainerDTO containerDTO) {
        if (containerService.existsContainer(containerDTO.getContainer())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O Container já está cadastrado!");
        }
        var containerModel = new ContainerModel();
        BeanUtils.copyProperties(containerDTO, containerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(containerService.save(containerModel));
    }

    @GetMapping
    public ResponseEntity<List<ContainerModel>> getAllContainers() {
        return ResponseEntity.status(HttpStatus.OK).body(containerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContainerById(@PathVariable(value = "id") UUID id) {
        Optional<ContainerModel> containerModelOptional = containerService.findById(id);
        if (!containerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(containerModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContainerById(@PathVariable(value = "id") UUID id) {
        Optional<ContainerModel> containerModelOptional = containerService.findById(id);
        if (!containerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container não encontrado");
        }
        containerService.deleteById(containerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Container Deletado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContainer(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContainerDTO containerDTO) {
        Optional<ContainerModel> containerModelOptional = containerService.findById(id);
        if (!containerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Container não encontrado");
        }
        var containerModel = new ContainerModel();
        BeanUtils.copyProperties(containerDTO, containerModel);
        containerModel.setId(containerModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(containerService.save(containerModel));

        // OU
        // var containerModel = containerModelOptional.get();
        // containerModel.setCliente(containerModelOptional.get().getCliente());
        // containerModel.setContainer(containerModelOptional.get().getContainer());
        // containerModel.setTipo(containerModelOptional.get().getTipo());
        // containerModel.setStatus(containerModelOptional.get().getStatus());
        // containerModel.setCategoria(containerModelOptional.get().getCategoria());
        //return ResponseEntity.status(HttpStatus.OK).body(containerService.save(containerModel));
        //// containerModel.setId();
    }
}
