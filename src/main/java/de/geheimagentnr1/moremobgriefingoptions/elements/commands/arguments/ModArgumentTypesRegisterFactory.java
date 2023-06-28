package de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.registry.ElementsRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import lombok.RequiredArgsConstructor;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@RequiredArgsConstructor
public class ModArgumentTypesRegisterFactory extends ElementsRegisterFactory<ArgumentTypeInfo<?, ?>> {
	
	
	@NotNull
	private final AbstractMod abstractMod;
	
	@NotNull
	@Override
	protected ResourceKey<Registry<ArgumentTypeInfo<?, ?>>> registryKey() {
		
		return Registries.COMMAND_ARGUMENT_TYPE;
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<ArgumentTypeInfo<?, ?>>> elements() {
		
		return List.of(
			RegistryEntry.create(
				ConfigOptionArgument.registry_name,
				ArgumentTypeInfos.registerByClass(
					ConfigOptionArgument.class,
					SingletonArgumentInfo.contextFree( () -> ConfigOptionArgument.config_option( abstractMod ) )
				)
			),
			RegistryEntry.create(
				MobGriefingOptionArgument.registry_name,
				ArgumentTypeInfos.registerByClass(
					MobGriefingOptionArgument.class,
					SingletonArgumentInfo.contextFree( MobGriefingOptionArgument::mob_griefing_option )
				)
			)
		);
	}
}
