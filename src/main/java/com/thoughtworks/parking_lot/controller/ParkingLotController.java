package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @DeleteMapping("/parkingLots/{parkingLotName}")
    public void deleteParkingLotByName(@PathVariable String parkingLotName) {
        parkingLotRepository.deleteByParkingLotName(parkingLotName);
    }

    @GetMapping("/parkingLots")
    public List<ParkingLot> findAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    @PostMapping("/parkingLots")
    public void postParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

}
