package com.augustocorreia.cadastro.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import com.augustocorreia.cadastro.services.ClienteServiceImpl;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClienteController.class})
@ExtendWith(SpringExtension.class)
public class ClienteControllerTest {
    @Autowired
    private ClienteController clienteController;

    @MockBean
    private ClienteServiceImpl clienteServiceImpl;

    @Test
    public void testAddCliente() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("clienteRequest",
                String.valueOf(new ClienteRequest(1, "com.augustocorreia.cadastro.models.ClienteRequest",
                        "alice.liddell@example.org", "jane.doe@example.org")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.clienteController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testFindById() throws Exception {
        when(this.clienteServiceImpl.findById((Long) any())).thenReturn(new ResponseEntity<Cliente>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/byId");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.clienteController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testFindAll() throws Exception {
        when(this.clienteServiceImpl.getAllUsers((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Cliente>(new ArrayList<Cliente>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.clienteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":0,\"size\":0,\"number"
                                        + "\":0,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}")));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder paramResult = getResult.param("clienteRequest", String.valueOf(new ClienteRequest(1,
                "com.augustocorreia.cadastro.models.ClienteRequest", "alice.liddell@example.org", "jane.doe@example.org")));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.clienteController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

