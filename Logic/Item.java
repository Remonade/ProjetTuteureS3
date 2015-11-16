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
public class Item {
    private String name;
    private int strengthBuff;
    private int defenseBuff;
    private int agilityBuff;
    private int maxHPBuff;
    
    public Item(){
        this.name="Useless item";
        this.strengthBuff=0;
        this.defenseBuff=0;
        this.agilityBuff=0;
        this.maxHPBuff=0;
    }
    
    public Item(String name, int strengthBuff, int defenseBuff, int agilityBuff, int maxHPBuff){
        this.name=name;
        this.strengthBuff=strengthBuff;
        this.defenseBuff=defenseBuff;
        this.agilityBuff=agilityBuff;
        this.maxHPBuff=maxHPBuff;
    }
    
    public String toString(){
        return "name : "+this.name+"\n"+
               "Strength buff : "+this.strengthBuff+"\n"+
               "Defense buff : "+this.defenseBuff+"\n"+
               "Agility buff : "+this.agilityBuff+"\n"+
               "Max health buff : "+this.maxHPBuff;
    }
    
    //getters
    public String getName(){
        return this.name;
    }
    public int getStrengthBuff(){
        return this.strengthBuff;
    }
    public int getDefenseBuff(){
        return this.defenseBuff;
    }
    public int getAgilityBuff(){
        return this.agilityBuff;
    }
    public int getMaxHPBuff(){
        return this.maxHPBuff;
    }
    
    //Setters
    public void setName(String name){
        this.name=name;
    }
    public void setStrengthBuff(int strengthBuff){
        this.strengthBuff=strengthBuff;
    }
    public void setDefenseBuff(int defenseBuff) {
        this.defenseBuff = defenseBuff;
    }
    public void setAgilityBuff(int agilityBuff) {
        this.agilityBuff = agilityBuff;
    }
    public void setMaxHPBuff(int maxHPBuff) {
        this.maxHPBuff = maxHPBuff;
    }
}
