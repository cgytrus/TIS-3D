package li.cil.tis3d.common.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.tis3d.util.RegistryUtils;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.CustomData;

import java.util.function.UnaryOperator;

public final class DataComponents {
    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = RegistryUtils.get(Registries.DATA_COMPONENT_TYPE);

    // --------------------------------------------------------------------- //

    public static final RegistrySupplier<DataComponentType<CustomData>> CODE_DATA =
        register("code_data", builder -> builder.persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC));

    public static final RegistrySupplier<DataComponentType<CustomData>> KEY =
        register("key", builder -> builder.persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC));

    public static final RegistrySupplier<DataComponentType<CustomData>> ROM_DATA =
        register("rom_data", builder -> builder.persistent(CustomData.CODEC).networkSynchronized(CustomData.STREAM_CODEC));

    // --------------------------------------------------------------------- //

    public static void initialize() {
        DATA_COMPONENT_TYPES.register();
    }

    private static <T> RegistrySupplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> (builder.apply(DataComponentType.builder())).build());
    }
}
