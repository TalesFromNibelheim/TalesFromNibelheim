package pl.grsrpg.field;

import lombok.Getter;
import lombok.ToString;
import pl.grsrpg.entity.Boss;
import pl.grsrpg.entity.Entity;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.utils.IOUtils;

@Getter
@ToString
public class BossField extends Field implements IBossIField {
    private boolean defeated = false;
    private Boss boss;

    @Override
    public void execute(IPlayer player) {
        if(defeated){
            super.execute(player);
            return;
        }
        System.out.println("Do you fell enough powerful to fight " + Logger.CYAN + boss.getName() + Logger.RESET + "?");
        System.out.print(Logger.YELLOW+"1. "+Logger.RESET+"Yes/"+Logger.YELLOW+"2. "+Logger.RESET+"No (default: 2) ");
        int choice = IOUtils.getScanner().nextInt();
        if(choice == 1){
            if(player.fight(boss)){
                defeated = true;
            }
        } else {
            super.execute(player);
        }
    }
}
