package com.anaskhaloul.bookwise.loan;

import com.anaskhaloul.bookwise.catalog.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;

@Service
public class LoanService {
    private final BookRepository books; private final MemberRepository members; private final LoanRepository loans;
    public LoanService(BookRepository books,MemberRepository members,LoanRepository loans){this.books=books;this.members=members;this.loans=loans;}
    @Transactional public LoanResponse checkout(long bookId,long memberId){
        Book book=books.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        Member member=members.findById(memberId).orElseThrow(()->new MemberNotFoundException(memberId));
        book.checkout(); return LoanResponse.from(loans.save(new Loan(book,member,Instant.now(),LocalDate.now(ZoneOffset.UTC).plusDays(14))));
    }
    @Transactional public LoanResponse returnLoan(long loanId){
        Loan loan=loans.findById(loanId).orElseThrow(()->new LoanNotFoundException(loanId));
        loan.markReturned(Instant.now()); loan.getBook().returnCopy(); return LoanResponse.from(loan);
    }
    @Transactional(readOnly=true) public java.util.List<LoanResponse> activeLoans(long memberId){
        if(!members.existsById(memberId))throw new MemberNotFoundException(memberId);
        return loans.findByMemberIdAndReturnedAtIsNullOrderByDueDateAsc(memberId).stream().map(LoanResponse::from).toList();
    }
    @Transactional(readOnly=true) public java.util.List<LoanResponse> overdue(){
        return loans.findByReturnedAtIsNullAndDueDateBeforeOrderByDueDateAsc(LocalDate.now(ZoneOffset.UTC)).stream().map(LoanResponse::from).toList();
    }
}
