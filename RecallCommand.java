package com.example.echomod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class RecallCommand {
    private static final HashMap<UUID, BlockPos> savedSpots = new HashMap<>();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("bindhere").executes(ctx -> {
            ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();
            BlockPos pos = player.getBlockPos();
            savedSpots.put(player.getUuid(), pos);

            ctx.getSource().sendFeedback(Text.literal("Saved your current spot! Use /recall to teleport back."), false);
            return 1;
        }));

        dispatcher.register(CommandManager.literal("recall").executes(ctx -> {
            ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();
            BlockPos pos = savedSpots.get(player.getUuid());

            if (pos == null) {
                ctx.getSource().sendError(Text.literal("No spot saved! Use /bindhere first."));
                return 0;
            }

            player.requestTeleport(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            ctx.getSource().sendFeedback(Text.literal("Teleported back to your saved spot!"), false);
            return 1;
        }));
    }
}
