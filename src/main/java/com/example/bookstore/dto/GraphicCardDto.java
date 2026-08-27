package com.example.bookstore.dto;

import jakarta.validation.constraints.*;

public record GraphicCardDto(

        @NotBlank
        @Size(max = 150)
        String marca,

        @NotBlank
        @Size(max = 100)
        String memoria,

        @NotBlank
        @Size(max = 100)
        String modelo,

        @NotNull
        @Min(1450)
        @Max(2100)
        Integer anoModelo

) {
}
