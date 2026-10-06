package model;

public class Vehicle {
    private final String brand;
    private final String modelName;
    private final int year;
    private double mileage;

    public Vehicle(String brand, String modelName, int year, double mileage) {
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("La marca no puede estar vacía");
        if (modelName == null || modelName.isBlank()) throw new IllegalArgumentException("El modelo no puede estar vacío");
        if (year < 1886) throw new IllegalArgumentException("Año inválido");
        if (mileage < 0) throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        this.brand = brand;
        this.modelName = modelName;
        this.year = year;
        this.mileage = mileage;
    }

    public String getBrand() { return brand; }
    public String getModelName() { return modelName; }
    public int getYear() { return year; }
    public double getMileage() { return mileage; }

    public void drive(double km) {
        if (km <= 0) throw new IllegalArgumentException("Los km recorridos deben ser positivos");
        mileage += km;
    }

    public boolean isClassic(int currentYear) { return currentYear - year >= 25; }
}
