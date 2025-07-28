package com.example.echomod.registry;

import com.example.echomod.EchoMod;
import com.example.echomod.block.GlowshroomBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block GLOWSHROOM_BLOCK = new GlowshroomBlock(Block.Settings.of(Material.PLANT)
            .strength(1.0f)
            .sounds(BlockSoundGroup.WOOD)
    );

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(EchoMod.MOD_ID, "glowshroom_block"), GLOWSHROOM_BLOCK);
    }
}
