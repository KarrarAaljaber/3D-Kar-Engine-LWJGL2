package Utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;

public class Maths {
	
	public static Matrix4f createTransformationMatrix (Vector3f translation, Vector3f rotate, Vector3f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotate.getX()), new Vector3f(1,0,0)	, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotate.getY()), new Vector3f(0,1,0)	, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotate.getZ()), new Vector3f(0,0,1)	, matrix, matrix);
		Matrix4f.scale(scale, matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getX()), new Vector3f(1,0,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getY()), new Vector3f(0,1,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getRotation().getZ()), new Vector3f(0,0,1), viewMatrix, viewMatrix);

		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, - cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		
		return viewMatrix;
	}

}
