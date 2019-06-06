package Entities;

import org.lwjgl.util.vector.Vector3f;

import Models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private Vector3f rotate;
	private Vector3f color;

	private Vector3f scale;
	
	public Entity(TexturedModel model, Vector3f position ) {
		super();
		this.model = model;
		this.position = position;

	}
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotate, Vector3f scale ) {
		super();
		this.model = model;
		this.position = position;
		this.scale = scale;
		this.rotate = rotate;
	}
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotate, Vector3f color, float n) {
		super();
		this.model = model;
		this.position = position;
		this.rotate = rotate;
		this.color = color;
	
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Vector3f getRotate() {
		return rotate;
	}

	public void setRotate(Vector3f rotate) {
		this.rotate = rotate;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x +=dx;
		this.position.y +=dy;
		this.position.z +=dz;
	}
	public void increaseRotation(float rx, float ry, float rz) {
		this.rotate.x +=rx;
		this.rotate.y +=ry;
		this.rotate.z +=rz;
	}
	public void TiltLight(float rx, float ry, float rz, float speed) {
		float tiltX = (float)Math.toRadians(rx * speed); 
		float tiltY = (float)Math.toRadians(ry * speed); 
		float tiltZ = (float)Math.toRadians(rz * speed); 
		
	
		this.rotate.x += tiltX;
		this.rotate.y += tiltY;
		this.rotate.z += tiltZ;

	}
	
	public void increaseScale(float sx, float sy, float sz) {
		this.scale.x +=sx;
		this.scale.y +=sy;		
		this.scale.z +=sz;
	}
	
	

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	
}
