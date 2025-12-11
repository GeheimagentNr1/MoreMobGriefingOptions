package de.geheimagentnr1.moremobgriefingoptions.api.registry;

import de.geheimagentnr1.moremobgriefingoptions.api.AbstractMod;
import de.geheimagentnr1.moremobgriefingoptions.api.events.ModEventHandlerInterface;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class ElementsRegisterFactory<T> implements ModEventHandlerInterface {
	
	
	@NotNull
	protected abstract AbstractMod getAbstractMod();
	
	@NotNull
	protected abstract ResourceKey<Registry<T>> registryKey();
	
	@NotNull
	protected abstract List<RegistryEntry<T>> elements();
	
	public void handleRegisterEvent( @NotNull RegisterEvent event ) {
		
		event.register( registryKey(), registry -> {
			elements().forEach( entry -> registry.register(
				ResourceLocation.fromNamespaceAndPath( getAbstractMod().getModId(), entry.name() ),
				entry.supplier().get()
			) );
		} );
	}
}
