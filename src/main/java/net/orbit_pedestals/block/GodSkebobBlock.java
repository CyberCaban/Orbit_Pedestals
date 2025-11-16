package net.orbit_pedestals.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;

public class GodSkebobBlock extends Block {
    public GodSkebobBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {

        if (world instanceof ServerWorldAccess serverWorld) {
            BlockPos bp = pos.add(
                    world.getRandom().nextInt(3) - 1,
                    world.getRandom().nextInt(3) - 1,
                    world.getRandom().nextInt(3) - 1
            );
            world.setBlockState(bp, state, Block.NOTIFY_ALL);
            serverWorld.getPlayers().forEach(player ->
                    player.sendMessage(Text.literal("Чюпеп")
                                    .formatted(Formatting.GREEN)
                                    .styled(style -> style.withBold(true)),
                            true));
        }
    }

}
