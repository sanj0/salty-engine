![Java CI with Maven](https://github.com/edgelord314/salty-engine/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
![license-badge](https://img.shields.io/hexpm/l/plug.svg) <p> 

- [Why using Salty Engine](#why-using-salty-engine)
- [Build Instructions](#build-instructions)
- [How to use Salty Engine](#how-to-use-salty-engine)
- [Games made with Salty Engine](#games-made-with-salty-engine)
- [Collaborate](#collaborate)
- [Help](#help)

# Salty Engine

Salty Engine is a Java library for making a whole 2D Game with only one single engine and **without any dependencies**. It aims to be a very user-friendly and easy-to-use 2D Game making tool for Java.

### It isn't just a library for rendering
It also includes collision detection, physics, several visual effects, over 100 beautiful colors, useful utility classes, hardware acceleration as well as saving data and much more, 
spread accross **more than 230 classes with an average of approximately 90 lines**

Minimal **tested** hardware requirements made possible by [JProfiler, a java profiler](https://www.ej-technologies.com/products/jprofiler/overview.html) (for [Escape the Junk](#games-made-with-salty-engine)):
- Intel(R) Celeron(R) CPU N3150 @ 1.60GHz 1.60GHz (dual core)
- no graphics card
- 200MB RAM

**NOTE: When you release a game that's made with Salty Engine, feel free to [send me an email](mailto:malte.dostal@gmail.com) that I can add it to [this section](#games-made-with-salty-engine)** <br></br>
**NOTE 2: The default font (`SaltySystem.defaultFont`) is the same on all systems to ensure the same looking graphics on every computer. This font is powered by Google Fonts and is published under the Apache 2 license as well.**  

# Why using Salty Engine?

### Features (at least some of them):
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
- **examples of the basics within the library** (package `testing` and `example`)

# Build instructions
You can build Salty Engine using `maven` and add it to your project by adding this to your pom.xml file:
```
<dependencies>
    <dependency>
        <groupId>de.edgelord.salty-engine</groupId>
        <artifactId>salty-engine</artifactId>
        <version>[version]</version>
    </dependency>
</dependencies>
```
Or: to get a usable JAR of this library, you can either download an existing release [here](https://github.com/edgelord314/salty-engine/releases/) or build one yourself following these instructions:

0. Requirements:
    - [git](https://git-scm.com) has to be installed properly
    - a JDK, 1.8 or later
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

4. You can now either use maven to get the lib into the build path of your project (recommended) or use the built JAR directly.
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

# How to use Salty Engine

You can very easily start a Salty Engine game project using [this](https://www.github.com/edgelord314/salty-engine-setup) tool. It basically creates a runnable project template.
There is a series of videos uploaded to YouTube that explain how to use Salty Engine

1. [How to install Salty Engine](https://youtu.be/7rQp3EQbX_k)

# Games made with Salty Engine

### Escape the Junk
Made by edgelord314 and LoOoNeliEst for Ludum Dare 42. You can play it [here](https://ldjam.com/events/ludum-dare/42/escape-the-junk).
![Escape the Junk](games/Escape-the-Junk.png)

### Moon Defender
A fun little pixel-style prototype of a top-down shooter, made by edgelord314. You can play it [here](https://edgelord314.itch.io/moon-defender)
![Moon Defender](https://img.itch.zone/aW1nLzI4ODk1NjQucG5n/315x250%23c/k3gwyf.png)

### Br34k0ut 
A simple, colorful clone of Breakout by Atari. You can play it [here](https://edgelord314.itch.io/br34k0ut) <p>
![Br34k0ut](https://img.itch.zone/aW1nLzI4ODk5ODEucG5n/347x500/rgYDOZ.png)

# Collaborate
Do you want to collaborate? Feel free to open a pull-request (preferably well documented and only well tested code). Also, feel free to [join the official discord server](https://discord.gg/VW45ySv) <p>

# Help
For help with how to use, there are five ways:

- read the [project wiki](https://github.com/edgelord314/salty-engine/wiki)
- join the [official discord server](https://discord.gg/VW45ySv), where I and other developers can help you personally
- take a look at [this section of the README](#how-to-use-salty-engine)
- take a look at the package [`testing`](https://github.com/edgelord314/salty-engine/tree/master/src/main/java/testing), where   
  we test the features of this library
- take a look at the package [`example`](https://github.com/edgelord314/salty-engine/tree/master/src/main/java/de/edgelord/saltyengine/example)
