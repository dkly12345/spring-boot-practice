package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/books")
public class BookServiceController {

    private final BookService bookService;

    public BookServiceController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return bookService.delete(id)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
    }
}
