package de.geheimagentnr1.moremobgriefingoptions.api;

import de.geheimagentnr1.moremobgriefingoptions.api.config.AbstractConfig;
import de.geheimagentnr1.moremobgriefingoptions.api.events.ForgeEventHandlerInterface;
import de.geheimagentnr1.moremobgriefingoptions.api.events.ModEventHandlerInterface;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


public abstract class AbstractMod {
	
	
	@NotNull
	private final Map<ModConfig.Type, AbstractConfig> configMap = new HashMap<>();
	
	@NotNull
	private final IEventBus modEventBus;
	
	@NotNull
	private final ModContainer modContainer;
	
	protected AbstractMod( @NotNull IEventBus modEventBus, @NotNull ModContainer modContainer ) {
		
		this.modEventBus = modEventBus;
		this.modContainer = modContainer;
		initMod();
	}
	
	@NotNull
	public abstract String getModId();
	
	protected abstract void initMod();
	
	protected void registerEventHandler( @NotNull ModEventHandlerInterface eventHandler ) {
		
		modEventBus.register( eventHandler );
	}
	
	protected void registerEventHandler( @NotNull ForgeEventHandlerInterface eventHandler ) {
		
		net.neoforged.neoforge.common.NeoForge.EVENT_BUS.register( eventHandler );
	}
	
	protected void registerConfig( @NotNull Function<AbstractMod, AbstractConfig> configFactory ) {
		
		AbstractConfig config = configFactory.apply( this );
		configMap.put( config.type(), config );
		modContainer.registerConfig( config.type(), config.getSpec() );
	}
	
	@NotNull
	public <T extends AbstractConfig> Optional<T> getConfig(
		@NotNull ModConfig.Type type,
		@NotNull Class<T> configClass ) {
		
		AbstractConfig config = configMap.get( type );
		if( configClass.isInstance( config ) ) {
			return Optional.of( configClass.cast( config ) );
		}
		return Optional.empty();
	}
	
	@NotNull
	public IEventBus getModEventBus() {
		
		return modEventBus;
	}
}
