import java.util.Scanner;

public class PeminjamanPengembalian {
    static Scanner input = new Scanner(System.in);
    static String[][] bukuPeminjaman;
    static int totalDenda;

    public static void main(String[] args) {
        System.out.print("Masukkan nama peminjam: ");
        String nama = input.nextLine();
        System.out.print("Masukkan NIM peminjam: ");
        String NIM = input.nextLine();

        System.out.print("Masukkan jumlah buku yang ingin dipinjam: ");
        int jumlahBuku = input.nextInt();
        input.nextLine(); // Consume the newline character
        int choice;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Peminjaman Buku");
            System.out.println("2. Pengembalian Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    bukuPeminjaman = peminjamanBuku(jumlahBuku);
                    break;
                case 2:
                    totalDenda = pengembalianBuku(bukuPeminjaman);
                    break;
                case 3:
                    informasiLengkap(nama, NIM, jumlahBuku, totalDenda);
                    break;
                case 4:
                    cariBuku(bukuPeminjaman);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
            }
        } while (choice != 4);
    }

    static String[][] peminjamanBuku(int jumlahBuku) {
        String[][] bukuPeminjaman = new String[jumlahBuku][10];

        for (int i = 0; i < jumlahBuku; i++) {
            System.out.println("\nMasukkan informasi buku ke-" + (i + 1));
            System.out.print("Masukkan nama buku: ");
            bukuPeminjaman[i][0] = input.nextLine();

            for (int j = 1; j <= 3; j++) {
                System.out.print("Lama meminjam (" + (j == 1 ? "DD" : (j == 2 ? "MM" : "YYYY")) + "): ");
                bukuPeminjaman[i][j] = input.nextLine();
            }

            for (int j = 4; j <= 9; j++) {
                System.out.print("Tanggal " + (j <= 6 ? "peminjaman" : "pengembalian") + " (" +
                        ((j - 4) % 3 == 0 ? "DD" : ((j - 4) % 3 == 1 ? "MM" : "YYYY")) + "): ");
                bukuPeminjaman[i][j] = input.nextLine();
            }
        }

        return bukuPeminjaman;
    }

    static int pengembalianBuku(String[][] bukuPeminjaman) {
        int totalDenda = 0;

        for (int i = 0; i < bukuPeminjaman.length; i++) {
            int hariTerlambat = Integer.parseInt(bukuPeminjaman[i][7]) - (Integer.parseInt(bukuPeminjaman[i][4]) + Integer.parseInt(bukuPeminjaman[i][1]));
            int bulanTerlambat = Integer.parseInt(bukuPeminjaman[i][8]) - (Integer.parseInt(bukuPeminjaman[i][5]) + Integer.parseInt(bukuPeminjaman[i][2]));
            int tahunTerlambat = Integer.parseInt(bukuPeminjaman[i][9]) - (Integer.parseInt(bukuPeminjaman[i][6]) + Integer.parseInt(bukuPeminjaman[i][3]));

            if (tahunTerlambat > 0 || bulanTerlambat > 0 || hariTerlambat > 0) {
                int totalHariTerlambat = tahunTerlambat * 365 + bulanTerlambat * 30 + hariTerlambat;
                int denda = totalHariTerlambat * 500;
                totalDenda += denda;
                System.out.println("Anda terkena denda keterlambatan sebesar: " + denda);
                System.out.println("\n=======INFORMASI LENGKAP=======");
                System.out.println("Buku ke-" + (i + 1) + ": " + bukuPeminjaman[i][0]);
                System.out.println("Total Denda: " + denda);
            } else {
                System.out.print("\n=======INFORMASI LENGKAP======= \nBuku ke-" + (i + 1) + ": " + bukuPeminjaman[i][0] + "\nBuku Berhasil Dikembalikan");
            }
        }

        return totalDenda;
    }

    static void informasiLengkap(String nama, String NIM, int jumlahBuku, int totalDenda) {
        System.out.println("\n=======INFORMASI LENGKAP=======\nNAMA : " + nama + "\nNIM : " + NIM + "\nJUMLAH BUKU" + jumlahBuku + "\nTOTAL DENDA : " + totalDenda);

        if (totalDenda > 0) {
            System.out.println("\nTotal denda keseluruhan: " + totalDenda);
            System.out.print("Apakah anda ingin membayar denda? (ya/tidak): ");
            String jawaban = input.next();

            if (jawaban.equalsIgnoreCase("ya")) {
                bayarDenda(totalDenda);
            } else if (jawaban.equalsIgnoreCase("tidak")) {
                System.out.println("Anda memilih untuk tidak membayar denda");
                System.out.println("Buku berhasil dikembalikan");
            } else {
                System.out.println("Input tidak valid, Buku berhasil dikembalikan tanpa pembayaran");
            }
        }
    }

    static void bayarDenda(int totalDenda) {
        System.out.print("Masukkan nominal pembayaran anda: ");
        int bayar = input.nextInt();

        while (bayar < totalDenda) {
            System.out.println("Nominal pembayaran anda tidak mencukupi, silahkan masukkan nominal yang mencukupi");
            System.out.print("Masukkan nominal pembayaran anda: ");
            bayar = input.nextInt();
        }

        if (bayar > totalDenda) {
            double kembalian = bayar - totalDenda;
            System.out.println("Kembalian anda adalah: " + kembalian);
        } else {
            System.out.print("Uang anda pas, ");
        }

        System.out.println("Buku berhasil dikembalikan");
    }

    static void cariBuku(String[][] bukuPeminjaman) {
        System.out.print("Masukkan judul buku yang ingin dicari: ");
        String judulCari = input.next();

        // Search for the book title within the bukuPeminjaman array
        boolean bookFound = false;
        for (int i = 0; i < bukuPeminjaman.length; i++) {
            if (bukuPeminjaman[i][0].equalsIgnoreCase(judulCari)) {
                System.out.println("Buku ditemukan di indeks: " + i);
                System.out.println("Judul Buku: " + bukuPeminjaman[i][0]);
                bookFound = true;
                break; // Stop searching after finding the first match
            }
        }

        if (!bookFound) {
            System.out.println("Buku tidak ditemukan.");
        }
    }
}
