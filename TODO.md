# TODO - Jamesium Industries — Project

## Bug Fix / Clean Up
- [ ] Pet Rabbit interactions can fire on both hands due to disconnect between client and server interactions
- [ ] Map log blocks and Red Ore Blocks currently place holder blocks both require corresponding block-drop loot tables, both require block loot-table providers to self drop block items.
- [ ] Changing an EVIL rabbit back does not fully restore peaceful behavior. Consider either removing EVIL variant interactions, making EVIL a one-way transformation, or explicitly managing the reversible behavior.
- [ ] Continue investigating tests to deliberately control time for `SeasonalHelpers` `assertTrue(seasons.isAroundEaster());`
- [ ] Convert Maple Log Block from decorative to conventional wood log block, make appropriate sounds, hardness, and orientation.

## Pet Rabbit

### Mob / Pet Interaction
- [ ] Ownership/taming semantics
- [ ] Follow owner behaviour
- [ ] Sit/stay behaviour
- [ ] Additional pet AI goals

### Loot Tables / Periodic Gifts
- [ ] Tune weights/counts/drop rates after playing with it

### Seasonal Gifts
- [ ] Decide how seasonal table selection interacts with rabbit variants

## Canada Expansion Pack

### Grizzly bear
- [ ] Research best vanilla entity to extend/use as architectural reference
- [ ] Create Canadian grizzly bear
- [ ] Custom model/texture as required
- [ ] Make grizzly a pet
- [ ] Ownership/taming
- [ ] Make grizzly rideable
- [ ] Research passenger/mount mechanics
- [ ] Determine how player controls ridden bear
- [ ] Add combat behaviour
- [ ] Attack hostile/appropriate targets
- [ ] Determine whether bear protects owner
- [ ] Add bear-specific sounds/animations if warranted

#### Honey
- [ ] Bear periodically produces little potion bottles of honey
- [ ] Decide whether these are custom items or actual potion-like consumables
- [ ] Decide effects
- [ ] Create item/texture
- [ ] Create bear gift loot table
- [ ] Reuse/generalise periodic gift architecture where sensible

# BACKLOG

## Canada Expansion Pack

### Maple Trees
Intentionally postponded because #WORLDGEN
- [ ] Decide whether we're really doing this
- [ ] Accept that yes, eventually we're probably doing this
- [ ] Research tree/worldgen APIs
- [ ] Maple log
- [ ] Maple wood
- [ ] Stripped maple log
- [ ] Stripped maple wood
- [ ] Maple planks
- [ ] Maple leaves
- [ ] Maple sapling
- [ ] Generate appropriate block/item models
- [ ] Generate blockstates
- [ ] Generate loot tables
- [ ] Generate recipes
- [ ] Add wood-family blocks as desired:
    - [ ] stairs
    - [ ] slabs
    - [ ] fences
    - [ ] gates
    - [ ] doors
    - [ ] trapdoors
    - [ ] buttons
    - [ ] pressure plates
    - [ ] bookshelves
    - [ ] signs, etc.
- [ ] Make maple saplings grow
- [ ] World generation / natural maple trees
- [ ] Decide biome placement
- [ ] Eventually realise maple trees imply maple sap
- [ ] Eventually realise maple sap implies maple syrup

## Research rabbit holes deliberately deferred
- [ ] Synched entity data when a feature actually needs client/server state synchronisation
- [ ] Persistent custom pet state when a feature actually needs to survive save/reload
- [ ] Persistent `nextPetTime` and/or `nuggetTime` across unload/reload of the entity
- [ ] Ownership UUID persistence
- [ ] More advanced AI goal architecture
- [ ] Custom entity rendering/models
- [ ] Worldgen
- [ ] Data components for stateful items where appropriate
- [ ] Loot contexts beyond `EMPTY` when gift generation actually needs contextual information
- [ ] GitHub Releases/CD when distributing builds becomes worthwhile

# DONE

## Bug Fix / Clean Up
- [X] Currently namespace collision between vanilla `minecraft:nether_brick` recipe and with an associated nether_brick advancement needs to be repaired.
- [X] Variant interaction server guard `setVariant()` lacks interaction path protections
- [X] Pet Rabbit Transformations consume the offered item
- [X] Add Maple Log Block Placeholder to creative tab.

