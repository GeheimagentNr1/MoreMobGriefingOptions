package de.geheimagentnr1.moremobgriefingoptions.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Config {
	
	
	private final static Logger LOGGER = LogManager.getLogger();
	
	private final static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	private final static ForgeConfigSpec CONFIG;
	
	private final static String MOBGRIEFING = "mobGriefing";
	
	@SuppressWarnings( "PublicStaticArrayField" )
	public final static ConfigOption[] OPTIONS = new ConfigOption[] {
		new ConfigOption( "blaze", BlazeEntity.class ),//MN
		new ConfigOption( "cow", CowEntity.class ),//MN
		new ConfigOption( "creeper", CreeperEntity.class ),//MN
		new ConfigOption( "ender_dragon", EnderDragonEntity.class ),//MN
		new ConfigOption( "enderman", EndermanEntity.class ),//MN
		new ConfigOption( "evoker", EvokerEntity.class ),//MN
		new ConfigOption( "fox", FoxEntity.class ),//MN
		new ConfigOption( "ghast", GhastEntity.class ),//MN
		new ConfigOption( "husk", HuskEntity.class ),//MN
		new ConfigOption( "ravager", RavagerEntity.class ),//MN
		new ConfigOption( "rabbit", RabbitEntity.class ),//MN
		new ConfigOption( "sheep", SheepEntity.class ),//MN
		new ConfigOption( "silverfish", SilverfishEntity.class ),//MN
		new ConfigOption( "snow_golem", SnowGolemEntity.class ),//MN
		new ConfigOption( "villager", VillagerEntity.class ),//MN
		new ConfigOption( "wither", WitherEntity.class ),//MN
		new ConfigOption( "zombie", ZombieEntity.class ),//MN
		//new ConfigOption( "zombie_pigman", ZombiePigmanEntity.class ),//
		//new ConfigOption( "zombie_villager", ZombieVillagerEntity.class ),//M MobGriefing aus funktioniert nicht
	};
	
	static {
		
		BUILDER.comment( "MobGriefing settings" ).push( MOBGRIEFING );
		for( ConfigOption option : OPTIONS ) {
			option.value = BUILDER.comment( option.key + " " + MOBGRIEFING )
				.define( option.key, MobGriefingOptionType.DEFAULT );
		}
		BUILDER.pop();
		
		CONFIG = BUILDER.build();
	}
	
	public static void loadConfig() {
		
		CommentedFileConfig configData = CommentedFileConfig.builder( FMLPaths.CONFIGDIR.get()
			.resolve( "moremobgriefingoptions.toml" ) ).sync().autosave().writingMode( WritingMode.REPLACE ).build();
		
		LOGGER.info( "Loading \"More MobGriefing Options\" Config" );
		configData.load();
		CONFIG.setConfig( configData );
		for( ConfigOption option : OPTIONS ) {
			LOGGER.info( "{} " + MOBGRIEFING + " = {}", option.key, option.getValue() );
		}
		LOGGER.info( "\"More MobGriefing Options\" Config loaded" );
	}
}
