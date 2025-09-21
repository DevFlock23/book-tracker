package com.example.book_tracker.service;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository repository;
    public BookService(BookRepository repository) { this.repository = repository; }

    public List<Book> getAllBooks() { return repository.findAll(); }
    
    public Book addBook(Book book) {
        logger.debug("Adding book: {}", book);
        return repository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = repository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setRead(bookDetails.isRead());
        logger.debug("Updating book with id {}: {}", id, bookDetails);
        return repository.save(book);
    }
    public void deleteBook(Long id) {
        logger.debug("Deleting book with id {}", id);
        repository.deleteById(id);
    }

    public List<Book> searchByAuthor(String author) {
        logger.debug("Searching books by author: {}", author);
        return repository.findByAuthorContainingIgnoreCase(author);
    }

    public Page<Book> getAllBooks(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Book> getBooksFiltered(String title, PageRequest pageRequest) {
        if (title != null && !title.isEmpty()) {
            logger.debug("Filtering books by title: {}", title);
            return repository.findByTitleContainingIgnoreCase(title, pageRequest);
        }
        logger.debug("Fetching all books (no filter)");
        return repository.findAll(pageRequest);
    }

    public Book getBookById(Long id) {
        logger.debug("Fetching book with id {}", id);
        return repository.findById(id).orElseThrow();
    }
}
