package com.online.library.domain.service.impl;

import com.online.library.domain.constants.enums.ServiceType;
import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.exception.BusinessException;
import com.online.library.domain.cqrs.exception.ErrorCode;
import com.online.library.domain.cqrs.query.BookQuery;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.cqrs.result.EntityConverter;
import com.online.library.domain.model.StatusInfo;
import com.online.library.domain.repos.bo.BookBO;
import com.online.library.domain.repos.jpa.BookRepository;
import com.online.library.domain.service.LibraryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Service("bookService")
public class LibraryServiceImpl implements LibraryService {

    protected static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
    //private final Gson gsonObj = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    @Autowired
    private BookRepository bookRepository;

    public LibraryServiceImpl() {
    }

    @Override
    @Transactional
    public BookResult processBook(BookCommand command) throws BusinessException {
        logger.debug("BookServiceImpl | processBook |");
        BookResult bookResult = new BookResult();
        if (ServiceType.CREATE.getValue().equalsIgnoreCase(command.getServiceType())) {
            return addBook(command);
        } else if (ServiceType.UPDATE.getValue().equalsIgnoreCase(command.getServiceType())) {
            return updateBook(command);
        } else if (ServiceType.DELETE.getValue().equalsIgnoreCase(command.getServiceType())) {
            return deleteBook(command);
        }
        logger.debug("BookServiceImpl | processBook | INVALID_SERVICE");
        throw new BusinessException(new ErrorCode("INVALID_SERVICE", "Invalid serviceType"));
    }

    @Override
    public BookResult fetchBooks(BookQuery query) throws BusinessException {
        logger.debug("BookServiceImpl | fetchBooks |");
        StringBuilder error = new StringBuilder();
        if (null != query.getBookId()) {
            Optional<BookBO> bookBO = bookRepository.findById(query.getBookId());
            if (bookBO.isPresent()) {
                BookBO bo = bookBO.get();
                return mapResult(bo);
            }
            error.append("Invalid BookId");
        }
        logger.debug("BookServiceImpl | fetchBooks | NO_DATA_FOUND");
        throw new BusinessException(new ErrorCode("INVALID_DATA_FOUND", error.toString()));
    }

    @Override
    public Page<BookResult> fetchBooks(BookQuery query, Pageable pageable) throws BusinessException {
        logger.debug("BookServiceImpl | fetchBooks |");
        StringBuilder error = new StringBuilder();
        Page<BookBO> bookBOs = fetchData(query, pageable);
        logger.debug("BookServiceImpl | fetchBooks | bookBOs-{}", bookBOs);
        List<BookResult> resultList = new ArrayList<>();
        for (BookBO bo : bookBOs) {
            BookResult result = mapResult(bo);
            resultList.add(result);
        }
        if (null == bookBOs || bookBOs.isEmpty()) {
            throw new BusinessException(new ErrorCode("NO_DATA", "No data found"));
        }
        return PageableExecutionUtils.getPage(resultList, pageable, () -> bookBOs.getTotalElements());
    }

    private Page<BookBO> fetchData(BookQuery query, Pageable pageable) {
        if (StringUtils.isNotEmpty(query.getName()) && StringUtils.isNotEmpty(query.getAuthor())) {
            return bookRepository.findByNameAndAuthorEquals(query.getName(), query.getAuthor(), pageable);
        } else if (StringUtils.isNotEmpty(query.getName())) {
            return bookRepository.findByNameEquals(query.getName(), pageable);
        } else if (StringUtils.isNotEmpty(query.getAuthor())) {
            return bookRepository.findByAuthorEquals(query.getAuthor(), pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

    private BookResult mapResult(BookBO bo) {
        return BookResult.builder().bookId(bo.getId())
                .name(bo.getName())
                .description(bo.getDescription())
                .classification(bo.getClassification())
                .price(bo.getPrice())
                .isbn(bo.getIsbn())
                .author(bo.getAuthor())
                .build();
    }

    private BookResult addBook(BookCommand command) throws BusinessException {
        logger.debug("BookServiceImpl | addBook |");
        BookResult bookResult = null;
        BookBO bo = new BookBO();
        bo.setName(command.getName());
        bo.setDescription(command.getDescription());
        bo.setAuthor(command.getAuthor());
        bo.setClassification(command.getClassification());
        bo.setPrice(command.getPrice());
        bo.setIsbn(command.getIsbn());
        BookBO saveBO = bookRepository.save(bo);
        logger.debug("BookServiceImpl | addBook | Book saved");
        command.setBookId(null != saveBO ? saveBO.getId() : 0);
        bookResult = EntityConverter.buildResult(command);
        return bookResult;
    }

    private BookResult updateBook(BookCommand command) throws BusinessException {
        logger.debug("BookServiceImpl | updateBook |");
        Optional<BookBO> bookBO = bookRepository.findById(command.getBookId());
        if (bookBO.isPresent()) {
            BookResult result = EntityConverter.buildResult(command);
            result.getStatusInfo().setStatus("UPDATED");
            return result;
        }
        logger.debug("BookServiceImpl | updateBook | INVALID_BOOK_DETAILS");
        throw new BusinessException(new ErrorCode("INVALID_BOOK_DETAILS", "Invalid Book details"));
    }

    private BookResult deleteBook(BookCommand command) throws BusinessException {
        logger.debug("BookServiceImpl | deleteBook | ");
        Optional<BookBO> bookBO = bookRepository.findById(command.getBookId());
        if (!bookBO.isPresent()) {
            logger.debug("BookServiceImpl | deleteBook | INVALID_BOOK_DETAILS");
            throw new BusinessException(new ErrorCode("INVALID_BOOK_DETAILS", "Invalid Book details"));
        }
        bookRepository.deleteById(command.getBookId());
        logger.debug("BookServiceImpl | deleteBook | Book deleted");
        return BookResult.builder()
                .statusInfo(new StatusInfo("DELETED")).build();
    }

}
