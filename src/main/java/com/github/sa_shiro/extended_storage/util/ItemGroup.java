package com.github.sa_shiro.extended_storage.util;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

public class ItemGroup {
    public static net.minecraft.item.ItemGroup itemGroup = new net.minecraft.item.ItemGroup("extended_storage") {
        // TODO: change ItemGroup Icon
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.STONE);
        }

        @Override
        public boolean hasScrollbar() {
            return true;
        }
    };
}
