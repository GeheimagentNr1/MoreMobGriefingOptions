package de.geheimagentnr1.moremobgriefingoptions.config;

import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


public class MainConfig {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String mod_name = "More MobGriefing Options";
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	private static final String MOBGRIEFING = "mobGriefing";
	
	private static ConfigOption[] OPTIONS;
	
	public static ForgeConfigSpec init() {
		
		BUILDER.comment( "MobGriefing settings" ).push( MOBGRIEFING );
		ArrayList<ConfigOption> configOptions = new ArrayList<>();
		Registry.ENTITY_TYPE.forEach(
			entityType -> configOptions.add( new ConfigOption( entityType.getRegistryName(), entityType ) )
		);
		OPTIONS = configOptions.toArray( new ConfigOption[0] );
		for( ConfigOption option : OPTIONS ) {
			option.setSpec( BUILDER.comment( option.getKey() + " " + MOBGRIEFING )
				.defineEnum( option.getKey().toString(), MobGriefingOptionType.DEFAULT ) );
		}
		BUILDER.pop();
		return BUILDER.build();
	}
	
	public static void printConfig() {
		
		LOGGER.info( "Loading \"{}\" Config", mod_name );
		for( ConfigOption option : OPTIONS ) {
			LOGGER.info( "{} " + MOBGRIEFING + " = {}", option.getKey(), option.getValue() );
		}
		LOGGER.info( "\"{}\" Config loaded", mod_name );
	}
	
	public static Stream<ConfigOption> getOptionsStream() {
		
		return Arrays.stream( OPTIONS );
	}
	
	public static ConfigOption[] getOptions() {
		
		return OPTIONS;
	}
}
