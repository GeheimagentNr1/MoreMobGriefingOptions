package de.geheimagentnr1.moremobgriefingoptions.elements.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameRules;

import java.util.Locale;


public class MobGriefingCommand {
	
	
	@SuppressWarnings( "SameReturnValue" )
	public static void register( CommandDispatcher<CommandSourceStack> dispatcher ) {
		
		LiteralArgumentBuilder<CommandSourceStack> mobgriefingCommand = Commands.literal( "mobgriefing" ).requires(
			commandSource -> commandSource.hasPermission( 2 ) );
		mobgriefingCommand.then( Commands.literal( "list" )
			.executes( MobGriefingCommand::list ) );
		mobgriefingCommand.then( Commands.argument( "entity_name", ConfigOptionArgument.config_option() )
			.executes( MobGriefingCommand::showValue )
			.then( Commands.argument( "value", MobGriefingOptionArgument.mob_griefing_option() )
				.executes( MobGriefingCommand::setValue ) ) );
		dispatcher.register( mobgriefingCommand );
	}
	
	private static int list( CommandContext<CommandSourceStack> context ) {
		
		CommandSourceStack source = context.getSource();
		source.sendSuccess(
			Component.literal( String.format(
				"mobGriefing gamerule = %b",
				source.getServer().getGameRules().getBoolean( GameRules.RULE_MOBGRIEFING )
			) ),
			false
		);
		ServerConfig.getOptionsStream().forEach(
			configOption -> source.sendSuccess(
				Component.literal( String.format(
					"%s = %s",
					configOption.getKey(),
					configOption.getValue().name().toLowerCase( Locale.ENGLISH )
				) ),
				false
			)
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private static int showValue( CommandContext<CommandSourceStack> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "entity_name" );
		context.getSource().sendSuccess(
			Component.literal(
				configOption.getKey() + " mobGriefing is currently set to: " + configOption.getValue()
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private static int setValue( CommandContext<CommandSourceStack> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, "entity_name" );
		configOption.setValue( MobGriefingOptionArgument.getMobGriefingOption( context, "value" ) );
		context.getSource().sendSuccess(
			Component.literal(
				configOption.getKey() + " mobGriefing is now set to: " + configOption.getValue()
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
}
