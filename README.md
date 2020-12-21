# Splash-Screen Mod for Fabric
[Forge Version](https://github.com/StopModReposts/Splash-Screen-Mod) -
[Fabric Version](https://github.com/StopModReposts/Splash-Screen-Mod-Fabric)

## What does this mod do?
This mod helps modders to bring attention to players about the StopModReposts
campaign. It does this by showing a toast and making a notice explaining the
basic goal of StopModReposts. Don't worry - both the toast, and the button are
configurable by either hosting a programmatic election among mods or with a
config file, shippable by modpacks.

<img src="https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod-Fabric/master/assets/titlescreen.png" width="30%"></img>
<img src="https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod-Fabric/master/assets/notice.png" width="30%"></img>
<img src="https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod-Fabric/master/assets/settings.png" width="30%"></img>


## How can I use this mod?
### If you are a player
You don't really need this mod if you found it yourself and want to add it.
You can just visit https://StopModReposts.org and learn the same info this mod
provides.

### If you are a modder
#### Participate in mods election
If Splash-Screen Mod is presented, it will host an election on the first launch
to determine which features to enable. To participate you just need to add
these custom values to your `fabric.mod.json`:
```json
{
  "custom": {
    "stopmodreposts:showToast": true,
    "stopmodreposts:showButton": true
  }
}
```
Splash-Screen Mod will automatically host an election and count all votes for
and against each feature. Splash-Screen Mod itself doesn't have a vote in the
election. You can also vote against enabling one or both of the features.

#### Embed Splash-Screen Mod
Besides participating in election, you can also embed the mod. Not many
modpack authors and players may know about this mod, so you may take action
first. Just add this repository and dependency to your `build.gradle`:
```groovy
repositories {
    maven {
        url "https://maven.falseresync.ru"
    }
}

dependencies {
    include "org.stopmodreposts:Splash-Screen-Mod-Fabric:1.0.0"
}
```
NOTE: you should also participate in mods election if you embed Splash-Screen
Mod, because it won't happen automatically.

NOTE: it requires Fabric API.

### If you are a modpack-maker
#### Override default config
Create `.minecraft/config/stopmodreposts.json` file with following contents:
```json
{
  "showToast": true,
  "showButton": true
}
```
This overrides election results between the mods, but can be changed by a
player via ModMenu. You can also use this file to disable one or both of the
features of Splash-Screen Mod (this will also override mods election).

#### Add this mod to your modpack
If you are already overriding mods election results, you should consider adding
this mod yourself. It will also help if no mods embed it, but some still want
to vote. You can download it from the following:
- [Modrinth](https://modrinth.com/mod/stopmodreposts)
- [GitHub](https://github.com/StopModReposts/Splash-Screen-Mod-Fabric/releases)

NOTE: it requires Fabric API.

## I don't like this mod. How can I disable it?
### If you are a player
1. Download ModMenu for Fabric
1. Go to `Mods` screen and find `StopModReposts` mod
1. Click the setting button
1. Configure the mod however you like

### If you are a modder
Add these custom values to your `fabric.mod.json`:
```json
{
  "custom": {
    "stopmodreposts:showToast": false,
    "stopmodreposts:showButton": false
  }
}
```
Splash-Screen Mod will automatically host an election and count all votes for
and against each feature. Splash-Screen Mod itself doesn't have a vote in the
election. You can also vote for enabling one or both of the features.

### If you are a modpack-maker
Create `.minecraft/config/stopmodreposts.json` file with following contents:
```json
{
  "showToast": false,
  "showButton": false
}
```
This overrides election results between the mods, but can be changed by a
player via ModMenu. You can also use this file to enable one or both of the
features of Splash-Screen Mod (this will also override mods election).
