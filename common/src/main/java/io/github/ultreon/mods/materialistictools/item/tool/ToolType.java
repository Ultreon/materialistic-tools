package io.github.ultreon.mods.materialistictools.item.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class ToolType {
    private static final Map<String, ToolType> mapping = new HashMap<>();

    public static final ToolType PICKAXE = get("pickaxe", BlockTags.MINEABLE_WITH_PICKAXE);
    public static final ToolType SHOVEL = get("shovel", BlockTags.MINEABLE_WITH_SHOVEL);
    public static final ToolType AXE = get("axe", BlockTags.MINEABLE_WITH_AXE);
    public static final ToolType HOE = get("hoe", BlockTags.MINEABLE_WITH_HOE);

    private final String type;
    private final TagKey<Block> tag;

    private boolean isVanilla;

    public ToolType(String type, TagKey<Block> tag) {
        this.type = type;
        this.tag = tag;

        mapping.put(type, this);
    }

    public static ToolType get(String type) {
        return mapping.get(type);
    }

    public static ToolType create(String type, TagKey<Block> tag) {
        return new ToolType(type, tag);
    }

    public static ToolType get(String type, TagKey<Block> tag) {
        return create(type, tag).asVanilla();
    }

    private ToolType asVanilla() {
        this.isVanilla = true;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public TagKey<Block> getTag() {
        return this.tag;
    }

    public boolean isVanilla() {
        return this.isVanilla;
    }
}
