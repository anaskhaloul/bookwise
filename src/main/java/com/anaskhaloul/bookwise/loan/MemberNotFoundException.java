package com.anaskhaloul.bookwise.loan;
public class MemberNotFoundException extends RuntimeException { public MemberNotFoundException(long id){super("Member "+id+" was not found");} }
