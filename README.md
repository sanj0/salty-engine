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
- Display
- rendering
- GameObjects
- Scenes
- Layers
- LayerCollections
- Animations
- Spritesheets
- Resource management (inner as well as outer)
- Music and Sound
- UI with pre-defined elements like Buttons
- Components (behave like extensions to GameObjects)
- Collsion detection with hitboxes
- Keyboard and mouse input as simple as possible for the developer

**Version Description** v0.3 Zeus (after 0.1 Apollo and 0.2 Hermes) This version has heavy performance improves, mainly because all of the repainting and updating is now in a single class, split into only two threads. Class GameObject has got heavy improves, too. Some abstract methods were added, like onFixedTick(), which gets called every x milliseconds, and which is for things that should be the same, doesn't matter how much FPS the game is running, like movement, animations and physics, onCollision(GameObject other) which gets called whenever this GameObject's hitbox collides with another and onTick() which gets called whenever the stage gets repainted.
Also, the engine is now running at dynamic FPS, because MainLoops repaints whenever the last rendering finishes, so heavy improves there, too.  

**Known bugs/glitches/issues**
- all that has to do with physics (force, friction, gravity)

**TODO** (sort by relevance)
- improve physics system (velocity, impulse, bounce, rotation, integration of mass and acceleration)
- fixe click-detection in ui.Button
- add more UIElements
- Item, ItemContainer and Inventory
- (textboxes)

**NOTE** for help with how to use, please check out the files in 
src/main/java/testing

**Coming soon:** (or not so soon) Javadoc for all public methods but getters and setters
