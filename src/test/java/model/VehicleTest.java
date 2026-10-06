package model;

import factory.VehicleFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void createsVehicleWithValidData() {
        Vehicle v = VehicleFactory.createDefault();
        assertEquals("Toyota", v.getBrand());
        assertEquals("Corolla", v.getModelName());
        assertEquals(2020, v.getYear());
        assertEquals(30000, v.getMileage());
    }

    @Test
    void driveAddsMileage() {
        Vehicle v = VehicleFactory.createBrandNew();
        v.drive(150.5);
        assertEquals(150.5, v.getMileage(), 0.001);
    }

    @Test
    void driveRejectsNonPositiveKm() {
        Vehicle v = VehicleFactory.createDefault();
        assertThrows(IllegalArgumentException.class, () -> v.drive(0));
        assertThrows(IllegalArgumentException.class, () -> v.drive(-10));
    }

    @Test
    void classicIsDetected() {
        assertTrue(VehicleFactory.createClassic().isClassic(2026));
        assertFalse(VehicleFactory.createDefault().isClassic(2026));
    }

    @Test
    void rejectsInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("", "X", 2000, 0));
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("A", "", 2000, 0));
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("A", "X", 1800, 0));
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("A", "X", 2000, -1));
    }
}
