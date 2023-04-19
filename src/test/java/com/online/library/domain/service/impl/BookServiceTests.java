package com.online.library.domain.service.impl;

import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.repos.bo.BookBO;
import com.online.library.domain.repos.jpa.BookRepository;
import com.online.library.domain.service.LibraryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BookServiceTests {

	@InjectMocks
	LibraryService bookService = new LibraryServiceImpl();

	@Mock
	BookRepository bookRepository;

	@Test
	public void addBook() throws Exception{
		BookCommand command = mockRequest();
		BookBO bo = mockBo(command);
		bo.setId(12);
		BookBO savedBO = new BookBO();
		savedBO.setId(12);
		Mockito.when(bookRepository.save(bo)).thenReturn(savedBO);
		BookResult result = bookService.processBook(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertEquals(result.getName(), "Test");
	}

	@Test
	public void invalidName() throws Exception{
		BookCommand command = mockRequest();
		command.setName(null);
		BookBO bo = mockBo(command);
		bo.setId(12);
		BookBO savedBO = new BookBO();
		savedBO.setId(12);
		Mockito.when(bookRepository.save(bo)).thenReturn(savedBO);
		BookResult result = bookService.processBook(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertNotEquals(result.getName(), "Test");
	}

	private BookCommand mockRequest() {
		return BookCommand.builder().name("Test")
				.description("Test")
				.author("Shajahan")
				.classification("comic")
				.price(new BigDecimal("100"))
				.isbn("wee")
				.serviceType("CREATE")
				.build();
	}

	private BookResult mockResponse() {
		return BookResult.builder().name("Test")
				.description("Test")
				.author("Shajahan")
				.classification("comic")
				.price(new BigDecimal("100"))
				.isbn("wee")
				.build();
	}

	private BookBO mockBo(BookCommand command) {
		BookBO bo = new BookBO();
		bo.setName(command.getName());
		bo.setDescription(command.getDescription());
		bo.setAuthor(command.getAuthor());
		bo.setClassification(command.getClassification());
		bo.setPrice(command.getPrice());
		bo.setIsbn(command.getIsbn());
		return bo;
	}

}
