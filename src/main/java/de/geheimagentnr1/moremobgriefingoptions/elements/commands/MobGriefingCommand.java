package de.geheimagentnr1.moremobgriefingoptions.elements.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.elements.commands.CommandInterface;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option.ConfigOptionArgument;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option.MobGriefingOptionArgument;
import lombok.RequiredArgsConstructor;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
public class MobGriefingCommand implements CommandInterface {
	
	
	@NotNull
	private final AbstractMod abstractMod;
	
	private ServerConfig serverConfig;
	
	@NotNull
	private ServerConfig serverConfig() {
		
		if( serverConfig == null ) {
			serverConfig = abstractMod.getConfig( ModConfig.Type.SERVER, ServerConfig.class )
				.orElseThrow( () -> new IllegalStateException( MoreMobGriefingOptions.SERVER_CONFIG_NOT_FOUND_ERROR_MESSAGE ) );
		}
		return serverConfig;
	}
	
	@NotNull
	@Override
	public LiteralArgumentBuilder<CommandSourceStack> build() {
		
		LiteralArgumentBuilder<CommandSourceStack> mobgriefingCommand = Commands.literal( "mobgriefing" ).requires(
			commandSource -> commandSource.hasPermission( 2 ) );
		mobgriefingCommand.then( Commands.literal( "list" )
			.executes( this::list ) );
		mobgriefingCommand.then( Commands.argument( "entity_name", ConfigOptionArgument.config_option( abstractMod ) )
			.executes( this::showValue )
			.then( Commands.argument( "value", MobGriefingOptionArgument.mob_griefing_option() )
				.executes( this::setValue ) ) );
		return mobgriefingCommand;
	}
	
	private int list( CommandContext<CommandSourceStack> context ) {
		
		CommandSourceStack source = context.getSource();
		source.sendSuccess(
			() -> Component.literal( String.format(
				"mobGriefing gamerule = %b",
				source.getServer().getGameRules().getBoolean( GameRules.RULE_MOBGRIEFING )
			) ),
			false
		);
		
		BuiltInRegistries.ENTITY_TYPE.forEach(
			entityType -> source.sendSuccess(
				() -> Component.literal( String.format(
					"%s = %s",
					serverConfig().entityTypeToRegistryKey( entityType ),
					serverConfig().getMobGriefingOptionTypeOfEntityType( entityType ).getSerializedName()
				) ),
				false
			)
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private int showValue( CommandContext<CommandSourceStack> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, serverConfig(), "entity_name" );
		context.getSource().sendSuccess(
			() -> Component.literal(
				configOption.getKey() + " mobGriefing is currently set to: " + configOption.getValue()
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
	
	private int setValue( CommandContext<CommandSourceStack> context ) throws CommandSyntaxException {
		
		ConfigOption configOption = ConfigOptionArgument.getConfigOption( context, serverConfig(), "entity_name" );
		serverConfig().setMobGriefingOptionType(
			configOption.getKey(),
			MobGriefingOptionArgument.getMobGriefingOption( context, "value" )
		);
		context.getSource().sendSuccess(
			() -> Component.literal(
				configOption.getKey() + " mobGriefing is now set to: " +
					serverConfig().getMobGriefingOptionTypeOfEntityType( configOption.getKey().toString() )
			),
			false
		);
		return Command.SINGLE_SUCCESS;
	}
}
