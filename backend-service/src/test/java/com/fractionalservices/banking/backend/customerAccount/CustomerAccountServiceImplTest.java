package com.fractionalservices.banking.backend.customerAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerAccountServiceImplTest {
    
    // Add unit test foir getCustomerAccountDetails method
    @Test
    public void testGetCustomerAccountDetails() {   

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/additions.csv", numLinesToSkip = 1)    
    public void testAddition(int a, int b, int expected) {
        CustomerAccountServiceImpl service = new CustomerAccountServiceImpl();
        int actual = service.addition(a, b);
        assertEquals(expected, actual);

        System.out.println("Testingn has been completed");
    }

}
