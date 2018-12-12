package yuma140902.uptodatemod.blocks.generics;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.items.generics.ItemBlockGenericSlab;
import yuma140902.uptodatemod.util.Stat;

public class BlockGenericSlab extends BlockSlab implements IRegisterable {

	private Block baseBlock;
	private int meta;
	private String name;
	private BlockGenericSlab slab;
	private BlockGenericSlab slabDouble;
	
	public BlockGenericSlab(Block baseBlock, int meta, String name) {
		this(false, baseBlock, meta, name);
	}
	
	protected BlockGenericSlab(boolean isDouble, Block baseBlock, int meta, String name) {
		super(isDouble, baseBlock.getMaterial());
		this.baseBlock = baseBlock;
		this.meta = meta;
		this.name = name;
    this.setStepSound(baseBlock.stepSound);
    this.setHarvestLevel(baseBlock.getHarvestTool(0), baseBlock.getHarvestLevel(0));
		setLightOpacity(0);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	private void setSlabs(BlockGenericSlab slab, BlockGenericSlab slabDouble) {
		this.slab = slab;
		this.slabDouble = slabDouble;
	}
	
	@Override
	public void register() {
		if(isDouble()) return;
		
		BlockGenericSlab slabDouble = new BlockGenericSlab(true, this.baseBlock, this.meta, this.name);
		this.setSlabs(this, slabDouble);
		slabDouble.setSlabs(this, slabDouble);
		
		this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(this, ItemBlockGenericSlab.class, name);
		slabDouble.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		GameRegistry.registerBlock(slabDouble, ItemBlockGenericSlab.class, "double_" + name);
	}
	
	public BlockGenericSlab getSlab() {
		return this.slab;
	}
	
	public BlockGenericSlab getSlabDouble() {
		return this.slabDouble;
	}
	
	public boolean isDouble() {
		return this.field_150004_a;
	}
	
	public void registerRecipe() {
		if(isDouble()) return;
		GameRegistry.addRecipe(
				new ItemStack(getSlab(), 6),
				"###",
				'#', new ItemStack(baseBlock, 1, meta)
				);
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return baseBlock.getBlockHardness(world, x, y, z);
	}
	
	@Override
	public float getExplosionResistance(Entity entity) {
		return baseBlock.getExplosionResistance(entity);
	}
	
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(this.slab);
	}
	
	@Override
	public int damageDropped(int p_149692_1_) {
		return 0;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(this.slab);
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return isDouble() ? 2 : 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return baseBlock.getIcon((meta & 0b0001) == 0 ? side : Stat.SIDE_TOP, this.meta);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		if(!isDouble())
			super.getSubBlocks(item, creativeTab, list);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		return ModUpToDateMod.MOD_ID + "." + name;
	}
}
