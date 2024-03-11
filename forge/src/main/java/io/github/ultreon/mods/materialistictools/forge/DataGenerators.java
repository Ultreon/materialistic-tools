package io.github.ultreon.mods.materialistictools.forge;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public final class DataGenerators {
    private DataGenerators() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, event.getLookupProvider(), event.getModContainer().getModId(), event.getExistingFileHelper());
        gen.addProvider(true, blockTags);
        gen.addProvider(true, new ModItemTagsProvider(gen.getPackOutput(), event.getLookupProvider(), blockTags.contentsGetter(), event.getModContainer().getModId(), event.getExistingFileHelper()));
    }
}
