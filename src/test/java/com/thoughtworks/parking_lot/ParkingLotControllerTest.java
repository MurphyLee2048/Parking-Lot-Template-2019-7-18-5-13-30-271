package com.thoughtworks.parking_lot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest  // 集成
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ParkingLotControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingLotRepository parkingLotRepository;

    @Test
    public void should_delete_parkingLot_given_by_parkingLot_name() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("myHome");
        parkingLot.setCapacity(4);
        parkingLot.setLocation("Zhongshan");

        ResultActions resultActions = mockMvc.perform(delete("/parkingLots/{parkingLotName}", parkingLot.getParkingLotName()));

        resultActions.andExpect(status().isOk());
    }

    @Test
    public void should_get_parkingLots() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setParkingLotName("myHome");
        parkingLot1.setCapacity(4);
        parkingLot1.setLocation("Zhongshan");

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setParkingLotName("myHome");
        parkingLot2.setCapacity(4);
        parkingLot2.setLocation("Zhongshan");

        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        when(parkingLotRepository.findAll()).thenReturn(parkingLots);

        mockMvc.perform(get("/parkingLots"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"parkingLotName\":\"myHome\",\"capacity\":4,\"location\":\"Zhongshan\"},{\"parkingLotName\":\"myHome\",\"capacity\":4,\"location\":\"Zhongshan\"}]"));

    }

    @Test
    public void should_post_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("myHome");
        parkingLot.setCapacity(4);
        parkingLot.setLocation("Zhongshan");

        ObjectMapper objectMapper = new ObjectMapper();  // object转string
        String str = objectMapper.writeValueAsString(parkingLot);

        mockMvc.perform(post("/parkingLots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(str))
                .andExpect(status().isCreated());

    }

    @Test
    public void should_get_parkingLot_given_by_name() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("myHome");
        parkingLot.setCapacity(4);
        parkingLot.setLocation("Zhongshan");
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<ParkingLot> optionalParkingLot = Optional.of(parkingLot);

        when(parkingLotRepository.findById(anyString())).thenReturn(optionalParkingLot);

        mockMvc.perform(get("/parkingLots/{parkingLotName}", parkingLot.getParkingLotName()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(parkingLot)));
    }

    @Test
    public void should_larger_the_capacity() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName("myHome");
        parkingLot.setCapacity(4);
        Optional<ParkingLot> optionalParkingLot = Optional.of(parkingLot);

        when(parkingLotRepository.findById(anyString())).thenReturn(optionalParkingLot);

        mockMvc.perform(patch("/parkingLots/{parkingLotName}", parkingLot.getParkingLotName())
                .contentType(MediaType.APPLICATION_JSON)
                .content("5"))
                .andExpect(status().isOk());

    }

}
