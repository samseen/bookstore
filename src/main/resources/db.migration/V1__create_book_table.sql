CREATE TABLE book
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(60),
    author      VARCHAR(60),
    category    VARCHAR(60),
    price       NUMERIC(10, 2),
    total_count integer,
    sold        integer,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX index_book_title ON book (title);
CREATE INDEX index_book_author ON book (author);
CREATE INDEX index_category ON book (category);
CREATE INDEX index_book_created_at ON book (created_at);