package de.geheimagentnr1.moremobgriefingoptions;

import de.geheimagentnr1.moremobgriefingoptions.commands.ModArgumentTypes;
import de.geheimagentnr1.moremobgriefingoptions.config.MainConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@SuppressWarnings( { "UtilityClassWithPublicConstructor", "unused" } )
@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions {
	
	
	public static final String MODID = "moremobgriefingoptions";
	
	public MoreMobGriefingOptions() {
		
		
		ModLoadingContext.get().registerConfig( ModConfig.Type.COMMON, MainConfig.CONFIG, MODID + ".toml" );
		ModArgumentTypes.registerArgumentTypes();
	}
}
