package li.cil.tis3d.data.fabric;

import li.cil.tis3d.common.block.Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public final class ModLootTableProvider {
    public static class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
        protected ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            dropSelf(Blocks.CASING.get());
            dropSelf(Blocks.CONTROLLER.get());
        }
    }
}
