package mpoz.quickmagic;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player player1;
    private final Player player2;


    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void resolve() {
        getActivePlayer().getAttackingCreatures().stream().filter(creature -> !creature.isBlocked())
                .forEach(creature -> getDefendingPlayer().looseLife(creature.getPower()));

        getActivePlayer().getAttackingCreatures().stream().forEach(creature -> {
            if (creature.isBlocked()) {
                Creature blockingCreature = creature.getBlockedBy();
                if (creature.getPower() > blockingCreature.getPower()) {
                    getDefendingPlayer().sendCreatureToGraveyard(blockingCreature);
                }
                else if (creature.getPower() < blockingCreature.getPower()) {
                    getActivePlayer().sendCreatureToGraveyard(creature);
                }
                else {
                    getActivePlayer().sendCreatureToGraveyard(creature);
                    getDefendingPlayer().sendCreatureToGraveyard(blockingCreature);
                }
            }
        });
    }

    private Player getActivePlayer() {
        if (player1.isActiv()) {
            return player1;
        }
        return player2;
    }

    private Player getDefendingPlayer() {
        if (player1.isActiv()) {
            return player2;
        }
        return player1;
    }

}
