package org.car.rental.reservation;

import java.time.LocalDate;

public class Reservation {
    public Long id;

    public String userId;

    public Long carId;

    public LocalDate startDate;

    public  LocalDate endDate;

    public boolean isReserved(LocalDate startDay, LocalDate endDay) {
        return (!(this.endDate.isBefore(startDay) ||
                this.startDate.isAfter(endDay)));
    }
}
