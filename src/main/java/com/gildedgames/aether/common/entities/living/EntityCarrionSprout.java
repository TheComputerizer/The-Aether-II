package com.gildedgames.aether.common.entities.living;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCarrionSprout extends EntityAetherAnimal
{
	public final static int sizeID = 13;

	private float maxSproutSize;

	@SideOnly(Side.CLIENT)
	public float sinage, prevSinage;

	public EntityCarrionSprout(World world)
	{
		super(world);

		this.setSproutSize(0.5F);
		this.setMaxSproutSize(0.5F + (this.rand.nextFloat() * 0.9F));
		this.setSize(0.5F, 1.4F);

		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();

		this.dataWatcher.addObject(sizeID, 0.0F);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setFloat("size", this.getSproutSize());
		nbt.setFloat("maxSize", this.getMaxSproutSize());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		this.setSproutSize(nbt.getFloat("size"));
		this.setMaxSproutSize(nbt.getFloat("maxSize"));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote)
		{
			this.clientUpdate();
		}
		else
		{
			BlockPos beneathPos = new BlockPos(this.posX, this.getEntityBoundingBox().minY - 0.1D, this.posZ);
			Block blockBeneath = this.worldObj.getBlockState(beneathPos).getBlock();

			if (blockBeneath != BlocksAether.aether_grass && blockBeneath != BlocksAether.aether_dirt)
			{
				this.setHealth(0);
			}
		}
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getSproutSize() < this.getMaxSproutSize())
		{
			this.setSproutSize(this.getSproutSize() + 0.0001f);
		}
	}

	@Override
	protected int getItemQuantityDropped()
	{
		return this.rand.nextInt((int) Math.ceil(this.getSproutSize() / 0.5f)) + 1;
	}

	@Override
	protected Item getDropItem()
	{
		return ItemsAether.wyndberry;
	}

	public boolean isFullyGrown()
	{
		return (this.getSproutSize() >= this.getMaxSproutSize());
	}

	public float getMaxSproutSize()
	{
		return this.maxSproutSize;
	}

	public void setMaxSproutSize(float x)
	{
		this.maxSproutSize = x;
	}

	public float getSproutSize()
	{
		return this.getDataWatcher().getWatchableObjectFloat(sizeID);
	}

	public void setSproutSize(float newSize)
	{
		this.getDataWatcher().updateObject(sizeID, newSize);
	}

	@SideOnly(Side.CLIENT)
	public void clientUpdate()
	{
		this.prevSinage = this.sinage;

		if (this.hurtTime > 0)
		{
			this.sinage += 0.9F;
		}
		else
		{
			this.sinage += 0.15F;
		}

		if (this.sinage > 3.141593F * 2F)
		{
			this.sinage -= (3.141593F * 2F);
		}
	}

	@Override
	public void jump()
	{
	} // remove jump

	@Override
	public void knockBack(Entity entityIn, float distance, double motionX, double motionY)
	{
	} // remove player damage knock-back

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
	}
}
