package com.company;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntitySpawner;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import common.PlayerControl;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WorldsHardestApp extends GameApplication {

    // laver en player. Vi definerer vores ting i spillet i form af enums.
private enum Type {
    PLAYER, BLUEDOT
}

private RotatingControl rotatingControl;
private PlayerControl playerControl;
private Entity player,bluedot;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        // basale gamessetings. Styrer størrelsen af vores program. Og om der skal være en menu osv.
        // For nu slår vi alt fra.
        gameSettings.setWidth(1200);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Worlds Hardest Game!");
        gameSettings.setVersion("0.1");
        gameSettings.setProfilingEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setIntroEnabled(false);
        gameSettings.setMenuEnabled(false);
    }

    @Override
    protected void initGame() {
        playerControl = new PlayerControl();
        rotatingControl = new RotatingControl();

        // Spawner vores spiller, vi definerer hvor den spawner, samt hvor stor den skal være.

// spawner vores bluedot.
//
bluedot = Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,200)
                .viewFromNodeWithBBox(new Rectangle(13,13,Color.BLUE))
                .build();
// Spawner bluedot 2
        Entities.builder()
                .type(Type.BLUEDOT)
                .at(400,400)
                .viewFromNodeWithBBox(new Rectangle(13,13,Color.BLUE))
                .with(rotatingControl)
                .with(new CollidableComponent(true))
                .buildAndAttach();


        // Spawner vores spiller, vi definerer hvor den spawner, samt hvor stor den skal være.
        player = Entities.builder()
                .type(Type.PLAYER)
                .at(25,100)
                .bbox(new HitBox("PLAYER_BODY", BoundingShape.box(25,25)))
                .viewFromNode(new Rectangle(25,25, Color.DARKRED))
                .with(playerControl)
                .build();




        player.addComponent(new CollidableComponent(true));
        bluedot.addComponent(new CollidableComponent(true));



getGameWorld().addEntities(player,bluedot);



    }

    @Override
    protected void initInput() {
        Input input = getInput();

        // laver en user action som gør du bevæger firkanten til venstre på A
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                playerControl.left();
            }
        }, KeyCode.A);

        // laver en user action som gør du bevæger firkanten til højre på D
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                playerControl.right();
            }
        }, KeyCode.D);

        // laver en user action som gør du bevæger firkanten op på W
        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                playerControl.up();
            }
        }, KeyCode.W);

        // laver en user action som gør du bevæger firkanten ned på S
        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                playerControl.down();
            }
        }, KeyCode.S);
    }

    protected void initPhysics(){


        PhysicsWorld physics = getPhysicsWorld();


        // her laver vi en kollision imellem player og bluedot.
        physics.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.BLUEDOT) {

            // Her definerer vi at hvis en kollision finder sted imellem player og bluedot, sætter den players position tilbage til spawn!!
           @Override
            protected void onHitBoxTrigger(Entity player, Entity bluedot, HitBox playerBox, HitBox bluedotBox){
               player.setPosition(25,100);
           }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
