package de.geheimagentnr1.moremobgriefingoptions.api.registry;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;


public record RegistryEntry<T>( @NotNull String name, @NotNull Supplier<T> supplier ) {
	
	
	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull T value ) {
		
		return new RegistryEntry<>( name, () -> value );
	}
	
	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull Supplier<T> supplier ) {
		
		return new RegistryEntry<>( name, supplier );
	}
}
