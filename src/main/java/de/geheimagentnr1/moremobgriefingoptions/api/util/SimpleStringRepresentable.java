package de.geheimagentnr1.moremobgriefingoptions.api.util;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;


public interface SimpleStringRepresentable extends StringRepresentable {
	
	
	@NotNull
	String name();
	
	@NotNull
	@Override
	default String getSerializedName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
}
