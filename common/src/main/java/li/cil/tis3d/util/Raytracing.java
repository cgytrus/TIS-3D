package li.cil.tis3d.util;

import net.minecraft.core.BlockPos;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static net.minecraft.world.level.BlockGetter.traverseBlocks;

/**
 * Custom ray-tracing implementation for ray-block tests, to allow custom
 * filter methods for blocks to take into account.
 */
public final class Raytracing {
    /**
     * Trace along the specified line, testing for collision with blocks along the way.
     *
     * @param level        the level to shoot the ray in.
     * @param start        the start of the line to trace.
     * @param end          the end of the line to trace.
     * @param filter       the method to call for each potential hit to filter out entities.
     * @param shapeGetter  the method to call for each potential hit to get the shape to test against.
     * @return the first detected hit, or {@code null} if there was none.
     */
    @Nullable
    public static HitResult block(Level level, Vec3 start, Vec3 end, BiPredicate<BlockState, BlockPos> filter,
                                  PropertyDispatch.TriFunction<BlockState, BlockGetter, BlockPos, VoxelShape> shapeGetter) {
        return traverseBlocks(
            start, end, null,
            (unused, blockPos) -> {
                BlockState state = level.getBlockState(blockPos);
                if (!filter.test(state, blockPos)) {
                    return null;
                }
                VoxelShape shape = shapeGetter.apply(state, level, blockPos);
                if (shape.isEmpty()) {
                    return null;
                }
                return shape.clip(start, end, blockPos);
            },
            unused -> null
        );
    }

    /**
     * Trace along the specified line, testing for collision with entities along the way.
     *
     * @param level    the level to shoot the ray in.
     * @param start    the start of the line to trace.
     * @param end      the end of the line to trace.
     * @param ignore   the entity to ignore during the cast.
     * @param filter   the method to call for each potential hit to filter out entities.
     * @return the first detected hit, or {@code null} if there was none.
     */
    @Nullable
    public static HitResult entity(Level level, Vec3 start, Vec3 end, Entity ignore, Predicate<Entity> filter) {
        return ProjectileUtil.getEntityHitResult(ignore, start, end, new AABB(start, end), filter, Double.MAX_VALUE);
    }

    // --------------------------------------------------------------------- //

    private Raytracing() {
    }
}
