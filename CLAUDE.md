# CLAUDE.md - More MobGriefing Options

Erweitert die Gamerule `mobGriefing` um eine eigene Einstellung pro Entity-Typ (`default` = Gamerule, `true`/`false` = überschreiben). Mod-ID `moremobgriefingoptions`, Package `de.geheimagentnr1.moremobgriefingoptions`. Client+Server (`displayTest=MATCH_VERSION`, keine Dist-Beschränkung): die Logik läuft serverseitig, der Mod registriert aber eigene Befehls-Argumenttypen, daher gehört er auch auf den Client.

Diese Datei ist auf allen `develop_*`-Branches identisch; Quelle: `../Docs/mod-claude/MoreMobGriefingOptions.md` (Änderungen dort, dann `Docs/tools/sync_mod_claude_md.sh`). Versionsstand: [`../Docs/VERSIONEN.md`](../Docs/VERSIONEN.md).

## Versionen

- Forge: `develop_1.14.4` bis `develop_1.21`, dazu `develop_1.21.1_forge`.
- NeoForge: `develop_1.21.1` (1.21.1 - 1.21.10), `develop_1.21.11`, `develop_26.1` (26.1 - 26.2, nur Tooling, Bytecode identisch), `develop_26.3` (ab NeoForge 26.3.0.37-beta: Config-Typ `SYNCED` statt `SERVER` mit altem Dateinamen `<modid>-server.toml`, alte `world/serverconfig/`-Datei wird auf Dedicated Servern einmalig nach `world/syncedconfig/` kopiert - `config/ServerConfigRegistration`, `config/LegacyServerConfigMigration`, 2026-10-19).
- Jar-Grenze 1.21.11: `Identifier`, Rechtesystem, Game-Rules nur noch über das Level. Ranges und Tags: [`../Docs/VERSIONEN.md`](../Docs/VERSIONEN.md).

## Abhängigkeiten

- Keine Mod-Abhängigkeiten, keine gebündelten Bibliotheken.
- Forge ab 1.20: MinecraftForgeAPI per `jarJar` (siehe [`../Docs/forge/README.md`](../Docs/forge/README.md)). Die NeoForge-Branches enthalten stattdessen eine eigene, mitkompilierte Kopie der benötigten API-Klassen unter `api/` (nicht ManyIdeasCore).

## Architektur

- `MoreMobGriefingOptions` - Hauptklasse, `extends api.AbstractMod`; registriert Argumenttypen, Befehle, `LateConfigInitilizationHandler` und `MobGriefingHandler`. `initMobgriefingConfig()` registriert die Server-Config erst spät.
- `api/` (nur NeoForge) - `AbstractMod`, `config/AbstractConfig` (Config-Basis, `setValue` mit `save()`), `elements/commands/`, `events/` (Mod-/Game-Bus-Handler-Interfaces), `registry/`, `util/SimpleStringRepresentable`.
- `config/` - `ServerConfig` (für jeden Eintrag in `BuiltInRegistries.ENTITY_TYPE` ein Enum-Wert unter `mobGriefing.<registry-key>`, Standard `DEFAULT`), `MobGriefingOptionType` (`DEFAULT`/`TRUE`/`FALSE`), `ConfigOption`.
- `handlers/LateConfigInitilizationHandler` - legt die Config in `FMLCommonSetupEvent` an, damit alle Entity-Typen (auch anderer Mods) registriert sind.
- `handlers/MobGriefingHandler` - `EntityMobGriefingEvent`: `TRUE`/`FALSE` setzen `setCanGrief(..)`, `DEFAULT` lässt die Gamerule entscheiden.
- `elements/commands/` - `MobGriefingCommand`, `arguments/` (`ConfigOptionArgument` für Entity-Namen, `MobGriefingOptionArgument` + Parser).

## Funktionen, Befehle, Konfiguration

- `/mobgriefing list` - alle Werte; `/mobgriefing <entity>` - Wert abfragen; `/mobgriefing <entity> <default|true|false>` - setzen. Rechtestufe 2 (ab 1.21.11 `Commands.LEVEL_GAMEMASTERS`).
- Server-Config `world/serverconfig/moremobgriefingoptions-server.toml`, Abschnitt `mobGriefing` mit einem Eintrag pro Entity-Typ.
- Keine Welt-Daten.

## Fallstricke

- **Config speichern:** `ModConfigSpec.ConfigValue.set(..)` schreibt nicht auf die Platte - `api/config/AbstractConfig.setValue` ruft `configValue.save()` auf. Bis 3.0.1 fehlte das, per `/mobgriefing` gesetzte Werte gingen beim Neustart verloren (gefixt in 3.0.2 auf allen NeoForge-Branches).
- 1.21.11: `MinecraftServer.getGameRules()` entfernt - Gamerule über `source.getLevel().getGameRules().get( GameRules.MOB_GRIEFING )`; `hasPermission(int)` entfernt → `Commands.hasPermission( Commands.LEVEL_GAMEMASTERS )`. Details: [`../Docs/migrations/1.21.10-to-1.21.11.md`](../Docs/migrations/1.21.10-to-1.21.11.md) 8.
- Die Config wird bewusst spät (Common-Setup) erzeugt; Entity-Typen, die erst danach registriert würden, fehlen in der Config.

## Testen

- Ingame/RCON: `/mobgriefing list`, Creeper mit `/mobgriefing minecraft:creeper false|true` gegen die Gamerule `mobGriefing` testen, Rechte ohne OP prüfen.
- Speichern per RCON: Wert setzen → Eintrag in `moremobgriefingoptions-server.toml` → Neustart → Wert abfragen.
- `../Docs/testing/tools/forge_1.20.1_smoke.sh` deckt `/mobgriefing` auf Forge 1.20.1 ab. GameTests gibt es ab `develop_1.21.11` nicht mehr, nur JUnit. Allgemein: [`../Docs/testing/README.md`](../Docs/testing/README.md).

## Links

- Wiki: https://github.com/GeheimagentNr1/MoreMobGriefingOptions/wiki
- CurseForge: https://www.curseforge.com/minecraft/mc-mods/more-mobgriefing-options (Projekt-ID 389013)
- Modrinth: https://modrinth.com/mod/423SG4Jc
- [`../Docs/migrations/BREAKING_CHANGES.md`](../Docs/migrations/BREAKING_CHANGES.md), [`../MOD_KOMPATIBILITAET.md`](../MOD_KOMPATIBILITAET.md) (Config-`save()`)
