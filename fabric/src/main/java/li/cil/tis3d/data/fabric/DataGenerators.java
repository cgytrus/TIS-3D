package li.cil.tis3d.data.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(final FabricDataGenerator generator) {
        final var pack = generator.createPack();

        final var blockTags = pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider((a, b) -> new ModItemTagsProvider(a, b, blockTags));
        pack.addProvider(ModLootTableProvider.ModBlockLootTableProvider::new);
        pack.addProvider(ModRecipesProvider::new);
    }
}
