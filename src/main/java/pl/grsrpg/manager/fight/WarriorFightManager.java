package pl.grsrpg.manager.fight;

import pl.grsrpg.entity.Enemy;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.utils.IOUtils;
import pl.grsrpg.utils.DiceRoll;

public class WarriorFightManager extends NormalFightManager{
    GamePlayerWarrior player;
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

    private void printDmg(float dmg){
        System.out.println("Enemy lost " + dmg + " hp." );
    }

    private void bless(){
        numberOfTour = DiceRoll.rollPrivate(2,4);
        numberOfAdditionalArmor = DiceRoll.rollPrivate(5,30);
        System.out.println(Logger.YELLOW + "God supports you. You gain " + numberOfAdditionalArmor + " additional armor for "+ numberOfTour +" tours." + Logger.RESET);
    }




    @Override
    public boolean fight(Enemy enemy) {
        while(enemy.getHealth() > 0 && player.getHealth() > 0){
            switch(tour){
                case 1: // tura wojownika
                    if(numberOfTour >= 0) numberOfTour--;
                    System.out.println("It's your turn " + Logger.BLUE  /*imie wojownika*/ + "." + Logger.RESET);
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
                            enemy.takeDamage(player.knockdown());
                            break;
                        case 3:
                            enemy.takeDamage(player.cleave(enemy));
                            break;
                        case 4:
                            player.blessingOfTheShield();
                            bless();
                    }
                    if(numberOfTour < 0){
                        numberOfTour = 0;
                        numberOfAdditionalArmor = 0;
                    }
                    tour = 2;
                    break;
                case 2:

            }




        }
        return true;
    }




}
