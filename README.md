# Salty Engine

Salty Engine is a simple Java game library with the goal
to make game developing doable for everybody.

### It isn't just a library for rendering
It also includes GameObject management, camera moving,
display management, support for hitboxes, input, and
resource management, but also support for scene and
stage management as well as saving with
stdf (github.com/edgelord314/stdf)

## SGS
SGS stands for "salty gameObject scripting". SGS is a simple
scripting language with which you can script GameObject and Scenes in the Salty Engine.
So you can make a whole game **without any coding knowledge**. <p>

[Documentation](https://github.com/edgelord314/salty-engine/tree/master/SGS.md) -
[Examples](https://github.com/edgelord314/salty-engine/tree/master/src/main/resources/sgs)

## Why using Salty Engine?

### Pro:
- simple Display management
- have the game in a panel inside your own window
- easy rendering process with ```SaltyGraphics```
- GameObjects for the simplest game developing possible
- Scenes for collecting and defining what to render and update
- Layers which are pretty much the same as Scenes
- LayerCollections for having more than one Layer at once on the Screen,
  but if needed, moving each with a different speed
- linear Keyframe Animations
- Animations
- Spritesheets
- Resource management (inner as well as outer)
- Music and Sound
- UI with pre-defined elements like Buttons
- Components (behave like extensions to GameObjects)
- Collision detection with hitboxes
- A simple yet working physics engine
- Keyboard and mouse input as simple as possible for the developer
- JavaDoc for newer methods
- **examples for almost all of those features within the library** (```testing.Tester``` as main)

### Contra: 
- the engine basics maybe lie to much on static stuff (you won't notice that when using)
- the physics are too simple for some games (no impulses, no realistic bouncing...)
- no amazing Graphical effects out of the box due to limitations because of java2d
- no JavaDoc for older classes/methods

# Join the team
Do you want to collaborate? Join the project at https://crowdforge.io/projects/447 <p>
If you want to get access to a comfortable and frequently updated TODO list of this project create a (free) `Wunderlist` account and send me your username.

**Known bugs/glitches/issues**
- See in the ```Wunderlist``` TODO-List

**NOTE** for help with how to use, please check out the files in
src/main/java/testing
