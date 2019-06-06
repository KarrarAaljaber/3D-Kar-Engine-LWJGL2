package RunEngine;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.vector.Vector3f;

import BaseEngine.ModelLoader;
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Textures.ModelTexture;
import shaders.StaticShader;


public class Window  {
	
	// The window handle
		private long window;

		private int width, height;
		private String title;
		

		
		public Window(int width, int height, String title) {
			this.width = width;
			this.height = height;
			this.title = title;

		
		}
	
		public void updateDisplay() {
			Display.sync(120);
			Display.update();
			
			 
		}
		public void CloseDisplay() {
			Display.destroy();
			
		}
		public void createDisplay() {
			
			ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
			try {
				Display.setDisplayMode(new DisplayMode(width, height));
				Display.create(new PixelFormat(),attribs );
				Display.setTitle(title);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			GL11.glViewport(0, 0, width, height);
		}

		}
	