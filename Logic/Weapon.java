/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Flavien
 */
public class Weapon {
    private String name;
    private int damage;
    private int type; //0:melee, 1:ranged
        
    public Weapon() {
        this.name="unknown laser sword";
        this.damage=5;
        this.type=0;
    }
    public Weapon(String name, int damage, int type){
        this.name=name;
        this.damage=damage;
        this.type=type;
    }
    
    public String toString(){
        String returningString = "Name : "+this.name+"\n"+
                                "Damage : "+this.damage+"\n"+
                                "Type : ";
        if (this.type==0){
            returningString = returningString+"melee";
        }
        else{
            returningString = returningString+"ranged";
        }
        return returningString;
    }
    
    public String getName(){
        return this.name;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getType(){
        return this.type;
    }
    
    public void setName(String name){
        this.name=name;
    }
    public void setDamage(int damage){
        this.damage=damage;
    }
    public void setType(short type){
        this.type=type;
    }
    
}
