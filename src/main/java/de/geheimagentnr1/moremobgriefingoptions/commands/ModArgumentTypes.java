package de.geheimagentnr1.moremobgriefingoptions.commands;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;


public class ModArgumentTypes {
	
	public static void registerArgumentTypes() {
		
		ArgumentTypes.register( MoreMobGriefingOptions.MODID + ":config_option", ConfigOptionArgument.class,
			new ArgumentSerializer<>( ConfigOptionArgument::config_option ));
		ArgumentTypes.register( MoreMobGriefingOptions.MODID + ":mob_griefing_option", MobGriefingOptionArgument.class,
			new ArgumentSerializer<>( MobGriefingOptionArgument::mob_griefing_option ));
	}
}
