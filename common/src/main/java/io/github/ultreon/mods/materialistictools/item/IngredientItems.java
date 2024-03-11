package io.github.ultreon.mods.materialistictools.item;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import io.github.ultreon.mods.materialistictools.init.ModItems;

public enum IngredientItems implements ItemLike {
    URANIUM_ROD,
    ZOMBIE_LEATHER,
    ;

    private final FoodProperties food;
    private RegistrySupplier<Item> item;

    IngredientItems() {
        this(null);
    }

    IngredientItems(FoodProperties food) {
        this.food = food;
    }

    public static void register() {
        for (IngredientItems item : values()) {
            item.item = ModItems.REGISTER.register(item.getName(), () -> new Item(createProperties(item.food)));
        }
    }

    private static Item.Properties createProperties(FoodProperties food) {
        Item.Properties properties = new Item.Properties();
        if (food != null) properties = properties.food(food);
        return properties;
    }

    @Override
    public @NotNull Item asItem() {
        return this.item.get();
    }

    public String getName() {
        return this.name().toLowerCase();
    }
}
