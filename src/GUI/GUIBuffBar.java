/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Graphic.GraphicMain;
import Graphic.Model;
import Logic.Buff.Buff;
import Logic.EntityUnit;
import Maths.Vector2f;
import Maths.Vector4f;
import java.util.ArrayList;

public class GUIBuffBar extends GUI {

	private EntityUnit m_player;
	private ArrayList<Buff> m_buffs;
	
	private float offsetY=1;
	public GUIBuffBar () {
		super();
	}
	@Override
	public void setSize(float x, float y) {
		super.setSize(x,y);
		offsetY=getSize().y*2;
	}
	@Override
	public boolean draw() {
		m_player=Logic.Logic.getPlayer();
		if(m_player!=null) {
			m_buffs=m_player.getBuffList();
			if(m_buffs==null)
				return false;
			int buffCount=m_buffs.size();
			for(int i=0;i<buffCount;i++) {
				Buff b=m_buffs.get(i);
				Vector4f color=new Vector4f(1,1,1,1);

				Vector2f bPos=new Vector2f(getPos().x-(getSize().x+10),getPos().y-(getSize().y+10)-i*(offsetY+10));
				Model.renderTexture(b.getIcone(), bPos, getSize(), color);
				GraphicMain.drawString(b.getName(), bPos.subtract(new Vector2f(getSize().x,-getSize().y/2)), 0.8f, new Vector4f(0,0,0,1));
				float duration=((float)(int)(b.getRemainingDuration()*10))/10;
				GraphicMain.drawString(""+duration, bPos.subtract(new Vector2f(getSize().x/2,getSize().y/2)), 1.5f, new Vector4f(0,0,0,1));
			}
			return true;
		}
		return false;
	}
}
