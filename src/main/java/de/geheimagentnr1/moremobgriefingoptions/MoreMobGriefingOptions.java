package de.geheimagentnr1.moremobgriefingoptions;

import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions {
	
	
	public static final String MODID = "moremobgriefingoptions";
	
	private static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(
		Registry.COMMAND_ARGUMENT_TYPE_REGISTRY,
		MODID
	);
	
	private static final RegistryObject<SingletonArgumentInfo<ConfigOptionArgument>>
		CONFIG_OPTION_COMMAND_ARGUMENT_TYPE =
		COMMAND_ARGUMENT_TYPES.register(
			ConfigOptionArgument.registry_name,
			() -> ArgumentTypeInfos.registerByClass(
				ConfigOptionArgument.class,
				SingletonArgumentInfo.contextFree( ConfigOptionArgument::config_option )
			)
		);
	
	private static final RegistryObject<SingletonArgumentInfo<MobGriefingOptionArgument>>
		MOB_GRIEFING_OPTION_COMMAND_ARGUMENT_TYPE =
		COMMAND_ARGUMENT_TYPES.register(
			MobGriefingOptionArgument.registry_name,
			() -> ArgumentTypeInfos.registerByClass(
				MobGriefingOptionArgument.class,
				SingletonArgumentInfo.contextFree( MobGriefingOptionArgument::mob_griefing_option )
			)
		);
	
	@SuppressWarnings( "unused" )
	public MoreMobGriefingOptions() {
		
		COMMAND_ARGUMENT_TYPES.register( FMLJavaModLoadingContext.get().getModEventBus());
	}
}
