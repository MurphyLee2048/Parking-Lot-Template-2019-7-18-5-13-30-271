package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @DeleteMapping("/parkingLots/{parkingLotName}")
    public void deleteParkingLotByName(@PathVariable String parkingLotName) {
        parkingLotRepository.deleteByName(parkingLotName);
    }

}
