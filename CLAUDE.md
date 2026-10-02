# CLAUDE.md - More MobGriefing Options

## Projekt-Übersicht

**More MobGriefing Options** ist ein NeoForge Minecraft Mod.
- **Mod ID**: `moremobgriefingoptions`
- **Package**: `de.geheimagentnr1.moremobgriefingoptions`
- **Java Version**: 21
- **NeoForge Version**: je Branch, siehe Tabelle

Erweitert die MobGriefing-Gamerule um individuelle Optionen pro Mob-Typ.

| Branch | MC | Range | NeoForge (kompiliert gegen) | Hinweis |
|---|---|---|---|---|
| `develop_1.21.1` | 1.21.1 - 1.21.10 | `[1.21.1,1.21.10]` | `21.1.216` | Release `1.21.1-3.0.2` (2026-10-02, Fix: per `/mobgriefing` gesetzte Werte werden gespeichert) |
| `develop_1.21.11` | 1.21.11 | `[1.21.11,1.21.12)` | `21.11.45` | Release `1.21.11-3.0.2` (2026-10-02); `ResourceLocation` → `Identifier`, `LEVEL_GAMEMASTERS`, `GameRules.MOB_GRIEFING` über `source.getLevel().getGameRules()` (`MinecraftServer.getGameRules()` entfernt), GameTest entfernt, JUnit ergänzt |

`develop_1.21.3` ist ein alter, nur lokaler Forge-Stand (`forge_version`) und kein NeoForge-Port.

**Config speichern:** `ModConfigSpec.ConfigValue.set(..)` schreibt nicht auf die Platte - `api/config/AbstractConfig.setValue` ruft deshalb `configValue.save()` auf (bis 3.0.1 fehlte das, Werte gingen beim Neustart verloren).

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

## Testing

### Java-Versionen

Verschiedene Java-Versionen sind unter `C:\Program Files\Eclipse Adoptium` installiert. Für einen Gradle-Build muss die passende Java-Version gewählt werden:

```powershell
# Java 21 für MC 1.20.5+ (NeoForge)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.12.8-hotspot"
./gradlew build
```

### Unit Tests (JUnit 5)

Für reine Logik-Tests ohne Minecraft-Abhängigkeiten:

```bash
./gradlew test
```

Tests liegen unter `src/test/java/`. Ergebnisse: `build/reports/tests/test/index.html`

### NeoForge GameTest Framework

Ab `develop_1.21.11` gibt es keine GameTests mehr (trivialer Smoke-Test samt Run-Config und CI-Job entfernt).

### Ingame-Test

`/mobgriefing list`, Creeper mit `/mobgriefing minecraft:creeper false|true` gegen die Gamerule `mobGriefing` testen, Rechte ohne OP. Speichern automatisch per RCON prüfen: Wert setzen → Eintrag in `world/serverconfig/moremobgriefingoptions-server.toml` → Neustart → Wert abfragen.

### CI/CD (GitHub Actions)

Der Workflow `.github/workflows/build-and-test.yml` führt automatisch aus:
1. **Build**: Kompiliert den Mod
2. **Unit Tests**: Führt JUnit Tests aus

### Was kann automatisiert getestet werden?

| Aspekt | Automatisiert? | Methode |
|--------|----------------|---------|
| Utility-Klassen | ✅ | JUnit |
| Config-Parsing | ✅ | JUnit |
| Commands | ✅ | GameTest |
| Block/Item-Verhalten | ✅ | GameTest |
| Multi-MC-Version | ⚠️ Pro Branch | CI Matrix |

## Referenzen

- [NeoForge Migration Primer](https://docs.neoforged.net/primer/docs/) — Dokumentiert API-Aenderungen zwischen Minecraft/NeoForge-Versionen; nuetzlich fuer die Pruefung von Breaking Changes beim Upgrade auf neue Versionen

---

## Wissensdatenbank

Versionsübergreifende Migrations- und Entwicklungs-Erkenntnisse (Breaking Changes, Fixes, Testumgebungs-Patterns) werden zentral in [`../Docs/`](../Docs/) gepflegt. Bei neuen relevanten Erkenntnissen dort ergänzen, nicht nur hier.
