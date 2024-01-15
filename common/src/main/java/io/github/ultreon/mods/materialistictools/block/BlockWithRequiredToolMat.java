package io.github.ultreon.mods.materialistictools.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import io.github.ultreon.mods.materialistictools.item.tool.ToolRequirement;
import io.github.ultreon.mods.materialistictools.util.RequiresToolMat;

public class BlockWithRequiredToolMat extends Block implements RequiresToolMat {
    private final ToolRequirement toolRequirement;

    public BlockWithRequiredToolMat(ToolRequirement toolRequirement) {
        super(Properties.of().mapColor(MapColor.METAL)
                .strength(4, 20)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
        );
        this.toolRequirement = toolRequirement;
    }

    @Override
    public ToolRequirement getRequirement() {
        return this.toolRequirement;
    }
}
