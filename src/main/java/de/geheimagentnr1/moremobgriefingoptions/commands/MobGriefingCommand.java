package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameRules;

import java.util.Locale;


public class MobGriefingCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSource> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSource> mobgriefingCommand = Commands.literal( "mobgriefing" ).requires(
			commandSource -> commandSource.hasPermission( 2 ) );
		mobgriefingCommand.then( Commands.literal( "list" )
			.executes( MobGriefingCommand::list ) );
		mobgriefingCommand.then( Commands.argument( "entity_name", ConfigOptionArgument.config_option() )
			.executes( MobGriefingCommand::showValue )
			.then( Commands.argument( "value", MobGriefingOptionArgument.mob_griefing_option() )
				.executes( MobGriefingCommand::setValue ) ) );
		dispatcher.register( mobgriefingCommand );
	}
	
	private static int list( CommandContext<CommandSource> context ) {
		
		CommandSource source = context.getSource();
		source.sendSuccess(
			new StringTextComponent( String.format(
				"mobGriefing gamerule = %b",
				source.getServer().getGameRules().getBoolean( GameRules.RULE_MOBGRIEFING )
			) ),
			false
		);
		ServerConfig.getOptionsStream().forEach(
			configOption -> source.sendSuccess(
				new StringTextComponent( String.format(
					"%s = %s",
					configOption.getKey(),
					configOption.getValue().name().toLowerCase( Locale.ENGLISH )
				) ),
				false
			)
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private static int showValue( CommandContext<CommandSource> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "entity_name" );
		context.getSource().sendSuccess(
			new StringTextComponent(
				configOption.getKey() + " mobGriefing is currently set to: " + configOption.getValue()
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private static int setValue( CommandContext<CommandSource> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "entity_name" );
		configOption.setValue( MobGriefingOptionArgument.getMobGriefingOption( context, "value" ) );
		context.getSource().sendSuccess(
			new StringTextComponent(
				configOption.getKey() + " mobGriefing is now set to: " + configOption.getValue()
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
}
