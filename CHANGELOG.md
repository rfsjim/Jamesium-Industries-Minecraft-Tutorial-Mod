# Changelog

All notable changes to Jamesium Industries will be documented in this file.


## neoforge-1.21.11-0.30.3 - 2026-10-05



### Fixed


- Consistency with handling of variables, persistance of tamed state, attach petting cooldown to per rabbit, add required MOVE and LOOK flags for Follow Owner AI Goal

- Add required JUMP flag for Random Jump When Idle AI Goal



## neoforge-1.21.11-0.30 - 2026-10-05



### Added


- Add chance for new pet rabbits to inherit their parents ownership and variant or inherit a new variant



## neoforge-1.21.11-0.29 - 2026-10-05



### Added


- Add new Pet Rabbit Goal Follow Owner



## neoforge-1.21.11-0.28.1 - 2026-10-04



### Fixed


- Fix null string regression preventing pet rabbit spawn eggs from spawning pet rabbits



## neoforge-1.21.11-0.28.0 - 2026-10-04



### Added


- Add framework for taming Pet Rabbits persistant owner name and scaffolfing for owner related functions



### Fixed


- Resolve Failing Build due to File Naming Issue

- Rename dropGiftStep.java to DropGiftStep.java to fix build failing issue



## neoforge-1.21.11-0.27.0 - 2026-10-04



### Added


- Custom logger now accepts logging levels with customised message colouring

- Add persistence for Pet Rabbit Gift Drop Timers



### Fixed


- Updated Seasonsal Helper Test to Final

- Removed magic number from nextInt() method



## neoforge-1.21.11-0.25.3 - 2026-10-03



### Added


- Diagnostic logging tool



### Fixed


- Adjusted gift & petting visualisation particles to be more consistent over entity

- Update logic for player following

- Continued Pet Rabbit FollowPlayer Goal fixes

- Adjusted Pet Rabbit MobInteractions to only allow petting on genuine main and off hand both being empty, allowing for other interactions with off hand



## neoforge-1.21.11-0.24.0 - 2026-10-01



### Added


- Pet Rabbit randomly jumps when idle



## neoforge-1.21.11-0.23.0 - 2026-09-29



### Added


- Created initial framework for Pet Rabbits to follow players around



### Fixed


- Versions appear to need only numbers and not alphabets



## neoforge-1.21.11-0.22.0 - 2026-09-27



### Added


- Red Ore World Generation around ruined portals



## neoforge-1.21.11-0.21.0 - 2026-09-26



### Added


- Initial Worldgen Adds Silver Ore to Underground Ores



## neoforge-1.21.11-0.20.0 - 2026-09-26



### Added


- Add Pet Rabbit Breed - now creates Pet Rabbits instead of Rabbits



### Fixed


- Initial Interaction Causing Double Firing for Pet Rabbit Entity when naming with Silver Ingot

- Removes Pet Rabbit Double Handling of Silver Ingot Interaction Logic



## neoforge-1.21.11-0.19.0 - 2026-09-25



### Added


- Add all mod items and blocks to Mod Creative Tab

- Complete mining block lifecycle



### Fixed


- Pet Rabbit Interactions logic as interactions were triggering on EVIL and Wither Roses which are supposed to be disabled



## neoforge-1.21.11-0.17.0 - 2026-09-25



### Added


- Silver Ingots now have placeholder interaction that gives Pet Rabbits a CUSTOM NAME as start of taming functions



### Fixed


- Adjusted tests for applying enchantment helper methods applyEnchantmentValidated and applyEnchantmentUnchecked as there is different data available during datagen than during runtime new helper methods reflect this difference



## neoforge-1.21.11-0.16.1 - 2026-09-24



### Added


- Updated recipe categories for silver blocks

- Nether Sword crafting recipe and creative tab adds Fire Aspect I



### Fixed


- Added guards to enchantment helper



## neoforge-1.21.11-0.15.1 - 2026-09-23



### Added


- Port Silver blocks and items from previous repo based on Minecraft 1.16.x Forge 36 Silver Ore Blocks, Silver Blocks, and Silver Ingots

- Upscaled placeholder Silver textures from ported mod 16 x 16 into 64 x 64 files



### Fixed


- Added datagen for Tags to create burnable logs



## neoforge-1.21.11-0.14.1 - 2026-09-22



### Fixed


- Bugfix for build.yml and shields in README.md



## neoforge-1.21.11-0.14.0 - 2026-09-22



