package Physic;

import Logic.Entity;
import Logic.EntityDynamic;
import java.util.ArrayList;
import Maths.Vector2f;

public class PhysicMain {
    
    boolean collision;
    
    public static void update(ArrayList<Entity> entities){
        for(int i=0; i<entities.size(); i++){
            Entity temp=entities.get(i);
            if (temp instanceof EntityDynamic) {
                EntityDynamic temp2=(EntityDynamic)temp;
                temp2.addSpeed(0f,Gravity);
                Entity futurY=new Entity();
                futurY.setSize(temp2.getSize());
                futurY.setPos(temp2.getPos().add(new Vector2f(0,temp2.getSpeed().y)));
                boolean isColliding = false;
                for(int j=0;j<entities.size() && !isColliding; j++){
                    if(i!=j)
                    isColliding=collisionEE(futurY, entities.get(j));
                }
                if(!isColliding)
                    temp.setPos(futurY.getPos());
                else
                    temp2.setSpeed(temp2.getSpeed().x,0);
                
                Entity futurX=new Entity();
                futurX.setSize(temp2.getSize());
                futurX.setPos(temp2.getPos().add(new Vector2f(temp2.getSpeed().x,0)));
                isColliding = false;
                for(int j=0;j<entities.size() && !isColliding; j++){
                    if(i!=j)
                    isColliding=collisionEE(futurX, entities.get(j));
                }
                if(!isColliding)
                    temp.setPos(futurX.getPos());
                else
                    temp2.setSpeed(0,temp2.getSpeed().y);
            }
        }
        
    }
    
    public static boolean collisionEE(Entity ent1, Entity ent2){
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();

        if(pos1.x-size1.x > pos2.x+size2.x || //gauche1 > droite2
           pos1.x+size1.x < pos2.x-size2.x || //droite1 < gauche2
           pos1.y-size1.y > pos2.y+size2.y || //bas1 > haut2
           pos1.y+size1.y < pos2.y-size2.y){ //haut1 < bas2
           System.out.println("OUT");
           return false;
        }
        System.out.println("IN");
        return true;
       
    }
    
    private static final float Gravity = -0.0005f;
            
}
