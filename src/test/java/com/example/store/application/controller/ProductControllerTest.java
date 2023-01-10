package com.example.store.application.controller;

import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.service.ProductService;
import com.example.store.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void productsEmptyList() {
        mockMvc.perform(get("/allProducts?size=20&page=0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(service, times(1)).findAll(any());
    }

    @SneakyThrows
    @Test
    void productNotFountTest() {
        when(service.findById(28L)).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/product/28")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(TestUtils.getProductNotFoundResponse()));
    }

    @Test
    @SneakyThrows
    void findProduct() {
        when(service.findById(28L)).thenReturn(TestUtils.getProductResponse());
        mockMvc.perform(get("/product/28")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.getValidProduct()));
    }
}