package projetoesdlinear;

import aesd.ds.exceptions.EmptyStackException;
import aesd.ds.implementations.linear.LinkedStack;
import aesd.ds.interfaces.Stack;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import projetoesdlinear.engine.Engine;

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
    
    // pilha usada para desenhar os dados da pilha que está sendo operada
    private Stack<String> pilhaDesenho;
    
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
            "Simulador de Pilhas",// título da janela
            true,                 // ativa a suavização (antialiasing)
            60 );                 // 60 quadros por segundo

    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void criar() {
        
        pilha = new LinkedStack<>();
        pilhaDesenho = new LinkedStack<>();
        
        raio = 30;
        distanciaEntreElementos = 20;
        tamanhoFonte = 20;
        margemBaixo = 80;
        
        /*pilha.push( "a" );
        pilha.push( "b" );
        pilha.push( "c" );
        atualizarPilhaDesenho();*/
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void atualizar() {
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void desenhar() {
        desenharOpcoesEstadoPilha();
        desenharPilha();
    }

    private void desenharOpcoesEstadoPilha() {
        
        int yInicial = 30;
        
        drawText( "1) Empilhar", 10, yInicial, tamanhoFonte, BLACK );
        drawText( "2) Desempilhar", 10, yInicial += 30, tamanhoFonte, BLACK );
        
        drawText( "Desempilhou: " + ( valorDesempilhado == null ? "nenhum" : valorDesempilhado ), 
                10, yInicial += 30, tamanhoFonte, BLUE );
        
        if ( pilha.isEmpty() ) {
            drawText( "Pilha vazia!", 10, yInicial += 30, tamanhoFonte, RED );
        }
        
    }
    
    private void desenharPilha() {
        
        int yInicial = getScreenHeight() - margemBaixo;
        int elementoAtual = 0;
        
        int xCentro = getScreenWidth() / 2;
        int xCentroAnterior = xCentro;
        
        for ( String valor : pilhaDesenho ) {
            
            int yCentro = yInicial - ( raio * 2  + distanciaEntreElementos ) * elementoAtual;
            int yCentroAnterior = yInicial - ( raio * 2  + distanciaEntreElementos ) * (elementoAtual-1);
            
            drawCircleLines( xCentro, yCentro, raio, BLACK );
            drawText( valor, 
                    xCentro - measureText( valor, tamanhoFonte ) / 2, 
                    yCentro + 5,
                    tamanhoFonte,
                    BLACK );
            
            // arcos    
            // ponto do início (arco de 45 graus)
            int xAtual = xCentro + (int) ( Math.cos( Math.toRadians( 45 ) ) * raio );
            int yAtual = yCentro + (int) ( Math.sin( Math.toRadians( 45 ) ) * raio );

            // ponto do fim (arco de 315 graus)
            int xAnterior = xCentroAnterior + (int) ( Math.cos( Math.toRadians( 315 ) ) * raio );
            int yAnterior = yCentroAnterior + (int) ( Math.sin( Math.toRadians( 315 ) ) * raio );

            int xControle = ( xAtual + xAnterior ) / 2 + 20;
            int yControle = ( yAtual + yAnterior ) / 2;
            
            drawSplineSegmentBezierQuadratic( 
                    xAtual, yAtual, 
                    xControle, yControle,
                    xAnterior, yAnterior, 
                    1, BLUE );
            if ( elementoAtual >= 1 ) {
                desenharSeta( xAnterior, yAnterior, 8, 135, BLUE );
            } else {
                drawLine( xAnterior - 5, yAnterior, xAnterior + 5, yAnterior, BLUE );
                drawLine( xAnterior - 10, yAnterior + 5, xAnterior + 10, yAnterior + 5, BLUE );
            }
            drawText( "anterior", xControle, yControle, tamanhoFonte, BLUE );
            
            elementoAtual++;
            
        }
        
        if ( elementoAtual == pilha.getSize() || pilha.isEmpty() ) {
                
            int comp = 20;
            int xFinal = xCentro - raio;
            int xInicial = xFinal - comp;
            int yCentro = yInicial - ( raio * 2  + distanciaEntreElementos ) * (elementoAtual-1);
            
            if ( pilha.isEmpty() ) {
                yCentro = yInicial;
            }

            drawLine( 
                    xInicial, yCentro, 
                    xFinal, yCentro, 
                    BLACK );
            
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
                    yCentro + 5, 
                    tamanhoFonte, 
                    BLACK );

        }
        
    }
    
    @Override
    public void tratarTeclado( KeyEvent e, KeyboardEventType ket ) {
        
        if ( ket == KeyboardEventType.PRESSED ) {
            
            String valor;
            
            switch ( e.getKeyCode() ) {
                
                case KeyEvent.VK_1:
                case KeyEvent.VK_NUMPAD1:
                    simularEmpilhar();
                    break;
                    
                case KeyEvent.VK_2:
                case KeyEvent.VK_NUMPAD2:
                    simularDesempilhar();
                    break;
                    
            }
            
        }
        
    }

    private void simularEmpilhar() {
        
        String valor = JOptionPane.showInputDialog( "Valor a empilhar:" );
        
        if ( valor != null && !valor.isBlank() ) {
            pilha.push( valor );
            atualizarPilhaDesenho();
        }
                    
    }
    
    private void simularDesempilhar() {
        
        try {
            valorDesempilhado = pilha.pop();
            atualizarPilhaDesenho();
        } catch ( EmptyStackException exc ) {
            // pilha vazia, o retorno será visual
        }
                    
    }
    
    private void atualizarPilhaDesenho() {
        
        pilhaDesenho.clear();
        
        for ( String valor : pilha ) {
            pilhaDesenho.push( valor );
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
