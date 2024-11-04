package projetoesdlinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * Simulador de lista:
 *     Simula as seguintes operações de uma lista encadeada/ligada/dinâmica:
 *         - Inserir no fim;
 *         - Inserir em posição especificada;
 *         - Alterar em posição especificada;
 *         - Remover de posição especificada
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorLista extends EngineFrame {

    // declaração de variáveis

    public SimuladorLista() {

        // cria a janela do jogo ou simulação
        super( 
            800,                  // 800 pixels de largura
            600,                  // 600 pixels de largura
            "Simulador de Lista", // título da janela
            60,                   // 60 quadros por segundo
            true );               // ativa a suavização (antialiasing)

    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void create() {
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     * O parâmetro delta contém o tempo que passou entre o quadro
     * anterior e o quadro atual.
     */
    @Override
    public void update( double delta ) {
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void draw() {
    }

    public static void main( String[] args ) {
        new SimuladorLista();
    }
    
}
