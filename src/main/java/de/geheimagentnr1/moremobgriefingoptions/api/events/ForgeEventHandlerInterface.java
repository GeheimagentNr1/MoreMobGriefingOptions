package de.geheimagentnr1.moremobgriefingoptions.api.events;

import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import org.jetbrains.annotations.NotNull;


public interface ForgeEventHandlerInterface {
	
	
	default void handleEntityMobGriefingEvent( @NotNull EntityMobGriefingEvent event ) {
		
	}
}
