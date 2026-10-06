package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {
    private final String title;
    private final List<String> ingredients = new ArrayList<>();
    private final int minutes;

    public Recipe(String title, int minutes) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("El título no puede estar vacío");
        if (minutes <= 0) throw new IllegalArgumentException("El tiempo debe ser positivo");
        this.title = title;
        this.minutes = minutes;
    }

    public String getTitle() { return title; }
    public int getMinutes() { return minutes; }
    public List<String> getIngredients() { return Collections.unmodifiableList(ingredients); }

    public void addIngredient(String ingredient) {
        if (ingredient == null || ingredient.isBlank()) throw new IllegalArgumentException("Ingrediente inválido");
        if (ingredients.contains(ingredient)) throw new IllegalArgumentException("Ingrediente duplicado");
        ingredients.add(ingredient);
    }

    public boolean isQuick() { return minutes <= 30; }
}
