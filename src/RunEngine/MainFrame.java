package RunEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import BaseEngine.MasterRenderer;
import BaseEngine.ModelLoader;
import BaseEngine.ObjLoader;
import Entities.Camera;
import Entities.Entity;
import Entities.Light;
import Models.RawModel;
import Models.TexturedModel;
import Terrains.Terrain;
import Textures.ModelTexture;

public class MainFrame {
	

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "3D-Kar-Engine";
	
	public static void main(String[]args) {
		Window window = new Window(WIDTH, HEIGHT, TITLE);
		window.createDisplay();

		ModelLoader loader = new ModelLoader();

		Mouse.setGrabbed(true);
		
		
		  
		  RawModel model = ObjLoader.loadObjModel("Goblin", loader);
		  ModelTexture texture = new ModelTexture(loader.loadTexture("MDTEX"));
		  TexturedModel texturedModel = new TexturedModel(model, texture);
		  ModelTexture Texture= texturedModel.getTexture();
		  Texture.setShineDamper(10);
		  Texture.setReflectivity(0.001f);

		  RawModel model2 = ObjLoader.loadObjModel("DeadTree", loader);
		  ModelTexture texture2 = new ModelTexture(loader.loadTexture("empty"));
		  TexturedModel texturedModel2 = new TexturedModel(model2, texture2);
		  ModelTexture Texture2 = texturedModel2.getTexture();

		  
		  Terrain terrain = new Terrain(0,0,loader, new ModelTexture(loader.loadTexture("MDTEX")));
		  Terrain terrain2 = new Terrain(1,0,loader, new ModelTexture(loader.loadTexture("MDTEX")));

		  ModelTexture texture3 = terrain.getTexture();
		  texture3.setReflectivity(0.5f);
		  
		  Entity entity1 = new Entity(texturedModel, new Vector3f(0f,-8f,-5.0f), new Vector3f(0,0,0),new Vector3f(10,10,10) );
		  Entity entity2 = new Entity(texturedModel2, new Vector3f(-2f,-3f,-5.0f), new Vector3f(0,0,0),new Vector3f(1,1,1) );
		 // Entity terrain = new Entity(texturedModel3, new Vector3f(-2f,-50f,-5.0f), new Vector3f(0,0,0),new Vector3f(1,1,1) );


		  List<Entity> entities = new ArrayList<Entity>();
		  Random rand = new Random();
		  for(int i = 0; i < 50; i++) {
			  entities.add(new Entity(texturedModel2, new Vector3f(rand.nextInt(500), -50f, rand.nextInt(1000)), new Vector3f(0,0,0), new Vector3f(2,2,2)));
		  }
		  
		  Light light = new Light(new Vector3f(200,1000,200), new Vector3f(1,0,0));
		  Camera camera  = new Camera();
		  
		
		  MasterRenderer renderer = new MasterRenderer();
		  while(!Display.isCloseRequested()) {
			  if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				  window.CloseDisplay();
			  }

				camera.move();
				//entity2.increaseRotation(0, 0.3f, 0);
	
				
				renderer.processTerrain(terrain);
				renderer.processEntity(entity1);
				/*
				for(Entity trees: entities) {
					renderer.processEntity(trees);
				}
				*/

				
				renderer.render(light, camera);
				window.updateDisplay();

				
		
				
				 
				 
				
		}
		  renderer.cleanUp();
		loader.cleanUP();
		window.CloseDisplay();

		
	}
	

}


