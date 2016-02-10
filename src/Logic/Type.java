/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Logic;

/**
 *
 * @author Pierrot
 */
public enum Type {
    MELEE ("Melee ennemi"),
    RANGED ("Ranged ennemi"),
    SNIPER(""),
    GUNNER("");
    private String name = "";
    
    Type(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
