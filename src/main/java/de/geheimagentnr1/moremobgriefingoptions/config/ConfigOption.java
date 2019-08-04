package de.geheimagentnr1.moremobgriefingoptions.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class ConfigOption {
	
	
	//package-private
	final String key;
	
	//package-private
	ForgeConfigSpec.BooleanValue value;
	
	@SuppressWarnings( "PublicField" )
	public final Class entity_class;
	
	//package-private
	ConfigOption( String _key, Class _entity_class ) {
		
		key = _key;
		entity_class = _entity_class;
	}
	
	public String getKey() {
		
		return key;
	}
	
	public boolean getValue() {
		
		return value.get();
	}
	
	public void setValue( Boolean _value ) {
		
		value.set( _value );
	}
}
