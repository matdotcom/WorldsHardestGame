package common;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.entity.component.Required;


    @Required(PositionComponent.class)
    public class PlayerControl extends Control {

        private PositionComponent position;

        private double speed = 0;

        @Override
        public void onUpdate(Entity entity, double tpf) {
            speed = tpf * 60;
        }

        public void up() {
            position.translateY(-1.1 * speed);
        }

        public void down() {
            position.translateY(1.1 * speed);
        }

        public void left() {
            position.translateX(-1.1 * speed);
        }

        public void right() {
            position.translateX(1.1 * speed);
        }
    }

