package com.example.book_tracker.service;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Book> getAllBooks(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Book> getBooksFiltered(String title, PageRequest pageRequest) {
        if (title != null && !title.isEmpty()) {
            return repository.findByTitleContainingIgnoreCase(title, pageRequest);
        }
        return repository.findAll(pageRequest);
    }
}
