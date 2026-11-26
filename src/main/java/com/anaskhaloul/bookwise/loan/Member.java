package com.anaskhaloul.bookwise.loan;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="members", uniqueConstraints=@UniqueConstraint(name="uk_members_email", columnNames="email"))
public class Member {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=100) private String fullName;
    @Column(nullable=false,length=254) private String email;
    @Column(nullable=false,updatable=false) private Instant createdAt;
    protected Member() { }
    public Member(String fullName,String email) { this.fullName=fullName.trim(); this.email=email.trim().toLowerCase(); this.createdAt=Instant.now(); }
    public Long getId(){return id;} public String getFullName(){return fullName;} public String getEmail(){return email;} public Instant getCreatedAt(){return createdAt;}
}
