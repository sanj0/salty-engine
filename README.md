# sjgl

Sjgl is a simple Java game library with the goal
to make game developing doable for everybody.

### It isn't just a library for rendering
It also includes GameObject management, camera moving,
display management, support for hitboxes, input, and
resource management, but also support for scene and
stage management as well as saving with
stdf (github.com/edgelord314/stdf)

## SGS
SGS stands for "salty gameObject scripting". SGS is a simple
scripting language with which you can script GameObject and Scenes in sjgl.
So you can make a whole game **without any coding knowledge**. SGS is currently not usable,
but I will focus on that after the Ludum Dare jam and when the Physics are working. <p>
[Documentation](https://github.com/edgelord314/sjgl/tree/master/SGS.md) -
[Examples](https://github.com/edgelord314/sjgl/tree/master/src/main/java/resources/sgs)

**Features**
- simple Display management
- rendering
- GameObjects for the simplest game developing possible
- Scenes for collecting and defining what to render and update
- Layers which are pretty much the same as Scenes
- LayerCollections for having more than one Layer at once on the Screen,
  but if needed, moving with a different speed
- Keyframe Animations
- Animations
- Spritesheets
- Resource management (inner as well as outer)
- Music and Sound
- UI with pre-defined elements like Buttons
- Components (behave like extensions to GameObjects)
- Collision detection with hitboxes
- Keyboard and mouse input as simple as possible for the developer
- **examples for almost all of those features within the library** (```testing.Tester``` as main)

**Known bugs/glitches/issues**
- the motion stopping on collisions doesn't work with the <code>BirdPlayer</code>
- the gravity stops working after a collision (<code>BirdPlayer</code>)

**TODO** (sort by relevance)
- add more UIElements

**NOTE** for help with how to use, please check out the files in
src/main/java/testing

**Coming soon:** (or not so soon) Javadoc for all public methods but getters and setters
