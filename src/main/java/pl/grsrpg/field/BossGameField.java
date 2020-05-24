package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.entity.Entity;

@Getter
@ToString
public class BossGameField extends GameField implements BossField {
    private boolean defeated;
    private Entity boss;

/*    public BossGameField(String name, String description, boolean defeated, Entity boss) {
        super(name, description);
        this.defeated = defeated;
        this.boss = boss;
    }*/
}
