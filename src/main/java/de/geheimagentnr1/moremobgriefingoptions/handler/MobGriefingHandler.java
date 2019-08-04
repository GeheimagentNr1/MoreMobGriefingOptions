package de.geheimagentnr1.moremobgriefingoptions.handler;

import de.geheimagentnr1.moremobgriefingoptions.config.Config;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.FORGE )
public class MobGriefingHandler {
	
	
	@SubscribeEvent
	public static void handleMobGriefing( EntityMobGriefingEvent event ) {
		
		for( ConfigOption option : Config.OPTIONS ) {
			if( option.entity_class.isInstance( event.getEntity() ) ) {
				event.setResult( option.getValue() ? Event.Result.ALLOW : Event.Result.DENY );
				break;
			}
		}
	}
}
