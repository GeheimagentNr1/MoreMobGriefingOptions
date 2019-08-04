package de.geheimagentnr1.moremobgriefingoptions;

import de.geheimagentnr1.moremobgriefingoptions.config.Config;
import net.minecraftforge.fml.common.Mod;


@Mod( "moremobgriefingoptions" )
public class MoreMobGriefingOptions {
	
	
	public MoreMobGriefingOptions() {
		
		Config.loadConfig();
	}
}
