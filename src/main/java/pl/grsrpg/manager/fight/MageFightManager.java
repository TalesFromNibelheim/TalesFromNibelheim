package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.IOUtils;

@NoArgsConstructor
public class MageFightManager extends NormalFightManager {
    int tour = DiceRoll.rollPrivate(1,2);
    public MageFightManager(IPlayer player) {
        super(player);
    }

    private void chooseAttack(){
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack." );
        System.out.println(Logger.CYAN + "2." + Logger.RESET + " Thunder." );
        System.out.println(Logger.CYAN + "3." + Logger.RESET + " Fire Ball." );
        System.out.println(Logger.CYAN + "4." + Logger.RESET + " Ice Chain." );
    }
    private float damageReduce(){
        float absorb = DiceRoll.rollPrivate(1,4)* (player.getArmor());
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." +  Logger.RESET);
        return absorb;
    }


    private void printDmg(float dmg){ System.out.println("Enemy lost " + dmg + " hp." );}

    private float basicAttack(){
        float dmg = ( 0.2F * player.getBaseStrength() + player.getAdditionalStrength())* DiceRoll.rollPrivate(1,4);
        printDmg(dmg);
        return dmg;
    }
    private float enemyAttack(Enemy enemy){
        float dmg = ( 0.4F * enemy.getBaseStrength())* DiceRoll.rollPrivate(1,4);
        printDmg(dmg);
        return dmg;
    }


    @Override
    public boolean fight(Enemy enemy) {
        boolean stun = false;
        PlayerMage mage = (PlayerMage)player;
        while(enemy.getHealth() > 0 && player.getHealth() > 0){
            switch(tour){
                case 1: // tura wojownika
                    stun = false;
                    System.out.println("It's your turn " + Logger.BLUE + mage.getName() + "." + Logger.RESET);
                    System.out.println("Your current health: " + Logger.RED  +player.getHealth()+ "." + Logger.RESET);
                    System.out.println("Choose your attack: " );
                    chooseAttack();
                    int temp;
                    do temp = IOUtils.getScanner().nextInt();
                    while(temp != 1 && temp != 2 && temp != 3 && temp != 4 );
                    switch(temp){
                        case 1:
                            enemy.takeDamage(basicAttack());
                            break;
                        case 2:
                            enemy.takeDamage(mage.thunder());
                            stun = true;
                            break;
                        case 3:
                            enemy.takeDamage(mage.fireBall());
                            break;
                        case 4:
                            mage.iceChain();
                            break;
                    }
                    tour = 2;
                    break;
                case 2: // tura wroga
                    if(!stun){
                        System.out.println(Logger.BLUE +"Opponent's turn." + Logger.RESET);
                        System.out.println("Enemy current health: " + Logger.RED  +enemy.getHealth()+ "." + Logger.RESET);
                        float enemyDamage = enemyAttack(enemy);
                        float absorb = damageReduce();
                        if(enemyDamage - absorb <= 0) enemyDamage = 0;
                        else enemyDamage -= absorb;
                        player.takeDamage(enemyDamage);
                        System.out.println("Enemy dealt you: " + Logger.RED + enemyDamage + " damage." + Logger.RESET);
                    }
                    tour = 1;
            }
        }
        return true;
    }

}
