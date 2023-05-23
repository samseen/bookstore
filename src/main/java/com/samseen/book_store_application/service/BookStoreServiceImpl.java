package com.samseen.book_store_application.service;

import com.samseen.book_store_application.dto.BookDto;
import com.samseen.book_store_application.entity.Book;
import com.samseen.book_store_application.exceptions.DuplicateResourceException;
import com.samseen.book_store_application.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
public class BookStoreServiceImpl implements BookStoreService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookStoreServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * Register new book with new identifier into database
     * Save the book In Book Domain
     * Maintain the count in BookCount Domain
     *
     * @param bookDto
     */
    @Override
    @Transactional
    public void addNewBook(BookDto bookDto) {
        Optional<Book> bookById = bookRepository.findById(bookDto.getId());
        bookById.ifPresent(book -> {
            throw new DuplicateResourceException("Book with the same Id is already present. " +
                    " Either use update methods to update the book counts or use addBook(Long id, int quantityToAdd) methods");
        });
        if (!bookById.isPresent()) {
            log.info("No duplicates found!");
            //Map bookDTO to book
            Book book = modelMapper.map(bookDto, Book.class);

            bookRepository.save(book);
        }
    }
}
