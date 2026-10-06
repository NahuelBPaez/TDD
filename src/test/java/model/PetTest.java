package model;

import factory.PetFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void createsPetWithValidData() {
        Pet pet = PetFactory.createDefault();
        assertEquals("Firulais", pet.getName());
        assertEquals("Perro", pet.getSpecies());
        assertEquals(5, pet.getAge());
    }

    @Test
    void birthdayIncreasesAge() {
        Pet pet = PetFactory.createCat();
        pet.celebrateBirthday();
        assertEquals(4, pet.getAge());
    }

    @Test
    void puppyIsDetected() {
        assertTrue(PetFactory.createPuppy().isPuppy());
        assertFalse(PetFactory.createDefault().isPuppy());
    }

    @Test
    void rejectsInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new Pet("", "Perro", 1));
        assertThrows(IllegalArgumentException.class, () -> new Pet("Rex", " ", 1));
        assertThrows(IllegalArgumentException.class, () -> new Pet("Rex", "Perro", -1));
    }
}
