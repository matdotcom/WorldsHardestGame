package com.company;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class BluedotControlLeft extends Control {
    private LocalTimer timer = FXGL.newLocalTimer();
    private boolean goingUp = false;

    @Override
    public void onUpdate(Entity entity, double tpf) {
        // 1. check if timer elapsed
        if (timer.elapsed(Duration.seconds(1.4))) {
            // 2. perform logic
            goingUp = !goingUp;

            // 3. capture time so that timer is reset
            timer.capture();
        }

        double speed = tpf * -110;

        entity.translateX(goingUp ? -speed : speed);
    }
}
