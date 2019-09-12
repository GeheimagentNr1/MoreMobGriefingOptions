package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.Config;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.MobGriefingOptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.server.command.EnumArgument;


public class MobGriefingCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSource> mobgriefingCommand = Commands.literal( "mobgriefing" ).requires(
			commandSource -> commandSource.hasPermissionLevel( 2 )
		);
		mobgriefingCommand.executes( command -> {
			command.getSource().sendFeedback( new StringTextComponent( "/mobgriefing <mob name> [<value>]" ), true );
			return 1;
		} );
		for( ConfigOption option : Config.OPTIONS ) {
			mobgriefingCommand.then( Commands.literal( option.getKey() ).executes( command -> {
				command.getSource().sendFeedback( new StringTextComponent( option.getKey() +
					" mobGriefing is currently set to: " + option.getValue() ), false );
				return 1;
			} ).then( Commands.argument( "value", EnumArgument.enumArgument( MobGriefingOptionType.class ) )
				.executes( command -> {
					option.setValue( command.getArgument( "value", MobGriefingOptionType.class ) );
					command.getSource().sendFeedback( new StringTextComponent( option.getKey() +
						" mobGriefing is now set to: " + option.getValue() ), false );
					return 1;
				} ) ) );
		}
		dispatcher.register( mobgriefingCommand );
	}
}
