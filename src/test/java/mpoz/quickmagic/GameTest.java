package mpoz.quickmagic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    private static final Creature NO_BLOCKER = null;

    @Test
    public void should_mathieu_loose_2_lifes() {
        Player francois = new Player("francois", 20);
        Player mathieu = new Player("mathieu", 20);
        Creature warrior = new Creature(2, 2, "warrior");
        Game game = new Game(francois, mathieu);

        francois.beginTurn();
        francois.cast(warrior);
        francois.declaresAttacker(warrior);
        game.resolve();

        assertThat(mathieu.getLife(), is(18));
    }

    @Test
    public void should_mathieu_loose_3_lifes() {
        Player francois = new Player("francois", 20);
        Player mathieu = new Player("mathieu", 20);
        Creature warrior = new Creature(2, 2, "warrior");
        Creature spirit1 = new Creature(1, 1, "spirit");
        Creature spirit2 = new Creature(1, 1, "spirit");
        Creature wall = new Creature(0, 5, "wall");

        Game game = new Game(francois, mathieu);

        francois.beginTurn();
        francois.cast(warrior);
        francois.cast(spirit1);
        francois.cast(spirit2);
        francois.declaresAttacker(warrior);
        francois.declaresAttacker(spirit1);
        francois.declaresAttacker(spirit2);

        mathieu.declaresBlocker(spirit2, wall);

        game.resolve();

        assertThat(mathieu.getLife(), is(17));
    }

    @Test
    public void should_mathieu_looses_spirit() {
        Player francois = new Player("francois", 20);
        Player mathieu = new Player("mathieu", 20);
        Creature warrior = new Creature(2, 2, "warrior");
        Creature spirit = new Creature(1, 1, "spirit");

        Game game = new Game(mathieu, francois);

        mathieu.beginTurn();
        mathieu.cast(spirit);
        mathieu.endTurn();

        francois.beginTurn();
        francois.cast(warrior);

        francois.declaresAttacker(warrior);
        mathieu.declaresBlocker(spirit, warrior);
        francois.endTurn();

        game.resolve();

        assertThat(mathieu.creaturesCount(), is(0));
    }


}
