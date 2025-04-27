package net.explorie.mod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class StructureFinderItem extends Item {
    public StructureFinderItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos centerPos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            int radius = 100;
            boolean foundBlock = false;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos checkedPos = centerPos.add(x, y, z);
                        BlockState state = context.getWorld().getBlockState(checkedPos);
                        if (isOutpostBlock(state)) {
                            outputValuableCoordinates(checkedPos, player, state.getBlock());
                            foundBlock = true;
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.literal("No structures found within " + radius + " blocks."), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found Structure" + " at " + "[" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + "]"), false);
    }

    private boolean isOutpostBlock(BlockState state) {
        return state.isOf(Blocks.PRISMARINE) ||
                state.isOf(Blocks.GRAY_GLAZED_TERRACOTTA) ||
                state.isOf(Blocks.REINFORCED_DEEPSLATE) ||
                state.isOf(Blocks.NETHER_BRICKS) ||
                state.isOf(Blocks.GILDED_BLACKSTONE) ||
                state.isOf(Blocks.PURPUR_BLOCK) ||
                state.isOf(Blocks.ORANGE_TERRACOTTA) ||
                state.isOf(Blocks.MOSSY_STONE_BRICKS) ||
                state.isOf(Blocks.SNOW_BLOCK) ||
                state.isOf(Blocks.END_PORTAL_FRAME) ||
                state.isOf(Blocks.SUSPICIOUS_SAND) ||
                state.isOf(Blocks.BELL) ||
                state.isOf(Blocks.DARK_OAK_LOG) ||
                state.isOf(Blocks.BOOKSHELF);
    }
}