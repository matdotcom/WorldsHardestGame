package com.company;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.settings.GameSettings;
import common.PlayerControl;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.app.DSLKt.loopBGM;

public class WorldsHardestApp extends GameApplication {

    // laver en player. Vi definerer vores ting i spillet i form af enums.
private enum Type {
    PLAYER, BLUEDOT, ENDZONE
}

private RotatingControl rotatingControl;
private PlayerControl playerControl;
private Entity player,bluedot, endzone;
private ReverseRotation reverseRotation;
private BluedotControlUp bluedotControlUp;
private BluedotControlDown bluedotControlDown;
private BluedotControlLeft bluedotControlLeft;
private BluedotControlRight bluedotControlRight;

    @Override
    protected void preInit() {
        getAudioPlayer().setGlobalSoundVolume(0.2);
        getAudioPlayer().setGlobalMusicVolume(0.2);

        loopBGM("bgm.mp3");
    }
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
        // her initialiserer vi alle vores entity kontrol klasser.
        playerControl = new PlayerControl();
        rotatingControl = new RotatingControl();
        reverseRotation = new ReverseRotation();
        bluedotControlUp = new BluedotControlUp();
        bluedotControlDown = new BluedotControlDown();
        bluedotControlLeft = new BluedotControlLeft();
        bluedotControlRight = new BluedotControlRight();


        // Spawner vores spiller, vi definerer hvor den spawner, samt hvor stor den skal være.

// spawner vores bluedot.
// DONT DELETE THIS. GAME WILL BREAK IF U DO.
bluedot = Entities.builder()
                .type(Type.BLUEDOT)
                .at(0,0)
                .viewFromNodeWithBBox(new Rectangle(4,4,Color.BLUE))
                .build();
// x = 1100, y = 604
endzone = Entities.builder()
        .type(Type.ENDZONE)
        .at(1100,604)
        .viewFromNodeWithBBox(new Rectangle(96,206,Color.GREEN))
        .buildAndAttach();

// Spawner vores spiller, vi definerer hvor den spawner, samt hvor stor den skal være.
        player = Entities.builder()
                .type(Type.PLAYER)
                .at(100, 700)
                .bbox(new HitBox("PLAYER_BODY", BoundingShape.box(25,25)))
                .viewFromNode(new Rectangle(25,25, Color.DARKRED))
                .with(playerControl)
                .build();


// Spawner de roterende hjul som man skal undgå i første række af mappet.
        Entities.builder()
                .type(Type.BLUEDOT)
                .at(900,4)
                .viewFromNodeWithBBox(new Rectangle(13,200,Color.BLUE))
                .with(rotatingControl)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(700,4)
                .viewFromNodeWithBBox(new Rectangle(13,200,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(300,4)
                .viewFromNodeWithBBox(new Rectangle(13,200,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();


        Entities.builder()
                .type(Type.BLUEDOT)
                .at(500,4)
                .viewFromNodeWithBBox(new Rectangle(13,200,Color.BLUE))
                .with(rotatingControl)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(400,96)
                .viewFromNodeWithBBox(new Rectangle(200,13,Color.BLUE))
                .with(rotatingControl)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(600,96)
                .viewFromNodeWithBBox(new Rectangle(200,13,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(800,96)
                .viewFromNodeWithBBox(new Rectangle(200,13,Color.BLUE))
                .with(rotatingControl)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,96)
                .viewFromNodeWithBBox(new Rectangle(200,13,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();

// Spawner patruljerende bluedots i anden række

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(250,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(300,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(350,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(400,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(450,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(500,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(550,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(600,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(650,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(700,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(750,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(800,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(850,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(900,200)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(950,352)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        // tredje rækkes forhindringer


        Entities.builder()
                .type(Type.BLUEDOT)
                .at(750,402)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(700,552)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlDown())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(650,402)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlUp())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(800,402)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlRight())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(800,482)
                .viewFromNodeWithBBox(new Rectangle(50,40,Color.BLUE))
                .with(new BluedotControlRight())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(800,552)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlRight())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(600,402)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(600,482)
                .viewFromNodeWithBBox(new Rectangle(50,40,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(600,552)
                .viewFromNodeWithBBox(new Rectangle(50,50,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,496)
                .viewFromNodeWithBBox(new Rectangle(200,13,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(300,404)
                .viewFromNodeWithBBox(new Rectangle(13,200,Color.BLUE))
                .with(reverseRotation)
                .with(new CollidableComponent(true))
                .buildAndAttach();

        // Sidste Rækkes forhindringer!

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(430,650)
                .viewFromNodeWithBBox(new Rectangle(13,150,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(550,600)
                .viewFromNodeWithBBox(new Rectangle(13,100,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(650,600)
                .viewFromNodeWithBBox(new Rectangle(13,75,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(650,725)
                .viewFromNodeWithBBox(new Rectangle(13,75,Color.BLUE))
                .with(new BluedotControlLeft())
                .with(new CollidableComponent(true))
                .buildAndAttach();


        // Her laver jeg mappet
        Entities.builder()
                .type(Type.BLUEDOT)
                .at(0,0)
                .viewFromNodeWithBBox(new Rectangle(4,1000,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(0,796)
                .viewFromNodeWithBBox(new Rectangle(1200,4,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(0,0)
                .viewFromNodeWithBBox(new Rectangle(1200,4,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(1196,0)
                .viewFromNodeWithBBox(new Rectangle(4,800,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(0,400)
                .viewFromNodeWithBBox(new Rectangle(1000,4,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,200)
                .viewFromNodeWithBBox(new Rectangle(1000,4,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();

        Entities.builder()
                .type(Type.BLUEDOT)
                .at(200,600)
                .viewFromNodeWithBBox(new Rectangle(1000,4,Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();







        player.addComponent(new CollidableComponent(true));
        bluedot.addComponent(new CollidableComponent(true));
        endzone.addComponent(new CollidableComponent(true));



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

        physics.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.ENDZONE) {

            // Her definerer vi at hvis en kollision finder sted imellem player og bluedot, sætter den players position tilbage til spawn!!
            @Override
            protected void onHitBoxTrigger(Entity player, Entity endzone, HitBox playerBox, HitBox endzoneBox){
                // her skal den skifte level. Når almas laver tutorial omkring level objekter.
                // Og sætte playerposition til den nye starting position.
                getAudioPlayer().stopAllMusic();
                getAudioPlayer().stopAllSounds();
                getAudioPlayer().playSound("victory.mp3");
            }
        });


        // her laver vi en kollision imellem player og bluedot.
        physics.addCollisionHandler(new CollisionHandler(Type.PLAYER, Type.BLUEDOT) {

            // Her definerer vi at hvis en kollision finder sted imellem player og bluedot, sætter den players position tilbage til spawn!!
           @Override
            protected void onHitBoxTrigger(Entity player, Entity bluedot, HitBox playerBox, HitBox bluedotBox){
               player.setPosition(100,700);
               getAudioPlayer().playSound("smack.wav");
           }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
