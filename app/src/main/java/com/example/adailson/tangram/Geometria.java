package com.example.adailson.tangram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Geometria {

    private GL10 gl;
    private float [] coordenadas;
    private FloatBuffer buffer;

    public Geometria(GL10 gl){
        this.gl = gl;
    }

    public abstract void desenha(int first, int count);

    public void setCor(float red, float green, float blue, float alpha){
        gl.glColor4f(red, green, blue, alpha);
    }

    public void setAng(float angulo, float posX, float posY, float posZ){
        gl.glRotatef(angulo, posX, posY, posZ);
    }

    public GL10 getGl() {
        return gl;
    }

    public void setGl(GL10 gl) {
        this.gl = gl;
    }

    public float[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(float[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void criarBuffer() {
        this.buffer = Geometria.criaNIOBuffer(coordenadas);
        //Solicita a liberação do recurso array de vertices para a OPENGL
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //Registra o vetor de vertices criado (FLOATBUFFER) na OPENGL
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, buffer);
    }

    public static FloatBuffer criaNIOBuffer(float[] coordenadas) {
        //Aloca a qtd de bytes necessárias para armazenar os dados dos vertices
        ByteBuffer vetBytes = ByteBuffer.allocateDirect(coordenadas.length * 4);

        //Usa o sistema de endereçamento de memória
        //nativo no processador em questão
        vetBytes.order(ByteOrder.nativeOrder());

        //cria o FloatBuffer a partir do byteBuffer
        FloatBuffer buffer = vetBytes.asFloatBuffer();

        //Limpa um eventual lixo de memória
        buffer.clear();

        //Encapsula o array java no objeto Float Buffer
        buffer.put(coordenadas);

        //Retira as sobras de memória
        buffer.flip();

        //Retorna o objeto de coordenadas
        return buffer;
    }
}
