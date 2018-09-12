package com.example.adailson.tangram;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {
    public Paralelogramo(GL10 gl) {
        super(gl);
    }

    @Override
    public void desenha() {
        super.getGl().glDrawArrays(GL10.GL_TRIANGLES, 1, 4);
    }
}
