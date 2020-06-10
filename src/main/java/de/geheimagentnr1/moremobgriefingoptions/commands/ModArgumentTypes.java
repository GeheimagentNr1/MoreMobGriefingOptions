package de.geheimagentnr1.moremobgriefingoptions.commands;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;


public class ModArgumentTypes {
	
	
	public static void registerArgumentTypes() {
		
		ArgumentTypes.register( MoreMobGriefingOptions.MODID + ":" + ConfigOptionArgument.registry_name,
			ConfigOptionArgument.class, new ArgumentSerializer<>( ConfigOptionArgument::config_option ) );
		ArgumentTypes.register( MoreMobGriefingOptions.MODID + ":" + MobGriefingOptionArgument.registry_name,
			MobGriefingOptionArgument.class,
			new ArgumentSerializer<>( MobGriefingOptionArgument::mob_griefing_option ) );
	}
}
