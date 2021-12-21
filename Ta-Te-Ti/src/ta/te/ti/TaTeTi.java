
package ta.te.ti;

import java.util.Scanner;

/**
 *
 * @author Magali Garcia
 */
public class TaTeTi {

    public static void main(String[] args) {
        jugar();
    }
    
    public static void jugar() {
        char simboloJugador1 = 'X';
        char simboloJugador2 = 'O';
        char casillaVacia = '-';
        int fila;
        int columna;
        boolean posicionValidada;
        boolean correcto = false;
        
        // true = jugador1 // false = jugador2
        boolean turno = true;
        
        //El usuario selecciona el tamaño del tablero
        int tamanio = seleccionTablero();
        
        //Instanciando la matriz seleccionada
        char tablero[][] = new char[tamanio][tamanio];
        
        //Inicialización de la matriz con casilleros disponibles
        rellenarMatriz(tablero, casillaVacia);
        
        //Pidiendo nombres de los jugadores
        String nombreJugadorUno = ingresarNombreJugador1();
        String nombreJugadorDos = ingresarNombreJugador2();
        
        //Mientras queden casilleros disponibles y no haya un ganador el bucle se repite
        while(!finPartida(tablero, casillaVacia)){
            do{
            mostrarTurnoActual(turno,nombreJugadorUno,nombreJugadorDos);
            //Muestro matriz
            mostrarMatriz(tablero);
            //Se pide fila y columna donde poner la marca
            fila = pedirEntero("Ingrese el numero de fila");
            columna = pedirEntero("Ingrese el numero de columna");
            //Se valida que la posicion seleccionada este dentro del rango
            posicionValidada = posicionValida(tablero, fila, columna);
        
            //Si es valido, comprobamos que no haya ninguna marca
                if (posicionValidada) {

                    //Si no hay marca, significa que es correcto
                    if (!posicionLibre(tablero, fila, columna, casillaVacia)) {
                        correcto = true;
                    } else {
                        System.out.println("Ya hay una marca en esa posicion");
                    }
                } else {
                    System.out.println("La posicion no es valida");
                }
            } while(!correcto);   
        //depende del turno, inserta un simbolo u otro
            if (turno) {
                insertarEn(tablero, fila, columna, simboloJugador1);
            } else {
                insertarEn(tablero, fila, columna, simboloJugador2);
            }            
            
            //cambio de turno
            turno = !turno;
                    
        }
        
                //Muestra el tablero
        mostrarMatriz(tablero);

        //Mostramos el ganador
        mostrarGanador(tablero, simboloJugador1, simboloJugador2, casillaVacia, nombreJugadorUno, nombreJugadorDos);
    }
    
    public static int seleccionTablero() {
        Scanner scTablero = new Scanner(System.in);
        System.out.println("Seleccione el tamaño del tablero:");
        System.out.println("1)- 3x3");
        System.out.println("2)- 4x4");
        System.out.println("3)- 5x5");
        System.out.println("4)- 6x6");
        System.out.println("5)- 7x7");
        System.out.println("6)- 8x8");
        System.out.println("7)- 9x9");
        System.out.println("8)- 10x10");
        int tamanioTablero = scTablero.nextInt();
        
        while(tamanioTablero < 1 || tamanioTablero > 8){
            System.out.println("Error, debe seleccionar una opción entre 1 y 8. Reintentelo: ");
            System.out.println("Seleccione el tamaño del tablero:");
            System.out.println("1)- 3x3");
            System.out.println("2)- 4x4");
            System.out.println("3)- 5x5");
            System.out.println("4)- 6x6");
            System.out.println("5)- 7x7");
            System.out.println("6)- 8x8");
            System.out.println("7)- 9x9");
            System.out.println("8)- 10x10");
            tamanioTablero = scTablero.nextInt();
        }
      
        int tamanioSeleccionado = 0;
        switch(tamanioTablero){
                case 1:
                    tamanioSeleccionado = 3;
                break;
                case 2:
                    tamanioSeleccionado = 4;
                break;
                case 3:
                    tamanioSeleccionado = 5;
                break;
                case 4:
                    tamanioSeleccionado = 6;
                break;
                case 5:
                    tamanioSeleccionado = 7;
                break;
                case 6:
                    tamanioSeleccionado = 8;
                break;
                case 7:
                    tamanioSeleccionado = 9;
                break;
                case 8:
                    tamanioSeleccionado = 10;
                break;
                default:
                    System.out.println("Error");
                    break;
        }
        
        return tamanioSeleccionado;
    }
    
    public static void mostrarEncabezado(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("            TA - TE -TI "              );
        System.out.println("╚═════════════════════════════════════════╝");
    }
    
