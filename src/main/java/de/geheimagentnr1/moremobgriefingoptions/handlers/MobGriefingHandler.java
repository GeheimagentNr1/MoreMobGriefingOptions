package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.MainConfig;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class MobGriefingHandler {
	
	
	@SubscribeEvent
	public static void handleMobGriefing( EntityMobGriefingEvent event ) {
		
		for( ConfigOption option : MainConfig.OPTIONS ) {
			if( option.entity_class.isInstance( event.getEntity() ) ) {
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
