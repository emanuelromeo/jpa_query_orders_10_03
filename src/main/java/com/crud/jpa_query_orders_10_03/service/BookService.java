package com.crud.jpa_query_orders_10_03.service;

import com.crud.jpa_query_orders_10_03.entity.Book;
import com.crud.jpa_query_orders_10_03.entity.Order;
import com.crud.jpa_query_orders_10_03.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Saves the given book.
     *
     * @param book
     * @return the saved book.
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Selects all books.
     *
     * @return the page of selected books.
     */
    public Page<Book> findAllBooks(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findAll(pageable);
    }

    /**
     * Selects a book by its id.
     *
     * @param id
     * @return an optional containing the book with the given id or Optional.empty() if none found.
     */
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Updates the book with the given id with values from the given updatedBook.
     *
     * @param id
     * @param updatedBook
     * @return an optional containing the updated book or Optional.empty() if none found.
     */
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            optionalBook.get().setTitle(updatedBook.getTitle());
            optionalBook.get().setAuthor(updatedBook.getAuthor());
            optionalBook.get().setPublicationDate(updatedBook.getPublicationDate());

            Book savedBook = bookRepository.save(optionalBook.get());
            return Optional.of(savedBook);
        }

        return Optional.empty();
    }

    /**
     * Soft deletes the book with the given id.
     *
     * @param id
     * @return an optional containing the deleted book or Optional.empty() if none found.
     */
    public Optional<Book> deleteBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            optionalBook.get().setDeleted(true);

            Book savedBook = bookRepository.save(optionalBook.get());
            return Optional.of(savedBook);
        }

        return Optional.empty();
    }

    /**
     * Selects all books published after the given publicationDate or written by the given author.
     *
     * @param publicationDate
     * @param author
     * @return the list of selected books.
     */
    public List<Book> findBooksByPublicationDateAfterOrAuthor(LocalDate publicationDate, String author) {
        return bookRepository.findByPublicationDateAfterOrAuthor(publicationDate, author);
    }

}
