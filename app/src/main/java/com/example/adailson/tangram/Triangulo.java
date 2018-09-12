package com.example.adailson.tangram;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo extends Geometria{

    FloatBuffer buffer;


    public Triangulo(GL10 gl) {
        super(gl);
    }

    @Override
    public void desenha() {
        super.getGl().glTranslatef(super.getPosX(), super.getPosY(), 0);
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
    }
}
