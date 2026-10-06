package model;

public class Pet {
    private final String name;
    private final String species;
    private int age;

    public Pet(String name, String species, int age) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (species == null || species.isBlank()) throw new IllegalArgumentException("La especie no puede estar vacía");
        if (age < 0) throw new IllegalArgumentException("La edad no puede ser negativa");
        this.name = name;
        this.species = species;
        this.age = age;
    }

    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }

    public void celebrateBirthday() { age++; }

    public boolean isPuppy() { return age < 2; }
}
