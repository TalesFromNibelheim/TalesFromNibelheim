package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;


@NoArgsConstructor
public class ScoutFightManager extends NormalFightManager {
    int tour = DiceRoll.rollPrivate(1,2);
    boolean dodge = false;
    public ScoutFightManager(IPlayer player) {
        super(player);
    }



    private void printDmg(float dmg){ System.out.println("Enemy lost " + dmg + " hp." );}

    private void chooseAttack(){
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack." );
        System.out.println(Logger.CYAN + "2." + Logger.RESET + " Critical shoot." );
        System.out.println(Logger.CYAN + "3." + Logger.RESET + " Series of arrows." );
        System.out.println(Logger.CYAN + "4." + Logger.RESET + " Repetable arrow." );
    }

    private float enemyAttack(Enemy enemy){
        float dmg = ( 0.4F * enemy.getBaseStrength())* DiceRoll.rollPrivate(1,4);
        printDmg(dmg);
        return dmg;
    }

    private float damageReduce(){
        float absorb = DiceRoll.rollPrivate(1,4)* (player.getArmor()) * 0.5F;
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." +  Logger.RESET);
        return absorb;
    }


    @Override
    public boolean fight(Enemy enemy) {
        PlayerScout scout = (PlayerScout)player;
        while(enemy.getHealth() > 0 && player.getHealth() > 0){
            switch(tour){
                case 1: // tura zwiadowcy
                    System.out.println("It's your turn " + Logger.BLUE + scout.getName() + "." + Logger.RESET);
                    System.out.println("Your current health: " + Logger.RED  +player.getHealth()+ "." + Logger.RESET);
                    System.out.println("Choose your attack: " );
                    chooseAttack();
                    int temp;
                    float dmg;
                    do temp = IOUtils.getScanner().nextInt();
                    while(temp != 1 && temp != 2 && temp != 3 && temp != 4 );
                    switch(temp){
                        case 1:
                            dmg = scout.basicAttack();
                            enemy.takeDamage(dmg);
                            printDmg(dmg);
                            break;
                        case 2:
                            dmg = scout.CriticalArrrow();
                            enemy.takeDamage(dmg);
                            printDmg(dmg);
                            break;
                        case 3:
                            dmg = scout.seriesOfArrows());
                            enemy.takeDamage(dmg);
                            printDmg(dmg);
                            break;
                        case 4:
                            dmg = scout.repetableArrow();
                            enemy.takeDamage(dmg);
                            printDmg(dmg);
                    }
                    tour = 2;
                    break;
                case 2: // tura wroga
                        float check = DiceRoll.rollPrivate(1,100);
                        if(0.5F* (check + scout.getAdditionalAgility() + scout.getBaseAgility()) > 50 ) dodge = true;
                        if(!dodge){
                            System.out.println(Logger.BLUE +"Opponent's turn." + Logger.RESET);
                            System.out.println("Enemy current health: " + Logger.RED  + enemy.getHealth()+ "." + Logger.RESET);
                            float enemyDamage = enemyAttack(enemy);
                            float absorb = damageReduce();
                            if(enemyDamage - absorb <= 0) enemyDamage = 0;
                            else enemyDamage -= absorb;
                            player.takeDamage(enemyDamage);
                            System.out.println("Enemy dealt you: " + Logger.RED + enemyDamage + " damage." + Logger.RESET);
                        }
                        else System.out.println( Logger.YELLOW + "Enemy missed! You dodged his attack!" + Logger.RESET);
                        tour = 1;
                        break;
            }
        }
        return true;
    }

}
