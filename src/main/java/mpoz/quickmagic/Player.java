package mpoz.quickmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private int life;
    private List<Creature> creatures;
    private boolean activ;

    public Player(String name, int life) {
        this.name = name;
        this.life = life;
        creatures = new ArrayList<>();
    }

    public int getLife() {
        return life;
    }

    public void looseLife(int amount) {
        this.life -= amount;
    }

    public void cast(Creature creature) {
        if (!activ) {
            throw new NotYourTurnException("Cannot cast : " + creature);
        }
        creatures.add(creature);
    }

    public void declaresAttacker(Creature creature) {
        if (!activ) {
            throw new NotYourTurnException("Cannot declare attacker : " + creature);
        }

        if (!creatures.contains(creature)) {
            throw new InvalidAttackerException(creature.getName());
        }
        creature.attack();
    }

    public List<Creature> getAttackingCreatures() {
        return creatures.stream().filter(creature -> creature.isAttacking()).collect(Collectors.toList());
    }

    public void declaresBlocker(Creature defending, Creature attacking) {
        defending.block(attacking);
    }

    public void beginTurn() {
        this.activ = true;
    }

    public void endTurn() {
        this.activ = false;
    }


    public boolean isActiv() {
        return activ;
    }

    public void sendCreatureToGraveyard(Creature creature) {
        this.creatures.remove(creature);
    }

    public int creaturesCount() {
        return creatures.size();
    }
}
