package net.orbit.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;

public class GodSkebobHoeItem extends Item {
    public GodSkebobHoeItem(Settings settings) {
        super(settings);
    }
//    @Override
//    public ActionResult useOnBlock(ItemUsageContext context) {
//
//        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
//        if(bs.isIn(BlockTags.DIRT))
//        {
//            context.getWorld().setBlockState(context.getBlockPos(), Blocks.FARMLAND.getDefaultState());
//        }
//        return ActionResult.SUCCESS;
//    }

}
