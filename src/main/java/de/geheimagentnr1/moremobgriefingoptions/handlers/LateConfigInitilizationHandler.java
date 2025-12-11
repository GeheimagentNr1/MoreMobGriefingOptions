package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.moremobgriefingoptions.api.events.ModEventHandlerInterface;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import lombok.RequiredArgsConstructor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
public class LateConfigInitilizationHandler implements ModEventHandlerInterface {
	
	
	@NotNull
	private final MoreMobGriefingOptions mod;
	
	@SubscribeEvent
	public void onFMLCommonSetupEvent( @NotNull FMLCommonSetupEvent event ) {
		
		mod.initMobgriefingConfig();
	}
}
