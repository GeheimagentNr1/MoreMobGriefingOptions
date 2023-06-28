package de.geheimagentnr1.moremobgriefingoptions.config;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.config.AbstractConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class ServerConfig extends AbstractConfig {
	
	
	@NotNull
	private static final String MOBGRIEFING = "mobGriefing";
	
	public ServerConfig( @NotNull AbstractMod _abstractMod ) {
		
		super( _abstractMod );
	}
	
	@NotNull
	@Override
	public ModConfig.Type type() {
		
		return ModConfig.Type.SERVER;
	}
	
	@Override
	public boolean isEarlyLoad() {
		
		return false;
	}
	
	@Override
	protected void registerConfigValues() {
		
		push( "MobGriefing settings", MOBGRIEFING );
		BuiltInRegistries.ENTITY_TYPE.forEach(
			entityType -> {
				String registryKey = entityTypeToRegistryKey( entityType );
				List<String> entityConfigPath = List.of( MOBGRIEFING, registryKey );
				registerConfigValue(
					registryKey + " " + MOBGRIEFING,
					entityConfigPath,
					( builder, path ) -> builder.defineEnum( path, MobGriefingOptionType.DEFAULT )
				);
			}
		);
		pop();
	}
	
	@NotNull
	private ResourceLocation entityTypeToResourceLocation( EntityType<?> entityType ) {
		
		return BuiltInRegistries.ENTITY_TYPE.getKey( entityType );
	}
	
	@NotNull
	public String entityTypeToRegistryKey( EntityType<?> entityType ) {
		
		return entityTypeToResourceLocation( entityType ).toString();
	}
	
	@NotNull
	public MobGriefingOptionType getMobGriefingOptionTypeOfEntityType( @NotNull EntityType<?> entityType ) {
		
		return getValue( MobGriefingOptionType.class, List.of( MOBGRIEFING, entityTypeToRegistryKey( entityType ) ) );
	}
	
	@NotNull
	public Stream<ConfigOption> getOptionsStream() {
		
		List<ConfigOption> configOption = new ArrayList<>();
		BuiltInRegistries.ENTITY_TYPE.forEach(
			entityType -> {
				MobGriefingOptionType mobGriefingOption = getMobGriefingOptionTypeOfEntityType( entityType );
				configOption.add( new ConfigOption( entityTypeToResourceLocation( entityType ), mobGriefingOption ) );
			}
		);
		return configOption.stream();
	}
	
	public void setMobGriefingOptionType(
		@NotNull ResourceLocation key,
		@NotNull MobGriefingOptionType mobGriefingOption ) {
		
		setValue( MobGriefingOptionType.class, List.of( MOBGRIEFING, key.toString() ), mobGriefingOption );
	}
}
