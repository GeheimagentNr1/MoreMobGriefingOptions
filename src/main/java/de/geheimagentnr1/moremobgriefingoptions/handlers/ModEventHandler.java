package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber( modid = MoreMobGriefingOptions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModEventHandler {
	
	
	@SubscribeEvent
	public static void handleFMLCommonSetupEvent( FMLCommonSetupEvent event ) {
		
		ModLoadingContext.get().registerConfig( ModConfig.Type.SERVER, ServerConfig.init() );
	}
	
	@SubscribeEvent
	public static void handleModConfigLoadingEvent( ModConfig.Loading event ) {
		
		ServerConfig.printConfig();
	}
	
	@SubscribeEvent
	public static void handleModConfigReloadingEvent( ModConfig.ConfigReloading event ) {
		
		ServerConfig.printConfig();
	}
}
