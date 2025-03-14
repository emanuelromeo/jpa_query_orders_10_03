package com.crud.jpa_query_orders_10_03.repository;

import com.crud.jpa_query_orders_10_03.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPublicationDateAfterOrAuthor(LocalDate publicationDate, String author);
}
