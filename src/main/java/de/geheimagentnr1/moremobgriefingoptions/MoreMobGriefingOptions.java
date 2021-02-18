package de.geheimagentnr1.moremobgriefingoptions;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;


@SuppressWarnings( "unused" )
@Mod( MoreMobGriefingOptions.MODID )
public class MoreMobGriefingOptions {
	
	
	public static final String MODID = "moremobgriefingoptions";
	
	private MoreMobGriefingOptions() {
		
		ModLoadingContext.get().registerExtensionPoint(
			ExtensionPoint.DISPLAYTEST,
			() -> Pair.of(
				() -> FMLNetworkConstants.IGNORESERVERONLY,
				( remote, isServer ) -> true
			)
		);
	}
}
