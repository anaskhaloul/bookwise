package com.anaskhaloul.bookwise.loan;

import java.time.Instant;
import java.time.LocalDate;

public record LoanResponse(Long id, Long bookId, String bookTitle, Long memberId,
                           String memberName, Instant checkedOutAt, LocalDate dueDate,
                           Instant returnedAt) {
    public static LoanResponse from(Loan loan) {
        return new LoanResponse(loan.getId(), loan.getBook().getId(), loan.getBook().getTitle(),
                loan.getMember().getId(), loan.getMember().getFullName(), loan.getCheckedOutAt(),
                loan.getDueDate(), loan.getReturnedAt());
    }
}
