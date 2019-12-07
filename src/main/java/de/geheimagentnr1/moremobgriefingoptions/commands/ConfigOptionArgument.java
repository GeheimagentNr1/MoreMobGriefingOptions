package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


public class ConfigOptionArgument implements ArgumentType<ConfigOption> {
	
	
	//package-private
	final static String registry_name = "config_option";
	
	private final static Collection<String> EXAMPLES = Collections.singletonList( "zombie" );
	
	//package-private
	static ConfigOptionArgument config_option() {
		
		return new ConfigOptionArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	static <S> ConfigOption getConfigOption( CommandContext<S> context, String name ) {
		
		return context.getArgument( name, ConfigOption.class );
	}
	
	@Override
	public ConfigOption parse( StringReader reader ) throws CommandSyntaxException {
		
		ConfigOptionParser parser = new ConfigOptionParser( reader ).parse();
		return parser.getConfigOption();
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions( CommandContext<S> context,
		SuggestionsBuilder builder ) {
		
		StringReader reader = new StringReader( builder.getInput() );
		reader.setCursor( builder.getStart() );
		ConfigOptionParser parser = new ConfigOptionParser( reader );
		
		try {
			parser.parse();
		} catch( CommandSyntaxException ignored ) {
		}
		return parser.fillSuggestions( builder );
	}
	
	@Override
	public Collection<String> getExamples() {
		
		return EXAMPLES;
	}
}
