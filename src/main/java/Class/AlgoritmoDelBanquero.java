//Creado por: Yojhan Alonso Espinoza Sanchez
//Cedula: 402490298
package Class;
import java.util.Random;

public class AlgoritmoDelBanquero {
    private int numeroDeProcesos;
    private int numeroDeRecursos;
    private int[] recursosTotales;
    private int[] recursosDisponibles;
    private int[][] recursosAsignados;
    private int[][] recursosSolicitados;
    private int[][] recursosNecesarios;
    private Random random;
    private int limiteInferior = 4;
    private int limiteSuperior = 10;

    // Constructor
    public AlgoritmoDelBanquero(int numeroDeProcesos, int numeroDeRecursos) {
        this.numeroDeProcesos = numeroDeProcesos;
        this.numeroDeRecursos = numeroDeRecursos;
        this.recursosTotales = new int[numeroDeRecursos];
        this.recursosDisponibles = new int[numeroDeRecursos];
        this.recursosAsignados = new int[numeroDeProcesos][numeroDeRecursos];
        this.recursosSolicitados = new int[numeroDeProcesos][numeroDeRecursos];
        this.recursosNecesarios = new int[numeroDeProcesos][numeroDeRecursos];
        this.random = new Random(); // Inicializa el generador de números aleatorios
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public int getNumeroDeProcesos() {
        return numeroDeProcesos;
    }

    public int getNumeroDeRecursos() {
        return numeroDeRecursos;
    }

    public int[] getRecursosTotales() {
        return recursosTotales;
    }

    public int[] getRecursosDisponibles() {
        return recursosDisponibles;
    }

    public int[][] getRecursosAsignados() {
        return recursosAsignados;
    }

    public int[][] getRecursosSolicitados() {
        return recursosSolicitados;
    }

    public int[][] getRecursosNecesarios() {
        return recursosNecesarios;
    }

    public Random getRandom() {
        return random;
    }

    public int getLimiteInferior() {
        return limiteInferior;
    }

    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

  //Inicializar Recursos
    public void inicializarRecursos() {
        
        for (int i = 0; i < numeroDeRecursos; i++) {
            int numeroAleatorio = limiteInferior + random.nextInt(limiteSuperior - limiteInferior + 1);
            recursosTotales[i] = numeroAleatorio;
            recursosDisponibles[i] = recursosTotales[i];
        }

        
        for (int i = 0; i < numeroDeProcesos; i++) {
            for (int j = 0; j < numeroDeRecursos; j++) {
                
                recursosNecesarios[i][j] = random.nextInt(5) + 1;
            }
        }

        
        for (int i = 0; i < numeroDeProcesos; i++) {
            for (int j = 0; j < numeroDeRecursos; j++) {
                recursosSolicitados[i][j] = 0;
                recursosAsignados[i][j] = 0;
            }
        }
    }

    // Método para verificar si una solicitud de recursos es válida
   public boolean verificarAsignacion(int idProceso, int[] solicitud) {
    // Verifica que la solicitud no exceda los recursos disponibles o necesarios
    for (int i = 0; i < numeroDeRecursos; i++) {
        if (solicitud[i] > recursosDisponibles[i] || solicitud[i] > recursosNecesarios[idProceso][i]) {
            return false; 
        }
    }

   
    for (int i = 0; i < numeroDeRecursos; i++) {
        recursosDisponibles[i] -= solicitud[i];
        recursosAsignados[idProceso][i] += solicitud[i];
        recursosNecesarios[idProceso][i] -= solicitud[i];
    }

    // Verifica si el sistema permanece en un estado seguro con la solicitud
    boolean seguro = esEstadoSeguro();

   
    for (int i = 0; i < numeroDeRecursos; i++) {
        recursosDisponibles[i] += solicitud[i];
        recursosAsignados[idProceso][i] -= solicitud[i];
        recursosNecesarios[idProceso][i] += solicitud[i];
    }

    return seguro;
}

    // Método para solicitar recursos
    public boolean solicitarRecursos(int idProceso, int[] solicitud) {
        if (!verificarAsignacion(idProceso, solicitud)) {
            return false; 
        }

       
        for (int i = 0; i < numeroDeRecursos; i++) {
            recursosDisponibles[i] -= solicitud[i];
            recursosAsignados[idProceso][i] += solicitud[i];
            recursosSolicitados[idProceso][i] += solicitud[i];
            recursosNecesarios[idProceso][i] -= solicitud[i];
        }

        return true; 
    }
    
      // Método para liberar recursos de un proceso
    public boolean liberarRecursos(int idProceso) {
      
        for (int i = 0; i < numeroDeRecursos; i++) {
            if (recursosAsignados[idProceso][i] < recursosNecesarios[idProceso][i]) {
                return false; 
            }
        }

       
        for (int i = 0; i < numeroDeRecursos; i++) {
            recursosDisponibles[i] += recursosAsignados[idProceso][i];
            recursosAsignados[idProceso][i] = 0;
            recursosSolicitados[idProceso][i] = 0;
            recursosNecesarios[idProceso][i] = 0;
        }

        return true; 
    }

    // Metodo para verificar si el sistema está en un estado seguro
    private boolean esEstadoSeguro() {
      boolean[] terminado = new boolean[numeroDeProcesos];
      int[] trabajo = recursosDisponibles.clone();

      while (true) {
          boolean progreso = false;

          for (int i = 0; i < numeroDeProcesos; i++) {
              if (!terminado[i]) {
                  boolean puedeTerminar = true;

                  for (int j = 0; j < numeroDeRecursos; j++) {
                      if (recursosNecesarios[i][j] > trabajo[j]) {
                          puedeTerminar = false;
                          break;
                      }
                  }

                  if (puedeTerminar) {
                      for (int j = 0; j < numeroDeRecursos; j++) {
                          trabajo[j] += recursosAsignados[i][j];
                      }
                      terminado[i] = puedeTerminar;
                      progreso = true;
                  }
              }
          }

          if (!progreso) {
              break;
          }
      }

      for (boolean t : terminado) {
          if (!t) {
              return false; 
          }
      }

      return true; 
  }
}