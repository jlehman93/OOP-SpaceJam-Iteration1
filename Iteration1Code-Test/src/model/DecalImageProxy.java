package model;

import java.awt.image.BufferedImage;

import utilities.ImageProcessing;
import utilities.Scaling;

public final class DecalImageProxy  {
	public static String REDCROSS_IMAGE_PATH = "src/res/img/redcross_decal.png";
	public static String SKULLANDCROSSBONES_IMAGE_PATH = "src/res/img/skullandcrossbones_decal.png";
	public static String GOLDSTAR_IMAGE_PATH = "src/res/img/goldstar_decal.png";
	public static String TELEPORTATION_IMAGE_PATH = "src/res/img/teleportation_decal.png";
	
	public static BufferedImage redcrossImage = ImageProcessing.scaleImage(Scaling.TILE_SCALE,  REDCROSS_IMAGE_PATH);
	public static BufferedImage skullandcrossbonesImage = ImageProcessing.scaleImage(Scaling.TILE_SCALE, SKULLANDCROSSBONES_IMAGE_PATH);
	public static BufferedImage goldstarImage = ImageProcessing.scaleImage(Scaling.TILE_SCALE, GOLDSTAR_IMAGE_PATH);
	public static BufferedImage teleportationImage = ImageProcessing.scaleImage(Scaling.TILE_SCALE, TELEPORTATION_IMAGE_PATH);
	
	
	public final static BufferedImage getDecalImage(RedCrossDecal red){
		return DecalImageProxy.redcrossImage;
	}
	
	public final static BufferedImage getDecalImage(SkullAndCrossbonesDecal skull){
		return DecalImageProxy.skullandcrossbonesImage;
	}
	
	public final static BufferedImage getDecalImage(GoldStarDecal star){
		return DecalImageProxy.goldstarImage;
	}
	
	public final static BufferedImage getDecalImage(TeleportationDecal d){
		return DecalImageProxy.teleportationImage;
	}
	
	public final static BufferedImage getNewDecalImage(RedCrossDecal red){
		return ImageProcessing.scaleImage(Scaling.TILE_SCALE, REDCROSS_IMAGE_PATH);
	}
	public final static BufferedImage getNewDecalImage(SkullAndCrossbonesDecal skull){
		return ImageProcessing.scaleImage(Scaling.TILE_SCALE, SKULLANDCROSSBONES_IMAGE_PATH);
	}
	public final static BufferedImage getNewDecalImage(GoldStarDecal star){
		return ImageProcessing.scaleImage(Scaling.TILE_SCALE, GOLDSTAR_IMAGE_PATH);
	}
	public final static BufferedImage getNewDecalImage(TeleportationDecal d){
		return ImageProcessing.scaleImage(Scaling.TILE_SCALE, TELEPORTATION_IMAGE_PATH);
	}
	

}
