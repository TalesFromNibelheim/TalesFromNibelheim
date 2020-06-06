package pl.grsrpg.player;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.grsrpg.card.ICard;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.field.IField;
import pl.grsrpg.utils.Attribute;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayerWarrior.class, name = "warrior"),
        @JsonSubTypes.Type(value = PlayerScout.class, name = "scout"),
        @JsonSubTypes.Type(value = PlayerMage.class, name = "mage")
})
public interface IPlayer extends Entity {
    void recalculateAttributes();

    void move(int mapLevel, int filedNumber, IField field);

    String getInfo();

    String getCardsInfo();

    boolean addCard(ICard card);

    ICard removeCard(String name);

    List<ICard> getCards();

    int getEqCapacity();

    int getGold();

    void setGold(int gold);

    boolean removeGold(int amount);

    int getCurrentField();

    boolean fight(Entity entity);

    int getAdditionalMaxHealth();

    int getAdditionalStrength();

    int getAdditionalAgility();

    int getAdditionalMagicPoints();

    boolean hasAdditionalPoint();

    void setAdditionalEqCapacity(int capacity);

    void setAdditionalPoint(boolean additionalPoint);

    void setGoldMultiplier(float goldMultiplier);

    void setFriend(boolean friend);

    boolean hasFriend();

    int getCurrentMapLevel();

    void addGold(int value);

    void addAdditionalMaxHealth(int maxHealth);

    void addAdditionalStrength(int strength);

    void addAdditionalAgility(int agility);

    void addAdditionalMagicPoints(int magicPoints);

    float getArmor();

    void restore();

    int getStartAttribute(Attribute attribute);

    void addArmor(float armor);

    int getMaxHealth();

    int getStrength();

    int getAgility();

    int getMagicPoints();

    float getGoldMultiplier();

    int getAdditionalEqCapacity();

    String getCurrentFriendStats();

    int getFightMagicPoints();
}
