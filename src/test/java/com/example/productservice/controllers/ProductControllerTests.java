package com.example.productservice.controllers;

import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdPartyClients.productService.fakeStore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductControllerTests {
    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Test
    @DisplayName("1 + 1 equals to 2 Test")
    void testOnePlusOneIsTrue() {
//        System.out.println("Its true!");
        assert 2 == 1 + 1;
        assertEquals(2, 1 + 1, "Addition resulted in 2");
        assertEquals(2, 1 + 2, "Addition didn't result in 2");
    }

    @Test
    void testAdditionIsCorrect() {
        assert -1 - 1 == -2;
        assert -1 + 0 == -1;
        assert -1 + 1 == 0;

        assertTrue(-1 -2 == -2, "Addiding -1 and -2 not giving out -2");
        assertFalse(-1 -2 == -2, "Adding -1 and -2 doesn't give out -2");

    }

    @Test
    void testFakeStoreServiceClient() throws NotFoundException {
//        assertNull(fakeStoreProductServiceClient.getProductById(300));
        assertThrows(NotFoundException.class, () -> fakeStoreProductServiceClient.getProductById(200));
    }
}


// Assertion Framework
// -> Allows you to make Assertions / Checks