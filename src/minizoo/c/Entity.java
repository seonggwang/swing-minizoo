package minizoo.c;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import minizoo.c.action.Action;
import minizoo.c.core.Vector2d;
import minizoo.i.Collider;
import minizoo.i.Drawable;
import minizoo.i.Task;

public abstract class Entity implements Comparable<Entity>, Collider, Drawable, Task {

	public Entity(String name) {
		this.name = name;
	}
	public String toString() {
		return "Entity:" + name + " " + getPosition();
	}

	@Override
	public boolean intersect(Point2D point, AffineTransform base) {
		AffineTransform affineTransform;

        if (base == null) {
            affineTransform = getTransform();
        } else {
            affineTransform = (AffineTransform)base.clone();
            affineTransform.concatenate(getTransform());
        }

		Rectangle2D boxCollider = new Rectangle2D.Double(0, 0, getContentSize().x, getContentSize().y);
		Shape transShaped = affineTransform.createTransformedShape(boxCollider);
		
		if (transShaped.contains(point)) {
			return true;
		} else {
			for (Entity child : getChildren()) {
				if (child.intersect(point, affineTransform)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		beforeDraw(g2);
		visit(g2);
		afterDraw(g2);
	}

	protected void beforeDraw(Graphics2D g2) {
		// Push Matrix
		lastAffineTransform = g2.getTransform();
		g2.transform(getTransform());
	}

	protected void visit(Graphics2D g2) {
	}

	protected void afterDraw(Graphics2D g2) {
		drawChildren(g2);

		// Pop Matrix
		g2.setTransform(lastAffineTransform);
	}

	protected void drawChildren(Graphics2D g2) {
		for (Entity child : getChildren()) {
			if (child!=null && child instanceof Drawable) {
				Drawable drawable = child;
				drawable.draw(g2);
			}
		}
	}

	@Override
	public int compareTo(Entity entity) {
		return this.zOrder <= entity.zOrder ? -1 : 1;
	}

	public void addChild(Entity entity) {
		addChild(entity, -1);
	}

	public void addChild(Entity entity, int zOrder) {
		entity.zOrder = zOrder;
        entity.parent = this;
		children.add(entity);
	}

	public void removeChild(Entity entity) {
		children.remove(entity);
        entity.parent = null;
	}

	public PriorityQueue<Entity> getChildren() {
		return children;
	}

    public void setParent(Entity parent) {
        if (this.parent != null) {
            this.parent.removeChild(this);
        }

        if (parent != null) {
            parent.addChild(this);
        }

        this.parent = parent;
    }
    public Entity getParent() {
        return parent;
    }

    @Override
	public void update(float elapsed) {
		updatedTime += elapsed;
        updateAction(elapsed);

        for (Entity child : children) {
            child.update(elapsed);
        }
	}

	public void setContentSize(Vector2d contentSize) {
		this.contentSize = contentSize;
		isDirty = true;
	}
	public Vector2d getContentSize() {
		return contentSize;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
		isDirty = true;
	}
	public Vector2d getPosition() {
		return position;
	}

	public void setAnchor(Vector2d anchor) {
		this.anchor = anchor;
		isDirty = true;
	}
	public Vector2d getAnchor() {
		return anchor;
	}

	public void setScale(Vector2d scale) {
		this.scale = scale;
		isDirty = true;
	}
	public Vector2d getScale() {
		return scale;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
		isDirty = true;
	}
	public double getRotation() {
		return rotation;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}

	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
	}
	public int getZOrder() {
		return zOrder;
	}

	public AffineTransform getTransform() {
		if (isDirty) {
			transform.setToIdentity();

			// Translation
			transform.translate(position.x, position.y);

			// Rotation
			transform.rotate(rotation);

			// Scale
			transform.scale(scale.x, scale.y);

			// Anchor Apply
			transform.translate(-contentSize.x * anchor.x, -contentSize.y * anchor.y);
		}

		return transform;
	}

    // Action
    private void updateAction(float elapsed) {
        class ActionControl {
            public ActionControl(ArrayList<Action> actions, float time) {
                this.actions = actions;
                this.time = time;
            }

            public void removeExpiredAction(float elapsed) {
                float threshold = time + elapsed;

                for (Iterator<Action> it = actions.iterator(); it.hasNext(); ) {
                    if (it.next().getDuration() <= threshold) {
                        it.remove();
                    }
                }
            }
            public void processAction(float elapsed) {
                for (Action action : actions) {
                    action.update(elapsed);
                }
            }

            ArrayList<Action> actions;
            float time;
        }

        ActionControl control = new ActionControl(actions, updatedTime-elapsed);
        control.removeExpiredAction(0);
        control.processAction(elapsed);
        control.removeExpiredAction(elapsed);
    }
    public void runAction(Action action) {
        action.setTarget(this);
        action.clear();
        actions.add(action);
    }
    public void runAction(Action action, String id) {
        action.setIdentifier(id);
        runAction(action);
    }
    public void stopAction(String id) {
        for (Action action : actions) {
            if (id.equals(action.getIdentifier())) {
                actions.remove(action);
            }
        }
    }
    public void stopAllAction() {
        actions.clear();
    }
    ArrayList<Action> actions = new ArrayList<Action>();

	// Entity property
	String name;
	Vector2d contentSize = new Vector2d(0, 0);
	Vector2d position = new Vector2d(0, 0);
	Vector2d anchor = new Vector2d(0.5, 0.5);
	Vector2d scale = new Vector2d(1, 1);
	double rotation = 0.0;
	Color color = Color.white;
	int zOrder = -1;

	AffineTransform transform = new AffineTransform();
	boolean isDirty = true;

    public float getUpdatedTime() {
        return updatedTime;
    }
	float updatedTime;
	AffineTransform lastAffineTransform;

	// Entity hierarchy
    Entity parent = null;
	PriorityQueue<Entity> children = new PriorityQueue<Entity>();
	public static PriorityQueue<Entity> list = new PriorityQueue<Entity>();
	public static void add(Entity entity) {
		Entity.add(entity, -1);
	}
	public static void add(Entity entity, int zOrder) {
		entity.zOrder = zOrder;
		Entity.list.add(entity);
	}
}
