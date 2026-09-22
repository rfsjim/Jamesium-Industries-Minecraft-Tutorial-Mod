# Jamesium Industries: A Minecraft Tutorial Mod

Using modding Minecraft as an example of how to use VS Code and learn more Java.

Updated Minecraft tutorial using Neoforge 21.11.45
Minecraft 1.21.11

![Static Badge](https://img.shields.io/badge/github-another_minecraft_mod_repo-800000?logo=github) 
![GitHub License](https://img.shields.io/github/license/rfsjim/Jamesium-Industries-Minecraft-Tutorial-Mod?color=red&link=LICENSE) 
![Last Commit](https://img.shields.io/github/last-commit/rfsjim/Jamesium-Industries-Minecraft-Tutorial-Mod) 
![GitHub commit activity](https://img.shields.io/github/commit-activity/w/rfsjim/Jamesium-Industries-Minecraft-Tutorial-Mod) 
![Build](https://img.shields.io/github/actions/workflow/status/rfsjim/Jamesium-Industries-Minecraft-Tutorial-Mod/build.yml) 


 



Features
=======
- A pet rabbit that periodically gifts nuggets and gems
- Red ore block that smelts into nether bricks

TODO List & CHANGELOG
=======
- [Project Plan / TODO list](TODO.md)
- [Change Log](CHANGELOG.md)

Useful Tools
=======
- `.\gradlew build` Build
- `.\gradlew runClient` Run Client
- `.\gradlew runServer` Run Server
- `.\gradlew classes` Fast Compilation Checks
- `.\gradlew clean` Clean Slate
- `.\gradlew test` Run Tests
- `Restart Java Language Server` VS Code's understanding of this Java project appears to be wrong. Rebuild it.
- `Clean Java Language Server Workspace` A heavier cache/project-workspace cleanup when restarting isn't enough.
- `Java: List All Java Source Paths` List ALL Java source paths

VS Code IDE Commands and Navigation
=======
- Go to Definition put your cursor on a symbol and F12 or right click --> Go to Definition, OR Ctrl + Click a symbol (editor.action.revealDefinition)
- Show All Symbols Ctrl+T (workbench.action.showAllSymbols)
- Go to File..., Quick Open	Ctrl+P	(workbench.action.quickOpen) # to go to symbols, : to go to line number, @ to go to symbol in current file
- Show Type Hierarchy Which classes extend, which classes implement, and full inheritance tree. Supertype, subtype, and subclasses
- Ctrl+F12 — Go to Implementations show the contracts for interfaces or abstract methods
- Shift+F12 - Find All References (editor.action.goToReferences) where does this actually get used?
- Alt+F12 — Peek Definition - You can inspect the signature/source without abandoning your code.
- Ctrl+Space Trigger Intellisense suggestions (editor.action.triggerSuggest)

Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
