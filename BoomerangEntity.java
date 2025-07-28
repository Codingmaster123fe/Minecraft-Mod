package com.example.echomod.entity;

import com.example.echomod.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BoomerangEntity extends ThrownItemEntity {
    public BoomerangEntity(EntityType<? extends BoomerangEntity> type, World world) {
        super(type, world);
    }

    public BoomerangEntity(EntityType<? extends BoomerangEntity> type, World world, LivingEntity owner) {
        super(type, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BOOMERANG;
    }

    @Override
    public void tick() {
        super.tick();

        if (!getWorld().isClient && getOwner() instanceof ServerPlayerEntity owner) {
            if (this.age > 10) {
                var toOwner = owner.getEyePos().subtract(this.getPos());
                this.setVelocity(toOwner.normalize().multiply(0.6));
            }

            if (this.squaredDistanceTo(owner) < 1.0) {
                ItemStack stack = new ItemStack(getDefaultItem());
                if (!owner.getInventory().insertStack(stack)) {
                    owner.dropItem(stack, false);
                }
                this.discard();
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        entityHitResult.getEntity().damage(getDamageSources().thrown(this, getOwner()), 5.0F);
    }
}
