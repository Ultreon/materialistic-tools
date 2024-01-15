//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.ultreon.mods.materialistictools.item.loot;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static io.github.ultreon.mods.materialistictools.MaterialisticTools.MOD_ID;

public class LootTableInjection {
    private static final Map<ResourceLocation, Injector> injections = new HashMap<>();

    public static ResourceLocation mcLoc(String path) {
        return new ResourceLocation("minecraft", path);
    }

    public static ResourceLocation forgeLoc(String path) {
        return new ResourceLocation("forge", path);
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static void register() {
        LootEvent.MODIFY_LOOT_TABLE.register(LootTableInjection::inject);
    }

    private static void inject(@Nullable LootDataManager lootDataManager, ResourceLocation id,
                               LootEvent.LootTableModificationContext context, boolean builtin) {
        Injector injector = injections.get(id);
        if (injector != null) {
            context.addPool(injector.createPool());
        }
    }

    public static void registerInjection(ResourceLocation target, ResourceLocation injection) {
        injections.put(target, new Injector(target, injection));
    }

    public static void registerInjection(ResourceLocation target, String modId) {
        registerInjection(target, new ResourceLocation(modId, target.getPath()));
    }

    private record Injector(ResourceLocation target, ResourceLocation injection) {
        private Injector(ResourceLocation target, ResourceLocation injection) {
            this.target = target;
            this.injection = injection;
        }

        private LootPool createPool() {
            LootPool.Builder builder = LootPool.lootPool()
                    .add(createInjectionEntry(this.injection))
                    .setBonusRolls(UniformGenerator.between(0.0F, 1.0F));
            return builder.build();
        }

        private static LootPoolEntryContainer.Builder<?> createInjectionEntry(ResourceLocation name) {
            return LootTableReference.lootTableReference(new ResourceLocation(name.getNamespace(), "inject/" + name.getPath())).setWeight(1);
        }

        public ResourceLocation target() {
            return this.target;
        }

        public ResourceLocation injection() {
            return this.injection;
        }
    }
}
