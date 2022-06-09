package de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;


public class ModArgumentTypes {
	
	
	public static void registerArgumentTypes() {
		
		Registry.register(
			Registry.COMMAND_ARGUMENT_TYPE,
			MoreMobGriefingOptions.MODID + ":" + ConfigOptionArgument.registry_name,
			ArgumentTypeInfos.registerByClass(
				ConfigOptionArgument.class,
				SingletonArgumentInfo.contextFree( ConfigOptionArgument::new )
			)
		);
		Registry.register(
			Registry.COMMAND_ARGUMENT_TYPE,
			MoreMobGriefingOptions.MODID + ":" + MobGriefingOptionArgument.registry_name,
			ArgumentTypeInfos.registerByClass(
				MobGriefingOptionArgument.class,
				SingletonArgumentInfo.contextFree( MobGriefingOptionArgument::new )
			)
		);
	}
}
