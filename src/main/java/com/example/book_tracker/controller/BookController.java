package com.example.book_tracker.controller;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BookController {

    private final BookService service;
    public BookController(BookService service) { this.service = service; }

    @GetMapping
     @Operation(summary = "Get all books (paginated)", description = "Returns a paginated list of books")
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title
    ) {
        return service.getBooksFiltered(title, PageRequest.of(page, size));
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Adds a new book to the database")
    public Book addBook(@RequestBody Book book) { return service.addBook(book); }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book", description = "Updates the details of an existing book")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) { return service.updateBook(id, book); }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book from the database")
    public void deleteBook(@PathVariable Long id) { service.deleteBook(id); }

    @GetMapping("/search")
    @Operation(summary = "Search books by author", description = "Returns a list of books by the specified author")
    public List<Book> searchByAuthor(@RequestParam String author) { return service.searchByAuthor(author); }
}
