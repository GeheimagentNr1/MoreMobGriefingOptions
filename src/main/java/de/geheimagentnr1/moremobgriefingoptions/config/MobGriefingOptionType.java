package de.geheimagentnr1.moremobgriefingoptions.config;

import java.util.Locale;


public enum MobGriefingOptionType {
	DEFAULT,
	TRUE,
	FALSE;
	
	@Override
	public String toString() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
