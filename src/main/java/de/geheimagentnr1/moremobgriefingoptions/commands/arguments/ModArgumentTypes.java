package de.geheimagentnr1.moremobgriefingoptions.commands.arguments;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;


public class ModArgumentTypes {
	
	
	public static void registerArgumentTypes() {
		
		ArgumentTypes.register(
			MoreMobGriefingOptions.MODID + ":" + ConfigOptionArgument.registry_name,
			ConfigOptionArgument.class,
			new EmptyArgumentSerializer<>( ConfigOptionArgument::config_option )
		);
		ArgumentTypes.register(
			MoreMobGriefingOptions.MODID + ":" + MobGriefingOptionArgument.registry_name,
			MobGriefingOptionArgument.class,
			new EmptyArgumentSerializer<>( MobGriefingOptionArgument::mob_griefing_option )
		);
	}
}
