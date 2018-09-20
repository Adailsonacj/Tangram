package com.example.adailson.tangram;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//essa classe implementa os métodos necessários para
//utilizar a biblioteca OPENGL no desenho gráfico que
// sera apresentado na tela pela superficie de desenho
class Renderizador implements GLSurfaceView.Renderer, View.OnTouchListener {

    Triangulo tri;
    Triangulo tri2;
    Triangulo tri3;
    Triangulo tri4;
    Triangulo tri5;
    Quadrado qua;
    GL10 gl;
    Paralelogramo para;

    float posX = 0;
    float posY = 0;
    private static int larguraX;
    private static int alturaY;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int largura, int altura) {
        this.gl = gl;
        this.alturaY = altura;
        this.larguraX = largura;
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
        tri.setPos(600, 100);
        tri.setAnguloRotacao(180);

        para = new Paralelogramo(gl);
        para.setPos(500, 0);

        tri2 = new Triangulo(gl);
        tri2.setPos(600, 130);
        tri2.setScale(1.50f, 1.50f);

        tri3 = new Triangulo(gl);
        tri3.setAnguloRotacao(-90);
        tri3.setPos(600, 430);
        tri3.setScale(1.50f, 1.50f);

        qua = new Quadrado(gl);
        qua.setScale(1.25f, 1.25f);
        qua.setGl(gl);
        qua.registraBuffer();

        tri4 = new Triangulo(gl);
        tri4.setPos(600, 380);
        tri4.setAnguloRotacao(-135);
        tri4.setScale(0.875f, 0.875f);

        tri5 = new Triangulo(gl);
        tri5.setPos(475, 255);
        tri5.setAnguloRotacao(-135);
        tri5.setScale(0.875f, 0.875f);

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

        tri3.setCor(1, 0, 0, 0);
        tri3.registraBuffer();
        tri3.desenha();

        para.setCor(1, 0, 1, 0);
        para.registraBuffer();
        para.desenha();
        qua.registraBuffer();

        qua.setPos(posX, posY);
        qua.setCor(0.50f, 0.50f, 1, 0);
        qua.desenha();

        tri4.setCor(1, 1, 0, 0);
        tri4.registraBuffer();
        tri4.desenha();

        tri5.setCor(1, 0.69f, 0, 0);
        tri5.registraBuffer();
        tri5.desenha();

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            posX = (int) motionEvent.getX();
            posY = alturaY - (int) motionEvent.getY();
        }
        return true;
    }
}
