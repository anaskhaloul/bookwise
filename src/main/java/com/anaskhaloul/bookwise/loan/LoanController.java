package com.anaskhaloul.bookwise.loan;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1")
public class LoanController {
    private final LoanService service; private final LoanRepository loans; private final MemberRepository members;
    public LoanController(LoanService service,LoanRepository loans,MemberRepository members){this.service=service;this.loans=loans;this.members=members;}
    @PostMapping("/members") public ResponseEntity<Member> register(@Valid @RequestBody MemberRequest request){
        if(members.existsByEmail(request.email().trim().toLowerCase()))throw new DuplicateEmailException(request.email());
        Member member=members.save(new Member(request.fullName(),request.email())); return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
    @PostMapping("/loans") public ResponseEntity<LoanResponse> checkout(@Valid @RequestBody LoanRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.checkout(request.bookId(),request.memberId()));}
    @PostMapping("/loans/{id}/return") public LoanResponse returnBook(@PathVariable long id){return service.returnLoan(id);}
    @GetMapping("/members/{id}/loans") public java.util.List<LoanResponse> activeLoans(@PathVariable long id){return service.activeLoans(id);}
    @GetMapping("/loans/overdue") public java.util.List<LoanResponse> overdue(){return service.overdue();}
}
