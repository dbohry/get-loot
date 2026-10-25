# Get-Loot

GetLoot is a Minecraft Mod that makes farming items easy by killing monsters. 
Causing monsters to drop items according to their rarity and adding a chance for animals to drop eggs.

## Requirements

Java 25 or higher  
Spigot 26.1 or higher (Minecraft 26.1 itself requires Java 25; mobs and items need a matching Minecraft version; the rest is skipped with a warning)

## Current lootable mobs

### Enemies (37 total)

- Blaze
- Bogged
- Breeze
- Camel Husk
- Cave Spider
- Creaking
- Creeper
- Dragon (Ender Dragon)
- Drowned
- Elder Guardian
- Enderman
- Endermite
- Ghast
- Guardian
- Hoglin
- Husk
- Magma Cube
- Parched
- Phantom
- Piglin
- Pillager (includes Vindicator, Illusioner, Evoker)
- Ravager
- Shulker
- Silverfish
- Skeleton
- Slime
- Spider
- Stray
- Vex
- Villager (includes Zombie Villager)
- Warden
- Witch
- Wither
- Zoglin
- Zombie
- Zombie Nautilus
- Zombie Piglin

### Animals (40 total)

- Allay
- Armadillo
- Axolotl
- Bat
- Bee
- Camel
- Cat
- Chicken
- Copper Golem
- Cow
- Dolphin
- Donkey
- Fox
- Frog
- Glow Squid
- Goat
- Happy Ghast
- Horse
- Iron Golem
- Llama
- Mooshroom
- Mule
- Nautilus
- Ocelot
- Panda
- Parrot
- Pig
- Polar Bear
- Rabbit
- Sheep
- Skeleton Horse
- Sniffer
- Snow Golem
- Squid
- Strider
- Trader Llama
- Turtle
- Wandering Trader
- Wolf
- Zombie Horse

## How does it work?

Basically this mod enables loot to drop from mobs adding more fun to your gameplay.
The rarity change depending on how hard/rare is the mob.

For instance, since the `Zombie` is very easy to find and very easy to kill it has a very small chance to drop `rare`
items.
Unlike the `Witch` or `Enderman` which has a bigger chance to drop`rare` items and can even drop `very rare` items.

## Configuration

All loot lives in `plugins/GetLoot/config.yml`, which is created on first start. Edit it and restart the server;
no rebuild is needed. The file documents its own format. In short:

- `tiers`: named item pools (`VERY_COMMON`, `COMMON`, `RARE`, `VERY_RARE`, `EPIC` and the two currency tiers), each
  with a `threshold` that sets how hard it is to roll.
- `mobs`: the drops per entity type. Each drop is either a `tier` or an explicit list of `items`, with optional
  `tries`, `multiplier` and `enchantments`.
- `bonus-drops`: extra rolls (currency) added to every kill that already dropped something.
- `exchange`: the chest-on-a-redstone-block recipes (see below).

Unknown materials or entity types (for example from a newer Minecraft version) are skipped with a warning in the log.

## Exchanging currency

Place a chest on top of a redstone block, put one of the recipe combinations in it, and close it. The items are
consumed (all but one of each stack is handed back) and you receive one item of the recipe's tier.

| Items                         | Reward    |
|-------------------------------|-----------|
| Emerald + Lapis + Gold ingot  | Epic      |
| Emerald + Lapis               | Very rare |
| Gold ingot + Iron ingot       | Rare      |
| Gold nugget + Iron nugget     | Common    |
