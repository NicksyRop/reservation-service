package org.car.rental.gaphql;

import java.util.List;

import org.car.rental.reservation.Car;
import org.car.rental.reservation.InventoryClient;
import org.eclipse.microprofile.graphql.Query;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;

@GraphQLClientApi( configKey = "inventory")
public interface GraphQLInventoryClient  extends InventoryClient{
    @Query("cars")
    List<Car> allCars();
}
