package com.anaskhaloul.bookwise.catalog;

import jakarta.validation.constraints.*;

public record BookRequest(@NotBlank @Size(max = 200) String title,
                          @NotBlank @Size(max = 160) String author,
                          @NotBlank @Pattern(regexp = "[0-9Xx-]{10,17}", message = "must be a valid ISBN format") String isbn,
                          @Min(1) @Max(10000) int copies) { }
