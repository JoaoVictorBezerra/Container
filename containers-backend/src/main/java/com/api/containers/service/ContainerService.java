package com.api.containers.service;

import com.api.containers.model.ContainerModel;
import com.api.containers.repositories.ContainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContainerService {
    final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Transactional
    public ContainerModel save(ContainerModel containerModel) {
        return containerRepository.save(containerModel);
    }

    public boolean existsContainer(String container) {
        return containerRepository.existsByContainer(container);
    }

    public List<ContainerModel> findAll() {
        return containerRepository.findAll();
    }

    public Optional<ContainerModel> findById(UUID id) {
        return containerRepository.findById(id);
    }

    @Transactional
    public void deleteById(ContainerModel containerModel) {
        containerRepository.delete(containerModel);
    }
}
