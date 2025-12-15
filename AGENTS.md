# AGENTS.md - More MobGriefing Options

## Projekt-Übersicht

**More MobGriefing Options** ist ein NeoForge Minecraft Mod für Minecraft 1.21.1.
- **Mod ID**: `moremobgriefingoptions`
- **Package**: `de.geheimagentnr1.moremobgriefingoptions`
- **Java Version**: 21
- **NeoForge Version**: 21.1.x

Erweitert die MobGriefing-Gamerule um individuelle Optionen pro Mob-Typ.

## Abhängigkeiten

Keine Mod-Abhängigkeiten - eigenständiger Mod.

## Projektstruktur

```
src/main/java/de/geheimagentnr1/moremobgriefingoptions/
├── MoreMobGriefingOptions.java              # Haupt-Mod-Klasse
├── api/
│   └── AbstractMod.java                     # Eigene AbstractMod Implementierung
├── config/
│   ├── ConfigOption.java                    # Konfigurations-Optionen
│   ├── MobGriefingOptionType.java           # Option-Typen Enum
│   └── ServerConfig.java                    # Server-Konfiguration
└── handlers/
    ├── LateConfigInitilizationHandler.java  # Späte Config-Initialisierung
    └── MobGriefingHandler.java              # MobGriefing Event-Handler
```

## Besonderheiten

- **Eigene AbstractMod**: Hat eine eigene `AbstractMod` Implementierung (nicht von ManyIdeas Core)
- **Dynamische Konfiguration**: Config-Optionen werden dynamisch basierend auf registrierten Mobs generiert
- **Late Initialization**: Konfiguration wird spät initialisiert, nachdem alle Mobs registriert sind

## Code-Stil

- **Annotations**: `@NotNull` aus `org.jetbrains.annotations`
- **Lombok**: Projekt nutzt Lombok
- **Formatierung**: Leerzeichen nach `(` und vor `)` bei Methodenaufrufen

## Build & Test

```bash
./gradlew build
./gradlew runClient
./gradlew runServer
```

## Deployment

- **CurseForge**: `./gradlew curseforge`
- **Modrinth**: `./gradlew modrinth`
