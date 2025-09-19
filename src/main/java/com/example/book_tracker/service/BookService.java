package com.example.book_tracker.service;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) { this.repository = repository; }

    public List<Book> getAllBooks() { return repository.findAll(); }
    public Book addBook(Book book) { return repository.save(book); }
    public Book updateBook(Long id, Book bookDetails) {
        Book book = repository.findById(id).orElseThrow();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setRead(bookDetails.isRead());
        return repository.save(book);
    }
    public void deleteBook(Long id) { repository.deleteById(id); }
    public List<Book> searchByAuthor(String author) { return repository.findByAuthorContainingIgnoreCase(author); }
}
