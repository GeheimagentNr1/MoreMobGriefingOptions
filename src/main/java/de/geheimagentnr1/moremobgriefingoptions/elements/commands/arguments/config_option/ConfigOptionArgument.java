package de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.config_option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import lombok.RequiredArgsConstructor;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


@RequiredArgsConstructor
public class ConfigOptionArgument implements ArgumentType<ResourceLocation> {
	
	
	@NotNull
	public static final String registry_name = "config_option";
	
	@NotNull
	private static final Collection<String> EXAMPLES = Collections.singletonList( "zombie" );
	
	@NotNull
	private static final DynamicCommandExceptionType INVALID_CONFIG_OPTION_EXCEPTION = new DynamicCommandExceptionType(
		( entityKey ) -> Component.literal( "Unkown entity: " ).append( entityKey.toString() )
	);
	
	
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
	public static ConfigOptionArgument config_option( @NotNull AbstractMod abstractMod ) {
		
		return new ConfigOptionArgument( abstractMod );
	}
	
	@SuppressWarnings( "SameParameterValue" )
	@NotNull
	public static <S> ConfigOption getConfigOption(
		@NotNull CommandContext<S> context,
		@NotNull ServerConfig serverConfig,
		@NotNull String name )
		throws CommandSyntaxException {
		
		ResourceLocation resourcelocation = context.getArgument( name, ResourceLocation.class );
		return serverConfig.getOptionsStream()
			.filter( configOption -> configOption.getKey().equals( resourcelocation ) )
			.findFirst()
			.orElseThrow( () -> INVALID_CONFIG_OPTION_EXCEPTION.create( resourcelocation ) );
	}
	
	@NotNull
	@Override
	public ResourceLocation parse( @NotNull StringReader reader ) throws CommandSyntaxException {
		
		return ResourceLocation.read( reader );
	}
	
	@NotNull
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		@NotNull CommandContext<S> context,
		@NotNull SuggestionsBuilder builder ) {
		
		if( context.getSource() instanceof SharedSuggestionProvider ) {
			return SharedSuggestionProvider.suggestResource(
				serverConfig().getOptionsStream().map( ConfigOption::getKey ),
				builder
			);
		}
		return Suggestions.empty();
	}
	
	@NotNull
	@Override
	public Collection<String> getExamples() {
		
		return EXAMPLES;
	}
}
