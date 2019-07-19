package com.thoughtworks.parking_lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "parking_order")
public class ParkingOrder {
    @Id
    private String orderId;
    private String carLicense;
    private Timestamp entryTime;
    private Timestamp leaveTime;
    private boolean status = true;

    public String getOrderId() {
        return orderId;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
