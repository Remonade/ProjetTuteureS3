/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Graphic.*;
import static java.lang.Math.random;
import java.nio.FloatBuffer;
import math.Vector2f;

/**
 *
 * @author Pierrot
 */
public class Ennemi{
    public Vector2f m_coordinates;
    public int m_direction;
    public ModelEnnemi m_model;
    public Category m_category;
    
    public Ennemi(ModelEnnemi aModel, float aX, float aY){
        m_coordinates = new Vector2f(aX, aY);
        m_direction = 1;
        m_model = aModel;
        m_category = new Category();
    }
    
    public void setCoordinates(float aX, float aY){
        m_coordinates.x = aX;
        m_coordinates.y = aY;
    }
    
    public void setModel(ModelEnnemi aModel){
        m_model = aModel;
    }
    
    public void setDirection(int d){
        m_direction = d;
        
        ModelEnnemi temp = new ModelEnnemi();
        temp.m_vertice = FloatBuffer.allocate(m_model.m_verticeCount*2);
        
        switch (m_direction){
            case 0:
                temp.m_vertice.put(temp.m_size* -1);temp.m_vertice.put(temp.m_size* 0);
                temp.m_vertice.put(temp.m_size* 1);temp.m_vertice.put(temp.m_size* 0);
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* -1);
                break;
            case 1:
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* -1);
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* 1);
                temp.m_vertice.put(temp.m_size* 1);temp.m_vertice.put(temp.m_size* 0);
                break;
            case 2:
                temp.m_vertice.put(temp.m_size* 1);temp.m_vertice.put(temp.m_size* 0);
                temp.m_vertice.put(temp.m_size* -1);temp.m_vertice.put(temp.m_size* 0);
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* 1);
                break;
            case 3:
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* 1);
                temp.m_vertice.put(temp.m_size* 0);temp.m_vertice.put(temp.m_size* -1);
                temp.m_vertice.put(temp.m_size* -1);temp.m_vertice.put(temp.m_size* 0);
                break;
        }
        
        m_model.m_vertice = temp.m_vertice;
        temp.destroy();
    }
    
    public void walk(Player player){
        switch (m_direction){
            case 0:
                if(player.m_coordinates.y < m_coordinates.y){
                    m_coordinates.y -= 0.01f;
                }
                break;
            case 1:
                if(player.m_coordinates.x > m_coordinates.x){
                    m_coordinates.x += 0.01f;
                }
                break;
            case 2:
                if(player.m_coordinates.y > m_coordinates.y){
                    m_coordinates.y += 0.01f;
                }
                break;
            case 3:
                if(player.m_coordinates.x < m_coordinates.x){
                    m_coordinates.x -= 0.01f;
                }
                break;
        }  
    }
    
    public void rotate(){
        double r = random()*10000;
        
        if(r<50){
            setDirection(0);
        }
        else if(r<100){
            setDirection(1);
        }
        else if(r<150){
            setDirection(2);
        }
        else if(r<200){
            setDirection(3);
        }
    }
}
