package org.pokemones;

import java.sql.SQLException;
import java.util.Scanner;

import static org.pokemones.MoveDAO.enlistarmovimientos;
import static org.pokemones.PokemonDAO.enlistarpokemon;
import static org.pokemones.TrainerDAO.enlistarentrenadores;
import static org.pokemones.TypeDAO.enlistartipos;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainerDAO trainerDAO = new TrainerDAO();
        PokemonDAO pokemonDAO = new PokemonDAO();
        MoveDAO moveDAO = new MoveDAO();
        TypeDAO typeDAO = new TypeDAO();

        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Registrar un entrenador");
            System.out.println("2. Registrar un Pokémon");
            System.out.println("3. Registrar un tipo");
            System.out.println("4. Registrar un movimiento");
            System.out.println("5. Relacionar Pokémon con tipo");
            System.out.println("6. Relacionar Pokémon con movimiento");
            System.out.println("7. Mostrar entreadores");
            System.out.println("8. Mostrar pokemones");
            System.out.println("9. Mostar movimientos");
            System.out.println("10. Mostrar tipos");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (choice) {
                case 1:
                    registrarEntrenador(scanner, trainerDAO);
                    break;
                case 2:
                    registrarPokemon(scanner, pokemonDAO);
                    break;
                case 3:
                    registrarTipo(scanner, typeDAO);
                    break;
                case 4:
                    registrarMovimiento(scanner, moveDAO);
                    break;
                case 5:
                    relacionarPokemonConTipo(scanner, pokemonDAO);
                    break;
                case 6:
                    relacionarPokemonConMovimiento(scanner, pokemonDAO);
                    break;
                    case 7:
                        enlistarentrenadores();
                        break;
                case 8:
                    enlistarpokemon();
                    break;
                case 9:
                    enlistarmovimientos();
                    break;
                case 10:
                    enlistartipos();
                    break;
                case 11:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }



    private static void registrarEntrenador(Scanner scanner, TrainerDAO trainerDAO) {
        System.out.print("Ingrese el nombre del entrenador: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la edad del entrenador: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Ingrese la región del entrenador: ");
        String region = scanner.nextLine();

        Trainer trainer = new Trainer(0, name, age, region);
        try {
            trainerDAO.saveTrainer(trainer);
            System.out.println("Entrenador registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el entrenador.");
            e.printStackTrace();
        }
    }

    private static void registrarPokemon(Scanner scanner, PokemonDAO pokemonDAO) {
        System.out.print("Ingrese el nombre del Pokémon: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la imagen del Pokémon: ");
        String image = scanner.nextLine();
        System.out.print("Ingrese el ID del entrenador: ");
        int trainerId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Pokemon pokemon = new Pokemon(0, name, image, trainerId);
        try {
            pokemonDAO.savePokemon(pokemon);
            System.out.println("Pokémon registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el Pokémon.");
            e.printStackTrace();
        }
    }

    private static void registrarTipo(Scanner scanner, TypeDAO typeDAO) {
        System.out.print("Ingrese el nombre del tipo: ");
        String name = scanner.nextLine();

        Type type = new Type(0, name);
        try {
            typeDAO.saveType(type);
            System.out.println("Tipo registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el tipo.");
            e.printStackTrace();
        }
    }

    private static void registrarMovimiento(Scanner scanner, MoveDAO moveDAO) {
        System.out.print("Ingrese el nombre del movimiento: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el daño del movimiento: ");
        int damage = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        Move move = new Move(0, name, damage);
        try {
            moveDAO.saveMove(move);
            System.out.println("Movimiento registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el movimiento.");
            e.printStackTrace();
        }
    }

    private static void relacionarPokemonConTipo(Scanner scanner, PokemonDAO pokemonDAO) {
        System.out.print("Ingrese el ID del Pokémon: ");
        int pokemonId = scanner.nextInt();
        System.out.print("Ingrese el ID del tipo: ");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try {
            pokemonDAO.savePokemonType(pokemonId, typeId);
            System.out.println("Relación Pokémon-Tipo registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la relación Pokémon-Tipo.");
            e.printStackTrace();
        }
    }

    private static void relacionarPokemonConMovimiento(Scanner scanner, PokemonDAO pokemonDAO) {
        System.out.print("Ingrese el ID del Pokémon: ");
        int pokemonId = scanner.nextInt();
        System.out.print("Ingrese el ID del movimiento: ");
        int moveId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try {
            pokemonDAO.savePokemonMove(pokemonId, moveId);
            System.out.println("Relación Pokémon-Movimiento registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la relación Pokémon-Movimiento.");
            e.printStackTrace();
        }
    }
}
