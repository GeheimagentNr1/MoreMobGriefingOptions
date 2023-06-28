package de.geheimagentnr1.moremobgriefingoptions.config;

import de.geheimagentnr1.minecraft_forge_api.util.SimpleStringRepresentable;
import org.jetbrains.annotations.NotNull;


public enum MobGriefingOptionType implements SimpleStringRepresentable {
	DEFAULT,
	TRUE,
	FALSE;
	
	@NotNull
	@Override
	public String toString() {
		
		return getSerializedName();
	}
}
