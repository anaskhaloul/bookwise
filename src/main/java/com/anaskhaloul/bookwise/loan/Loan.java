package com.anaskhaloul.bookwise.loan;

import com.anaskhaloul.bookwise.catalog.Book;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="loans", indexes={@Index(name="idx_loans_due_date",columnList="due_date"),@Index(name="idx_loans_member_status",columnList="member_id,returned_at")})
public class Loan {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="book_id",nullable=false) private Book book;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="member_id",nullable=false) private Member member;
    @Column(nullable=false,updatable=false) private Instant checkedOutAt;
    @Column(nullable=false) private LocalDate dueDate;
    private Instant returnedAt;
    @Version private long version;
    protected Loan() { }
    public Loan(Book book,Member member,Instant checkedOutAt,LocalDate dueDate){this.book=book;this.member=member;this.checkedOutAt=checkedOutAt;this.dueDate=dueDate;}
    public void markReturned(Instant at){if(returnedAt!=null)throw new IllegalStateException("This loan has already been returned");returnedAt=at;}
    public boolean isOverdue(LocalDate today){return returnedAt==null && dueDate.isBefore(today);}
    public Long getId(){return id;} public Book getBook(){return book;} public Member getMember(){return member;} public Instant getCheckedOutAt(){return checkedOutAt;} public LocalDate getDueDate(){return dueDate;} public Instant getReturnedAt(){return returnedAt;}
}
