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

    Triangulo trianguloMaior1;
    public int larguraX;
    public int alturaY;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int largura, int altura) {
        //É chamado quando a superfície de desenho for alterada.
        larguraX = largura;
        alturaY = altura;
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
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        trianguloMaior1 = new Triangulo(gl);
        trianguloMaior1.setCor(0, 1, 0, 0);
        float[] coordenadas = new float[]{
                0, 0,
                larguraX, 0,
                larguraX / 2, alturaY
        };
        trianguloMaior1.setCoordenadas(coordenadas);
        trianguloMaior1.criarBuffer();
        trianguloMaior1.desenha(1,3);

        Triangulo tri = new Triangulo(gl);
        tri.setCor(1,0,0,0);
        float[] coordenadas1 = new float[]{
                0, 0,
                larguraX/2, 0,
                larguraX / 4, alturaY/2
        };
        tri.setCoordenadas(coordenadas1);
        tri.criarBuffer();
        tri.desenha(1,3);
    }
}
