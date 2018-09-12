package com.example.adailson.tangram;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//essa classe implementa os métodos necessários para
//utilizar a biblioteca OPENGL no desenho gráfico que
// sera apresentado na tela pela superficie de desenho
class Renderizador implements GLSurfaceView.Renderer {

    Triangulo tri;
    Triangulo tri2;
    Quadrado qua;
    Paralelogramo para;
    private static int larguraX;
    private static int alturaY;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int largura, int altura) {
        //É chamado quando a superfície de desenho for alterada.
        //Configurando a área de cordenadas do plano cartesiano
        //MAtriz de projeção
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        //Define o espaço de trabalho.
        //volume (CUBO 3D) de renderização - Tudo que for configurado dentro desse volume aparece na tela.
        //Definindo X - Y - Z , LARGURA - ALTURA - PROFUNDIDADE
        gl.glOrthof(0.0f, largura, 0.0f, altura, -1.0f, 1.0f);
        //OPENGL aponta para nova matriz (De transformações geométricas)
        //Translação, Rotação e Escala
        //Matriz de câmera
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, largura, altura);


        tri = new Triangulo(gl);
        float[] co1 = new float[]{
                0, 0,
                800, 0,
                800 / 2, 800
        };
        tri.setCoordenadas(co1);
        tri.setPosY(10);
        tri.setPosX(10);



        tri2 = new Triangulo(gl);
        float[] co2 = new float[]{
                0, 0,
                400, 0,
                400 / 2, 400
        };
        tri2.setCoordenadas(co2);



        para = new Paralelogramo(gl);
        float[] co3 = new float[]{
                0, 0,
                700 / 2, 0,
                700 / 4, 700 / 4,
                3 * 700 / 4, 700 / 4
        };
        para.setCoordenadas(co3);

        qua = new Quadrado(gl);
        float[] co4 = new float[]{
                0,200,
                0,0,
                200,200,
                200,0
        };
        qua.setCoordenadas(co4);



    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        tri.setCor(0, 1, 0, 0);
        tri.registraBuffer();
        tri.desenha();

        tri2.setCor(0, 0, 1, 0);
        tri2.registraBuffer();
        tri2.desenha();

        para.setCor(1,0,0,0);
        para.registraBuffer();
        //para.desenha();

        qua.setCor(1,1,0,0);
        qua.registraBuffer();
        qua.desenha();

    }
}
