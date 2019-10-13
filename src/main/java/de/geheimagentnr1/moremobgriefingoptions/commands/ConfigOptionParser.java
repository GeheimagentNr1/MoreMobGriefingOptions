package de.geheimagentnr1.moremobgriefingoptions.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.geheimagentnr1.moremobgriefingoptions.MoreMobGriefingOptions;
import de.geheimagentnr1.moremobgriefingoptions.config.Config;
import de.geheimagentnr1.moremobgriefingoptions.config.ConfigOption;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


//package-private
class ConfigOptionParser {
	
	
	private static final DynamicCommandExceptionType CONFIG_OPTION_INVALID = new DynamicCommandExceptionType( object ->
		new TranslationTextComponent( MoreMobGriefingOptions.MODID + ":argument.config_option.invalid", object ) );
	
	private static final Set<String> CONFIG_OPTIONS = getConfigOptionKeySet();
	
	private final StringReader reader;
	
	private ConfigOption configOption;
	
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder;
	
	//package-private
	ConfigOptionParser( StringReader _reader ) {
		
		reader = _reader;
	}
	
	private static Set<String> getConfigOptionKeySet() {
		
		Set<String> keySet = new TreeSet<>();
		
		for( ConfigOption config_option : Config.OPTIONS ) {
			keySet.add( config_option.getKey() );
		}
		return keySet;
	}
	
	//package-private
	ConfigOption getConfigOption() {
		
		return configOption;
	}
	
	private void readConfigOption() throws CommandSyntaxException {
		
		int cursor = reader.getCursor();
		ResourceLocation resourceLocation = ResourceLocation.read( reader );
		configOption = getConfigOptionForRegistry( resourceLocation ).orElseThrow( () -> {
			reader.setCursor( cursor );
			return CONFIG_OPTION_INVALID.createWithContext( reader, resourceLocation.toString() );
		} );
	}
	
	private Optional<ConfigOption> getConfigOptionForRegistry( ResourceLocation resourceLocation ) {
		
		for( ConfigOption configOption : Config.OPTIONS ) {
			if( configOption.getKey().equals( resourceLocation.getPath() ) ) {
				return Optional.of( configOption );
			}
		}
		return Optional.empty();
	}
	
	@SuppressWarnings( "ReturnOfThis" )
	//package-private
	ConfigOptionParser parse() throws CommandSyntaxException {
		
		suggestionsBuilder = this::suggestColor;
		readConfigOption();
		suggestionsBuilder = this::suggestColorFuture;
		return this;
	}
	
	private CompletableFuture<Suggestions> suggestColorFuture( SuggestionsBuilder builder ) {
		
		return builder.buildFuture();
	}
	
	private CompletableFuture<Suggestions> suggestColor( SuggestionsBuilder builder ) {
		
		return ISuggestionProvider.suggest( CONFIG_OPTIONS, builder );
	}
	
	//package-private
	CompletableFuture<Suggestions> fillSuggestions( SuggestionsBuilder builder ) {
		
		return suggestionsBuilder.apply( builder.createOffset( reader.getCursor() ) );
	}
}
