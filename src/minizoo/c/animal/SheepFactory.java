package minizoo.c.animal;

import minizoo.c.Animal;
import minizoo.c.animal.sheep.Sheep;
import minizoo.i.AnimalFactory;

public class SheepFactory implements AnimalFactory {
    @Override
    public Animal build() {
        return new Sheep("Sheep");
    }
}
