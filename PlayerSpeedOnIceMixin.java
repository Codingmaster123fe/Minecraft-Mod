package com.example.echomod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerSpeedOnIceMixin {
    @Shadow public abstract World getWorld();
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract boolean isSneaking();
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "tick", at = @At("HEAD"))
    private void giveSpeedOnIce(CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity)(Object)this;
        BlockState blockBelow = getWorld().getBlockState(getBlockPos().down());

        if ((blockBelow.isOf(Blocks.ICE) || blockBelow.isOf(Blocks.PACKED_ICE) || blockBelow.isOf(Blocks.BLUE_ICE))
            && !self.isSneaking()) {
            addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1, false, false));
        }
    }
}
