package factory;

import model.Recipe;

public final class RecipeFactory {
    private RecipeFactory() {}

    public static Recipe createQuick() {
        Recipe r = new Recipe("Tostado de jamón y queso", 10);
        r.addIngredient("Pan");
        r.addIngredient("Jamón");
        r.addIngredient("Queso");
        return r;
    }

    public static Recipe createSlow() {
        Recipe r = new Recipe("Locro", 180);
        r.addIngredient("Maíz");
        r.addIngredient("Porotos");
        return r;
    }
}
