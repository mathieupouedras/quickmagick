package mpoz.quickmagic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test(expected = InvalidAttackerException.class)
    public void should_throw_exception_when_creature_does_not_exist() {
        Player mathieu = new Player("Mathieu", 20);
        Creature warrior = new Creature(2, 2, "warrior");

        mathieu.beginTurn();
        mathieu.declaresAttacker(warrior);
    }

    @Test(expected = NotYourTurnException.class)
    public void should_throw_excetpion_when_not_activ_player_cast_a_creature() {
        Player mathieu = new Player("Mathieu", 20);
        Creature warrior = new Creature(2, 2, "warrior");

        mathieu.cast(warrior);
    }

    @Test
    public void should_have_mathieu_attacking_with_2_creatures() {
        Player mathieu = new Player("Mathieu", 20);
        Player francois = new Player("Francois", 20);
        Creature warrior = new Creature(2, 2, "warrior");
        Creature spirit = new Creature(1, 1, "spirit");

        mathieu.beginTurn();
        mathieu.cast(warrior);
        mathieu.cast(spirit);
        mathieu.declaresAttacker(warrior);
        mathieu.declaresAttacker(spirit);

        assertThat(mathieu.getAttackingCreatures().size(), is(2));
    }
}
