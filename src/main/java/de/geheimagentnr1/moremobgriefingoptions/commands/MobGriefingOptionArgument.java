package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.MobGriefingOptionType;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


//package-private
class MobGriefingOptionArgument implements ArgumentType<MobGriefingOptionType> {
	
	
	//package-private
	static final String registry_name = "mob_griefing_option";
	
	private static final Collection<String> EXAMPLES = Collections.singletonList( "default" );
	
	//package-private
	static MobGriefingOptionArgument mob_griefing_option() {
		
		return new MobGriefingOptionArgument();
	}
	
	//package-private
	@SuppressWarnings( "SameParameterValue" )
	static <S> MobGriefingOptionType getMobGriefingOption( CommandContext<S> context, String name ) {
		
		return context.getArgument( name, MobGriefingOptionType.class );
	}
	
	@Override
	public MobGriefingOptionType parse( StringReader reader ) throws CommandSyntaxException {
		
		MobGriefingOptionParser parser = new MobGriefingOptionParser( reader ).parse();
		return parser.getMobGriefingOptionType();
	}
	
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		CommandContext<S> context,
		SuggestionsBuilder builder ) {
		
		StringReader reader = new StringReader( builder.getInput() );
		reader.setCursor( builder.getStart() );
		MobGriefingOptionParser parser = new MobGriefingOptionParser( reader );
		
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
