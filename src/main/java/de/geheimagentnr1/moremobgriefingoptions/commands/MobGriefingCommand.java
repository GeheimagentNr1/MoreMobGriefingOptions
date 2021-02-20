package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;


public class MobGriefingCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSource> mobgriefingCommand = Commands.literal( "mobgriefing" ).requires(
			commandSource -> commandSource.hasPermissionLevel( 2 ) );
		mobgriefingCommand.executes( context -> {
			context.getSource().sendFeedback( new StringTextComponent( "/mobgriefing <mob name> [<value>]" ), true );
			return Command.SINGLE_SUCCESS;
		} );
		mobgriefingCommand.then( Commands.argument( "mob name", ConfigOptionArgument.config_option() )
			.executes( context -> {
				ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "mob name" );
				context.getSource().sendFeedback( new StringTextComponent(
					configOption.getKey() + " mobGriefing is currently set to: " + configOption.getValue() ), false );
				return Command.SINGLE_SUCCESS;
			} )
			.then( Commands.argument( "value", MobGriefingOptionArgument.mob_griefing_option() ).executes( context -> {
				ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "mob name" );
				configOption.setValue( MobGriefingOptionArgument.getMobGriefingOption( context, "value" ) );
				context.getSource().sendFeedback( new StringTextComponent(
					configOption.getKey() + " mobGriefing is now set to: " + configOption.getValue() ), false );
				return Command.SINGLE_SUCCESS;
			} ) ) );
		dispatcher.register( mobgriefingCommand );
	}
}
