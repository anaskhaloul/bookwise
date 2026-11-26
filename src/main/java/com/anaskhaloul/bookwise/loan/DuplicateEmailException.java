package com.anaskhaloul.bookwise.loan;
public class DuplicateEmailException extends RuntimeException { public DuplicateEmailException(String email){super("A member with email "+email+" already exists");} }
