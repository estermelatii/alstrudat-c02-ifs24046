package del.alstrudat;

public class Program {
    private static final int SIZE = 13;
    private static final int EMPTY = 0;
    private static final int ACTIVE = 1;
    private static final int DELETED = 2;

    private int[] ids;
    private String[] names;
    private int[] stocks;
    private int[] status; // 0=EMPTY, 1=ACTIVE, 2=DELETED

    public Program() {
        ids = new int[SIZE];
        names = new String[SIZE];
        stocks = new int[SIZE];
        status = new int[SIZE]; // default 0 = EMPTY
    }

    private int h1(int key) {
        return key % 13;
    }

    private int h2(int key) {
        return 7 - (key % 7);
    }

    private int probe(int key, int i) {
        return (h1(key) + i * h2(key)) % 13;
    }

    public void insert(int id, String nama, int stok) {
        int insertIdx = -1; // slot terbaik (DELETED pertama atau EMPTY)

        for (int i = 0; i < SIZE; i++) {
            int idx = probe(id, i);

            if (status[idx] == ACTIVE && ids[idx] == id) {
                // ID sudah ada — UPDATE stok
                int oldStok = stocks[idx];
                stocks[idx] = stok;
                System.out.println("UPDATE " + id + " " + oldStok + " -> " + stok);
                return;
            }

            if (status[idx] == EMPTY) {
                if (insertIdx == -1) insertIdx = idx;
                break;
            }

            if (status[idx] == DELETED) {
                System.out.println("COLLISION at " + idx);
                if (insertIdx == -1) insertIdx = idx;
            } else {
                // ACTIVE, beda ID
                if (i > 0) System.out.println("COLLISION at " + idx);
            }
        }

        if (insertIdx == -1) {
            System.out.println("TABLE FULL");
            return;
        }

        status[insertIdx] = ACTIVE;
        ids[insertIdx] = id;
        names[insertIdx] = nama;
        stocks[insertIdx] = stok;
        System.out.println("INSERT " + id + " -> " + insertIdx);
    }

    public void delete(int id) {
        for (int i = 0; i < SIZE; i++) {
            int idx = probe(id, i);
            if (status[idx] == EMPTY) break;
            if (status[idx] == ACTIVE && ids[idx] == id) {
                status[idx] = DELETED;
                System.out.println("DELETE " + id + " -> " + idx);
                return;
            }
        }
        System.out.println("DELETE " + id + " -> NOT FOUND");
    }

    public void search(int id) {
        for (int i = 0; i < SIZE; i++) {
            int idx = probe(id, i);
            if (status[idx] == EMPTY) break;
            if (status[idx] == ACTIVE && ids[idx] == id) {
                System.out.println("FOUND " + id + " " + names[idx] + " " + stocks[idx] + " at " + idx);
                return;
            }
        }
        System.out.println("NOT FOUND " + id);
    }

    public void update(int id, int tambahStok) {
        for (int i = 0; i < SIZE; i++) {
            int idx = probe(id, i);
            if (status[idx] == EMPTY) break;
            if (status[idx] == ACTIVE && ids[idx] == id) {
                int oldStok = stocks[idx];
                stocks[idx] += tambahStok;
                System.out.println("UPDATE " + id + " " + oldStok + " -> " + stocks[idx]);
                return;
            }
        }
        System.out.println("UPDATE " + id + " -> NOT FOUND");
    }

    public void count() {
        int n = 0;
        for (int i = 0; i < SIZE; i++) {
            if (status[i] == ACTIVE) n++;
        }
        System.out.println("COUNT = " + n);
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            if (status[i] == ACTIVE) {
                System.out.println("[" + i + "] " + ids[i] + " " + names[i] + " " + stocks[i]);
            } else if (status[i] == DELETED) {
                System.out.println("[" + i + "] DELETED");
            } else {
                System.out.println("[" + i + "] EMPTY");
            }
        }
    }
}