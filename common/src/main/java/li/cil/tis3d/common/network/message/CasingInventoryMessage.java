package li.cil.tis3d.common.network.message;

import dev.architectury.networking.NetworkManager;
import li.cil.tis3d.api.machine.Casing;
import li.cil.tis3d.common.block.entity.CasingBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public final class CasingInventoryMessage extends AbstractMessageWithPosition {
    private int slot;
    private Tag stack;
    private CompoundTag moduleData;

    public CasingInventoryMessage(final Casing casing, final int slot, final ItemStack stack, @Nullable final CompoundTag moduleData) {
        super(casing.getPosition());
        this.slot = slot;
        this.stack = stack.saveOptional(casing.getCasingLevel().registryAccess());
        this.moduleData = moduleData;
    }

    public CasingInventoryMessage(final FriendlyByteBuf buffer) {
        super(buffer);
    }

    // --------------------------------------------------------------------- //
    // AbstractMessage

    @Override
    public void handleMessage(final NetworkManager.PacketContext context) {
        final Level level = getClientLevel();
        if (level != null) {
            withBlockEntity(level, CasingBlockEntity.class, casing ->
                casing.setStackAndModuleClient(slot, ItemStack.parseOptional(context.registryAccess(), (CompoundTag)stack), moduleData));
        }
    }

    @Override
    public void fromBytes(final FriendlyByteBuf buffer) {
        super.fromBytes(buffer);

        slot = buffer.readUnsignedByte();
        stack = buffer.readNbt();
        moduleData = buffer.readNbt();

        if (stack == null) {
            stack = new CompoundTag();
        }
    }

    @Override
    public void toBytes(final FriendlyByteBuf buffer) {
        super.toBytes(buffer);
        buffer.writeByte(slot);
        buffer.writeNbt(stack);
        buffer.writeNbt(moduleData);
    }
}
