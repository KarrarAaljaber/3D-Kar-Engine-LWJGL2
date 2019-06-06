package BaseEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Textures.ModelTexture;
import Utils.Maths;
import shaders.StaticShader;

public class EntityRenderer {

	private StaticShader shader;
	
	public  EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	

	public void render(Map<TexturedModel, List<Entity>> entities) {
		for(TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity entity: batch) {
				prepareInstance(entity);
				//render primitives from array data. mode - using trinagles to render. count = how many vertex
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT,0);
			}
			unBindTexturedModel();
		}
	}
	
	private void prepareTexturedModel(TexturedModel model) {
		RawModel model2 = model.getRawModel();
		//chooses the active vertex array
		GL30.glBindVertexArray(model2.getVaoID());
		//enables the first index of the vertex array
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper()	, texture.getReflectivity());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
		
	}
	private void unBindTexturedModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);

		GL30.glBindVertexArray(0);
	}
	private void prepareInstance(Entity entity) {

		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),new Vector3f(entity.getRotate().getX(), entity.getRotate().getY(), entity.getRotate().getZ()
				), new Vector3f(entity.getScale().x, entity.getScale().y, entity.getScale().z));
		shader.loadTransformationMatrix(transformationMatrix);
	}



}
