package de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.config.MobGriefingOptionType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;


public class MobGriefingOptionArgument implements ArgumentType<MobGriefingOptionType> {
	
	
	@NotNull
	public static final String registry_name = "mob_griefing_option";
	
	@NotNull
	private static final Collection<String> EXAMPLES = Collections.singletonList( "default" );
	
	@NotNull
	public static MobGriefingOptionArgument mob_griefing_option() {
		
		return new MobGriefingOptionArgument();
	}
	
	@SuppressWarnings( "SameParameterValue" )
	@NotNull
	public static <S> MobGriefingOptionType getMobGriefingOption(
		@NotNull CommandContext<S> context,
		@NotNull String name ) {
		
		return context.getArgument( name, MobGriefingOptionType.class );
	}
	
	@NotNull
	@Override
	public MobGriefingOptionType parse( @NotNull StringReader reader ) throws CommandSyntaxException {
		
		MobGriefingOptionParser parser = new MobGriefingOptionParser( reader ).parse();
		return parser.getMobGriefingOptionType();
	}
	
	@NotNull
	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(
		@NotNull CommandContext<S> context,
		@NotNull SuggestionsBuilder builder ) {
		
		StringReader reader = new StringReader( builder.getInput() );
		reader.setCursor( builder.getStart() );
		MobGriefingOptionParser parser = new MobGriefingOptionParser( reader );
		
		try {
			parser.parse();
		} catch( CommandSyntaxException ignored ) {
		}
		return parser.fillSuggestions( builder );
	}
	
	@NotNull
	@Override
	public Collection<String> getExamples() {
		
		return EXAMPLES;
	}
}
