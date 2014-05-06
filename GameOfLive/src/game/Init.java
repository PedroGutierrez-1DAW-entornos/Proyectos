package game;

/*
 * Tenemos un rejilla rectangular abierta, en la cual cada celdilla puede estar ocupada o no por un microorganismo.
 * Las celdillas vivas o muertas cambian de una generacion a la siguiente, segun el numero de celdillas vecinas que esten vivas,
 * en la forma siguiente:
 * 1. Los vecinos de una celda son los 8 que la tocan verticar horizontal y en diagonales.
 * 2. Si una celda está viva pero no tiene celdillas vecinas vivas o solamente una, en la siguiente generacion muere de soledad.
 * 3. Si una celdilla que esta viva tiene 4 o mas vecinos tambien vivos  en la siguiente generacion muere por hacinamiento.
 * 4. Una celdilla viva con 2 o 3 vecinas vivas permanece viva en la siguiente generacion
 * 5. Si una celdilla esta muerta en la siguiente generacion recuperara la vida si tiene exactamente 3 vecinos vivos. 
 *    Todas las otras celdillas muertas permaneceran muertas
 * 6. Todos los nacimientos y muertes tienen lugar exactamente en el mismo instante por lo que una muerte 
 *    no causa efectos en la sigueinte generacion.
 */

public class Init {

	public static void main(String[] args) {
		
		World world = new World(44, 44, 0.2, 15);
		
		world.initShow();
		
	}

}
