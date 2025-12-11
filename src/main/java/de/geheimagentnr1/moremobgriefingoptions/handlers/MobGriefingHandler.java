package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.api.AbstractMod;
import de.geheimagentnr1.moremobgriefingoptions.api.events.ForgeEventHandlerInterface;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
public class MobGriefingHandler implements ForgeEventHandlerInterface {
	
	
	@NotNull
	private final AbstractMod abstractMod;
	
	private ServerConfig serverConfig;
	
	@NotNull
	private ServerConfig serverConfig() {
		
		if( serverConfig == null ) {
			serverConfig = abstractMod.getConfig( ModConfig.Type.SERVER, ServerConfig.class )
				.orElseThrow( () -> new IllegalStateException( MoreMobGriefingOptions.SERVER_CONFIG_NOT_FOUND_ERROR_MESSAGE ) );
		}
		return serverConfig;
	}
	
	@SubscribeEvent
	public void onEntityMobGriefingEvent( @NotNull EntityMobGriefingEvent event ) {
		
		Entity entity = event.getEntity();
		
		if( entity == null ) {
			return;
		}
		switch( serverConfig().getMobGriefingOptionTypeOfEntityType( entity.getType() ) ) {
			case TRUE -> event.setCanGrief( true );
			case FALSE -> event.setCanGrief( false );
		}
	}
}
