package net.builderdog.candylands.block;

import net.builderdog.candylands.Candylands;
import net.builderdog.candylands.item.CandylandsItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class CandylandsBlocks extends CandylandsBlockBuilders {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Candylands.MODID);

    public static final DeferredBlock<Block> GLAZE = register("glaze", () -> cream(MapColor.WOOL));
    public static final DeferredBlock<Block> FROZEN_YOGHURT = register("frozen_yoghurt", () -> cream(MapColor.SNOW));
    public static final DeferredBlock<Block> STRAWBERRY_CREAM = register("strawberry_cream", () -> cream(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> VANILLA_CREAM = register("vanilla_cream", () -> cream(MapColor.SAND));
    public static final DeferredBlock<Block> CHOCOLATE_CREAM = register("chocolate_cream", () -> cream(MapColor.COLOR_BROWN));

    public static final DeferredBlock<Block> JELLY = register("jelly", () -> jelly(MapColor.TERRACOTTA_PINK));
    public static final DeferredBlock<Block> DARK_JELLY = register("dark_jelly", () -> jelly(MapColor.TERRACOTTA_RED));

    private static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = BLOCKS.register(name, block);
        CandylandsItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <B extends Block> DeferredBlock<B> register(String name, Supplier<B> block) {
        return baseRegister(name, block, CandylandsBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> deferredBlock) {
        return () -> {
            DeferredBlock<T> block = Objects.requireNonNull(deferredBlock);
            return new BlockItem(block.get(), new Item.Properties());
        };
    }
}