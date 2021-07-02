import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int SIZE = 10;
    public static final int EMPTY = 0;
    public static final int SHIP = 1;
    public static final int DEAD = 2;
    public static final int MISS = 3;
    public static final int MAX_COUNT = 10;
    public static int shoots = 30;

    public static void main(String[] args) {
        int warField[][] = new int[SIZE][SIZE];

        System.out.println("Добро пожаловать в игру!");

        // placing ships
        Random random = new Random();
        System.out.println("Располагаем корабли ... \n");
        for (int i = 0; i < MAX_COUNT; i++) {
            int shipPlace1 = random.nextInt(SIZE);
            int shipPlace2 = random.nextInt(SIZE);

            if (warField[shipPlace1][shipPlace2] == SHIP) {
                i--;
            }
            warField[shipPlace1][shipPlace2] = SHIP;
        }

        printField(warField);

        while (true) {
            // проверка на кол-во ходов
            if (shoots == 0) {
                System.out.println("Количество попыток закончилось. Игра завершена. \nВЫ " +
                        "ПРОИГРАЛИ " + "\u2620\u2620\u2620");
                break;
            }

            // проверка на убитые корабли
            int deadShips = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (warField[i][j] == DEAD) {
                        deadShips++;
                        if (deadShips == MAX_COUNT) {
                            System.out.println("Победа! Поздравляем, вы подбили все вражеские " +
                                    "корабли!" + "\u2693");
                            break;
                        }
                    }
                }
            }

            System.out.println("\nВведите координаты для атаки _:_ (осталось " + shoots + " " +
                    "попыток):");
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            String[] shoot = inputString.split(":");
            int shoot1 = Integer.parseInt(shoot[0]) - 1;
            int shoot2 = Integer.parseInt(shoot[1]) - 1;

            if (warField[shoot1][shoot2] == EMPTY || warField[shoot1][shoot2] == MISS) {
                warField[shoot1][shoot2] = MISS;
                printField(warField);
            } else {
                warField[shoot1][shoot2] = DEAD;
                printField(warField);
            }
            shoots--;
        }

    }

    public static void printField(int[][] arr) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] == EMPTY || arr[i][j] == SHIP) {
                    System.out.format("%2s", "\u26f6");
                }
                if (arr[i][j] == DEAD) {
                    System.out.format("%2s", "\u22a0");
                }
                if (arr[i][j] == MISS) {
                    System.out.format("%2s", "\u22a1");
                }
            }
            System.out.println();
        }
    }
}