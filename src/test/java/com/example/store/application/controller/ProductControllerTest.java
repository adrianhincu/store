package com.example.store.application.controller;

import com.example.store.domain.exceptions.ProductNotFoundException;
import com.example.store.domain.service.ProductService;
import com.example.store.utils.TestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testProductsEmptyList() {
        mockMvc.perform(get("/products?size=20&page=0").with(httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(service, times(1)).findAll(any());
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testproductNotFound() {
        when(service.findById(28L)).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/product/28").with(httpBasic("user", "user"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(TestUtils.getProductNotFoundResponse()));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testFindProduct() {
        when(service.findById(28L)).thenReturn(TestUtils.getProductResponse());
        mockMvc.perform(get("/product/28").with(httpBasic("user", "user"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.getValidProduct()));
    }
}