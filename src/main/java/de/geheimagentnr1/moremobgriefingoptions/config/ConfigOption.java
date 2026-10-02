package de.geheimagentnr1.moremobgriefingoptions.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;


@Data
@RequiredArgsConstructor
public class ConfigOption {
	
	
	@NotNull
	private final Identifier key;
	
	@NotNull
	private final MobGriefingOptionType value;
}
