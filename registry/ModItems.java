package com.example.echomod.registry;

import com.example.echomod.EchoMod;
import com.example.echomod.item.BoomerangItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item BOOMERANG = new BoomerangItem(new FabricItemSettings().maxCount(1).group(ItemGroup.COMBAT));
    public static final Item GLOWSHROOM_BLOCK_ITEM = new BlockItem(ModBlocks.GLOWSHROOM_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(EchoMod.MOD_ID, "boomerang"), BOOMERANG);
        Registry.register(Registry.ITEM, new Identifier(EchoMod.MOD_ID, "glowshroom_block"), GLOWSHROOM_BLOCK_ITEM);
    }
}
