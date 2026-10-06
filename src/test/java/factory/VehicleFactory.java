package factory;

import model.Vehicle;

public final class VehicleFactory {
    private VehicleFactory() {}

    public static Vehicle createDefault() { return new Vehicle("Toyota", "Corolla", 2020, 30000); }
    public static Vehicle createClassic() { return new Vehicle("Ford", "Falcon", 1975, 250000); }
    public static Vehicle createBrandNew(){ return new Vehicle("Fiat", "Cronos", 2026, 0); }
}
