package pl.grsrpg.manager.fight;

import lombok.NoArgsConstructor;
import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;

@NoArgsConstructor
public class WarriorFightManager extends NormalFightManager{
    public WarriorFightManager(IPlayer player) {
        super(player);
    }

    int tour = DiceRoll.rollPrivate(1,2);
    int numberOfTour = 0;
    int numberOfAdditionalArmor = 0;
    int time = 0;

    private void chooseAttack(){
        System.out.println(Logger.CYAN + "1." + Logger.RESET + " Basic Attack." );
        System.out.println(Logger.CYAN + "2." + Logger.RESET + " Knockdown." );
        System.out.println(Logger.CYAN + "3." + Logger.RESET + " Cleave." );
        System.out.println(Logger.CYAN + "4." + Logger.RESET + "Blessing of the shield." );
    }

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



    private void printDmg(float dmg){ System.out.println("Enemy lost " + dmg + " hp." );}

    private void bless(){
        numberOfTour = DiceRoll.rollPrivate(2,4);
        numberOfAdditionalArmor = DiceRoll.rollPrivate(5,30);
        System.out.println(Logger.YELLOW + "God supports you. You gain " + numberOfAdditionalArmor + " additional armor for "+ numberOfTour +" tours." + Logger.RESET);
    }

    private float damageReduce(){
        float absorb = DiceRoll.rollPrivate(1,4)* (player.getArmor() + numberOfAdditionalArmor );
        System.out.println(Logger.WHITE + "Your armor absorb: " + absorb + " damage." +  Logger.RESET);
        return absorb;
    }

    @Override
    public boolean fight(Enemy enemy) {
        boolean stun = false;
        PlayerWarrior warrior = (PlayerWarrior)player;
        while(enemy.getHealth() > 0 && player.getHealth() > 0){
            switch(tour){
                case 1: // tura wojownika
                    stun = false;
                    if(numberOfTour >= 0) numberOfTour--;
                    System.out.println("It's your turn " + Logger.BLUE + warrior.getName() + "." + Logger.RESET);
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
                            enemy.takeDamage(warrior.knockdown());
                            stun = true;
                            break;
                        case 3:
                            enemy.takeDamage(warrior.cleave(enemy));
                            break;
                        case 4:
                            warrior.blessingOfTheShield();
                            bless();
                    }
                    if(numberOfTour < 0){
                        numberOfTour = 0;
                        numberOfAdditionalArmor = 0;
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
