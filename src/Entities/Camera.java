package Entities;

import java.awt.event.MouseMotionAdapter;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f rotation = new Vector3f(0,0,0);
	private Vector3f previous = new Vector3f(0,0,0);
	private float speed = 0.1f;
	
	
	public void move() {
		
	
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y)) * speed;
		
			previous.setY(position.getY());
			position.y -= Math.cos(Math.toRadians(rotation.y)) * speed;
	
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y)) * speed;
		
			previous.setY(position.getY());
			position.y += Math.cos(Math.toRadians(rotation.y)) * speed;
	
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y)) * speed;
		
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y)) * speed;
	
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			previous.setX(position.getX());
			position.x -= Math.sin(Math.toRadians(rotation.y)) * speed;

			previous.setZ(position.getZ());
			position.z += Math.cos(Math.toRadians(rotation.y)) * speed;
			
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y - 90)) * speed;
		
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y - 90)) * speed;
			
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			previous.setX(position.getX());
			position.x += Math.sin(Math.toRadians(rotation.y + 90)) * speed;
			
			previous.setZ(position.getZ());
			position.z -= Math.cos(Math.toRadians(rotation.y + 90)) * speed;
			
		}
		if (Mouse.isGrabbed())
		{
			rotation.y += (float) Mouse.getDX() * 0.05f ;
			rotation.x -= (float) Mouse.getDY() * 0.05f ;
			if (rotation.x < -90) rotation.x = -90;
			else if (rotation.x > 90) rotation.x = 90;
		}

	}



	public Vector3f getRotation() {
		return rotation;
	}



	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}



	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	

}
