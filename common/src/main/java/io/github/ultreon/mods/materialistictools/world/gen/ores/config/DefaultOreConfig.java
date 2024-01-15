package io.github.ultreon.mods.materialistictools.world.gen.ores.config;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.jetbrains.annotations.Range;

import java.util.Objects;

@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public class DefaultOreConfig implements OreConfig {
    private final int veinCount;
    private final int veinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int spawnChance;

    public DefaultOreConfig(int veinCount, int veinSize, int minHeight, int maxHeight) {
        this(veinCount, veinSize, minHeight, maxHeight, 1);
    }

    public DefaultOreConfig(int veinCount, int veinSize, int minHeight, int maxHeight, int spawnChance) {
        this.veinCount = veinCount;
        this.veinSize = veinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.spawnChance = spawnChance;
    }

    @Override
    public int getVeinCount() {
        return this.veinCount;
    }

    @Override
    public int getVeinSize() {
        return this.veinSize;
    }

    @Override
    public int getMinHeight() {
        return this.minHeight;
    }

    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override
    public int getSpawnChance() {
        return this.spawnChance;
    }

    @Override
    public String toString() {
        return "DefaultOreConfig{" +
                "veinCount=" + this.veinCount +
                ", veinSize=" + this.veinSize +
                ", minHeight=" + this.minHeight +
                ", maxHeight=" + this.maxHeight +
                ", spawnChance=" + this.spawnChance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        DefaultOreConfig that = (DefaultOreConfig) o;
        return this.getVeinCount() == that.getVeinCount() && this.getVeinSize() == that.getVeinSize() && this.getMinHeight() == that.getMinHeight() && this.getMaxHeight() == that.getMaxHeight() && this.getSpawnChance() == that.getSpawnChance();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getVeinCount(), this.getVeinSize(), this.getMinHeight(), this.getMaxHeight(), this.getSpawnChance());
    }

    @Range(from = 0, to = 1)
    public float getDiscardChanceOnExposure() {
        return 0.0F;
    }
}
