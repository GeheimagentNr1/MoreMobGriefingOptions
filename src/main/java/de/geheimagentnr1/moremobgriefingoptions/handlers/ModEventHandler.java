package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.config.MainConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModEventHandler {
	
	
	@SubscribeEvent
	public static void handleFMLCommonSetupEvent( FMLCommonSetupEvent event ) {
		
		ModLoadingContext.get().registerConfig( ModConfig.Type.SERVER, MainConfig.init() );
	}
	
	@SubscribeEvent
	public static void handleModConfigLoadingEvent( ModConfig.Loading event ) {
		
		MainConfig.printConfig();
	}
	
	@SubscribeEvent
	public static void handleModConfigReloadingEvent( ModConfig.ConfigReloading event ) {
		
		MainConfig.printConfig();
	}
}
