package com.thoughtworks.parking_lot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ParkingOrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingOrderRepository parkingOrderRepository;




    @Transactional
    @Test
    public void should_add_parking_order() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("a");
        parkingLot.setCapacity(100);
        parkingLot.setLocation("Zhuhai");
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setOrderId("001");
        parkingOrder.setCarLicense("粤C 1111");
        parkingOrder.setParkingLot(parkingLot);
        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(post("/parkingOrders?name={name}", parkingLot.getParkingLotName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingOrder)))
                .andExpect(status().isCreated());
    }
}
