# Salty Engine
![version-badge](https://img.shields.io/badge/version-0.14.8-orange.svg) ![release-badge](https://img.shields.io/badge/release-0.14.8-brightgreen.svg) ![license-badge](https://img.shields.io/hexpm/l/plug.svg) ![price-badge](https://img.shields.io/badge/price-free-red.svg)

![salty-engine-logo](logos/logo_small.png)

Salty Engine is a Java library for making a whole 2D Game with only one single engine. It aims to be the most user-friendly and easy-to-use 2D Game making tool for Java.

### It isn't just a library for rendering
It also includes GameObject management, camera moving,
display management, support for hitboxes, input, and
resource management, but also support for scene and
stage management as well as saving with
[stdf](github.com/edgelord314/stdf).

Minimal **tested** hardware requirements (for [Escape the Junk](#games-made-with-salty-engine)):
- Intel(R) Celeron(R) CPU N3150 @ 1.60GHz 1.60GHz (dual core)
- only integrated graphics
- 4GB RAM

**NOTE: When you release a game that's made with Salty Engine, feel free to [send me an email](mailto:malte.dostal@gmail.com)**

## Content
1. [Why using Salty Engine](#why-using-salty-engine)
2. [Build Instructions](#build-instructions)
3. [Games made with Salty Engine](#games-made-with-salty-engine)
4. [Collaborate](#collaborate)
5. [Help](#help)


# Why using Salty Engine?
**Note:** When you call `Game#start()`, a splashscreen wil appear, saying "Created with Salty Engine". For that, you have five different options using the enum `SplashWindow#Splash` and using `Game#start(Splash splash)` or `Game#start(long fps, Splash splash)`.
You can also disable the Splash using `Game.start(SplashWindow.Splash.NO_SPLASH)` (or with the fps in front), but consider using one to support us :)


### Pro:
- simple Display management
- have the game in a panel inside your own window if you want
- easy rendering process with ```SaltyGraphics```
- GameObjects for the simplest game developing possible
- Scenes for collecting and defining what to render and update
- A huge selection of beautiful colors in `de.edgelord.saltyengine.utils.ColorUtil`
- linear Keyframe Animations
- Animations
- Spritesheets
- Resource management (inner as well as outer)
- Music and Sound
- UI with pre-defined elements like Buttons, Labels and TextBoxes
- Components (like extensions to GameObjects)
- Collision detection with hitboxes
- A simple yet working physics engine
- Keyboard and mouse input as simple as possible for the developer
- JavaDoc for unclear/complicated methods, fields and classes
- **examples of the basics within the library** (package `testing`)

### Contra: 
- the physics are too simple for some games (only rectangular hitboxes, no rotation by the physics...)
- mostly no JavaDoc
- ... (probably missed some points here) ...

# Build instructions
To get a usable JAR of this library, you can whether download an existing release [here](https://github.com/edgelord314/salty-engine/releases/) or build one yourself following these instructions:

0. Requirements: 
    - [git](https://git-scm.com) has to be installed properly
    - Java version 1.8 has to be installed, no newer version works! (When you have more java versions installed, make sure that the enviroment var JAVA_HOME points to 1.8)
    - you have to build [sysDepFiles](https://github.com/edgelord314/sysDepFiles) and [stdf](https://github.com/edgelord314/stdf) first, using the steps 1-3
1. Clone (download) the git repository. To do so, open up the terminal or cmd, go to any directory and type in 
   
   ```bash
   git clone https://github.com/edgelord314/salty-engine
   ```
   `https://github.com/edgelord314/stdf` for stdf and <br>
   `https://github.com/edgelord314/sysDepFiles` for sysDepFiles
    
2. "cd" into the downloaded directory. Type 
   
   ```bash
   cd salty-engine
   ``` 
   `stdf` for stdf and <br>
   `sysDepFiles` for sysDepFiles <br> 
   
   into the cmd or terminal

3. Build the project using the maven wrapper (no installation required). 
   For windows, type the following into the cmd 
   
   ```bash
   mvnw clean install
   ```
   
   For Linux/macOs type the following into the terminal: 
   
   ```bash
   ./mvnw clean install
   ```
   
4. You can now whether use maven to get the lib into the build path of your project (recommended) or use the built JAR directly.
   For maven, add the following to your `pom.xml`:
   
   ```xml
   <dependencies>
        <dependency>
            <groupId>de.edgelord.salty-engine</groupId>
            <artifactId>salty-engine</artifactId>
            <version>[the version you built in step 3]</version>
        </dependency>
    </dependencies>
   ``` 
   When you build the project as described in step 3, something like this should appear somewhere at the beggining of the output:
   
   ```bash
   [INFO] ---------------< de.edgelord.salty-engine:salty-engine >----------------
   [INFO] Building Salty Engine [the version is here ("-SNAPSHOT" is important!)]
   [INFO] --------------------------------[ jar ]---------------------------------

   ```
   
   Or you copy the JAR `target/salty-engine-[version]-jar-with-dependencies.jar` relative to the directory of the cloned project and add it to the buildpath manually using e.g. your IDE.


# Games made with Salty Engine

### Escape the Junk
Made by edgelord314 and LoOoNeliEst for Ludum Dare 42. You can play it [here](https://ldjam.com/events/ludum-dare/42/escape-the-junk).
![Escape the Junk](games/Escape-the-Junk.png)

# Collaborate
Do you want to collaborate? Feel free to open a pull-request (preferably well documentated and only well tested code). Also, feel free to [join the official discord server](https://discord.gg/VW45ySv) <p>
If you want to get access to the comfortable and heavily used TODO list for this project create a (free) [Wunderlist](https://www.wunderlist.com/) account and send me your username.

# Help
For help with how to use, there are three ways:

- read the [project wiki](https://github.com/edgelord314/salty-engine/wiki)
- join the [official discord server](https://discord.gg/VW45ySv), where I and other developers can help you personally
- take a look at the package [`testing`](https://github.com/edgelord314/salty-engine/tree/master/src/main/java/testing), where   
  we test the features of this library
  
