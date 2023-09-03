package org.car.rental.reservation;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class InMemoryInventoryClient implements InventoryClient{

    private  static final List<Car> ALL_CARS = List.of(
            new Car(1L,"KDG-232","Toyota" ,"Axio"),
            new Car(2L,"KDG-202","Mazda" ,"Demio"),
            new Car(3L,"KCX-232","Lexus" ,"Tetius"),
            new Car(4L,"KAC-232","Toyota" ,"Probox")
    );
    
    @Override
    public List<Car> allCars() {
        return ALL_CARS;
    }
}
