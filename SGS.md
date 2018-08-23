# SGS
This documentation will explain all features of sgs, the "salty gameObject scripting" script language. It is a easy way of making games with the java library [sjgl](https://github.com/edgelord314/sjgl) without any coding knowledge. SGS is made for beginners and is easy to learn and understand. Every sgs script is a simple text file (not with the ".txt" ending - for example: myScript.sgs) and stands for one single GameObject. We call this GameObject "parent".

## 1 The structure
SGS has its own structure of code.

### 1.1 The segments
All sgs scripts are basically made out of six different segments:

- The `scriptInit` segment in which you define some script parameters e.g. the name of the parent
- The `vars` segment in which you can define variables
- The `init` segment which gets called once directly at the beginning
- The `collision` segment which gets called whenever the parent collides with another GameObject
- The `fixedTick` segment which gets called every fixed tick (usually every 1 millisecond (0.001 second))
- The `draw` segment which gets called every time the game renders, in here you define how to draw the parent

### 1.2 The scriptLines
Every line in a sgs script stands for one specific action, and is made out of three parts:

- The `command` which says what action should be done
- The `major argument` which for example says what kind of variable to use
- The `minor argument` which for example tells which variable to use

## 2 Variables
Variables have a name and can store values, for example text, numbers or pictures. In sgs you have six different types of variables:

| Type name| Value                                                               | Value example | Declaration example       |
| -------- | ------------------------------------------------------------------- | ------------- | ------------------------  |
| Text     | Text (String in Java)                                               | Hello World   | `var Text myText`     |
| Number   | All Integers from -2,147,483,648 to 2,147,483,647 (int in Java) | 314           | `var Number myNumber` |
| Float    | All floating numbers from 3.40282347 x 10^38 to 1.40239846 x 10^-45 (float in Java) | 3.14          | `var Float myFloat`   |
| Vector2F | Two Floats, one for the x coordinate and the other for the y one    | 3.14 , 6.28   | `var Vector2F myVec`  |
| Image    | An Image (jpg, bmp, jpeg, wbmp, png, gif - BufferedImage in Java)   | image.png     | `var Image myImage`   |
| Animation| A Spritesheet as well as the information when to take which sprite  | -see below-   | `var Animation myAnim`|

To set a variable's value, use the `set` command which takes in the name of the variable you want to set as the `major argument` and the new value as the `minor argument`. A way of not setting a new value but changing the existing value is not available yet.

| Type name | What value to set       | Example                                   |
| --------- | ----------------------- | ----------------------------------------- |
| Text      | The text                | `set myText Hello#_#World`            |
| Number    | The integer             | `set myNumber 314`                    |    
| Float     | The float number        | `set myFloat 3.14`                    |
| Vector2F  | The x and y coordinates | `set myVec 3.14 , 6.28`               |
| Image     | The path to the image   | `set myImage path=res/pics/image.png` |
| Animation | The Spritesheet         | `set myAnim spritesheet=myImage`      |

**Note:** By setting an Image or Vector2F value it is very important to have the exact same syntax as in the table above. The spaces between the comma in the Vector2F setting are very important, but there are no spaces allowed between "path" "=" and the actual path in the setting of the Image! In a Text, you have to convert all spaces to "#_#", as seen in the table above.      

## 3 The `vars` segment
In this segment, you can define variables. The `vars` segment is usually the second one in a sgs script, but it can be everywhere in the script.

### 3.1 The surrounding
To tell sgs where the single segments start and end, you have to surround them in a specific way. The `vars` segment looks like this:

```
-vars
var Number myNumber
set myImage path=res/pics/image.png
--vars
```       
### 3.2 Commands
This is a list of all commands available for this segment:

| Command   | Action                       | Major argument    | Minor argument           | Example                   |
| --------- | ---------------------------- | ----------------- | ------------------------ | ------------------------- |
| `var` | Creates a new variable       | The variable type | The name of the variable | `var Number myNumber` |
| `set` | Sets the value of a variable | The variable name | The new value            | `set myNumber 314`    |

## 4 The `init` segment
The `init` segment is usually the third one in a sgs script but it can be everywhere. It gets called once right at the start and then never again, so you would use it to for example add Components to the GameObject or set some variable values.

### 4.1 The surrounding
To tell sgs where the single segments start and end, you have to surround them in a specific way. The `init` segment looks like this:

```
-init
set myImage path=res/pics/image.png
addComp ImageRender name=imgRender , image=myImage
--init
```
### 3.2 Commands
This is a list of all commands available for this segment:

| Command       | Action                       | Major argument     | Minor argument           | Example                              |
| ------------- | ---------------------------- | ------------------ | ------------------------ | ------------------------------------ |
| `set`     | Sets the value of a variable | The variable name  | The new value            | `set myNumber 314`               |
| `addComp` | Adds a Component to parent   | The component name | The necessary arguments  | `addComp OvalRender name=myOvRen`|
| `setPos`  | Sets the position of the `parent` | Whether to use an existing Vector2F or specifiy a new one (new ord Vector2F) | The new Vector2F or the name of an existing one | `setPos new 3.14 , 9.81` |
