package model;

import factory.RecipeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void quickRecipeIsQuick() {
        assertTrue(RecipeFactory.createQuick().isQuick());
        assertFalse(RecipeFactory.createSlow().isQuick());
    }

    @Test
    void addIngredientStoresIt() {
        Recipe r = RecipeFactory.createSlow();
        r.addIngredient("Zapallo");
        assertEquals(3, r.getIngredients().size());
        assertTrue(r.getIngredients().contains("Zapallo"));
    }

    @Test
    void duplicateIngredientIsRejected() {
        Recipe r = RecipeFactory.createQuick();
        assertThrows(IllegalArgumentException.class, () -> r.addIngredient("Pan"));
    }

    @Test
    void ingredientsListIsUnmodifiable() {
        Recipe r = RecipeFactory.createQuick();
        assertThrows(UnsupportedOperationException.class, () -> r.getIngredients().add("Hack"));
    }

    @Test
    void rejectsInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> new Recipe("", 10));
        assertThrows(IllegalArgumentException.class, () -> new Recipe("Algo", 0));
    }
}
