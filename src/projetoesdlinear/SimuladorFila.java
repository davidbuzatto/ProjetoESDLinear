package projetoesdlinear;

import projetoesdlinear.engine.Engine;

/**
 * Simulador de fila:
 *     Simula as operações de enfileirar e desenfileirar de uma fila
 *     encadeada/ligada/dinâmica.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorFila extends Engine {

    // declaração de variáveis

    public SimuladorFila() {

        // cria a janela do jogo ou simulação
        super( 
            800,                  // 800 pixels de largura
            600,                  // 600 pixels de largura
            "Simulador de Fila",  // título da janela
            true,                 // ativa a suavização (antialiasing)
            60 );                 // 60 quadros por segundo

    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void criar() {
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
    }

    public static void main( String[] args ) {
        new SimuladorFila();
    }
    
}
