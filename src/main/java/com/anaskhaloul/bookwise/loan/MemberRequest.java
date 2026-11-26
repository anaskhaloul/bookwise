package com.anaskhaloul.bookwise.loan;
import jakarta.validation.constraints.*;
public record MemberRequest(@NotBlank @Size(max=100) String fullName,@NotBlank @Email @Size(max=254) String email) { }
