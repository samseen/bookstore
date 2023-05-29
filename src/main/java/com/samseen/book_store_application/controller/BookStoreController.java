package com.samseen.book_store_application.controller;

import com.samseen.book_store_application.config.ApplicationConfig;
import com.samseen.book_store_application.core.response.Result;
import com.samseen.book_store_application.dto.BookDto;
import com.samseen.book_store_application.dto.SalesDto;
import com.samseen.book_store_application.service.BookStoreService;
import com.samseen.book_store_application.utils.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the bookstore projects
 * Acceptance Criterias:
 * 1)Add a book
 * 2)get books by Id
 * 3)get all books
 * 4)get number of books available by Id
 * 5)update a book
 * 6)sell a book
 * 7)sell a list of books
 * 8)get book(s) by category/keywords
 * 9)get number of books sold per category/keyword
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Bookstore Controller", description = "Bookstore REST Endpoints.")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    /**
     * AC:  1)Add a book
     * This add new book with new Identifier.
     *
     * @param bookDto
     */
    @ApiOperation(value = "Add New Book")
    @PostMapping("/add-new-book")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Result<BookDto> addNewBook(@Valid @RequestBody BookDto bookDto) {
        return this.bookStoreService.addNewBook(bookDto);
    }

    /**
     * AC: 1)Add a book
     * This method add quantity of book to the books which are already registered.
     *
     * @param id
     * @param quantityToAdd
     */
    @ApiOperation(value = "Add books")
    @PutMapping("/add-book/{id}/{quantityToAdd}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Result<BookDto> addBook(@PathVariable Long id,
                        @PathVariable int quantityToAdd) {
        return this.bookStoreService.addBook(id, quantityToAdd);
    }

    /**
     * AC: 2)get books by id
     *
     * @param id
     * @return bookDto
     */
    @ApiOperation(value = "Get Book By Id")
    @GetMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Result<BookDto> getBookById(@PathVariable Long id) {
        return this.bookStoreService.getBookById(id);
    }


    /**
     * AC: 3)Get All Books
     *
     * @return List<BookDto>
     */
    @ApiOperation(value = "Get All Books")
    @GetMapping("/book-list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Result<List<BookDto>> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    /**
     * AC: 4) Get number of books available by id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get Number of books by Id")
    @GetMapping("/number-of-books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public int getNumberOfBooksById(@PathVariable Long id) {
        return bookStoreService.getNumberOfBooksById(id);
    }

    /**
     * AC: 5) Update a book.
     */
    @ApiOperation(value = "Update a book")
    @PutMapping("/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id,
                           @Valid @RequestBody BookDto bookDto) {
        bookStoreService.updateBook(id, bookDto);
    }

    /**
     * AC: 6) Sell a single book
     *
     * @param id
     */
    @ApiOperation(value = "Sell a book")
    @PutMapping("/sell-book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void sellBook(@PathVariable Long id) {
        bookStoreService.sellBook(id);
    }

    /**
     * AC: 7) Sell list of books
     * SellDto has both book identifier and quantity that bookstore is selling
     *
     * @param sellDto
     */
    @ApiOperation(value = "Sell List of Books.")
    @PutMapping("/sell-books")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public void sellBooks(@Valid @RequestBody List<SalesDto> sellDto) {
        bookStoreService.sellBooks(sellDto);
    }

    /**
     * AC:8 Get Books by category/keyword
     *
     * @param category Respresnts Different Category of book. EG: NONFICTION, ACTION, ETC. Check the category enum
     * @param keyword  Assuming keyword to be any search value. This will be used to search on title, author or id of the book
     * @return
     */
    @ApiOperation(value = "Get Book by Category and Keyword")
    @GetMapping("/books")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<BookDto> getBookByCategoryKeyWord(@RequestParam String keyword,
                                                  @RequestParam Category category) {
        return bookStoreService.getBookByCategoryKeyWord(keyword, category);
    }

    /**
     * AC:9 Get Number of books sold per category/keyword
     *
     * @param keyword
     * @param category
     * @return
     */
    @ApiOperation(value = "Get Number of Books Sold Per Category/Keyword")
    @GetMapping("/number-of-books")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public int getNumberOfBooksSoldByCategoryAndKeyword(@RequestParam String keyword,
                                                        @RequestParam Category category) {
        return bookStoreService.getNumberOfBooksSoldByCategoryAndKeyword(keyword, category);
    }

}
