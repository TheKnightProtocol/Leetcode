import java.util.Scanner;

public class PersonRace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        int dist1 = Math.abs(x - z);
        int dist2 = Math.abs(y - z);

        if (dist1 < dist2) {
            System.out.println(1);
        } else if (dist2 < dist1) {
            System.out.println(2);
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}
