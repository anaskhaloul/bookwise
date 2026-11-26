package com.anaskhaloul.bookwise.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    @Query("select coalesce(sum(b.availableCopies), 0) from Book b")
    long countAvailableCopies();
    @Query("select b from Book b where lower(b.title) like lower(concat('%', :q, '%')) or lower(b.author) like lower(concat('%', :q, '%')) or b.isbn like concat('%', :q, '%')")
    Page<Book> search(@Param("q") String query, Pageable pageable);
}
