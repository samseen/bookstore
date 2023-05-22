package com.samseen.book_store_application.repository;

import com.samseen.book_store_application.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book b WHERE " +
                "(b.title LIKE %?1% OR CAST(b.id AS CHAR) LIKE %?1% OR LOWER(b.author) LIKE %?1%) " +
                "AND b.category=?2", nativeQuery = true)
    Page<Book> findAllBookByCategoryAndKeyword(String keyword, int category, Pageable pageable);

    @Query(value = "SELECT IF(SUM(b.sold) IS NULL, 0, SUM(b.sold)) FROM book b WHERE " +
            "(b.title LIKE %?1% OR CAST(b.id AS CHAR) LIKE %?1% OR LOWER(b.author) LIKE %?1%) " +
            "AND b.category=?2 AND b.sold > 0", nativeQuery = true)
    long countNumberOfBooksSold(String keyword, int category);
}
