package com.anaskhaloul.bookwise.loan;
import jakarta.validation.constraints.*;
public record LoanRequest(@Positive long bookId,@Positive long memberId) { }
