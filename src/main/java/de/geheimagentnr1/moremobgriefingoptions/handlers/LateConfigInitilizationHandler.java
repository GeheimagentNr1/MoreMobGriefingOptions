package de.geheimagentnr1.moremobgriefingoptions.handlers;

import de.geheimagentnr1.minecraft_forge_api.events.ModEventHandlerInterface;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import lombok.RequiredArgsConstructor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
public class LateConfigInitilizationHandler implements ModEventHandlerInterface {
	
	
	@NotNull
	private final MoreMobGriefingOptions mod;
	
	@SubscribeEvent
	@Override
	public void handleFMLCommonSetupEvent( @NotNull FMLCommonSetupEvent event ) {
		
		mod.initMobgriefingConfig();
	}
}
