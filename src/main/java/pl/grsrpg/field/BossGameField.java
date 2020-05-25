package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.entity.Entity;

@Getter
@ToString
public class BossGameField extends GameField implements BossField {
    private boolean defeated;
    private Entity boss;
}
