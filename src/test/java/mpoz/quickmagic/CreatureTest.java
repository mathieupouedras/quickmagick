package mpoz.quickmagic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreatureTest {

    @Test
    public void should_have_warrior_blocked_by_vampire() {
        Creature warrior = new Creature(2, 2, "warrior");
        Creature vampire = new Creature(1, 1, "vampire");

        vampire.block(warrior);

        assertThat(warrior.isBlocked(), is(true));
    }

}
