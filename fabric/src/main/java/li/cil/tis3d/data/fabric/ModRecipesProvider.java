package li.cil.tis3d.data.fabric;

import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.tis3d.api.API;
import li.cil.tis3d.common.item.Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public final class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> ignoredRegistries) {
        super(output, ignoredRegistries);
    }

    @Override
    public void buildRecipes(final RecipeOutput consumer) {
        ShapedRecipeBuilder
            .shaped(RecipeCategory.REDSTONE, Items.CASING.get(), 8)
            .pattern("IRI")
            .pattern("RSR")
            .pattern("IRI")
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('R', ConventionalItemTags.REDSTONE_DUSTS)
            .define('S', ConventionalItemTags.STORAGE_BLOCKS_IRON)
            .unlockedBy("has_redstone", inventoryChange(ConventionalItemTags.REDSTONE_DUSTS))
            .save(consumer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.REDSTONE, Items.CONTROLLER.get())
            .pattern("IRI")
            .pattern("RSR")
            .pattern("IRI")
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('R', ConventionalItemTags.REDSTONE_DUSTS)
            .define('S', ConventionalItemTags.DIAMOND_GEMS)
            .unlockedBy("has_redstone", inventoryChange(ConventionalItemTags.REDSTONE_DUSTS))
            .save(consumer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.TOOLS, Items.KEY.get())
            .pattern("GI ")
            .pattern("GI ")
            .pattern("LRQ")
            .define('L', ConventionalItemTags.LAPIS_GEMS)
            .define('G', ConventionalItemTags.GOLD_NUGGETS)
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('Q', ConventionalItemTags.QUARTZ_GEMS)
            .define('R', ConventionalItemTags.REDSTONE_DUSTS)
            .unlockedBy("has_casing", inventoryChange(Items.CASING.get()))
            .save(consumer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.PRISM.get())
            .requires(ConventionalItemTags.QUARTZ_GEMS)
            .requires(ConventionalItemTags.REDSTONE_DUSTS)
            .requires(ConventionalItemTags.LAPIS_GEMS)
            .requires(ConventionalItemTags.EMERALD_GEMS)
            .unlockedBy("has_execution_module", inventoryChange(Items.EXECUTION_MODULE.get()))
            .save(consumer);

        module(Items.AUDIO_MODULE, 2, net.minecraft.world.item.Items.NOTE_BLOCK, inventoryChange(Items.SEQUENCER_MODULE.get()))
            .save(consumer);
        module(Items.DISPLAY_MODULE, 2, Items.PRISM.get(), inventoryChange(Items.EXECUTION_MODULE.get()))
            .save(consumer);
        module(Items.EXECUTION_MODULE, 2, ConventionalItemTags.GOLD_INGOTS, inventoryChange(Items.REDSTONE_MODULE.get()))
            .save(consumer);
        module(Items.FACADE_MODULE, 8, net.minecraft.world.item.Items.PAPER, inventoryChange(Items.CASING.get()))
            .save(consumer);
        module(Items.INFRARED_MODULE, 2, net.minecraft.world.item.Items.SPIDER_EYE, inventoryChange(Items.REDSTONE_MODULE.get()))
            .save(consumer);
        module(Items.KEYPAD_MODULE, 2, ItemTags.BUTTONS, inventoryChange(Items.REDSTONE_MODULE.get()))
            .save(consumer);
        module(Items.RANDOM_MODULE, 2, ConventionalItemTags.ENDER_PEARLS, inventoryChange(Items.EXECUTION_MODULE.get()))
            .save(consumer);
        module(Items.RANDOM_ACCESS_MEMORY_MODULE, 2, ConventionalItemTags.EMERALD_GEMS, inventoryChange(Items.STACK_MODULE.get()))
            .save(consumer);
        module(Items.READ_ONLY_MEMORY_MODULE, 2, li.cil.tis3d.common.tags.ItemTags.BOOKS, inventoryChange(Items.STACK_MODULE.get()))
            .save(consumer);
        module(Items.REDSTONE_MODULE, 2, net.minecraft.world.item.Items.REPEATER, inventoryChange(ConventionalItemTags.REDSTONE_DUSTS))
            .save(consumer);
        module(Items.SEQUENCER_MODULE, 2, ConventionalItemTags.MUSIC_DISCS, inventoryChange(Items.QUEUE_MODULE.get()))
            .save(consumer);
        module(Items.SERIAL_PORT_MODULE, 2, ConventionalItemTags.QUARTZ_GEMS, inventoryChange(Items.EXECUTION_MODULE.get()))
            .save(consumer);
        module(Items.STACK_MODULE, 2, ConventionalItemTags.CHESTS, inventoryChange(Items.REDSTONE_MODULE.get()))
            .save(consumer);
        module(Items.TIMER_MODULE, 2, ConventionalItemTags.SANDS, inventoryChange(Items.EXECUTION_MODULE.get()))
            .save(consumer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, Items.TERMINAL_MODULE.get())
            .pattern("KDS")
            .pattern("IQI")
            .pattern(" R ")
            .define('K', Items.KEYPAD_MODULE.get())
            .define('D', Items.DISPLAY_MODULE.get())
            .define('S', Items.STACK_MODULE.get())
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('Q', ConventionalItemTags.QUARTZ_GEMS)
            .define('R', ConventionalItemTags.REDSTONE_DUSTS)
            .unlockedBy("has_keypad", inventoryChange(Items.KEYPAD_MODULE.get()))
            .save(consumer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.QUEUE_MODULE.get())
            .requires(Items.STACK_MODULE.get())
            .unlockedBy("has_stack", inventoryChange(Items.STACK_MODULE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(API.MOD_ID, Items.QUEUE_MODULE.getId().getPath() + "/from_stack"));
        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.STACK_MODULE.get())
            .requires(Items.QUEUE_MODULE.get())
            .unlockedBy("has_queue", inventoryChange(Items.QUEUE_MODULE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(API.MOD_ID, Items.STACK_MODULE.getId().getPath() + "/from_queue"));
    }

    private static ShapedRecipeBuilder module(final RegistrySupplier<? extends Item> module, final int count, final Item item, final Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy) {
        return module(module, count, unlockedBy)
            .define('S', item);
    }

    private static ShapedRecipeBuilder module(final RegistrySupplier<? extends Item> module, final int count, final TagKey<Item> tag, final Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy) {
        return module(module, count, unlockedBy)
            .define('S', tag);
    }

    private static ShapedRecipeBuilder module(final RegistrySupplier<? extends Item> module, final int count, final Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy) {
        return ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, module.get(), count)
            .pattern("PPP")
            .pattern("ISI")
            .pattern(" R ")
            .define('P', ConventionalItemTags.GLASS_PANES_COLORLESS)
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('R', ConventionalItemTags.REDSTONE_DUSTS)
            .unlockedBy("has_base_item", unlockedBy);
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryChange(final TagKey<Item> tag) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(tag).build());
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryChange(final ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
}
