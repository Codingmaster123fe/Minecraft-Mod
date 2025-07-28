package com.example.echomod;

import com.example.echomod.command.RecallCommand;
import com.example.echomod.registry.ModBlocks;
import com.example.echomod.registry.ModEntities;
import com.example.echomod.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoMod implements ModInitializer {
    public static final String MOD_ID = "echomod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModEntities.register();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> RecallCommand.register(dispatcher));
        LOGGER.info("EchoMod initialized with all features!");
    }
}
