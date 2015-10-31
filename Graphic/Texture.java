/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import org.lwjgl.opengl.GL32;

public class Texture {
	public Texture(String path) {
		m_ID=0;
	}
	public int getID() {
		return m_ID;
	}
	private final int m_ID;
}