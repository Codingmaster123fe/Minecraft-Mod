package com.example.echomod.registry;

import com.example.echomod.EchoMod;
import com.example.echomod.entity.BoomerangEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<BoomerangEntity> BOOMERANG = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(EchoMod.MOD_ID, "boomerang"),
            EntityType.Builder.<BoomerangEntity>create(BoomerangEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5f, 0.5f)
                    .trackRangeChunks(8)
                    .trackedUpdateRate(10)
                    .build("boomerang")
    );

    public static void register() {
        // Entity is registered statically above; add more if needed
    }
}
