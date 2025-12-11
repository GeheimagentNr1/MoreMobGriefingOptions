package de.geheimagentnr1.moremobgriefingoptions.elements.commands;

import de.geheimagentnr1.moremobgriefingoptions.api.AbstractMod;
import de.geheimagentnr1.moremobgriefingoptions.api.elements.commands.CommandInterface;
import de.geheimagentnr1.moremobgriefingoptions.api.elements.commands.CommandsRegisterFactory;
import lombok.RequiredArgsConstructor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
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
	
	@SubscribeEvent
	public void onRegisterCommands( @NotNull RegisterCommandsEvent event ) {
		
		handleRegisterCommandsEvent( event );
	}
}
