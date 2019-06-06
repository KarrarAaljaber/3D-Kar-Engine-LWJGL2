package BaseEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Models.RawModel;


public class ModelLoader {

	
	private List<Integer>vaos = new ArrayList<Integer>();
	private List<Integer>vbos = new ArrayList<Integer>();
	private List<Integer>textures = new ArrayList<Integer>();

	
	public RawModel loadToVAO(float[] positions, float[] textureCoords,float[] normals, int[] indices) {
		//using the already created vao from method createVAO()
		int vaoID = createVAO();
		//using method bindindicesBuffer to active the buffer
		bindIndicesBuffer(indices);
		//storing a vbo in the first index of the vao array and using it for
		//the position of a vertex
		storeDataInAttributeList(0,3, positions);
		//the texturecoords storing it in index 1
		storeDataInAttributeList(1,2, textureCoords);
		storeDataInAttributeList(2,3, normals);


		//after done using the vao it will be unbind using the unbindVAO method 
		unbindVAO();
		
		return new RawModel(vaoID, indices.length);
	}
	public int loadTexture(String fileName) {
		Texture texture = null;

			try {
				texture = TextureLoader.getTexture("PNG", new FileInputStream("res/" + fileName+".png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
		
		
	}
	private int createVAO() {
		//creating the empty vao
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		//activating the vao
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[]data) {
		//glgenbuffers = generate buffer object
		int vboID = GL15.glGenBuffers();
		//adding it to the list so we can clean up later
		vbos.add(vboID);
		//activating the vbo
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		//storing data in a floatbuffer and reading from it
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		// creates and initializes a buffer object's data store
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		//adding the vbo to the vao and adding it to index 0
		//startindex, size = since its a trinagle its 3, type = we are using floats, normalized = false, stride and pointer = offset 0 = beginning
		GL20.glVertexAttribPointer(attributeNumber,coordinateSize, GL11.GL_FLOAT, false, 0,0);
		//unbinds the buffer since we are done using it
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		//unbind the VAO
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[]indices) {
		//glgenbuffers = generate buffer object
		int vboID = GL15.glGenBuffers();
		//adding the created vbo to the list so we can clean it up after the program
		// is closed
		vbos.add(vboID);
		//activating the vbo 
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		
		IntBuffer buffer = StoreDataInIntBuffer(indices);
		//  initializes a buffer object's data 
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	private IntBuffer StoreDataInIntBuffer(int[]data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		//putting the data inside the intbuffer
		buffer.put(data);
		//flipping it so we can read from it
		buffer.flip();
		return buffer;
	}
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		//flipping it so we can read from it
		buffer.flip();
		return buffer;
	}
	
	
	public void cleanUP()	{
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
			
		}
		for(int vbo:vbos) {
			GL30.glDeleteVertexArrays(vbo);
			
		}
		for(int texture:textures) {
			GL11.glDeleteTextures(texture);
			
			
		}
	}
	
}
