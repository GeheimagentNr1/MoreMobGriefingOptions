package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import de.geheimagentnr1.moremobgriefingoptions.config.MainConfig;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


//package-private
class ConfigOptionArgument implements ArgumentType<ResourceLocation> {
	
	
	//package-private
	static final String registry_name = "config_option";
	
	private static final Collection<String> EXAMPLES = Collections.singletonList( "zombie" );
	
	private static final DynamicCommandExceptionType INVALID_CONFIG_OPTION_EXCEPTION = new DynamicCommandExceptionType(
		( entityKey ) -> new StringTextComponent( "Unkown entity: " ).func_240702_b_( entityKey.toString() )
	);
	
	//package-private
	static ConfigOptionArgument config_option() {
		
		return new ConfigOptionArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	static <S> ConfigOption getConfigOption( CommandContext<S> context, String name ) throws CommandSyntaxException {
		
		ResourceLocation resourcelocation = context.getArgument( name, ResourceLocation.class );
		return MainConfig.getOptionsStream()
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
		
		return context.getSource() instanceof ISuggestionProvider
			? ISuggestionProvider.func_212476_a(
			MainConfig.getOptionsStream()
				.map( ConfigOption::getKey ),
			builder
		)
			: Suggestions.empty();
	}
	
	@Override
	public Collection<String> getExamples() {
		
		return EXAMPLES;
	}
}
