package me.enderkill98.expose_n00bbots.render.model;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Environment(value=EnvType.CLIENT)
public class RecIndicatorModel<T extends LivingEntity>
        extends EntityModel<T> {
    public final ModelPart root;
    public final ModelPart indicator;
    public final ModelPart head;

    public RecIndicatorModel(ModelPart modelPart, ModelPart head) {
        super(RenderLayer::getEntityTranslucentCull);
        this.root = modelPart;
        this.indicator = modelPart.getChild("rec_indicator");
        this.head = head;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPart = modelData.getRoot();
        modelPart.addChild("rec_indicator",
                ModelPartBuilder.create().uv(0, 0)
                        .cuboid(-4f, -8f, -4f - MathHelper.EPSILON*2, 8f, 8f, 0f),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0F, 0, 0)
        );
        return TexturedModelData.of(modelData, 8, 8);
    }

    @Override
    public void setAngles(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.indicator.copyTransform(this.head);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
