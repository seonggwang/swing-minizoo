package minizoo.c.animal.panda;

import minizoo.c.Entity;
import minizoo.c.core.Vector2d;

import java.awt.*;

public class PandaBody extends Entity {
    public class PandaBodyInternal extends Entity {
        public PandaBodyInternal(String name) {
            super(name);
        }

        @Override
        public void visit(Graphics2D g2) {
            g2.setColor(Panda.WhiteColor);
            //g2.fillRect(0, 0, 315, 254);
            g2.fillOval(0, 0, 500, 400);
            //g2.setColor(Panda.BlackColor);
            //g2.fillOval(30, 40, 250, 200);
         //   g2.setColor(Sheep.brandColor);
           // g2.fillRect(2, 37, 395, 206);
            g2.setColor(Panda.DarkWhiteColor);
            g2.fillOval(20, 20, 450, 350);
            
        }
    }

    public PandaBody(String name) {
        super(name);

        this.setContentSize(new Vector2d(500, 400)); // width height
        
        LeftArm = new PandaLeftArm(name + ":LeftArm");
        LeftArm.setPosition(new Vector2d(130, 400));
        //LeftLeg.setNearLeg(true); // 여기까지 했어요
        addChild(LeftArm);

        RightArm = new PandaRightArm(name + ":RightArm");
        RightArm.setPosition(new Vector2d(450, 150));
        //RightLeg.setNearLeg(false);
        addChild(RightArm);

        LeftLeg = new PandaLeftLeg(name + ":LeftLeg");
        LeftLeg.setPosition(new Vector2d(100, 280));
       // LeftLeg.setNearLeg(true);
        addChild(LeftLeg);

        RightLeg = new PandaRightLeg(name + ":RightLeg");
        RightLeg.setPosition(new Vector2d(200, 280));
       // RightLeg.setNearLeg(false);
        addChild(RightLeg);

        bodyInternel = new PandaBodyInternal(name + ":bodyInternel");
        addChild(bodyInternel, 0);

        head = new PandaHead(name + ":head");
        head.setPosition(new Vector2d(250, -50));
        addChild(head, 1);
    }

    @Override
    public void update(float elapsed) {
        super.update(elapsed);
    }

    public PandaHead getHead() {
        return head;
    }
    public PandaBodyInternal getBodyInternel() { return bodyInternel; }
    public PandaLeftArm getLeftArm() {
        return LeftArm;
    }
    public PandaRightArm getRightArm() {
        return RightArm;
    }
    public PandaLeftLeg getLeftLeg() {
        return LeftLeg;
    }
    public PandaRightLeg getRightLeg() {
        return RightLeg;
    }

    PandaHead head;
    PandaBodyInternal bodyInternel;
    PandaLeftArm LeftArm;
    PandaRightArm RightArm;
    PandaLeftLeg LeftLeg;
    PandaRightLeg RightLeg;
}
