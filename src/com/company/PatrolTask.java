package com.company;

import com.almasb.fxgl.ai.SingleAction;
import com.almasb.fxgl.core.math.FXGLMath;
import javafx.geometry.Point2D;

public class PatrolTask extends SingleAction {

    private static final Point2D[] POINTS = new Point2D[] {
            new Point2D(300, 300),
            new Point2D(500, 500),
            new Point2D(450, 250)
    };

    private Point2D selectedPoint = POINTS[0];

    public PatrolTask() {
        super("Patrol");
    }

    @Override
    public void onUpdate(double tpf) {
        getEntity().translateTowards(selectedPoint, 60 * tpf);

        if (getEntity().getPosition().distance(selectedPoint) < 5) {
            selectedPoint = FXGLMath.random(POINTS).get();
        }
    }
}