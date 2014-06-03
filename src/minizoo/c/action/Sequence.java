package minizoo.c.action;

import java.util.ArrayList;

public class Sequence extends Finite {

    public Sequence(Finite... actions) {
        this.actions = new ArrayList<Finite>();

        for (Finite action : actions) {
            this.actions.add(action);
        }
    }

    @Override
    public void clear() {
        super.clear();
        for (Finite action : actions) {
            action.clear();
        }
    }

    @Override
    public float getDuration() {
        float accDuration = 0f;
        for (Finite action : actions) {
            accDuration += action.getDuration();
        }
        return accDuration;
    }

    @Override
    public void update(float elapsed) {
        float prevTime = time;
        super.update(elapsed);

        float accDuration = 0f;
        for (Finite action : actions) {
            if (time > accDuration && prevTime > accDuration) {
                action.update(elapsed);
            }

            accDuration += action.getDuration();
        }
    }

    @Override
    public void sample(float t) { }

    ArrayList<Finite> actions;
}
