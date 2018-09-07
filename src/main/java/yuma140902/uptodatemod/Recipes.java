package yuma140902.uptodatemod;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import yuma140902.uptodatemod.blocks.Stone;

public final class Recipes {
	private Recipes() {}
	
	public static void removeVanillaRecipes() {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		List<IRecipe> removeList = new ArrayList<IRecipe>();
		for (IRecipe recipe : recipes) {
			//see: http://forum.minecraftuser.jp/viewtopic.php?f=39&t=33757
      // nullチェックは十分に
      // これを怠ると、レシピ大量自動追加系によってnullクラッシュを喰らうことがあるので
      if(recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() != null){
         // 一致判定
         if("minecraft:wooden_door".equals(GameData.getItemRegistry().getNameForObject(recipe.getRecipeOutput().getItem()))){
            // このループ内では削除はせず、いったん削除予定リストに入れる
            removeList.add(recipe);
            break; //削除するレシピが一つしかないので
         }
      }
   }
   
   // 削除する
   for(IRecipe remove : removeList){
      // removeListに保存したレシピをrecipesから除去している
      recipes.remove(remove);
   }
	}
	
	public static void register() {
		registerStoneRecipes();
		registerDoorRecipes();
		registerFenceRecipes();
	}
	
	private static void registerStoneRecipes() {
	//4つ並べて磨かれた〇〇
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_GRANITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_GRANITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_DIORITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE));
			
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_ANDESITE), 
					"##",
					"##",
					'#', new ItemStack(MyBlocks.stone, 1, Stone.META_ANDESITE));
			
			//丸石+ネザー水晶->閃緑岩
			GameRegistry.addRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_DIORITE),
					"SN",
					"NS",
					'S', Blocks.cobblestone,
					'N', Items.quartz
					);
			
			//閃緑岩+丸石->安山岩
			GameRegistry.addShapelessRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_ANDESITE),
					Blocks.cobblestone,
					new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
					);
			
			//閃緑岩+ネザー水晶->花崗岩
			GameRegistry.addShapelessRecipe(
					new ItemStack(MyBlocks.stone, 2, Stone.META_GRANITE),
					Items.quartz,
					new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
					);
	}

	private static void registerDoorRecipes() {
		final int
				PLANK_META_OAK = 0,
				PLANK_META_ACACIA = 4,
				PLANK_META_BIRCH = 2,
				PLANK_META_DARKOAK = 5,
				PLANK_META_JUNGLE = 3,
				PLANK_META_SPRUCE = 1;
		
		GameRegistry.addRecipe(
				new ItemStack(Items.wooden_door, 1, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_OAK)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorAcacia, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_ACACIA)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorBirch, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_BIRCH)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorDarkOak, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_DARKOAK)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorJungle, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_JUNGLE)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorSpruce, 3, 0),
				"##",
				"##",
				"##",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_SPRUCE)
				);
	}
	
	private static void registerFenceRecipes() {
		final int
			PLANK_META_OAK = 0,
			PLANK_META_ACACIA = 4,
			PLANK_META_BIRCH = 2,
			PLANK_META_DARKOAK = 5,
			PLANK_META_JUNGLE = 3,
			PLANK_META_SPRUCE = 1;
		
		GameRegistry.addRecipe(
				new ItemStack(Blocks.fence, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_OAK),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceAcacia, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_ACACIA),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceBirch, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_BIRCH),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceDarkOak, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_DARKOAK),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceJungle, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_JUNGLE),
				'|', Items.stick
				);
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.fenceSpruce, 3, 0),
				"#|#",
				"#|#",
				'#', new ItemStack(Blocks.planks, 1, PLANK_META_SPRUCE),
				'|', Items.stick
				);
		
	}
}