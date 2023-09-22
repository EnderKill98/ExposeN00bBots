package me.enderkill98.expose_n00bbots;

import me.enderkill98.expose_n00bbots.render.model.RecIndicatorModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ClientMod implements ClientModInitializer {

	public static final EntityModelLayer PLAYER_REC_INDICATOR_LAYER = new EntityModelLayer(new Identifier("expose_n00bbots", "player"), "rec_indicator");

	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(PLAYER_REC_INDICATOR_LAYER, RecIndicatorModel::getTexturedModelData);
	}
}