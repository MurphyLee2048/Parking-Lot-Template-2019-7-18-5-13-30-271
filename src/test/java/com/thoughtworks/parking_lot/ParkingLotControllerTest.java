package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
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

        ResultActions resultActions = mockMvc.perform(get("/parkingLots"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.parkingLotName").value("myHome"))
                .andExpect(jsonPath("$.capacity").value(4))
                .andExpect(jsonPath("$.location").value("Zhongshan"));

    }

}
