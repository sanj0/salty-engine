# sjgl

Sjgl is a simple Java game library with the goal
to make game developing doable for everybody. 

### It isn't just a library for rendering
It also includes Gameobject management, camera moving,
display management, support for hitboxes, input, and 
resource management, but also support for scene and 
stage management and saving with 
stdf (github.com/edgelord314/stdf)

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

**TODO** 
- Music and Sound
- Vector2f integration
- Hitbox integration
- Item, ItemContainer and Inventory
- a font engine
- (textboxes)

**NOTE** for help with how to use, please check out the files in 
src/de/edgelord/sjgl/testing

**Coming soon:** (or not so soon) Javadoc for all public mehtods but getters and setters
