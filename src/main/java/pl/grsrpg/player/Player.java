package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grsrpg.card.ICard;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.IField;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.manager.fight.FightManager;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public abstract class Player extends Enemy implements IPlayer {
    protected int eqCapacity;
    protected List<ICard> cards = new LinkedList<>();
    protected int currentMapLevel = 1;
    protected int currentField = 0;
    protected int gold = 0;

    protected int fightMagicPoints;

    protected int additionalMaxHealth;
    protected float armor;

    protected int additionalStrength;
    protected int additionalAgility;
    protected int additionalMagicPoints;

    protected int additionalEqCapacity = 0;
    protected boolean additionalPoint = false;
    protected float goldMultiplier = 1;
    protected boolean friend = false;
    @JsonIgnore
    protected FightManager fightManager;

    public Player(String name, int maxHealth, int strength, int agility, int magicPoints, int eqCapacity) {
        super(name, maxHealth, strength, agility, magicPoints);
        this.eqCapacity = eqCapacity;
        this.fightMagicPoints = magicPoints;
    }

    @Override
    public boolean addCard(ICard card) {
        if (cards.size() < eqCapacity) {
            card.execute(this);
            if (!card.isCarriable())
                return true;
            cards.add(card);
            return true;
        }
        return false;
    }

    @Override
    public ICard removeCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(name)) {
                ICard card = cards.remove(i);
                this.recalculateAttributes();
                return card;
            }
        }
        return null;
    }

    @Override
    public void recalculateAttributes() {
        this.resetAdditionalAttributes();
        for (ICard card : cards) {
            card.execute(this);
        }
    }

    private void resetAdditionalAttributes() {
        this.additionalMagicPoints = 0;
        this.additionalAgility = 0;
        this.additionalStrength = 0;
        this.additionalMaxHealth = 0;
        this.armor = 0;
    }

    @Override
    public void move(int mapLevel, int filedNumber, IField field) {
        this.currentField = filedNumber;
        this.currentMapLevel = mapLevel;
        System.out.println();
        System.out.println("You are now in " + Logger.CYAN + field.getName() + Logger.RESET);
        field.execute(this);
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return "Statistics: \n" +
                " Health: " + Logger.CYAN + this.health + Logger.RESET + "/" + Logger.YELLOW + this.getMaxHealth() + "\n" + Logger.RESET +
                " Strength: " + Logger.YELLOW + this.getStrength() + "\n" + Logger.RESET +
                " Agility: " + Logger.YELLOW + this.getAgility() + "\n" + Logger.RESET +
                " Magic Points: " + Logger.YELLOW + this.getMagicPoints() + "\n" + Logger.RESET +
                " Equipment: " + Logger.CYAN + this.cards.size() + Logger.RESET + "/" + Logger.YELLOW + this.getEqCapacity() + "\n" + Logger.RESET +
                " Gold: " + Logger.YELLOW + this.getGold() + Logger.RESET + "\n" + Logger.RESET +
                " Armor: " + Logger.YELLOW + this.getArmor() + Logger.RESET + Logger.RESET;
    }

    @Override
    public boolean removeGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean fight(Entity entity) {
        System.out.println("You will fight with " + Logger.CYAN + entity.getName() + Logger.RESET+"!");
        return fightManager.fight(entity);
    }

    @JsonIgnore
    @Override
    public String getCardsInfo() {
        int i = 2;
        return "Cards: \n " + Logger.YELLOW + (cards.size() > 0 ? "1. " + Logger.RESET + cards.stream()
                .map(card -> "Name: " + Logger.CYAN + card.getName() + Logger.RESET + "\n    Description: " + card.getDescription())
                .collect(Collectors.joining("\n " + Logger.YELLOW + (i++) + ". " + Logger.RESET, "", "")) : "None" + Logger.RESET);
    }

    @Override
    public void addGold(int value) {
        this.gold += (goldMultiplier * value);
    }

    @Override
    public void addAdditionalMaxHealth(int maxHealth) {
        this.additionalMaxHealth += maxHealth;
    }

    @Override
    public void addAdditionalStrength(int strength) {
        this.additionalStrength += strength;
    }

    @Override
    public void addAdditionalAgility(int agility) {
        this.additionalAgility += agility;
    }

    @Override
    public void addAdditionalMagicPoints(int magicPoints) {
        this.additionalMagicPoints += magicPoints;
    }

    @Override
    public void restore() {
        this.fightMagicPoints = this.getMagicPoints();
        this.health = this.getMaxHealth();
    }

    public void addArmor(float armor) {
        this.armor += armor;
    }

    @Override
    public boolean hasFriend() {
        return this.friend;
    }

    @Override
    public void setFriend(boolean friend) {
        if (!friend) {
            this.friend = false;
            this.additionalEqCapacity = 0;
            this.additionalPoint = false;
            this.goldMultiplier = 1;
        } else {
            this.friend = true;
        }
    }

    @JsonIgnore
    @Override
    public int getMaxHealth() {
        return this.getAdditionalMaxHealth() + this.getBaseMaxHealth();
    }

    @JsonIgnore
    @Override
    public int getStrength() {
        return this.getAdditionalStrength() + this.getBaseStrength();
    }

    @JsonIgnore
    @Override
    public int getAgility() {
        return this.getAdditionalAgility() + this.getBaseAgility();
    }

    @JsonIgnore
    @Override
    public int getMagicPoints() {
        return this.getAdditionalMagicPoints() + this.getBaseMagicPoints();
    }

    @JsonIgnore
    @Override
    public int getEqCapacity() {
        return this.eqCapacity + this.additionalEqCapacity;
    }

    @JsonIgnore
    @Override
    public String getCurrentFriendStats() {
        return "You have friend with benefits: \n" +
                (this.getAdditionalEqCapacity() != 0 ? " Additional capacity: " + Logger.YELLOW + this.getAdditionalEqCapacity() + Logger.RESET + "\n" : "") +
                (this.hasAdditionalPoint() ? Logger.YELLOW + " +1 " + Logger.RESET + "to dice roll!\n" : "") +
                (this.getGoldMultiplier() != 1F ? " Gold multiplier: " + Logger.YELLOW + this.getGoldMultiplier() + Logger.RESET : "");
    }

    public boolean hasAdditionalPoint() {
        return additionalPoint;
    }
}
