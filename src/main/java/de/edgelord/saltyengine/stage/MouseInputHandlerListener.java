/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.stage;

import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;

public class MouseInputHandlerListener {

    private MouseInputHandler mouseHandler;

    public MouseInputHandlerListener(MouseInputHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public MouseInputHandler getMouseHandler() {
        return mouseHandler;
    }

    public void setMouseHandler(MouseInputHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }
}
