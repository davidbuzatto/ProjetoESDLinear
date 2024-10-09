package projetoesdlinear;

import projetoesdlinear.engine.Engine;

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
public class SimuladorLista extends Engine {

    // declaração de variáveis

    public SimuladorLista() {

        // cria a janela do jogo ou simulação
        super( 
            800,                  // 800 pixels de largura
            600,                  // 600 pixels de largura
            "Simulador de Listas",// título da janela
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
        new SimuladorLista();
    }
    
}
