import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class BlockNoise extends PApplet {
    public static void main(String[] args) {
        PApplet.main(BlockNoise.class);
    }

    @Override
    public void settings() {
        size(1000, 1000);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        blendMode(MULTIPLY);
        noLoop();
    }

    @Override
    public void draw() {
        for (int i = 0; i < PARTICLE_NUMBER; i++) {
            PVector p = new PVector(random(width), random(height));
            do {
                point(p.x, p.y);
                float curX = BLOCK_SIZE * (0.5f + floor(p.x / BLOCK_SIZE));
                float curY = BLOCK_SIZE * (0.5f + floor(p.y / BLOCK_SIZE));
                float angle = PI * (0.5f - noise(NOISE_SCALE * curX, NOISE_SCALE * curY))
                        + atan2(height / 2f - curY, width / 2f - curX);
                p.add(PVector.fromAngle(angle));
            } while (squaredDistanceToCenter(p) > 1);
        }

        saveSketch(this);
    }

    private float squaredDistanceToCenter(PVector p) {
        return sq(p.x - width / 2f) + sq(p.y - height / 2f);
    }
}
