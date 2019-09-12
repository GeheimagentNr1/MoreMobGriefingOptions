package de.geheimagentnr1.moremobgriefingoptions.handler;

import de.geheimagentnr1.moremobgriefingoptions.commands.MobGriefingCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class RegistryHandler {
	
	
	@SubscribeEvent
	public static void handlerServerStartEvent( FMLServerStartingEvent event ) {
		
		MobGriefingCommand.register( event.getCommandDispatcher() );
	}
}
