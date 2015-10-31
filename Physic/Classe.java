/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physic;

/**
 *
 * @author Flavien
 */
public class Classe {
    private int strength;
    private int defense;
    private int agility;
    private int maxHP;
    private String name;
    
    //Constructors
    public Classe(){
        strength=1;
        defense=1;
        agility=1;
        maxHP=100;
        name="Unknown";
    }
    public Classe(int strength, int defense, int agility, int maxHP, String name){
        this.strength=strength;
        this.defense=defense;
        this.agility=agility;
        this.maxHP=maxHP;
        this.name=name;
    }
    
    public String toString(){
        return "strength = "+this.strength+"\n"+
               "defense = "+this.defense+"\n"+
               "agility = "+this.agility+"\n"+
               "maxHP = "+this.maxHP+"\n"+
               "name = "+this.name+"\n";
    }
    
    //Getters
    public int getStrength(){
        return this.strength;
    }
    public int getDefense(){
        return this.defense;
    }
    public int getAgility(){
        return this.agility;
    }
    public int getMaxHP(){
        return this.maxHP;
    }
    public String getName(){
        return this.name;
    }
    
    //Setters
    public void setStrength(int strength){
        this.strength=strength;
    }
    public void setDefense(int defense){
        this.defense=defense;
    }
    public void setAgility(int agility){
        this.agility=agility;
    }
    public void setMaxHP(int maxHP){
        this.maxHP=maxHP;
    }
    public void setName(String name){
        this.name=name;
    }
}
