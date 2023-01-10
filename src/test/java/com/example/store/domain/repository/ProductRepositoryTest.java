package com.example.store.domain.repository;

import com.example.store.domain.model.Product;
import com.example.store.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveAndFindValidProduct() {

        productRepository.save(TestUtils.getProduct());
        Optional<Product> product = productRepository.findById(1L);

        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(TestUtils.getProduct().getName(), product.get().getName());
        Assertions.assertEquals(TestUtils.getProduct().getPrice(), product.get().getPrice());

    }
}