    public static void rellenarMatriz(char[][] matriz, char simbolo) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = simbolo;
            }
        }
    }
    
    public static void mostrarMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }

    }
    
    public static String ingresarNombreJugador1(){
        Scanner scJ1 = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador 1: ");
        String jugador1 = scJ1.nextLine();
        return jugador1;    
    }
    
    public static String ingresarNombreJugador2(){
        Scanner scJ2 = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador 2: ");
        String jugador2 = scJ2.nextLine();
        return jugador2;    
    }
    
        public static int pedirEntero(String mensaje) {
        Scanner scEntero = new Scanner(System.in);
        System.out.println(mensaje);
        int numero = scEntero.nextInt();
        return numero;

    }
        
        public static boolean posicionValida(char[][] tablero, int fila, int columna) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {
            return true;
        }
        return false;
    }
   
        public static boolean posicionLibre(char[][] matriz, int fila, int columna, char simboloLibre) {
        if (matriz[fila][columna] != simboloLibre) {
            return true;
        }
        return false;
    }
        
        public static void insertarEn(char[][] matriz, int fila, int columna, char simbolo) {
        matriz[fila-1][columna-1] = simbolo;
    }
        
    public static void mostrarTurnoActual(boolean turno, String jugador1, String jugador2) {

        if (turno) {
            System.out.println("╔══════════════════════╗");
            System.out.println(" TURNO: " + jugador1.toUpperCase() + " X ");
            System.out.println("╚══════════════════════╝");
        } else {
            System.out.println("╔══════════════════════╗");
            System.out.println(" TURNO: " + jugador2.toUpperCase() + " O ");
            System.out.println("╚══════════════════════╝");
        }
    }
    
    public static boolean matrizLlena(char[][] matriz, char simboloVacio) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == simboloVacio) {
                    return false;
                }
            }
        }
        return true;

    }
    
    public static boolean finPartida(char[][] matriz, char simboloDef) {

        if (matrizLlena(matriz, simboloDef)
                || coincidenciaLinea(matriz, simboloDef) != simboloDef
                || coincidenciaColumna(matriz, simboloDef) != simboloDef
                || coincidenciaDiagonal(matriz, simboloDef) != simboloDef) {
            return true;
        }

        return false;
    }

    /**
     * Indica si hay un ganador en una linea
     *
     * @param matriz
     * @param simboloDef
     * @return simbolo ganador, sino lo hay devuelve el de por defecto
     */
    public static char coincidenciaLinea(char[][] matriz, char simboloDef) {

        char simbolo;
        boolean coincidencia;

        for (int i = 0; i < matriz.length; i++) {
            //Reiniciamos la coincidencia
            coincidencia = true;
            //Cogemos el simbolo de la fila
            simbolo = matriz[i][0];
            if (simbolo != simboloDef) {
                for (int j = 1; j < matriz[0].length; j++) {
                    //sino coincide ya no habra ganadro en esta fila
                    if (simbolo != matriz[i][j]) {
                        coincidencia = false;
                    }
                }
                //Si no se mete en el if, devuelvo el simbolo ganador
                if (coincidencia) {
                    return simbolo;
                }
            }
        }
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
    }

    public static char coincidenciaColumna(char[][] matriz, char simboloDef) {

        char simbolo;
        boolean coincidencia;
        for (int j = 0; j < matriz.length; j++) {
            //Reiniciamos la coincidencia
            coincidencia = true;
            //Cogemos el simbolo de la columna
            simbolo = matriz[0][j];
            if (simbolo != simboloDef) {
                for (int i = 1; i < matriz[0].length; i++) {
                    //sino coincide ya no habra ganadro en esta fila
                    if (simbolo != matriz[i][j]) {
                        coincidencia = false;
                    }
                }
                //Si no se mete en el if, devuelvo el simbolo ganador
                if (coincidencia) {
                    return simbolo;
                }
            }
        }
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
    }

    public static char coincidenciaDiagonal(char[][] matriz, char simboloDef) {

        char simbolo;
        boolean coincidencia = true;
        //Diagonal principal
        simbolo = matriz[0][0];
        if (simbolo != simboloDef) {
            for (int i = 1; i < matriz.length; i++) {
                //sino coincide ya no habra ganadro en esta fila
                if (simbolo != matriz[i][i]) {
                    coincidencia = false;
                }
            }
            //Si no se mete en el if, devuelvo el simbolo ganador
            if (coincidencia) {
                return simbolo;
            }
        }

        //Diagonal inversa
        simbolo = matriz[0][2];
        if (simbolo != simboloDef) {
            for (int i = 1, j = 1; i < matriz.length; i++, j--) {
                //sino coincide ya no habra ganadro en esta fila
                if (simbolo != matriz[i][j]) {
                    coincidencia = false;
                }
            }
            //Si no se mete en el if, devuelvo el simbolo ganador
            if (coincidencia) {
                return simbolo;
            }
        }
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
    }
    
    public static void mostrarGanador(char[][] matriz, char J1, char J2, char simDef, String nombreJugadorUno, String nombreJugadorDos) {

        char simbolo = coincidenciaLinea(matriz, simDef);

        if (simbolo != simDef) {

            ganador(simbolo, J1, J2, 1, nombreJugadorUno, nombreJugadorDos);

            return;

        }

        simbolo = coincidenciaColumna(matriz, simDef);

        if (simbolo != simDef) {

            ganador(simbolo, J1, J2, 2, nombreJugadorUno, nombreJugadorDos);

            return;

        }

        simbolo = coincidenciaDiagonal(matriz, simDef);

        if (simbolo != simDef) {

            ganador(simbolo, J1, J2, 3, nombreJugadorUno, nombreJugadorDos);

            return;

        }

        System.out.println("Hay empate");

    }
    
        public static void ganador(char simbolo, char J1, char J2, int tipo, String nombreJugadorUno, String nombreJugadorDos) {

        switch (tipo) {
            case 1:
                if (simbolo == J1) {
                    System.out.println("Ha ganado " + nombreJugadorUno + " por linea");
                } else {
                    System.out.println("Ha ganado " + nombreJugadorDos + " por linea");
                }

                break;
            case 2:
                if (simbolo == J1) {
                    System.out.println("Ha ganado " + nombreJugadorUno + " por columna");
                } else {
                    System.out.println("Ha ganado " + nombreJugadorDos + " por columna");
                }
                break;
            case 3:
                if (simbolo == J1) {
                    System.out.println("Ha ganado " + nombreJugadorUno + " por diagonal");
                } else {
                    System.out.println("Ha ganado " + nombreJugadorDos + " por diagonal");
                }
                break;
        }

    }

}
