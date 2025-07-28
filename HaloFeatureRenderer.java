package com.example.echomod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FeatureRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class HaloFeatureRenderer extends FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>> {
    private static final Identifier HALO_TEX = new Identifier("echomod", "textures/misc/halo.png");

    public HaloFeatureRenderer(PlayerEntityRenderer context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (player.experienceLevel < 30) return;

        matrices.push();
        getContextModel().head.rotate(matrices);
        matrices.translate(0.0, -0.45, 0.0);
        matrices.scale(1.2f, 1.2f, 1.2f);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(net.minecraft.client.render.RenderLayer.getEntityTranslucent(HALO_TEX));
        var matrix = matrices.peek().getPositionMatrix();

        float s = 0.35f;
        vertexConsumer.vertex(matrix, -s, 0, -s).texture(0, 0).next();
        vertexConsumer.vertex(matrix, s, 0, -s).texture(1, 0).next();
        vertexConsumer.vertex(matrix, s, 0, s).texture(1, 1).next();
        vertexConsumer.vertex(matrix, -s, 0, s).texture(0, 1).next();

        matrices.pop();
    }
}
