package com.example.echomod.item;

import com.example.echomod.entity.BoomerangEntity;
import com.example.echomod.registry.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BoomerangItem extends Item {
    public BoomerangItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 0.8F, 1.0F);

        if (!world.isClient) {
            BoomerangEntity boomerang = new BoomerangEntity(ModEntities.BOOMERANG, world, user);
            boomerang.setItem(stack.copyWithCount(1));
            boomerang.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.2F, 0.0F);
            world.spawnEntity(boomerang);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
