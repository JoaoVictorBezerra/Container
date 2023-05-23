package com.api.containers.repositories;

import com.api.containers.model.ContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ContainerRepository extends JpaRepository<ContainerModel, UUID> {
    boolean existsByContainer(String container);
}
