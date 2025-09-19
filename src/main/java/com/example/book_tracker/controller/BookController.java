package com.example.book_tracker.controller;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    @GetMapping
    public List<Book> getBooks() { return service.getAllBooks(); }

    @PostMapping
    public Book addBook(@RequestBody Book book) { return service.addBook(book); }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) { return service.updateBook(id, book); }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) { service.deleteBook(id); }

    @GetMapping("/search")
    public List<Book> searchByAuthor(@RequestParam String author) { return service.searchByAuthor(author); }
}
