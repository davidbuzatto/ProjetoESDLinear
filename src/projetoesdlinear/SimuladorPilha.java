package projetoesdlinear;

import aesd.ds.exceptions.EmptyStackException;
import aesd.ds.implementations.linear.LinkedStack;
import aesd.ds.interfaces.Stack;
import br.com.davidbuzatto.jsge.core.Engine;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Simulador de pilha:
 *     Simula as operações de empilhar e desempilhar de uma pilha
 *     encadeada/ligada/dinâmica.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorPilha extends Engine {

    // pilha que passará pelas operações de empilhar e desempilhar
    private Stack<String> pilha;
    
    // valor desempilhado na última operação de desempilhar
    private String valorDesempilhado;
    
    private int raio;
    private int distanciaEntreElementos;
    private int tamanhoFonte;
    private int margemBaixo;

    public SimuladorPilha() {

        // cria a janela do jogo ou simulação
        super( 
            800,                  // 800 pixels de largura
            600,                  // 600 pixels de largura
            "Simulador de Pilha", // título da janela
            60,                   // 60 quadros por segundo
            true );               // ativa a suavização (antialiasing)

    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void create() {
        
        pilha = new LinkedStack<>();
        
        raio = 30;
        distanciaEntreElementos = 20;
        tamanhoFonte = 20;
        margemBaixo = 80;
        
        pilha.push( "a" );
        pilha.push( "b" );
        pilha.push( "c" );
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void update() {
        
        if ( isKeyPressed( KEY_ONE ) || isKeyPressed( KEY_KP_1 ) ) {
            simularEmpilhar();
        }
        
        if ( isKeyPressed( KEY_TWO ) || isKeyPressed( KEY_KP_2 ) ) {
            simularDesempilhar();
        }
                    
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void draw() {
        setFontStyle( FONT_BOLD );
        desenharOpcoesEstadoPilha();
        desenharPilha();
    }

    private void desenharOpcoesEstadoPilha() {
        
        int yInicial = 10;
        
        drawText( "1) Empilhar", 10, yInicial, tamanhoFonte, BLACK );
        drawText( "2) Desempilhar", 10, yInicial += 30, tamanhoFonte, BLACK );
        
        drawText( "Desempilhou: " + ( valorDesempilhado == null ? "nenhum" : valorDesempilhado ), 
                10, yInicial += 30, tamanhoFonte, BLUE );
        
        if ( pilha.isEmpty() ) {
            drawText( "Pilha vazia!", 10, yInicial += 30, tamanhoFonte, RED );
        }
        
    }
    
    private void desenharPilha() {
        
        int elementoAtual = 0;
        int xCentroAnterior = getScreenWidth() / 2;
        int xCentro = xCentroAnterior;
        int tamanho = pilha.getSize();
        int yInicialPilha = getScreenHeight() - margemBaixo - ( raio * 2  + distanciaEntreElementos ) * tamanho;
        
        for ( String valor : pilha ) {
            
            int yCentro = yInicialPilha + ( raio * 2  + distanciaEntreElementos ) * (elementoAtual+1);
            int yCentroAnterior = yInicialPilha + ( raio * 2  + distanciaEntreElementos ) * (elementoAtual+2);
            
            drawCircle( xCentro, yCentro, raio, BLACK );
            drawText( valor, 
                    xCentro - measureText( valor, tamanhoFonte ) / 2, 
                    yCentro - 8,
                    tamanhoFonte,
                    BLACK );
            
            // arcos    
            // ponto do início (arco de 45 graus)
            int xInicial = xCentro + (int) ( Math.cos( Math.toRadians( 45 ) ) * raio );
            int yInicial = yCentro + (int) ( Math.sin( Math.toRadians( 45 ) ) * raio );
            
            // ponto do fim (arco de 315 graus)
            int xFinal = xCentroAnterior + (int) ( Math.cos( Math.toRadians( 315 ) ) * raio );
            int yFinal = yCentroAnterior + (int) ( Math.sin( Math.toRadians( 315 ) ) * raio );

            int xControle = ( xInicial + xFinal ) / 2 + 20;
            int yControle = ( yInicial + yFinal ) / 2;
            
            drawQuadCurve( 
                    xInicial, yInicial, 
                    xControle, yControle,
                    xFinal, yFinal, BLUE );
            
            if ( elementoAtual == tamanho - 1 ) {
                drawLine( xFinal - 5, yFinal, xFinal + 5, yFinal, BLUE );
                drawLine( xFinal - 10, yFinal + 5, xFinal + 10, yFinal + 5, BLUE );
            } else {
                desenharSeta( xFinal, yFinal, 8, 135, BLUE );
            }
            
            drawText( "anterior", xControle, yControle - 8, tamanhoFonte, BLUE );
            
            elementoAtual++;
            
        }
        
        if ( elementoAtual == pilha.getSize() || pilha.isEmpty() ) {
                
            int comp = 20;
            int xFinal = xCentro - raio;
            int xInicial = xFinal - comp;
            int yCentro = yInicialPilha + ( raio * 2  + distanciaEntreElementos );
            
            if ( pilha.isEmpty() ) {
                yCentro = yInicialPilha;
            }

            drawLine( xInicial, yCentro, xFinal, yCentro, BLACK );
            
            if ( pilha.isEmpty() ) {
                drawLine( xFinal, yCentro - 5, xFinal, yCentro + 5, BLACK );
                drawLine( xFinal + 5, yCentro - 10, xFinal + 5, yCentro + 10, BLACK );
            } else {
                desenharSeta( xFinal, yCentro, 8, 0, BLACK );
            }
            
            String tLabel = "topo";
            drawText( 
                    tLabel, 
                    xInicial - measureText( tLabel, tamanhoFonte ) - 10, 
                    yCentro - 8, 
                    tamanhoFonte, 
                    BLACK );

        }
        
    }

    private void simularEmpilhar() {
        
        String valor = JOptionPane.showInputDialog( "Valor a empilhar:" );
        
        if ( valor != null && !valor.isBlank() ) {
            pilha.push( valor );
        }
                    
    }
    
    private void simularDesempilhar() {
        
        try {
            valorDesempilhado = pilha.pop();
        } catch ( EmptyStackException exc ) {
            // pilha vazia, o retorno será visual
        }
                    
    }
    
    private void desenharSeta( double x, double y, int tamanho, double graus, Color cor ) {
        
        drawLine( 
                x, y, 
                x + Math.cos( Math.toRadians( graus - 135 ) ) * tamanho,
                y + Math.sin( Math.toRadians( graus - 135 ) ) * tamanho,
                cor
        );
        
        drawLine( 
                x, y, 
                x + Math.cos( Math.toRadians( graus + 135 ) ) * tamanho,
                y + Math.sin( Math.toRadians( graus + 135 ) ) * tamanho,
                cor
        );
        
    }
    
    public static void main( String[] args ) {
        new SimuladorPilha();
    }
    
}
