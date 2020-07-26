package de.geheimagentnr1.moremobgriefingoptions;

import de.geheimagentnr1.moremobgriefingoptions.commands.ModArgumentTypes;
import de.geheimagentnr1.moremobgriefingoptions.config.Config;
import net.minecraftforge.fml.common.Mod;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions {
	
	
	public static final String MODID = "moremobgriefingoptions";
	
	@SuppressWarnings( "unused" )
	public MoreMobGriefingOptions() {
		
		Config.loadConfig();
		ModArgumentTypes.registerArgumentTypes();
	}
}
