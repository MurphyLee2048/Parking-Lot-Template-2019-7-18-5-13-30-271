package com.thoughtworks.parking_lot.model;


import javax.persistence.*;
@Entity
@Table(name="parkinglot")
public class ParkingLot {
    @Id
    private String parkingLotName;
    private int capacity;  // TODO: larger than 0
    private String location;

    public String getParkingLotName() {
        return parkingLotName;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
