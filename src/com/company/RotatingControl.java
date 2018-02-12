package com.company;

import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;

 class RotatingControl extends Control {

        @Override
        public void onUpdate(Entity entity, double tpf) {
            // 2. specify behavior of the entity enforced by this control
            entity.rotateBy(tpf * 63);}}
