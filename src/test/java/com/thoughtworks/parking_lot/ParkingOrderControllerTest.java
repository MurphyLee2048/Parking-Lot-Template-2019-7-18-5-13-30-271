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
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        parkingOrder.setCarLicense("粤C 1111");
        parkingOrder.setParkingLot(parkingLot);
        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(post("/parkingOrders?name={name}", parkingLot.getParkingLotName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parkingOrder)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_change_leaveTime_and_status() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("a");
        parkingLot.setCapacity(100);
        parkingLot.setLocation("Zhuhai");
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setCarLicense("粤C 1111");
        parkingOrder.setParkingLot(parkingLot);
        parkingOrder.setEntryTime(new Timestamp(new Date().getTime()));
        ObjectMapper objectMapper = new ObjectMapper();

        when(parkingOrderRepository.findParkingOrderByCarLicense(anyString())).thenReturn(parkingOrder);

        Boolean result = new Boolean(false);
        mockMvc.perform(patch("/parkingOrders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(parkingOrder.getCarLicense())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(result));



    }
}
