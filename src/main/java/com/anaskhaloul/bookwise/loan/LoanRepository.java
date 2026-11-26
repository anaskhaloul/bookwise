package com.anaskhaloul.bookwise.loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByMemberIdAndReturnedAtIsNullOrderByDueDateAsc(long memberId);
    List<Loan> findByReturnedAtIsNullAndDueDateBeforeOrderByDueDateAsc(java.time.LocalDate date);
}
