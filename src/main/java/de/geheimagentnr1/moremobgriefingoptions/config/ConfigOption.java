package de.geheimagentnr1.moremobgriefingoptions.config;

import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;


@SuppressWarnings( "rawtypes" )
public class ConfigOption {
	
	
	private final ResourceLocation key;
	
	private ForgeConfigSpec.EnumValue<MobGriefingOptionType> spec;
	
	private final EntityType<?> entity_type;
	
	//package-private
	ConfigOption( ResourceLocation _key, EntityType<?> _entity_type ) {
		
		key = _key;
		entity_type = _entity_type;
	}
	
	public ResourceLocation getKey() {
		
		return key;
	}
	
	//package-private
	void setSpec( ForgeConfigSpec.EnumValue<MobGriefingOptionType> _spec ) {
		
		spec = _spec;
	}
	
	public MobGriefingOptionType getValue() {
		
		return spec.get();
	}
	
	
	public void setValue( MobGriefingOptionType _value ) {
		
		spec.set( _value );
	}
	
	public EntityType<?> getEntityType() {
		
		return entity_type;
	}
}
