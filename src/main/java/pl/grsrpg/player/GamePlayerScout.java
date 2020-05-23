package pl.grsrpg.player;

import pl.grsrpg.field.Field;

public class GamePlayerScout extends GamePlayer {
    private static final float startMaxHealth = 20;
    private static final float startStrength = 5;
    private static final float startAgility = 2;
    private static final float startMagicPoints = 2;
    private static final int startInventorySize = 10;

    public GamePlayerScout(String name, Field currentField) {
        super(name, startMaxHealth, startStrength, startAgility, startMagicPoints, startInventorySize, currentField);
    }

    public static float getStartMaxHealth() {
        return startMaxHealth;
    }

    public static float getStartStrength() {
        return startStrength;
    }

    public static float getStartAgility() {
        return startAgility;
    }

    public static float getStartMagicPoints() {
        return startMagicPoints;
    }

    public static int getStartInventorySize() {
        return startInventorySize;
    }
}
