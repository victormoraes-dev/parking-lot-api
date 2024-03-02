package dev.victormoraes.domain.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class VehicleFactoryTest {


    @Test
    public void givenCarTypeShouldReturnCarInstance() {

        Vehicle vehicle = VehicleFactory.getVehicle(Car.class);
        assertInstanceOf(Car.class, vehicle);
    }

    @Test
    public void givenMotorcycleTypeShouldReturnMotorcycleInstance() {

        Vehicle vehicle = VehicleFactory.getVehicle(Motorcycle.class);
        assertInstanceOf(Motorcycle.class, vehicle);
    }

    @Test
    public void givenTruckTypeShouldReturnTruckInstance() {

        Vehicle vehicle = VehicleFactory.getVehicle(Truck.class);
        assertInstanceOf(Truck.class, vehicle);
    }

    @Test
    public void givenVanTypeShouldReturnVanInstance() {

        Vehicle vehicle = VehicleFactory.getVehicle(Van.class);
        assertInstanceOf(Van.class, vehicle);
    }
}