package minizoo.c.animal.panda;

import minizoo.c.Entity;
import minizoo.c.action.Forever;
import minizoo.c.action.RotateBy;
import minizoo.c.action.Sequence;
import minizoo.c.action.easing.EaseInOutSine;
import minizoo.c.action.easing.EaseInSine;
import minizoo.c.core.Vector2d;

import java.awt.*;

public class PandaRightLeg extends Entity {
    public PandaRightLeg(String name) {
        super(name);

        this.setContentSize(new Vector2d(62, 112));
        this.runAction(
                new Forever(
                        new Sequence(
                                new EaseInOutSine(
                                        new RotateBy(1f, 2f)),
                                new EaseInSine(
                                        new RotateBy(1f, -2f))
                        )
                )
        );
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
    }

    @Override
    public void visit(Graphics2D g2) {
        g2.setColor(getTintedColor(Panda.DarkWhiteColor));
        g2.rotate(Math.PI * 1 / 9.0);
        g2.fillOval(275, 180, 190, 112);

        g2.setColor(getTintedColor(Panda.BlackColor));
        g2.rotate(Math.PI * -2 / 9.0);
        g2.fillRoundRect(50, 200, 130, 200, 30, 30);
    }
}
