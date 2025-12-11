package de.geheimagentnr1.moremobgriefingoptions.api.events;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;


public interface ModEventHandlerInterface {
	
	
	default void handleFMLCommonSetupEvent( @NotNull FMLCommonSetupEvent event ) {
		
	}
}
