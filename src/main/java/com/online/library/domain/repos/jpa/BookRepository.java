package com.online.library.domain.repos.jpa;

import com.online.library.domain.repos.bo.BookBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */
public interface BookRepository extends JpaRepository<BookBO, Long> {

    Page<BookBO> findByNameEquals(String bookName, Pageable pageable);
    Page<BookBO> findByNameAndAuthorEquals(String bookName, String author, Pageable pageable);
    Page<BookBO> findByAuthorEquals(String author, Pageable pageable);
    @Query(value = "SELECT u FROM BookBO u ORDER BY id")
    Page<BookBO> findAllWithPagination(Pageable pageable);

}
