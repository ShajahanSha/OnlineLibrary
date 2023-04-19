package com.online.library.api;

import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.commandhandler.BookCommandHandler;
import com.online.library.domain.cqrs.result.BookResult;
import com.online.library.domain.model.StatusInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class OnlineBookStoreApplicationTests {

	@Mock
	BookCommandHandler bookCommandHandler;

	@InjectMocks
	OnlineLibraryController storeController;

	@Test
	public void addBook() throws Exception{
		BookCommand command = mockRequest();
		when(bookCommandHandler.handle(command)).thenReturn(mockResponse());
		ResponseEntity<?> response = storeController.addBook(command);
		Assert.assertNotNull(response.getStatusCode().value());
		BookResult result = (BookResult) response.getBody();
		Assert.assertEquals(result.getName(), "Test");
		Assert.assertTrue(response.getStatusCode().value() ==201);
	}

	@Test
	public void invalidName() throws Exception{
		BookCommand command = mockRequest();
		command.setName(null);
		when(bookCommandHandler.handle(command)).thenReturn(mockResponse());
		ResponseEntity<?> response = storeController.addBook(command);
		Assert.assertNotNull(response.getStatusCode().value());
		BookResult result = (BookResult) response.getBody();
		Assert.assertEquals(result.getName(), "Test");
		Assert.assertTrue(response.getStatusCode().value() ==201);
	}

	@Test
	public void updateBook() throws Exception{
		BookCommand command = mockRequest();
		command.setBookId(12);
		BookResult result = mockResponse();
		result.setBookId(12);
		when(bookCommandHandler.handle(command)).thenReturn(result);
		ResponseEntity<?> response = storeController.updateBook(command);
		Assert.assertNotNull(response.getStatusCode().value());
		BookResult bookResult = (BookResult) response.getBody();
		Assert.assertEquals(bookResult.getBookId(), 12);
		Assert.assertTrue(response.getStatusCode().value() ==201);
	}

	@Test
	public void deleteBook() throws Exception{
		BookCommand command = new BookCommand();
		command.setBookId(12);
		BookResult result = mockResponse();
		result.setStatusInfo(new StatusInfo("DELETED"));
		when(bookCommandHandler.handle(command)).thenReturn(result);
		ResponseEntity<?> response = storeController.deleteBook(12);
		//Assert.assertNotNull(response.getStatusCode().value());
		Assert.assertTrue(response.getStatusCode().value() == HttpStatus.NO_CONTENT.value());
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

}
