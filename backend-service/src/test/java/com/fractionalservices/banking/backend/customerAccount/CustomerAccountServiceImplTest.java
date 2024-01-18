package com.fractionalservices.banking.backend.customerAccount;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.fractionalservices.banking.backend.exception.BadRequestException;
import com.fractionalservices.banking.backend.transaction.NoTransactionException;

@SpringBootTest
public class CustomerAccountServiceImplTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/additions.csv", numLinesToSkip = 1)
    public void testAddition(int a, int b, int expected) {
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        int actual = service.addition(a, b);
        assertEquals(expected, actual);

    }

    // Existing test methods...

    @Test
    public void testValidateRequest_WithValidInputs_ShouldNotThrowException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = "USD";

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> service.validateRequest(customerId, currency));
    }

    @Test
    public void testValidateRequest_WithNullCurrency_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = null;

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> service.validateRequest(customerId, currency));
    }

    @Test
    public void testValidateRequest_WithEmptyCurrency_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = "";

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> service.validateRequest(customerId, currency));
    }

    @Test
    public void testValidateRequest_WithNullCustomerId_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = null;
        String currency = "USD";

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> service.validateRequest(customerId, currency));
    }

    @Test
    public void testValidateRequest_WithEmptyCustomerId_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "";
        String currency = "USD";

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> service.validateRequest(customerId, currency));
    }


    @Test
    public void testGetCustomerAccountDetails_WithValidInputs_ShouldReturnCustomerAccountDetails()
            throws NoTransactionException, BadRequestException {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = "USD";

        // Act
        CustomerAccountDetails result = service.getCustomerAccountDetails(customerId, currency);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
    }

    @Test
    public void testGetCustomerAccountDetails_WithInvalidCurrency_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = null;

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class,
                () -> service.getCustomerAccountDetails(customerId, currency));
    }

    @Test
    public void testGetCustomerAccountDetails_WithInvalidCustomerId_ShouldThrowBadRequestException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = null;
        String currency = "USD";

        // Act & Assert
        Assertions.assertThrows(BadRequestException.class,
                () -> service.getCustomerAccountDetails(customerId, currency));
    }

    @Test
    public void testGetCustomerAccountDetails_WithNoTransaction_ShouldThrowNoTransactionException() {
        // Arrange
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        String customerId = "123";
        String currency = "USD";

        // Act & Assert
        Assertions.assertThrows(NoTransactionException.class,
                () -> service.getCustomerAccountDetails(customerId, currency));
    }
}