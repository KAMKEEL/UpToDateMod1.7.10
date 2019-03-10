package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.structure.Rotation2D;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

final class WoodlandMansionWalls {
	private WoodlandMansionWalls() {};
	
	/**
	 * 2階と3階に使われている外壁を生成する
	 * @param startX 壁の左(壁を外から見たとき)下手前のx座標
	 * @param startY 壁の左下手前のy座標
	 * @param startZ 壁の左下手前のz座標
	 * @param rotationYaw 回転
	 * @param world
	 */
	public static void generateOuterWallType1(int startX, int startY, int startZ, Rotation2D rotationYaw, World world) {
		StructureRelativeCoordinateSystem relCoord
			= new StructureRelativeCoordinateSystem(startX, startY, startZ, rotationYaw, world);
		
//		System.out.println(String.format("WallType1 will spawn at (%d, %d, %d) = (%d, %d, %d)", startX, startY, startZ, relCoord.originX, relCoord.originY, relCoord.originZ));
		
		
		// 手前の層
		
		relCoord.setBlock(0, 0, 0, Blocks.cobblestone_wall);
		relCoord.setBlockAndRotatedMeta(1, 0, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH);
		relCoord.setBlock(2, 0, 0, Blocks.cobblestone_wall);
		relCoord.setBlock(3, 0, 0, Blocks.cobblestone_wall);
		relCoord.setBlock(4, 0, 0, Blocks.cobblestone_wall);
		relCoord.setBlockAndRotatedMeta(5, 0, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH);
		relCoord.setBlock(6, 0, 0, Blocks.cobblestone_wall);
		
		relCoord.setBlockAndRotatedMeta(0, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(1, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_WEST | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(5, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_EAST | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(6, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		
		final int west_east_darkoak = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_WEST_EAST);
		relCoord.setBlockAndMeta(1, 5, 0, Blocks.log2, west_east_darkoak);
		relCoord.setBlockAndMeta(2, 5, 0, Blocks.log2, west_east_darkoak);
		relCoord.setBlockAndMeta(3, 5, 0, Blocks.log2, west_east_darkoak);
		relCoord.setBlockAndMeta(4, 5, 0, Blocks.log2, west_east_darkoak);
		relCoord.setBlockAndMeta(5, 5, 0, Blocks.log2, west_east_darkoak);
		
		relCoord.setBlock(1, 4, 0, MyBlocks.fenceDarkOak);
		relCoord.setBlock(5, 4, 0, MyBlocks.fenceDarkOak);

		relCoord.setBlock(1, 3, 0, MyBlocks.fenceDarkOak);
		relCoord.setBlock(5, 3, 0, MyBlocks.fenceDarkOak);
		
		
		// 奥の層
		
		for(int x = 0; x <= 6; ++x) {
			for(int y = 0; y <= 6; ++y) {
				relCoord.setBlockAndMeta(x, y, -1, Blocks.planks, Stat.PLANK_META_DARKOAK);
			}
		}
		
		final int north_south_darkoak = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_NORTH_SOUTH);
		relCoord.setBlockAndMeta(1, 1, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(1, 2, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(1, 3, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(1, 4, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(1, 5, -1, Blocks.log2, north_south_darkoak);
		
		relCoord.setBlockAndMeta(5, 1, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(5, 2, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(5, 3, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(5, 4, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(5, 5, -1, Blocks.log2, north_south_darkoak);
		
		relCoord.setBlockAndMeta(1, 5, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(2, 5, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(3, 5, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(4, 5, -1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(5, 5, -1, Blocks.log2, north_south_darkoak);
		
		for(int x = 2; x <= 4; ++x) {
			for(int y = 1; y <= 4; ++y) {
				relCoord.setBlock(x, y, -1, Blocks.glass_pane);
			}
		}
	}
	
	/**
	 * 1階に使われている外壁を生成する
	 * @param startX 壁の左(壁を外から見たとき)下手前のx座標
	 * @param startY 壁の左下手前のy座標
	 * @param startZ 壁の左下手前のz座標
	 * @param rotationYaw 回転
	 * @param world
	 */
	public static void generateOuterWallType2(int startX, int startY, int startZ, Rotation2D rotationYaw, World world) {
		StructureRelativeCoordinateSystem relCoord
			= new StructureRelativeCoordinateSystem(startX, startY, startZ, rotationYaw, world);
		
//		System.out.println(String.format("WallType2 will spawn at (%d, %d, %d) = (%d, %d, %d)", startX, startY, startZ, relCoord.originX, relCoord.originY, relCoord.originZ));
		
		
		
		// 手前の層
		
		relCoord.setBlock(0, 0, 0, Blocks.cobblestone_wall);
		relCoord.setBlock(6, 0, 0, Blocks.cobblestone_wall);
		
		relCoord.setBlockAndRotatedMeta(0, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(1, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_WEST | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(5, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_EAST | Stat.META_STAIRS_UPPER);
		relCoord.setBlockAndRotatedMeta(6, 6, 0, Blocks.stone_stairs, Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		
		
		// 奥の層
		
		for(int x = 0; x <= 6; ++x) {
			for(int y = 0; y <= 6; ++y) {
				relCoord.setBlockAndMeta(x, y, 1, Blocks.planks, Stat.PLANK_META_DARKOAK);
			}
		}
		
		int north_south_darkoak = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_NORTH_SOUTH);
		relCoord.setBlockAndMeta(2, 3, 1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(3, 2, 1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(4, 3, 1, Blocks.log2, north_south_darkoak);
		relCoord.setBlockAndMeta(3, 4, 1, Blocks.log2, north_south_darkoak);
	}
}
