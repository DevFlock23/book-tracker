package com.example.book_tracker.service;

import com.example.book_tracker.model.Book;
import com.example.book_tracker.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private final BookRepository repository = mock(BookRepository.class);
    private final BookService service = new BookService(repository);

    @Test
    void testGetBooksFiltered_withTitle() {
        Book book = new Book("Summersnow", "Author", true);
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.findByTitleContainingIgnoreCase("summer", pageRequest))
                .thenReturn(new PageImpl<>(Arrays.asList(book)));

        Page<Book> result = service.getBooksFiltered("summer", pageRequest);

        assertEquals(1, result.getTotalElements());
        assertEquals("Summersnow", result.getContent().get(0).getTitle());
    }

    @Test
    void testGetBooksFiltered_withoutTitle() {
        Book book = new Book("Wintermoon", "Author", false);
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(Arrays.asList(book)));

        Page<Book> result = service.getBooksFiltered("", pageRequest);

        assertEquals(1, result.getTotalElements());
        assertEquals("Wintermoon", result.getContent().get(0).getTitle());
    }

    @Test
    void testAddBook() {
        Book book = new Book("Test", "Tester", false);
        when(repository.save(book)).thenReturn(book);

        Book result = service.addBook(book);

        assertEquals("Test", result.getTitle());
        assertEquals("Tester", result.getAuthor());
        assertFalse(result.isRead());
    }

    @Test
    void testUpdateBook() {
        Book existing = new Book("Old", "Author", false);
        existing.setTitle("Old");
        existing.setAuthor("Author");
        existing.setRead(false);

        Book updated = new Book("New", "Author2", true);

        when(repository.findById(anyLong())).thenReturn(Optional.of(existing));
        when(repository.save(any(Book.class))).thenReturn(updated);

        Book result = service.updateBook(1L, updated);

        assertEquals("New", result.getTitle());
        assertEquals("Author2", result.getAuthor());
        assertTrue(result.isRead());
    }
}