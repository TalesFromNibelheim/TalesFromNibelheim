package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.card.Card;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.Field;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GamePlayerWarrior.class, name = "warrior"),
        @JsonSubTypes.Type(value = GamePlayerScout.class, name = "scout"),
        @JsonSubTypes.Type(value = GamePlayerMage.class, name = "mage")
})
public interface Player extends Entity {
    void recalculateAttributes();

    void move(int mapLevel, int filedNumber, Field field);

    String getInfo();

    String getCardsInfo();

    boolean addCard(Card card);

    Card removeCard(String name);

    int getEquipmentCapacity();

    int getGold();

    void setGold(int gold);

    boolean removeGold(int amount);

    int getCurrentField();

    void fight(Entity entity);

    int getAdditionalMaxHealth();

    int getAdditionalStrength();

    int getAdditionalAgility();

    int getAdditionalMagicPoints();

    void setAdditionalMaxHealth(int maxHealth);

    void setAdditionalStrength(int strength);

    void setAdditionalAgility(int agility);

    void setAdditionalMagicPoints(int magicPoints);

    int getCurrentMapLevel();

    void addGold(int value);

    void addAdditionalMaxHealth(int maxHealth);

    void addAdditionalStrength(int strength);

    void addAdditionalAgility(int agility);

    boolean dodge ();

    void addAdditionalMagicPoints(int magicPoints);
}
