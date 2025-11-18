package net.orbit.block.entity.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.orbit.block.entity.custom.PedestalBlockEntity;
import net.orbit.block.entity.custom.PedestalRenderConfig;

import static java.lang.Math.*;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;
    private final BlockRenderManager blockRenderManager;
    private final ItemRenderer itemRenderer;
    private PedestalRenderConfig renderConfig;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
        this.renderConfig = PedestalRenderConfig.defaultMultiItem(); // Default config
        this.blockRenderManager = ctx.getRenderManager();
        this.itemRenderer = ctx.getItemRenderer();
    }

    public void setRenderConfig(PedestalRenderConfig config) {
        this.renderConfig = config;
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    private void renderSingleItem(ItemStack item, PedestalBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        Vec3d offset = renderConfig.itemOffset();
        matrices.translate(offset.x, offset.y, offset.z);

        float levitation = (float) sin(entity.getRenderingRotation() * renderConfig.levitationSpeed())
                * renderConfig.levitationAmplitude();
        matrices.translate(0, levitation, 0);

        Vec3d rotation = renderConfig.itemRotation().add(renderConfig.rotationStep().multiply(entity.getRenderingRotation()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) rotation.x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) rotation.y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) rotation.z));

        float scale = renderConfig.itemScale();
        matrices.scale(scale, scale, scale);

        renderItemOrBlock(item, matrices, entity, vertexConsumers, light);

        matrices.pop();
    }

    private void renderItemOrBlock(ItemStack item, MatrixStack matrices, PedestalBlockEntity entity, VertexConsumerProvider vertexConsumers, int light) {
        if (!(item.getItem() instanceof BlockItem blockItem) || renderConfig.forceRenderItem()) {
            itemRenderer.renderItem(item, ItemDisplayContext.GUI, light,
                    OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        } else {
            matrices.translate(-0.5, -0.5, -0.5);
            BlockState blockState = blockItem.getBlock().getDefaultState();
            blockRenderManager.renderBlockAsEntity(blockState, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
        }
    }

    private void renderMultiItems(DefaultedList<ItemStack> items, PedestalBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        float radius = renderConfig.radius();

        matrices.push();
        Vec3d offset = renderConfig.itemOffset();
        matrices.translate(offset.x, offset.y, offset.z);
        Vec3d rotation = renderConfig.itemRotation();
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) rotation.x));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) rotation.y));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float) rotation.z));
        for (int i = 0; i < items.size(); i++) {
            matrices.push();

            float angle = (float) (2 * PI * i / items.size()) + entity.getRenderingRotation();
            float x = (float) cos(angle) * radius;
            float z = (float) sin(angle) * radius;
            float delta = renderConfig.multiItemLevitationAmplitude();
            float fx = (float) (x * cos(angle) + z * sin(angle));
            float fz = (float) (z * cos(angle) - x * sin(angle));
            matrices.translate((x), (fx*fz*delta), (z));

            float levitation = (float) sin(angle * renderConfig.levitationSpeed())
                    * renderConfig.levitationAmplitude();
            matrices.translate(0, levitation, 0);

            float scale = renderConfig.itemScale();
            matrices.scale(scale, scale, scale);
            if (renderConfig.multiItemFancyRotation()){
    //            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getRenderingRotation()));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRenderingRotation() + (i * 360f / items.size())));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45f * x * z));
            }

            renderItemOrBlock(items.get(i), matrices, entity, vertexConsumers, light);

            matrices.pop();
        }
        matrices.pop();
    }

    @Override
    public void render(PedestalBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        setRenderConfig(entity.getRenderConfig());
        DefaultedList<ItemStack> items = entity.getNonNullItems();

        if (items.isEmpty()) return;

        if (items.size() == 1) {
            renderSingleItem(items.getFirst(), entity, matrices, vertexConsumers, light);
        } else {
            renderMultiItems(items, entity, matrices, vertexConsumers, light);
        }

        entity.updateRotation(renderConfig.rotationSpeed());
    }
}
