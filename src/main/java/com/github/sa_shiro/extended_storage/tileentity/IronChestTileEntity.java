package com.github.sa_shiro.extended_storage.tileentity;

import com.github.sa_shiro.extended_storage.block.IronChestBlock;
import com.github.sa_shiro.extended_storage.container.IronChestContainer;
import com.github.sa_shiro.extended_storage.event.DeferredRegistryEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IronChestTileEntity extends LockableLootTileEntity {

    private static final int rows = IronChestContainer.numRows;
    private static final int columns = IronChestContainer.numColumns;
    protected int playersUsing;
    private String translationKey = "container.iron_chest";
    private NonNullList<ItemStack> chestContent = NonNullList.withSize(rows * columns, ItemStack.EMPTY);
    private IItemHandlerModifiable items = createHandler();
    private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

    public IronChestTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public IronChestTileEntity() {
        this(DeferredRegistryEvent.IRON_CHEST_TILE_ENTITY.get());
    }

    public static void swapContents(IronChestTileEntity tileEntityIn, IronChestTileEntity tileEntityOut) {
        NonNullList<ItemStack> list = tileEntityIn.getItems();
        tileEntityIn.setItems(tileEntityOut.getItems());
        tileEntityOut.setItems(list);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    private void playSound(SoundEvent sound) {
        double dX = (double) this.pos.getX() + 0.5D;
        double dY = (double) this.pos.getY() + 0.5D;
        double dZ = (double) this.pos.getZ() + 0.5D;

        assert this.world != null;
        this.world.playSound(null, dX, dY, dZ, sound, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof IronChestBlock) {
            assert this.world != null;
            this.world.addBlockEvent(this.pos, block, 1, this.playersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    public int getPlayersUsing(IBlockReader reader, BlockPos pos) {
        BlockState blockState = reader.getBlockState(pos);
        if (blockState.hasTileEntity()) {
            TileEntity tileEntity = reader.getTileEntity(pos);
            if (tileEntity instanceof IronChestTileEntity) {
                return ((IronChestTileEntity) tileEntity).playersUsing;
            }
        }
        return 0;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.chestContent;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.chestContent = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(translationKey);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new IronChestContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return rows * columns;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestContent);
        }
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.chestContent = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.chestContent);
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.playersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.playersUsing < 0) {
                this.playersUsing = 0;
            }
        }

        ++this.playersUsing;
        this.onOpenOrClose();
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.playersUsing;
            this.onOpenOrClose();
        }
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.itemHandler != null) {
            this.itemHandler.invalidate();
            this.itemHandler = null;
        }
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        if (itemHandler != null) {
            itemHandler.invalidate();
        }
    }
}
