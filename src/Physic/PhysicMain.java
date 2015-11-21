package Physic;

import Logic.Entity;
import Logic.EntityDynamic;
import Logic.Logic;
import Maths.Vector2f;

import java.util.ArrayList;

public class PhysicMain {
    
    private boolean collision;
    public static final float GRAVITY = -0.0025f;
    
    public static void update(){
        ArrayList<EntityDynamic> entities=Logic.getDynamic();
        for (int i=0;i<entities.size();i++) {
            EntityDynamic dyn=entities.get(i);
            dyn.update();
            adjustX(dyn,Logic.getEntity());
            adjustY(dyn,Logic.getEntity());
            if(dyn.getContact(EntityDynamic.CONTACT_DOWN))
                dyn.setSpeed(dyn.getSpeed().x*0.9f,dyn.getSpeed().y);
            if(dyn.getContact(EntityDynamic.CONTACT_LEFT) || dyn.getContact(EntityDynamic.CONTACT_RIGHT))
                dyn.setSpeed(dyn.getSpeed().x,dyn.getSpeed().y*0.9f);
        }
    }
    
    public static boolean collisionEE(Entity ent1, Entity ent2){
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();
        
        return !(pos1.x-size1.x > pos2.x+size2.x || //gauche1 > droite2
                pos1.x+size1.x < pos2.x-size2.x || //droite1 < gauche2
                pos1.y-size1.y > pos2.y+size2.y || //bas1 > haut2
                pos1.y+size1.y < pos2.y-size2.y);
    }
    
    public static void adjustY(EntityDynamic dyn,ArrayList<Entity> entities) {
        Entity futurY=new Entity();
        futurY.setData(dyn.getData());
        Vector2f speed=new Vector2f(0,dyn.getSpeed().y);
        futurY.setPos(dyn.getPos().add(speed));
        
        int i=entities.indexOf(dyn);
        
        boolean isColliding = false;
        Entity collider=null;
        
        for(int j=0;j<entities.size() && !isColliding;j++){
            if(i!=j && entities.get(j).getCollide()) {
                collider=entities.get(j);
                isColliding=collisionEE(futurY, collider);
                while(isColliding && Math.abs(speed.y)>0.005f) {
                    if(dyn.getPos().y > collider.getPos().y) {
                        dyn.setContact(EntityDynamic.CONTACT_DOWN,true);
                        dyn.setContact(EntityDynamic.CONTACT_UP,false);
                    } else {
                        dyn.setContact(EntityDynamic.CONTACT_DOWN,false);
                        dyn.setContact(EntityDynamic.CONTACT_UP,true);
                    }
                    speed=speed.divide(2);
                    futurY.setPos(dyn.getPos().add(speed));
                    isColliding=collisionEE(futurY, collider);
                }
            }
        }
        
        if(!collisionEE(futurY, collider)) {
            dyn.setPos(futurY.getPos());
        } else {
            dyn.setSpeed(dyn.getSpeed().x,0);
        }
        if(Math.abs(dyn.getSpeed().y)>0.005) {
            dyn.setContact(EntityDynamic.CONTACT_DOWN,false);
            dyn.setContact(EntityDynamic.CONTACT_UP,false);
        }
    }
    
    public static void adjustX(EntityDynamic dyn,ArrayList<Entity> entities) {
        Entity futurX=new Entity();
        futurX.setData(dyn.getData());
        Vector2f speed=new Vector2f(dyn.getSpeed().x,0);
        futurX.setPos(dyn.getPos().add(speed));
        
        int i=entities.indexOf(dyn);
        
        boolean isColliding = false;
        Entity collider=null;
        
        for(int j=0;j<entities.size() && !isColliding;j++){
            if(i!=j && entities.get(j).getCollide()) {
                collider=entities.get(j);
                isColliding=collisionEE(futurX, collider);
                while(isColliding && Math.abs(speed.x)>0.005f) {
                    if(dyn.getPos().x > collider.getPos().x) {
                        dyn.setContact(EntityDynamic.CONTACT_RIGHT,true);
                        dyn.setContact(EntityDynamic.CONTACT_LEFT,false);
                    } else {
                        dyn.setContact(EntityDynamic.CONTACT_RIGHT,false);
                        dyn.setContact(EntityDynamic.CONTACT_LEFT,true);
                    }
                    speed=speed.divide(2);
                    futurX.setPos(dyn.getPos().add(speed));
                    isColliding=collisionEE(futurX, collider);
                }
            }
        }
        
        if(!isColliding) {
            dyn.setPos(futurX.getPos());
        } else {
            dyn.setSpeed(0,speed.y);
        }
        if(Math.abs(dyn.getSpeed().x)>0.005) {
            dyn.setContact(EntityDynamic.CONTACT_LEFT,false);
            dyn.setContact(EntityDynamic.CONTACT_RIGHT,false);
        }
    }
}
