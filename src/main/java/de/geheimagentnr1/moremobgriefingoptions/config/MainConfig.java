package de.geheimagentnr1.moremobgriefingoptions.config;

import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainConfig {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String mod_name = "More MobGriefing Options";
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec CONFIG;
	
	private static final String MOBGRIEFING = "mobGriefing";
	
	@SuppressWarnings( "PublicStaticArrayField" )
	public static final ConfigOption[] OPTIONS = new ConfigOption[] {
		new ConfigOption( "blaze", BlazeEntity.class ),//MN
		new ConfigOption( "cow", CowEntity.class ),//MN
		new ConfigOption( "creeper", CreeperEntity.class ),//MN
		new ConfigOption( "ender_dragon", EnderDragonEntity.class ),//MN
		new ConfigOption( "enderman", EndermanEntity.class ),//MN
		new ConfigOption( "evoker", EvokerEntity.class ),//MN
		new ConfigOption( "fox", FoxEntity.class ),//MN
		new ConfigOption( "ghast", GhastEntity.class ),//MN
		new ConfigOption( "horse", HorseEntity.class ),//MN
		new ConfigOption( "husk", HuskEntity.class ),//MN
		new ConfigOption( "pig", PigEntity.class ),//MN
		new ConfigOption( "piglin", PiglinEntity.class ),//MN
		new ConfigOption( "piglin_brute", PiglinBruteEntity.class ),//MN
		new ConfigOption( "ravager", RavagerEntity.class ),//MN
		new ConfigOption( "rabbit", RabbitEntity.class ),//MN
		new ConfigOption( "sheep", SheepEntity.class ),//MN
		new ConfigOption( "silverfish", SilverfishEntity.class ),//MN
		new ConfigOption( "snow_golem", SnowGolemEntity.class ),//MN
		new ConfigOption( "strider", StriderEntity.class ),//MN
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
				.defineEnum( option.key, MobGriefingOptionType.DEFAULT );
		}
		BUILDER.pop();
		
		CONFIG = BUILDER.build();
	}
	
	public static void printConfig() {
		
		LOGGER.info( "Loading \"{}\" Config", mod_name );
		for( ConfigOption option : OPTIONS ) {
			LOGGER.info( "{} " + MOBGRIEFING + " = {}", option.key, option.getValue() );
		}
		LOGGER.info( "\"{}\" Config loaded", mod_name );
	}
}
