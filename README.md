# NoExplosion

Dropped items don’t get blown up anymore. TNT, crystals, beds in the nether, respawn anchors — whatever. The loot stays. You can also choose whether items get a little knockback from the blast (so they scatter a bit like everything else) or just sit there untouched. Up to you.

## Why Paper?

NoExplosion is built for Paper. Paper’s basically a better Spigot — same game, same plugins, but faster and with way fewer weird bugs. If you’re on Spigot or Bukkit, swapping to Paper is usually worth it; your world and plugins keep working. This plugin only runs when something actually takes explosion damage, so it’s not sitting there doing work every tick. Needs Paper 1.21+ and Java 21.

## What it does

Items on the ground are protected from every kind of explosion. You are able to turn knockback on or off: when it’s on, items get a small push from the blast. When it’s off, they don’t move at all. Either way they never get destroyed.

## Install

Grab the JAR, drop it in your server’s `plugins` folder, start or restart the server. First run creates `plugins/NoExplosion/config.yml` with defaults. Done.

## Config

Everything’s in `plugins/NoExplosion/config.yml`. Restart or reload the plugin after you change it.

**apply-velocity** (true/false, default true) — If true, items get a little push when an explosion hits them. If false, they’re only protected and don’t move. That’s the only option right now.

Example:

```yaml
apply-velocity: true
```

## Building

Java 21, then:

```bash
./gradlew build
```

JAR ends up in `build/libs/NoExplosion-1.0.0.jar`. Put that in `plugins` and you’re good.

## Quick check

Drop some stuff, blow something up next to it. Items should still be there. If knockback’s on they’ll have shifted a bit; if it’s off they’ll be exactly where they were. If anything’s weird, make sure you’re on Paper 1.21+ and the plugin shows up as enabled.
