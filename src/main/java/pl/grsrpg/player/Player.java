package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.ICard;
import pl.grsrpg.entity.Boss;
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
    protected int equipmentCapacity;
    protected List<ICard> cards = new LinkedList<>();
    protected int currentMapLevel = 1;
    protected int currentField = 0;
    protected int gold = 0;

    protected int additionalMaxHealth;
    protected float armor;

    protected int additionalStrength;
    protected int additionalAgility;
    protected int additionalMagicPoints;

    protected int additionalCapacity = 0;
    protected boolean addPoint = false;
    protected float multiplierGold = 1;
    protected boolean friend = false;
    @JsonIgnore
    protected FightManager fightManager;

    public Player(String name, int maxHealth, int strength, int agility, int magicPoints, int equipmentCapacity) {
        super(name, maxHealth, strength, agility, magicPoints);
        this.equipmentCapacity = equipmentCapacity;
    }

    @Override
    public boolean addCard(ICard card) {
        if (cards.size() < equipmentCapacity) {
            card.execute(this);
            if(!card.isCarriable())
                return true;
            cards.add(card);
            return true;
        }
        return false;
    }

    @Override
    public ICard removeCard(String name) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(name)){
                this.recalculateAttributes();
                return cards.remove(i);
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
        System.out.println("You are now in "+ Logger.CYAN+field.getName()+Logger.RESET);
        System.out.println("From possible actions: ");
        field.execute(this);
    }

    @JsonIgnore
    @Override
    public String getInfo() {
        return  "Statistics: \n" +
                " Health: " + Logger.CYAN + this.health + Logger.RESET + "/" + Logger.YELLOW + (this.baseMaxHealth + this.additionalMaxHealth) + "\n" + Logger.RESET +
                " Strength: " + Logger.YELLOW + (this.baseStrength + this.additionalStrength) + "\n" + Logger.RESET +
                " Agility: " + Logger.YELLOW + +(this.baseAgility + this.additionalAgility) + "\n" + Logger.RESET +
                " Magic Points: " + Logger.YELLOW + (this.baseMagicPoints + this.additionalMagicPoints) + "\n" + Logger.RESET +
                " Equipment: " + Logger.CYAN + this.cards.size() + Logger.RESET + "/" + Logger.YELLOW + equipmentCapacity + Logger.RESET;
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
        if (entity instanceof Boss)
            return fightManager.fight((Boss) entity);
        else
            return fightManager.fight((Enemy) entity);
    }

    @JsonIgnore
    @Override
    public String getCardsInfo() {
        int i = 2;
        return  "Cards: \n " + Logger.YELLOW + (cards.size() > 0 ? "1. " + Logger.RESET + cards.stream()
                .map(card -> "Name: " + Logger.CYAN + card.getName() + Logger.RESET + "\n    Description: " + card.getDescription())
                .collect(Collectors.joining("\n " + Logger.YELLOW + (i++) + ". " + Logger.RESET, "", "")) : "None" + Logger.RESET );
    }

    @Override
    public void addGold(int value) {
        this.gold += (multiplierGold * value);
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
    public void addArmor(float armor){
        this.armor += armor;
    }

    @Override
    public boolean getAddPoint(){
        return this.addPoint;
    }

    @Override
    public boolean getFriend(){
        return this.friend;
    }

    @Override
    public void setFriend(boolean friend){
        if(!friend){
            this.friend = false;
            this.additionalCapacity = 0;
            this.addPoint = false;
            this.multiplierGold = 1;
        }else{
            this.friend = true;
        }
    }
}