## Core mod / infrastructure
- [X] Create Jamesium Industries mod
- [X] Minecraft 1.21.11
- [X] NeoForge 21.11.45
- [X] Java 21
- [X] ModDevGradle build environment
- [X] Establish package structure (`block/`, `item/`, `entity/`, `datagen/`, etc.)
- [X] Set up `DeferredRegister` architecture
- [X] Working `.\gradlew build`
- [X] Working `.\gradlew runClient`
- [X] Working `.\gradlew runData`
- [X] Git repository
- [X] GitHub Actions CI build
- [X] Create milestone tags/checkpoints
- [X] Test current loot/gift implementation thoroughly
- [X] Refactor growing bootstrap/main-class responsibilities
- [X] Move datagen provider wiring out of `JamesiumIndustries`
- [X] Consider an `event/` package as event handling grows

## Basic blocks/items
- [X] Register `RED_ORE_BLOCK`
- [X] Register corresponding `BlockItem`
- [X] Create ugly-but-distinctive placeholder block texture
- [X] Register `MAGIC_CUBE`
- [X] Create ugly-but-distinctive placeholder item texture
- [X] Add localisation/display names
- [X] Add Jamesium Industries creative tab
- [X] Populate appropriate creative tabs
- [X] Generate block models through datagen
- [X] Generate item models through datagen
- [X] Establish recipe datagen
- [X] Create working shapeless recipe
- [X] Create working smelting recipe
- [X] Update Item textures
- [X] Updated Block texture using different textures for top, bottom, and sides.

## Datagen
- [X] Model provider
- [X] Recipe provider
- [X] Loot-table subprovider
- [X] All currently wired through `GatherDataEvent.Client`
- [X] Extract datagen registration/wiring from main mod class
- [X] Keep individual providers in `datagen/`
- [X] Reduce `JamesiumIndustries` toward boring bootstrap/registration duties
- [X] Centralise loot-table `ResourceKeys`/constants
- [X] Create Block Model Templates 

## Pet Rabbit

### Entity Foundation
- [X] Create `PetRabbitEntity`
- [X] Extend vanilla `Rabbit` so rabbit already knows how to rabbit
- [X] Register custom `EntityType`
- [X] Use vanilla rabbit dimensions
- [X] Register default rabbit attributes
- [X] Register client-side `RabbitRenderer`
- [X] Successfully `/summon` pet rabbit
- [X] Preserve vanilla rabbit behaviour/variants
- [X] Preserve important inherited rabbit behaviour, including agricultural crime
- [X] Preserve latent Killer Bunny murder capability
- [X] Create spawn egg
- [X] Create coloured heart spawn-egg texture
- [X] Add spawn egg to creative inventory
- [X] Add spawn-egg localisation
- [X] Add survival crafting recipe for pet-rabbit spawn egg

### Mob / Pet Interaction
- [X] Detect empty-hand `mobInteract`
- [X] Keep interaction authoritative on logical server
- [X] Pat rabbit
- [X] Heal rabbit when patted
- [X] Heal player when rabbit is patted
- [X] Heart particle feedback
- [X] Petting cooldown
- [X] Rabbit sound when interaction is on cooldown
- [X] Delegate non-petting interactions back to vanilla `Rabbit`

### Loot Tables / Periodic gifts
- [X] Investigate vanilla Chicken egg-laying implementation
- [X] Identify `aiStep()` as appropriate lifecycle hook
- [X] Add `nuggetTime` timer
- [X] Restrict production to server
- [X] Require rabbit to be alive
- [X] Prevent baby rabbits producing gifts
- [X] Randomise production interval
- [X] Add egg-laying sound
- [X] Add particles
- [X] Establish loot-table datagen
- [X] Create `ModLootTableSubProvider`
- [X] Wire `LootTableProvider` into `GatherDataEvent.Client`
- [X] Generate `pet_rabbit_gift` loot table
- [X] Resolve loot table from runtime entity code
- [X] Call loot table when gift timer expires
- [X] Spawn resulting items into world
- [X] Confirm that rabbits really do gift nuggets and gems
- [X] Only produce celebratory sound/particles when loot generation succeeds
- [X] Have datagen and runtime share the same `PET_RABBIT_GIFT` key

### Seasonal Gifts
- [X] Add `isAroundEaster()`
- [X] Use deliberately broad Easter season rather than implementing ecclesiastical computus
- [X] Approximate season: 1 March → 1 May
- [X] Create Easter gift loot table
- [X] Add enhanced Easter loot such as emeralds/diamonds
- [X] Make seasonal date logic easy enough to test without waiting until March

### Variant Changing Interactions
- [X] Allow offered `ItemStacks` to transform rabbit variants
- [X] Gold ingot → appropriate gold rabbit variant
- [X] Gold rabbit → influence gift production toward gold nuggets
- [X] Wither rose or wither skeleton skull → EVIL/Killer Bunny
- [X] Determine whether other vanilla rabbit variants deserve corresponding transformation items