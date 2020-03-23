package yuma140902.uptodatemod;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.integration.Plugins;
import yuma140902.uptodatemod.network.ArmorStandInteractHandler;
import yuma140902.uptodatemod.network.ArmorStandInteractMessage;
import yuma140902.uptodatemod.network.NoteBlockPlayHandler;
import yuma140902.uptodatemod.network.NoteBlockPlayMessage;
import yuma140902.uptodatemod.proxy.CommonProxy;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.UpdateChecker;
import yuma140902.uptodatemod.world.generation.MyMinableGenerator;

@Mod(modid = ModUpToDateMod.MOD_ID, name = ModUpToDateMod.MOD_NAME, version = ModUpToDateMod.MOD_VERSION, useMetadata = true, guiFactory = Stat.MOD_CONFIG_GUI_FACTORY,
			dependencies = "after:etfuturum;after:ProjectE"
		)
public class ModUpToDateMod {
	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.Instance
	public static ModUpToDateMod INSTANCE;
	
	@SidedProxy(clientSide = Stat.PROXY_CLIENT, serverSide = Stat.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper networkWrapper;
	
	public static final String MOD_ID = "uptodate";
	public static final String MOD_NAME = "UpToDateMod";
	public static final String MOD_TEXTURE_DOMAIN = "uptodate";
	public static final String MOD_UNLOCALIZED_ENTRY_DOMAIN = "uptodate";
	public static final String MINECRAFT_VERSION = "1.7.10";
	public static final String MOD_VERSION = "2.1.2";
	public static final String MOD_VERSIONS_TSV_URL = "https://raw.githubusercontent.com/yuma140902/UpdateJSON_Forge/master/UpToDateModVersions.tsv";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
	
	public Path uptodatemodDirectory;
	
	public static int glazedTerracottaRenderId;
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = MOD_ID;
		modMetadata.name = MOD_NAME;
		modMetadata.version = MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "Adds new Minecraft 1.8.0+ features";
		modMetadata.url = "https://www.curseforge.com/minecraft/mc-mods/uptodatemod";
		modMetadata.autogenerated = false;
	}
	
	private void tweakVanilla() {
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		BlockTrapDoor.disableValidation = true;
		Items.blaze_rod.setFull3D();
		
		Blocks.trapped_chest.setCreativeTab(CreativeTabs.tabRedstone);
		
		setFinalField(ItemFood.class, Items.carrot, 3, "healAmount", "field_77853_b");
		setFinalField(ItemFood.class, Items.baked_potato, 5, "healAmount", "field_77853_b");
		
		Blocks.packed_ice.setHarvestLevel("pickaxe", 0);
		Blocks.ladder.setHarvestLevel("axe", 0);
		Blocks.melon_block.setHarvestLevel("axe", 0);
	}
	
	private void setFinalField(Class<?> clazz, Object that, Object newValue, String... fieldNames) {
		try {
			Field field = ReflectionHelper.findField(clazz, fieldNames);
			field.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(that, newValue);
		} catch (Exception e) {
			LOGGER.warn("Failed to tweak a property.");
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		loadModMetadata(modMetadata);
		ModConfigCore.loadConfig(event);
		LOGGER.info("preInit");
		try {
			UpdateChecker.INSTANCE.checkForUpdates();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info(UpdateChecker.INSTANCE.hasNewVersionAvailable() ? "There is a new version available. - v" + UpdateChecker.INSTANCE.availableNewVersion + ". Visit " + UpdateChecker.INSTANCE.getNewVersionUrl() : "UpToDateMod is now up-to-date.");
		
		this.uptodatemodDirectory = Paths.get("uptodatemod").toAbsolutePath();
		proxy.loadVanillaResources();

		
		tweakVanilla();
		MyBlocks.register();
		MyItems.register();
		
		MyTileEntities.register();
		proxy.registerTileEntities();
		MyGuis.register();
		
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		networkWrapper.registerMessage(ArmorStandInteractHandler.class, ArmorStandInteractMessage.class, 0, Side.SERVER);
//		networkWrapper.registerMessage(NoteBlockPlayHandler.class, NoteBlockPlayMessage.class, 1, Side.SERVER);
		networkWrapper.registerMessage(NoteBlockPlayHandler.class, NoteBlockPlayMessage.class, 1, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("init");
		Recipes.removeVanillaRecipes();
		Recipes.register();
		
		proxy.registerEntities();
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.glazedTerracotta))
			glazedTerracottaRenderId = proxy.getNewRenderId();
		proxy.registerRenderers();
		
		
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.stones)) {
			MyMinableGenerator.Config stoneConfig = new MyMinableGenerator.Config(ModConfigCore.worldGen_genStones, 33, 10, 0, 80, ModConfigCore.worldGen_genStones_blackList);
			
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_GRANITE, stoneConfig);
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_DIORITE, stoneConfig);
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_ANDESITE, stoneConfig);
		}
		WorldGenerators.register();
		
		proxy.registerEventHandlers();
		
		Plugins.tweakMods();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.info("postInit");
		Plugins.tweakModsPost();
		Plugins.logPluginStats();
	}
}
