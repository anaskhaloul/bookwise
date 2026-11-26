package com.anaskhaloul.bookwise.loan;
public class LoanNotFoundException extends RuntimeException { public LoanNotFoundException(long id){super("Loan "+id+" was not found");} }
