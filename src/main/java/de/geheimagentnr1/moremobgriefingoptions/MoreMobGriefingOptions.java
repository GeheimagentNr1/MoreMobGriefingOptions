package de.geheimagentnr1.moremobgriefingoptions;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.moremobgriefingoptions.config.ServerConfig;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.ModCommandsRegisterFactory;
import de.geheimagentnr1.moremobgriefingoptions.elements.commands.arguments.ModArgumentTypesRegisterFactory;
import de.geheimagentnr1.moremobgriefingoptions.handlers.LateConfigInitilizationHandler;
import de.geheimagentnr1.moremobgriefingoptions.handlers.MobGriefingHandler;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;


@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions extends AbstractMod {
	
	
	@NotNull
	public static final String MODID = "moremobgriefingoptions";
	
	@NotNull
	public static final String SERVER_CONFIG_NOT_FOUND_ERROR_MESSAGE = "MoreMobGriefingOptions ServerConfig not found";
	
	@NotNull
	@Override
	public String getModId() {
		
		return MODID;
	}
	
	@Override
	protected void initMod() {
		
		registerEventHandler( new ModArgumentTypesRegisterFactory( this ) );
		registerEventHandler( new ModCommandsRegisterFactory( this ) );
		registerEventHandler( new LateConfigInitilizationHandler( this ) );
		registerEventHandler( new MobGriefingHandler( this ) );
	}
	
	public void initMobgriefingConfig() {
		
		registerConfig( ServerConfig::new );
	}
}
