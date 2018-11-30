package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSandStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.items.ItemBlockRedSandStone;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockRedSandStone extends BlockSandStone implements IRegisterable {
	
	public static final String[] names = new String[] {"", "chiseled", "cut"};
  @SideOnly(Side.CLIENT)
  private IIcon[] sideIcons;
  @SideOnly(Side.CLIENT)
  private IIcon topIcon;
  @SideOnly(Side.CLIENT)
  private IIcon bottomIcon;
	
	public BlockRedSandStone() {
		setStepSound(soundTypePiston);
		setHardness(0.8F);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".red_sandstone");
		setBlockTextureName(ModUpToDateMod.MOD_ID + ":red_sandstone");
		GameRegistry.registerBlock(this, ItemBlockRedSandStone.class, "red_sandstone");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side != 1 && (side != 0 || meta != 1 && meta != 2)) {
			if (side == 0) {
				return this.bottomIcon;
			}
			else {
				if (meta < 0 || meta >= this.sideIcons.length) meta = 0;
				return this.sideIcons[meta];
			}
		}
		else {
			return this.topIcon;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.sideIcons = new IIcon[names.length];
		
		for (int i = 0; i < this.sideIcons.length; ++i) {
			this.sideIcons[i] = register.registerIcon(this.getTextureName() + StringUtil.add("_", names[i]));
		}
		
		this.topIcon = register.registerIcon(this.getTextureName() + "_top");
		this.bottomIcon = register.registerIcon(this.getTextureName() + "_bottom");
	}
}
