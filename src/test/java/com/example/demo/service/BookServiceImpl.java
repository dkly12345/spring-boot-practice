package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> mapper.map(book, BookDto.class));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        return mapper.map(bookRepository.save(book), BookDto.class);
    }

    @Override
    public Optional<BookDto> update(Long id, BookDto bookDto) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(bookDto.getTitle());
            existing.setAuthor(bookDto.getAuthor());
            return mapper.map(bookRepository.save(existing), BookDto.class);
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) return false;
        bookRepository.deleteById(id);
        return true;
    }
}
