package de.geheimagentnr1.moremobgriefingoptions.api.elements.commands;

import de.geheimagentnr1.moremobgriefingoptions.api.events.ForgeEventHandlerInterface;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class CommandsRegisterFactory implements ForgeEventHandlerInterface {
	
	
	@NotNull
	public abstract List<CommandInterface> commands();
	
	public void handleRegisterCommandsEvent( @NotNull RegisterCommandsEvent event ) {
		
		commands().forEach( command -> event.getDispatcher().register( command.build() ) );
	}
}
