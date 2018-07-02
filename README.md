# sjgl

Sjgl is a simple Java game library with the goal
to make game developing doable for everybody. 

### It isn't just a library for rendering
It also includes Gameobject management, camera moving,
display management, support for hitboxes, input, and 
resource management, but also support for scene and 
stage management and saving with 
stdf (github.com/edgelord314/stdf)

**Version Description** v0.3 Zeus (after 0.1 Apollo and 0.2 Hermes) This version has heavy performance improves, mainly because all fo the repainting and Updating is now in a single class, split into only two threads. Class GameObject has gotten heavy improves, too. Some abstract methods were added, like onFixedTick(), which gets called every x milliseconds, and which is for things that should be the same, doesn't matter how much FPS the game is running, like movement, animations, physics, onCollision(GameObject other) which gets called whenever this GameObject's hitbox collides with an other (not used yet) and onTick() which gets called whenever the stage gets repainted.
Also, the engine is now running at dynamic FPS, because MainLoops repaints whenever the last rendering finishes, so heavy improves there, too.  

- renamed packages from starboard to edgelord
- added all those fancy new abstract methods to GameObject

**TODO** - Music and Sound
- Vector2f integration
- Hitbox integration
- Item, ItemContainer and Inventory
- a font engine
- (textboxes)

**NOTE** for help with how to use, please check out the files in 
src/de/edgelord/sjgl/testing

**Coming soon:** (or not so soon) Javadoc for all public mehtods but getters and setters
