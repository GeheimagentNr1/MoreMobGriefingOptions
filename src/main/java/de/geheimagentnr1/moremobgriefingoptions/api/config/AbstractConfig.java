package de.geheimagentnr1.moremobgriefingoptions.api.config;

import de.geheimagentnr1.moremobgriefingoptions.api.AbstractMod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public abstract class AbstractConfig {
	
	
	@NotNull
	private final AbstractMod abstractMod;
	
	@NotNull
	private final ModConfigSpec.Builder builder;
	
	@NotNull
	private final Map<List<String>, ModConfigSpec.ConfigValue<?>> configValues = new HashMap<>();
	
	private ModConfigSpec spec;
	
	protected AbstractConfig( @NotNull AbstractMod abstractMod ) {
		
		this.abstractMod = abstractMod;
		this.builder = new ModConfigSpec.Builder();
		registerConfigValues();
		this.spec = builder.build();
	}
	
	@NotNull
	public abstract ModConfig.Type type();
	
	public abstract boolean isEarlyLoad();
	
	protected abstract void registerConfigValues();
	
	protected void push( @NotNull String comment, @NotNull String path ) {
		
		builder.comment( comment ).push( path );
	}
	
	protected void pop() {
		
		builder.pop();
	}
	
	protected <T> void registerConfigValue(
		@NotNull String comment,
		@NotNull List<String> path,
		@NotNull BiFunction<ModConfigSpec.Builder, List<String>, ModConfigSpec.ConfigValue<T>> valueFactory ) {
		
		ModConfigSpec.ConfigValue<T> configValue = valueFactory.apply( builder.comment( comment ), path );
		configValues.put( path, configValue );
	}
	
	@NotNull
	@SuppressWarnings( "unchecked" )
	public <T> T getValue( @NotNull Class<T> type, @NotNull List<String> path ) {
		
		ModConfigSpec.ConfigValue<?> configValue = configValues.get( path );
		if( configValue == null ) {
			throw new IllegalArgumentException( "Config value not found for path: " + path );
		}
		Object value = configValue.get();
		if( type.isInstance( value ) ) {
			return (T) value;
		}
		throw new IllegalArgumentException( "Config value type mismatch for path: " + path );
	}
	
	@SuppressWarnings( "unchecked" )
	public <T> void setValue( @NotNull Class<T> type, @NotNull List<String> path, @NotNull T value ) {
		
		ModConfigSpec.ConfigValue<?> configValue = configValues.get( path );
		if( configValue == null ) {
			throw new IllegalArgumentException( "Config value not found for path: " + path );
		}
		( (ModConfigSpec.ConfigValue<T>) configValue ).set( value );
	}
	
	@NotNull
	public ModConfigSpec getSpec() {
		
		return spec;
	}
	
	@NotNull
	protected AbstractMod getAbstractMod() {
		
		return abstractMod;
	}
}
