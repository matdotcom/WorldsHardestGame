package com.company;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

@SetEntityFactory
public class WorldsHardestFactory implements EntityFactory {

    @Spawns("bludot")
        public Entity bluDot(SpawnData data){
            return Entities.builder()
                    .from(data)
                    .type(WorldsHardestType.BLUDOT)
                    .viewFromNodeWithBBox(new Rectangle(10,2, Color.BLUE))
                    .with(new CollidableComponent(true))
                    .build();

    }
    @Spawns("player")
    public Entity player(SpawnData data){
        return Entities.builder()
                .from(data)
                .type(WorldsHardestType.PLAYER)
                .viewFromNodeWithBBox(new Rectangle(100,100, Color.RED))
                .with(new CollidableComponent(true))
                .build();

    }
}
