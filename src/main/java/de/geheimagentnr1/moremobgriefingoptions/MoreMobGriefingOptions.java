package de.geheimagentnr1.moremobgriefingoptions;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.network.FMLNetworkConstants;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions {
	
	
	public static final String MODID = "moremobgriefingoptions";
	
	@SuppressWarnings( "unused" )
	public MoreMobGriefingOptions() {
		
		ModLoadingContext.get().registerExtensionPoint(
			IExtensionPoint.DisplayTest.class,
			() -> new IExtensionPoint.DisplayTest(
				() -> FMLNetworkConstants.IGNORESERVERONLY,
				( remote, isServer ) -> true
			)
		);
	}
}
