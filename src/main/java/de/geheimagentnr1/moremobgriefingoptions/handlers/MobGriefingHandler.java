package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.events.ForgeEventHandlerInterface;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
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
	@Override
	public void handleEntityMobGriefingEvent( @NotNull EntityMobGriefingEvent event ) {
		
		Entity entity = event.getEntity();
		
		if( entity == null ) {
			return;
		}
		switch( serverConfig().getMobGriefingOptionTypeOfEntityType( entity.getType() ) ) {
			case TRUE -> event.setResult( Event.Result.ALLOW );
			case FALSE -> event.setResult( Event.Result.DENY );
		}
	}
}
