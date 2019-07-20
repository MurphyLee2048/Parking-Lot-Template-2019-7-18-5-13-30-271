package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingOrderController {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;

    @PostMapping("/parkingOrders")
    public ResponseEntity addParkingOrders(@RequestBody ParkingOrder parkingOrder) {
        return ResponseEntity.status(201).body(parkingOrderRepository.save(parkingOrder));
    }
}
