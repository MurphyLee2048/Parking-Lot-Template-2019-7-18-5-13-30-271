package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @DeleteMapping("/parkingLots/{parkingLotName}")
    public void deleteParkingLotByName(@PathVariable String parkingLotName) {
        parkingLotRepository.deleteById(parkingLotName);
    }

    @GetMapping("/parkingLots")
    public List<ParkingLot> findAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    @PostMapping("/parkingLots")
    public ResponseEntity postParkingLot(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.status(201).body(parkingLotRepository.save(parkingLot));
    }
    
    @GetMapping("/parkingLots/{parkingLotName}")
    public ParkingLot findParkingLotById(@PathVariable String parkingLotName) {
        return parkingLotRepository.findById(parkingLotName).get();
    }
    
    @PatchMapping("/parkingLots/{parkingLotName}")
    public void addParkingLotCapacity(@PathVariable String parkingLotName, @RequestBody int capacity) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotName).get();
        parkingLot.setCapacity(capacity);
        parkingLotRepository.saveAndFlush(parkingLot);
    }
}
