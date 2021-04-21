package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.commands.MobGriefingCommand;
import de.geheimagentnr1.moremobgriefingoptions.commands.ModArgumentTypes;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber( modid = MoreMobGriefingOptions.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE )
public class ForgeEventHandler {
	
	
	@SubscribeEvent
	public static void handlerRegisterCommandsEvent( RegisterCommandsEvent event ) {
		
		ModArgumentTypes.registerArgumentTypes();
		MobGriefingCommand.register( event.getDispatcher() );
	}
	
	@SubscribeEvent
	public static void handleEntityMobGriefingEvent( EntityMobGriefingEvent event ) {
		
		for( ConfigOption option : ServerConfig.getOptions() ) {
			if( option.getEntityType() == event.getEntity().getType() ) {
				switch( option.getValue() ) {
					case DEFAULT:
						return;
					case TRUE:
						event.setResult( Event.Result.ALLOW );
						return;
					case FALSE:
						event.setResult( Event.Result.DENY );
						return;
				}
				break;
			}
		}
	}
}
