package com.api.containers.dtos;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovimentacaoDTO {

    @NotBlank
    private int idMovimentacao;
    @NotBlank
    @Size(max = 11)
    private String container;
    @NotBlank
    private String tipoMovimentacao;
    @NotBlank
    private LocalDateTime horarioInicio;
    @NotBlank
    private LocalDateTime horarioFinal;
}
