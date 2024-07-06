package com.rafsunjani.wsd.ecommerce.controller;

import com.rafsunjani.wsd.ecommerce.domain.entity.Customer;
import com.rafsunjani.wsd.ecommerce.domain.entity.WishList;
import com.rafsunjani.wsd.ecommerce.service.CustomerService;
import com.rafsunjani.wsd.ecommerce.service.WishListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class WishListControllerTest {

    @Mock
    private WishListService wishListService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private WishListController wishListController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(wishListController).build();
    }

    @Test
    public void testGetWishListByCustomerUsername() throws Exception {
        // Mock data
        String customerUsername = "testuser";
        Customer customer = new Customer();
        customer.setUsername(customerUsername);

        WishList wishListItem1 = new WishList();
        wishListItem1.setId(1L);

        WishList wishListItem2 = new WishList();
        wishListItem2.setId(2L);

        List<WishList> wishList = new ArrayList<>();
        wishList.add(wishListItem1);
        wishList.add(wishListItem2);

        // Mock the service methods
        when(customerService.findByUsername(customerUsername)).thenReturn(customer);
        when(wishListService.getWishListByCustomer(customer)).thenReturn(wishList);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/wishlist/{customerUsername}", customerUsername))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));

        // Verify that the service methods were called
        verify(customerService, times(1)).findByUsername(customerUsername);
        verify(wishListService, times(1)).getWishListByCustomer(customer);
    }
}
