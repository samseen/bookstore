package com.samseen.book_store_application.service;

import com.samseen.book_store_application.dto.BookDto;
import com.samseen.book_store_application.dto.SalesDto;
import com.samseen.book_store_application.utils.Category;

import java.util.List;

public interface BookStoreService {
    void addNewBook(BookDto bookDto);

    void addBook(Long id, int quantityToAdd);

    BookDto getBookById(Long id);

    List<BookDto> getAllBooks();

    int getNumberOfBooksById(Long id);

    void updateBook(Long id, BookDto bookDto);

    void sellBook(Long id);

    void sellBooks(List<SalesDto> salesDtos);

    List<BookDto> getBookByCategoryKeyWord(String keyword, Category category);

    int getNumberOfBooksSoldByCategoryAndKeyword(String keyword, Category category);
}
