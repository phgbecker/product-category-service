package br.com.juno.recruta.backend.controller;

import br.com.juno.recruta.backend.ProductCategoryServiceApplication;
import br.com.juno.recruta.backend.category.IntegrationTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductCategoryServiceApplication.class)
@AutoConfigureMockMvc
@Category(IntegrationTestCategory.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenRequest_whenId_thenExpectEquals() throws Exception {
        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Alimentos")));
    }

    @Test
    public void givenInvalidRequest_whenId_thenStatusIsNotFound() throws Exception {
        mockMvc.perform(get("/categories/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenRequest_whenListAll_thenExpectEquals() throws Exception {
        mockMvc.perform(get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Alimentos")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Eletrodomésticos")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Móveis")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].name", is("Queima de estoque")));
    }

    @Test
    public void givenRequest_whenListAllByProduct_thenExpectEquals() throws Exception {
        mockMvc.perform(get("/categories/product/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].name", is("Eletrodomésticos")))
                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].name", is("Queima de estoque")));
    }

    @Test
    public void givenInvalidRequest_whenListAllByProduct_thenStatusIsNotFound() throws Exception {
        mockMvc.perform(get("/categories/product/-5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenRequest_whenListAllProducts_thenExpectEquals() throws Exception {
        mockMvc.perform(get("/categories/1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Arroz")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Feijão")));
    }

    @Test
    public void givenInvalidRequest_whenListAllProducts_thenStatusIsNoContent() throws Exception {
        mockMvc.perform(get("/categories/-1/products"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenRequest_whenWithPattern_thenExpectEquals() throws Exception {
        mockMvc.perform(get("/categories/pattern/e"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is("Queima de estoque")));
    }

    @Test
    public void givenInvalidRequest_whenWithPattern_thenStatusIsNoContent() throws Exception {
        mockMvc.perform(get("/categories/pattern/z"))
                .andExpect(status().isNoContent());
    }
}