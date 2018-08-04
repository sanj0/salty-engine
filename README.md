# sjgl

Sjgl is a simple Java game library with the goal
to make game developing doable for everybody. 

### It isn't just a library for rendering
It also includes GameObject management, camera moving,
display management, support for hitboxes, input, and 
resource management, but also support for scene and 
stage management as well as saving with 
stdf (github.com/edgelord314/stdf)

**Features**
- simple Display management
- rendering
- GameObjects for the simplest game developing possible
- Scenes for defining what to render and update
- Layers which are pretty much the same as Scenes
- LayerCollections for having more than one Layer at once on the Screen,
  but if needed, moving with a different speed
- Animations
- Spritesheets
- Resource management (inner as well as outer)
- Music and Sound
- UI with pre-defined elements like Buttons
- Components (behave like extensions to GameObjects)
- Collision detection with hitboxes
- Keyboard and mouse input as simple as possible for the developer
- **examples for almost all of those features within the library** (```testing.Tester``` as main)

**Milestones**
- mavenized project (thanks to maggu2810)
- added all the abstract methods to GameObject (instead of GameUpdating)
- heavily improved the performance, due to move every Thread-thing to one class (core.MainLoops) into only two different Threads
- been able to add InnerResource (again; thanks to maggu2810)
- changed almost everything having to do with positioning to floats instead of integers
- added GameObjectComponents (for exampe a not working physics-components)
- added audio
- FPS-computing (after almost a year of working on a Java game library)
- added a UI

**Known bugs/glitches/issues**
- all that has to do with physics (force, friction, gravity)

**TODO** (sort by relevance)
- improve physics system (targetVelocity, impulse, bounce, rotation, integration of mass and acceleration)
- add more UIElements
- Item, ItemContainer and Inventory

**NOTE** for help with how to use, please check out the files in 
src/main/java/testing

**Coming soon:** (or not so soon) Javadoc for all public methods but getters and setters

 # Join the team 
 Do you want to collaborate? Join the project at https://crowdforge.io/projects/447