package de.geheimagentnr1.moremobgriefingoptions.commands.arguments.config_option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


public class ConfigOptionArgument implements ArgumentType<ResourceLocation> {
	
	
	public static final String registry_name = "config_option";
	
	private static final Collection<String> EXAMPLES = Collections.singletonList( "zombie" );
	
	private static final DynamicCommandExceptionType INVALID_CONFIG_OPTION_EXCEPTION = new DynamicCommandExceptionType(
		( entityKey ) -> new TextComponent( "Unkown entity: " ).append( entityKey.toString() )
	);
	
	public static ConfigOptionArgument config_option() {
		
		return new ConfigOptionArgument();
	}
	
	@SuppressWarnings( "SameParameterValue" )
	public static <S> ConfigOption getConfigOption( CommandContext<S> context, String name ) throws CommandSyntaxException {
		
		ResourceLocation resourcelocation = context.getArgument( name, ResourceLocation.class );
		return ServerConfig.getOptionsStream()
			.filter( configOption -> configOption.getKey().equals( resourcelocation ) )
			.findFirst()
			.orElseThrow( () -> INVALID_CONFIG_OPTION_EXCEPTION.create( resourcelocation ) );
	}
	
	@Override
	public ResourceLocation parse( StringReader reader ) throws CommandSyntaxException {
		
		return ResourceLocation.read( reader );
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		CommandContext<S> context,
		SuggestionsBuilder builder ) {
		
		if( context.getSource() instanceof SharedSuggestionProvider ) {
			return SharedSuggestionProvider.suggestResource(
				ServerConfig.getOptionsStream().map( ConfigOption::getKey ),
				builder
			);
		}
		return Suggestions.empty();
	}
	
	@Override
	public Collection<String> getExamples() {
		
		return EXAMPLES;
	}
}
