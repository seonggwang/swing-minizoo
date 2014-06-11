package minizoo.c.animal.bear;

import minizoo.c.Entity;
import minizoo.c.action.Forever;
import minizoo.c.action.Instant;
import minizoo.c.action.MoveBy;
import minizoo.c.action.RotateBy;
import minizoo.c.action.Sequence;
import minizoo.c.action.easing.EaseInOutSine;
import minizoo.c.core.Vector2d;

import java.awt.*;
//import java.awt.geom.Ellipse2D;

public class BearLeftLeg extends Entity {
    public BearLeftLeg(String name) {
        super(name);

        this.setContentSize(new Vector2d(62, 112));
        this.runAction(
               new Forever(
                        new Sequence(
                               // new Instant(
                                    //    new RotateBy(0.5f, 10f)),
                                        new EaseInOutSine(
                                                new RotateBy(2f, 5f))
                        )
                )
        );
    }

   // public void setNearLeg(boolean isNear) {
   //     this.isNear = isNear;
   // }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
    }

    @Override
    public void visit(Graphics2D g2) {
      //  g2.setColor(Bear.BlackColor);
        //g2.fillRect(259, 563, 62, 112);
        //g2.fillOval(0, 0, 62, 112);
      //  g2.rotate(Math.PI*-1/8.0);
        //g2.fillRoundRect(50, 200, 130, 200, 30, 30);
        //g2.setColor(Panda.BlackColor);
        //g2.fillRect(387, 563, 62, 112);
        //g2.fillOval(387, 563, 62, 112);
       // g2.setColor(isNear? Panda.brandColor : Panda.subBrandColor);
       // g2.fillRect(0, 0, 64, 59);
        g2.setColor(Bear.OutSkinColor);
        g2.rotate(Math.PI*0.5);
        g2.fillOval(300, 150, 190, 112);
        
       g2.setColor(Bear.InSkinColor);
       //g2.fillRect(259, 563, 62, 112);
      // g2.fillOval(0, 0, 62, 112);
       g2.rotate(Math.PI*-2/9.0);
       g2.fillRoundRect(100, 150, 130, 200, 30, 30);
        
    }

    boolean isNear;
}
