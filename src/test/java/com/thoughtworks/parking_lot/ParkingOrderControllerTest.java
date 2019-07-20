package com.thoughtworks.parking_lot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ParkingOrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingOrderRepository parkingOrderRepository;


//    @Test
//    public void should_add_parking_order() throws Exception {
//        ParkingOrder parkingOrder = new ParkingOrder();
//        parkingOrder.setCarLicense("粤C 1111");
//        parkingOrder.setOrderId("001");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        mockMvc.perform(post("/parkingOrders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(parkingOrder)))
//                .andExpect(status().isCreated());
//    }
}
