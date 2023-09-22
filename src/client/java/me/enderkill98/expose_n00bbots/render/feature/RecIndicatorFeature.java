package me.enderkill98.expose_n00bbots.render.feature;

import me.enderkill98.expose_n00bbots.render.model.RecIndicatorModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class RecIndicatorFeature<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private RecIndicatorModel<T> model;
    private static final Identifier TEXTURE = new Identifier("expose_n00bbots", "textures/entity/player/rec_indicator/rec_indicator.png");

    public RecIndicatorFeature(FeatureRendererContext<T, M> context, ModelPart part, ModelPart head) {
        super(context);
        this.model = new RecIndicatorModel<T>(part, head);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T livingEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(!(livingEntity instanceof AbstractClientPlayerEntity player)) return;
        if(!player.getGameProfile().getName().startsWith("N00bBot")) return;

        if(livingEntity.age % 20 < 10) return; // Blink 0,5s off, 0,5s on

        getContextModel().copyStateTo(this.model);
        model.animateModel(livingEntity, limbAngle, limbDistance, tickDelta);

        model.setAngles(livingEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(model.getLayer(TEXTURE));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
