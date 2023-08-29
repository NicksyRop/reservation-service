package org.car.rental.reservation.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.car.rental.reservation.Car;
import org.car.rental.reservation.InMemoryReservationsRepository;
import org.car.rental.reservation.InventoryClient;
import org.car.rental.reservation.Reservation;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final InventoryClient inventoryClient;

    private final InMemoryReservationsRepository inMemoryReservationsRepository;

    public ReservationResource(InventoryClient inventoryClient, InMemoryReservationsRepository inMemoryReservationsRepository) {
        this.inventoryClient = inventoryClient;
        this.inMemoryReservationsRepository = inMemoryReservationsRepository;
    }

    @GET
    @Path("availability")
    public Collection<Car>  availability(@RestQuery LocalDate startDate , @RestQuery LocalDate endDate){
        List<Car> cars =  inventoryClient.allCars();
        Map<Long, Car> carsById = new HashMap<>();
        for (Car car : cars) {
            carsById.put(car.id, car);
        }

        List<Reservation> reservations = inMemoryReservationsRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.isReserved(startDate, endDate)) {
                carsById.remove(reservation.carId);
            }
        }
        return carsById.values();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public  Reservation save(Reservation reservation){
        return inMemoryReservationsRepository.save(reservation);
    }


}
