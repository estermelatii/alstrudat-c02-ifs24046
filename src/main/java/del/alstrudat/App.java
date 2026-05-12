package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Program program = new Program();

        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String op = parts[0];

            switch (op) {
                case "INSERT":
                    int insertId = Integer.parseInt(parts[1]);
                    String nama = parts[2];
                    int stok = Integer.parseInt(parts[3]);
                    program.insert(insertId, nama, stok);
                    break;
                case "DELETE":
                    int deleteId = Integer.parseInt(parts[1]);
                    program.delete(deleteId);
                    break;
                case "SEARCH":
                    int searchId = Integer.parseInt(parts[1]);
                    program.search(searchId);
                    break;
                case "UPDATE":
                    int updateId = Integer.parseInt(parts[1]);
                    int tambah = Integer.parseInt(parts[2]);
                    program.update(updateId, tambah);
                    break;
                case "COUNT":
                    program.count();
                    break;
                case "DISPLAY":
                    program.display();
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}
