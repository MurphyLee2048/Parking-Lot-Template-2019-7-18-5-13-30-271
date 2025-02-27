package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@RestController
public class ParkingOrderController {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;

    @PostMapping(value = "/parkingOrders", params = "name")
    public ResponseEntity addParkingOrders(@RequestBody ParkingOrder parkingOrder, @RequestParam String name) {
        ParkingLot parkingLot = parkingLotRepository.findById(name).get();
        parkingOrder.setParkingLot(parkingLot);
        parkingOrder.setEntryTime(new Timestamp(new Date().getTime()));
        return ResponseEntity.status(201).body(parkingOrderRepository.save(parkingOrder));
    }

    @PatchMapping("/parkingOrders")
    public ParkingOrder updateParkingOrderBy(@RequestBody String carLicense) {
        ParkingOrder parkingOrder = parkingOrderRepository.findParkingOrderByCarLicense(carLicense);
        parkingOrder.setStatus(false);
        parkingOrder.setLeaveTime(new Timestamp(new Date().getTime()));
        parkingOrderRepository.save(parkingOrder);
        return parkingOrder;
    }
}
