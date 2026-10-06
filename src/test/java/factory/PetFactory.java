package factory;

import model.Pet;

public final class PetFactory {
    private PetFactory() {}

    public static Pet createDefault() { return new Pet("Firulais", "Perro", 5); }
    public static Pet createPuppy()   { return new Pet("Toby", "Perro", 1); }
    public static Pet createCat()     { return new Pet("Misi", "Gato", 3); }
}
