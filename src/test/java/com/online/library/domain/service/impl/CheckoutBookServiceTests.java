package com.online.library.domain.service.impl;

import com.online.library.domain.cqrs.command.BookCommand;
import com.online.library.domain.cqrs.command.CheckoutBookCommand;
import com.online.library.domain.cqrs.result.CheckoutBookResult;
import com.online.library.domain.service.CheckoutService;
import com.online.library.domain.service.DiscountRules;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CheckoutBookServiceTests {

	@InjectMocks
	CheckoutService checkoutBookService = new CheckoutServiceImpl();

	/*@Mock
	List<DiscountRules> discountRulesList;*/

	@Test
	public void addBook() throws Exception{
		CheckoutBookCommand command = mockRequest();
		List<DiscountRules> discountRulesList = new ArrayList<>();
		discountRulesList.add(new ComicDiscountRule());
		discountRulesList.add(new FictionDiscountRule());
		//Mockito.when(checkoutBookService.getRules()).thenReturn(discountRulesList);
		CheckoutBookResult result = checkoutBookService.processCheckout(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertEquals(result.getTotalCheckoutAmount(), new BigDecimal("600"));
		Assert.assertEquals(result.getDiscountAmount(), new BigDecimal("10"));
	}

	@Test
	public void discountNotMatched() throws Exception{
		CheckoutBookCommand command = mockRequest();
		List<DiscountRules> discountRulesList = new ArrayList<>();
		discountRulesList.add(new ComicDiscountRule());
		discountRulesList.add(new FictionDiscountRule());
		//Mockito.when(checkoutBookService.getRules()).thenReturn(discountRulesList);
		CheckoutBookResult result = checkoutBookService.processCheckout(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertEquals(result.getTotalCheckoutAmount(), new BigDecimal("600"));
		Assert.assertNotEquals(result.getDiscountAmount(), new BigDecimal("20"));
	}

	@Test
	public void checkoutAmountNotMatched() throws Exception{
		CheckoutBookCommand command = mockRequest();
		List<DiscountRules> discountRulesList = new ArrayList<>();
		discountRulesList.add(new ComicDiscountRule());
		discountRulesList.add(new FictionDiscountRule());
		//Mockito.when(checkoutBookService.getRules()).thenReturn(discountRulesList);
		CheckoutBookResult result = checkoutBookService.processCheckout(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertNotEquals(result.getTotalCheckoutAmount(), new BigDecimal("700"));
		Assert.assertEquals(result.getDiscountAmount(), new BigDecimal("10"));
	}

	@Test
	public void checkoutAmountAndDiscountNotMatched() throws Exception{
		CheckoutBookCommand command = mockRequest();
		List<DiscountRules> discountRulesList = new ArrayList<>();
		discountRulesList.add(new ComicDiscountRule());
		discountRulesList.add(new FictionDiscountRule());
		//Mockito.when(checkoutBookService.getRules()).thenReturn(discountRulesList);
		CheckoutBookResult result = checkoutBookService.processCheckout(command);
		Assert.assertNotNull(result.getStatusInfo().getStatus());
		Assert.assertNotEquals(result.getTotalCheckoutAmount(), new BigDecimal("700"));
		Assert.assertNotEquals(result.getDiscountAmount(), new BigDecimal("20"));
	}

	private CheckoutBookCommand mockRequest() {
		List<BookCommand> bookCommands = new ArrayList<>();
		bookCommands.add(mockBookCommand("Java", "Shajahan", "comic", new BigDecimal("100"), "121212"));
		bookCommands.add(mockBookCommand("Java1", "Shajahan1", "fiction", new BigDecimal("200"), "121892"));
		bookCommands.add(mockBookCommand("Java2", "Shajahan2", "comic", new BigDecimal("300"), "190812"));
		return CheckoutBookCommand.builder().checkoutCode("BokTest")
				.promotionCode("PROMO10")
				.bookCommands(bookCommands)
				.build();
	}

	private BookCommand mockBookCommand(String name, String author, String classification, BigDecimal price, String isbn) {
		return BookCommand.builder().name(name)
				.description(name)
				.author(author)
				.classification(classification)
				.price(price)
				.isbn(isbn)
				.serviceType("CREATE")
				.build();
	}

}
