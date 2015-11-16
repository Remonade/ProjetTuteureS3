package Tests;

import Logic.*;

/**
 *
 * @author Flavien
 */
public class TestClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Category warrior = new Category(40, 70, 10, 500, "Warrior");
        Category sniper = new Category(10,15, 100, 250, "Sniper");
        Weapon overPoweredGun = new Weapon("Over powered gun", 666, 1);
        System.out.println(overPoweredGun);
        Weapon noobiesLaserSword = new Weapon();
        System.out.println(noobiesLaserSword);
        Item item1 = new Item();
        item1.setName("Yolo ring");
        Item item2 = new Item();
        item2.setName("Swag ring");
        Item item3 = new Item();
        item3.setName("Kikoo ring");
        Item item4 = new Item();
        item4.setName("Wololooooo");
        Item item5 = new Item();
        item5.setName("Peasant shirt");
        Item item6 = new Item();
        item6.setName("Diablo's bra");
        /*
        Player current = new Player();
        current.setClassePerso(sniper);
        current.setName("Thrandread");
        current.setWeapon(overPoweredGun);
        current.addItemToInventory(item1);
        current.addItemToInventory(item2);
        current.addItemToInventory(item3);
        current.addItemToInventory(item4);
        current.addItemToInventory(item5);
        current.addItemToInventory(item6);
        System.out.println(current);
        */
    }
    
}
