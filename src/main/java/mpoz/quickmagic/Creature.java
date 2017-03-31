package mpoz.quickmagic;

public class Creature {

    private int power;
    private int toughtness;
    private final String name;
    private boolean attacking;
    private Creature blockedBy;

    public Creature(int power, int toughtness, String name) {
        this.power = power;
        this.toughtness = toughtness;
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public void attack() {
        this.attacking = true;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public boolean isBlocked() {
        return blockedBy != null;
    }

    public void block(Creature attacking) {
        attacking.setBlockedBy(this);
    }

    public void setBlockedBy(Creature blockedBy) {
        this.blockedBy = blockedBy;
    }

    public Creature getBlockedBy() {
        return blockedBy;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                '}';
    }


}
