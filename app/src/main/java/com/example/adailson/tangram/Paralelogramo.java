package com.example.adailson.tangram;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {
    public Paralelogramo(GL10 gl) {
        super(gl);
    }

    @Override
    public void desenha() {
        super.getGl().glLoadIdentity();
        super.getGl().glRotatef(super.getAnguloRotacao(), 0, 0, 1);
        super.getGl().glTranslatef(super.getPosX(), super.getPosY(), 0);
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 6);
    }
}
