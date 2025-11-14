package net.skebob.block.entity.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.skebob.block.entity.custom.PedestalBlockEntity;

import static java.lang.Math.*;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;
    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }
    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    @Override
    public void render(PedestalBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        DefaultedList<ItemStack> items = entity.getNonNullItems();
        float radius = 1.2f;

        for (int i = 0; i < items.size(); i++) {
            matrices.push();

            float angle = (float) (2 * PI * i / (items.size())) + entity.getRenderingRotation();
            float x = (float) cos(angle) * radius;
            float z = (float) sin(angle) * radius;
            matrices.translate((0.5f + x), 1.15f+cos(angle)/3, (0.5f + z));
            if (items.get(i).isOf(Items.END_CRYSTAL)) {
                float s = 0.8f;
                matrices.scale(s, s, s);
                EndCrystalEntity crystal = EntityType.END_CRYSTAL.create(entity.getWorld(), SpawnReason.COMMAND);
                if(crystal != null && entity.getWorld() != null) {
                    crystal.setShowBottom(false);
                    crystal.age = (int) entity.getRenderingRotation();
                    crystal.speed = 0.0f;
                }
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0));
                entityRenderDispatcher.render(crystal, 0, 0, 0, 0, matrices, vertexConsumers, light);
            } else {
                float s = 1.8f;
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRenderingRotation()+(i*360f / items.size())));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45f*x*z));
                matrices.scale(-s, s, -s);

                itemRenderer.renderItem(items.get(i), ItemDisplayContext.GUI, getLightLevel(entity.getWorld(),
                    entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            }

            matrices.pop();
        entity.updateRotation(0.0100f);
        }
    }
}
