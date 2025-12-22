package li.cil.tis3d.data.fabric;

import li.cil.tis3d.common.tags.BlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

import static li.cil.tis3d.common.item.Items.*;
import static li.cil.tis3d.common.tags.ItemTags.*;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagsProvider(final FabricDataOutput output, final CompletableFuture<HolderLookup.Provider> registries,
                               FabricTagProvider.BlockTagProvider blockTagProvider) {
        super(output, registries, blockTagProvider);
    }

    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        copy(BlockTags.COMPUTERS, COMPUTERS);

        tag(MODULES).add(
            key(AUDIO_MODULE.get()),
            key(DISPLAY_MODULE.get()),
            key(EXECUTION_MODULE.get()),
            key(FACADE_MODULE.get()),
            key(INFRARED_MODULE.get()),
            key(KEYPAD_MODULE.get()),
            key(QUEUE_MODULE.get()),
            key(RANDOM_MODULE.get()),
            key(RANDOM_ACCESS_MEMORY_MODULE.get()),
            key(READ_ONLY_MEMORY_MODULE.get()),
            key(REDSTONE_MODULE.get()),
            key(SEQUENCER_MODULE.get()),
            key(SERIAL_PORT_MODULE.get()),
            key(STACK_MODULE.get()),
            key(TERMINAL_MODULE.get()),
            key(TIMER_MODULE.get())
        );

        tag(BOOKS).add(
            key(BOOK_CODE.get()),
            key(BOOK_MANUAL.get()),
            key(Items.BOOK),
            key(Items.ENCHANTED_BOOK),
            key(Items.WRITABLE_BOOK),
            key(Items.WRITTEN_BOOK)
        );

        tag(KEYS).add(
            key(KEY.get()),
            key(KEY_CREATIVE.get())
        );
    }

    private static ResourceKey<Item> key(final Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
    }
}
