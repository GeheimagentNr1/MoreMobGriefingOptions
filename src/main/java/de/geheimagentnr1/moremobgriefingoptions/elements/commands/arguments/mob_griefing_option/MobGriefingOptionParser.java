package de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.mob_griefing_option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.MobGriefingOptionType;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class MobGriefingOptionParser {
	
	
	private static final DynamicCommandExceptionType MOB_GRIEFING_OPTION_INVALID = new DynamicCommandExceptionType(
		object -> Component.translatable(
			MoreMobGriefingOptions.MODID + ":argument.mob_griefing_option.invalid",
			object
		)
	);
	
	private static final Set<String> MOB_GRIEFING_OPTIONS = getMobGriefingOptionKeySet();
	
	private final StringReader reader;
	
	private MobGriefingOptionType mobGriefingOptionType;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	MobGriefingOptionParser( StringReader _reader ) {
		
		reader = _reader;
	}
	
	private static Set<String> getMobGriefingOptionKeySet() {
		
		Set<String> keySet = new TreeSet<>();
		
		for( MobGriefingOptionType mobGriefingOptionType : MobGriefingOptionType.values() ) {
			keySet.add( mobGriefingOptionType.name().toLowerCase( Locale.ENGLISH ) );
		}
		return keySet;
	}
	
	//package-private
	MobGriefingOptionType getMobGriefingOptionType() {
		
		return mobGriefingOptionType;
	}
	
	private void readConfigOption() throws CommandSyntaxException {
		
		int cursor = reader.getCursor();
		ResourceLocation resourceLocation = ResourceLocation.read( reader );
		mobGriefingOptionType = getMobGriefingOptionForRegistry( resourceLocation ).orElseThrow( () -> {
			reader.setCursor( cursor );
			return MOB_GRIEFING_OPTION_INVALID.createWithContext( reader, resourceLocation.toString() );
		} );
	}
	
	private Optional<MobGriefingOptionType> getMobGriefingOptionForRegistry( ResourceLocation resourceLocation ) {
		
		for( MobGriefingOptionType configOption : MobGriefingOptionType.values() ) {
			if( configOption.name().equals( resourceLocation.getPath().toUpperCase( Locale.ENGLISH ) ) ) {
				return Optional.of( configOption );
			}
		}
		return Optional.empty();
	}
	
	//package-private
	@SuppressWarnings( "ReturnOfThis" )
	MobGriefingOptionParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestColor;
		readConfigOption();
		suggestionsBuilder = this::suggestColorFuture;
		return this;
	}
	
	private CompletableFuture<Suggestions> suggestColorFuture( SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	private CompletableFuture<Suggestions> suggestColor( SuggestionsBuilder builder ) {
		
		return SharedSuggestionProvider.suggest( MOB_GRIEFING_OPTIONS, builder );
	}
	
	//package-private
	CompletableFuture<Suggestions> fillSuggestions( SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
