package de.geheimagentnr1.moremobgriefingoptions.elements.commands;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandInterface;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandsRegisterFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@RequiredArgsConstructor
public class ModCommandsRegisterFactory extends CommandsRegisterFactory {
	
	
	@NotNull
	private final AbstractMod abstractMod;
	
	@NotNull
	@Override
	public List<CommandInterface> commands() {
		
		return List.of(
			new MobGriefingCommand( abstractMod )
		);
	}
}
