package io.github.ultreon.mods.materialistictools.item.tool;

import io.github.ultreon.mods.materialistictools.item.tool.trait.AbstractTrait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraitPack {
    public static final TraitPack EMPTY = new TraitPack();

    List<AbstractTrait> sword = new ArrayList<>();
    List<AbstractTrait> axe = new ArrayList<>();
    List<AbstractTrait> pickaxe = new ArrayList<>();
    List<AbstractTrait> shovel = new ArrayList<>();
    List<AbstractTrait> hoe = new ArrayList<>();
    List<AbstractTrait> helmet = new ArrayList<>();
    List<AbstractTrait> chestplate = new ArrayList<>();
    List<AbstractTrait> leggings = new ArrayList<>();
    List<AbstractTrait> boots = new ArrayList<>();

    private TraitPack() {

    }

    public List<AbstractTrait> getSword() {
        return Collections.unmodifiableList(this.sword);
    }

    public List<AbstractTrait> getAxe() {
        return Collections.unmodifiableList(this.axe);
    }

    public List<AbstractTrait> getPickaxe() {
        return Collections.unmodifiableList(this.pickaxe);
    }

    public List<AbstractTrait> getShovel() {
        return Collections.unmodifiableList(this.shovel);
    }

    public List<AbstractTrait> getHoe() {
        return Collections.unmodifiableList(this.hoe);
    }

    public List<AbstractTrait> getHelmet() {
        return Collections.unmodifiableList(this.helmet);
    }

    public List<AbstractTrait> getChestplate() {
        return Collections.unmodifiableList(this.chestplate);
    }

    public List<AbstractTrait> getLeggings() {
        return Collections.unmodifiableList(this.leggings);
    }

    public List<AbstractTrait> getBoots() {
        return Collections.unmodifiableList(this.boots);
    }

    public static Builder create() {
        return new Builder();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static class Builder {
        private final TraitPack pack;

        private Builder() {
            this.pack = new TraitPack();
        }

        public Builder sword(AbstractTrait... traits) {
            this.pack.sword.addAll(List.of(traits));
            return this;
        }

        public Builder axe(AbstractTrait... traits) {
            this.pack.axe.addAll(List.of(traits));
            return this;
        }

        public Builder pickaxe(AbstractTrait... traits) {
            this.pack.pickaxe.addAll(List.of(traits));
            return this;
        }

        public Builder shovel(AbstractTrait... traits) {
            this.pack.shovel.addAll(List.of(traits));
            return this;
        }

        public Builder hoe(AbstractTrait... traits) {
            this.pack.hoe.addAll(List.of(traits));
            return this;
        }

        public Builder helmet(AbstractTrait... traits) {
            this.pack.helmet.addAll(List.of(traits));
            return this;
        }

        public Builder chestplate(AbstractTrait... traits) {
            this.pack.chestplate.addAll(List.of(traits));
            return this;
        }

        public Builder leggings(AbstractTrait... traits) {
            this.pack.leggings.addAll(List.of(traits));
            return this;
        }

        public Builder boots(AbstractTrait... traits) {
            this.pack.boots.addAll(List.of(traits));
            return this;
        }

        public TraitPack build() {
            return this.pack;
        }

        public Builder all(AbstractTrait... traits) {
            this.helmet(traits);
            this.chestplate(traits);
            this.leggings(traits);
            this.boots(traits);
            this.sword(traits);
            this.axe(traits);
            this.pickaxe(traits);
            this.shovel(traits);
            this.hoe(traits);

            return this;
        }

        public Builder armor(AbstractTrait... traits) {
            this.helmet(traits);
            this.chestplate(traits);
            this.leggings(traits);
            this.boots(traits);

            return this;
        }

        public Builder tools(AbstractTrait... traits) {
            this.sword(traits);
            this.axe(traits);
            this.pickaxe(traits);
            this.shovel(traits);
            this.hoe(traits);

            return this;
        }
    }
}
