package minizoo.c.action.easing;

import minizoo.c.action.Easing;
import minizoo.c.action.Finite;

public class EaseOutSine extends Easing {
    public EaseOutSine(Finite targetAction) {
        super(targetAction);
    }

    @Override
    public void sample(float t) {
        getTargetAction().sample((float) Math.sin(t * (Math.PI/2.0)));
    }
}
