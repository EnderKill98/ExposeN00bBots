package me.enderkill98.expose_n00bbots.mixin.client;

import me.enderkill98.expose_n00bbots.ClientMod;
import me.enderkill98.expose_n00bbots.render.feature.RecIndicatorFeature;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    public void constructor(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo info) {
        FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context = (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) (Object)this;
        ((LivingEntityRendererInvoker) this).invokeAddFeature(new RecIndicatorFeature<>(context, ctx.getPart(ClientMod.PLAYER_REC_INDICATOR_LAYER), context.getModel().getHead()));
    }

}
