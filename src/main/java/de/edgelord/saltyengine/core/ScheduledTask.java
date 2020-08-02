/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;

/**
 * Contains the delay and the task itself of a task that is scheduled for later
 * execution by {@link Game#executeLater(Runnable, long)}.
 * <p>
 * Any instance of this class that lives until its {@link #delay} is reached
 * will {@link Runnable#run() run} its {@link #task task} and {@link
 * java.util.List#remove(Object) removes} itself from the list of scheduled
 * tasks within {@link Engine}.
 */
public class ScheduledTask implements FixedTickRoutine {

    private final Runnable task;
    private final long delay;
    private long ticks = 0;

    public ScheduledTask(final Runnable task, final long delay) {
        this.task = task;
        this.delay = delay;
    }

    @Override
    public void onFixedTick() {
        if (ticks >= delay) {
            task.run();
            Game.getEngine().getScheduledTasks().remove(this);
        } else {
            ticks++;
        }
    }
}