### Added


- Converted Maple Log Block from decorative to conventional wood log block, added block item tags for RED ORE BLOCK and MAPLE LOG BLOCKS, adjusted textures for logs



## neoforge-1.21.11-0.13.1 - 2026-09-21



### Fixed


- Pet Rabbit bugfix - Adjusted the guard checks around chaning variant to EVIL preventing WITHER ROSE from initiating the variant change



## neoforge-1.21.11-0.13.0 - 2026-09-21



### Added


- Added Loot tables for Red Ore and Maple Log Blocks



## neoforge-1.21.11-0.12.5 - 2026-09-21



### Fixed


- Adjust model template and block properties for Maple Log Blocks



## neoforge-1.21.11-0.12.4 - 2026-09-21



### Fixed


- Fixed code that allowed Pet Rabbit interactions can fire on both hands due to disconnect between client and server interactions

- Temporarily disabled EVIL Pet Rabbit variant interactions due to Changing an EVIL rabbit back does not fully restore peaceful behavior.



## neoforge-1.21.11-0.12.0 - 2026-09-21



### Added


- Added place holder maple logs to creative tab



## neoforge-1.21.11-0.11.0 - 2026-09-20



### Added


- Framework for seasonal Pet Rabbit Gifts created, added date aware seasonal checks, adjust loot table based on current season



## neoforge-1.21.11-0.10.4 - 2026-09-20



### Fixed


- Added controls for change variant mechanics and items are correctly consumed on variant changes



## neoforge-1.21.11-0.10.2 - 2026-09-20



### Changed


- Updated YAML build file to current Node version for Gradle



## neoforge-1.21.11-0.10.1 - 2026-09-20



### Fixed


- Repaired incorrect namespace issue with netherbrick smelting recipe from red ore blocks, removed overriden vanilla recipe



## neoforge-1.21.11-0.10.0 - 2026-09-20



### Added


- Maple Logs, initial item for future Maple Tree world generation



## neoforge-1.21.11-0.9.2 - 2026-09-19



### Added


- Red Ore Block textures for top, bottom, and sides



## neoforge-1.21.11-0.9.1 - 2026-09-19



### Added


- Updated textures for customed items - Magic Cube, Nether Sword, and Pet Rabbit Spawn Egg



## neoforge-1.21.11-0.9.0 - 2026-09-13



### Added


- Added Netherbrick sword



## neoforge-1.21.11-0.8.0 - 2026-09-13



### Added


- Pet Rabbits change their variants based on supplied items, each variant spawns different items similar to a chicken laying eggs. Gold variant created with gold ingots supply gold nuggets, White variant created with iron ingots supply iron nuggets, Black variant created with coal supply coal, Brown variant created with dirt supply grass dirt or cobblestone, White Splotched variant created with flint supply flint granite or birch wood, Salt variant created with apple supplies assorted crops, Evil variant created with wither rose supplies wither skeleton skull



## neoforge-1.21.11-0.7.4 - 2026-09-12



### Changed


- Increase pet rabbit gift interval to 5-10 minutes



## neoforge-1.21.11-0.7.1 - 2026-09-11



### Added


- Pet Rabbits Drop Nuggets and Gems



### Fixed


- Remove unused import references



## neoforge-1.21.11-0.6.4 - 2026-09-09



### Fixed


- Gradlew permissions

- Update Build YAML File

- Add missing gradle-wrapper.jar

- Update Build YAML File



## neoforge-1.21.11-0.6.1 - 2026-09-09



### Added


- Pet Rabbit Heals Player and Rabbit



## neoforge-1.21.11-0.5.0 - 2026-09-08



### Added


- Pet Rabbit Spawn Egg Recipe

- Smelting red ore in a furnace gives nether bricks



## neoforge-1.21.11-0.4.0 - 2026-09-06



### Added


- Pet Rabbit Spawn Egg



## neoforge-1.21.11-0.3.0 - 2026-09-06



### Added


- Red_ore_block magic_cube pet_rabbit - blocks items entities examples



## neoforge-1.21.11-0.2.1 - 2026-09-06



### Added


- Add initial files via upload from Forge 1.11 modding tutorial by SilentChaos512

- Add basic block and item assets



### Changed


- Migrate tutorial mod from Forge 1.11.2 to NeoForge 1.21.11



## neoforge-1.21.11-0.1.0 - 2017-06-25



### Added


- Initial commit


