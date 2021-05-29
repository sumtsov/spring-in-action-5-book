package com.dsumtsov.tacocloud;

import com.dsumtsov.tacocloud.repository.jpa.IngredientRepository;
import com.dsumtsov.tacocloud.repository.jpa.OrderRepository;
import com.dsumtsov.tacocloud.repository.jpa.TacoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IngredientRepository ingredientRepository;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private TacoRepository tacoRepository;

    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));
    }
}
