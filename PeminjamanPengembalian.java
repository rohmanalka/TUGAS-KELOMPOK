import java.util.Scanner;

public class PeminjamanPengembalian {
    static Scanner input = new Scanner(System.in);
    static String[][] bukuPeminjaman;
    static int totalDenda;
    static String nama;
    static String NIM;  
    static int jumlahBuku;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("=========== MENU ===========");
            System.out.println("1. Peminjaman Buku");
            System.out.println("2. Pengembalian Buku");
            System.out.println("3. Informasi dan Bayar Denda");
            System.out.println("4. Cari Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character
            System.out.println("============================");

            switch (choice) {
                case 1:
                    bukuPeminjaman = peminjamanBuku();
                    break;
                case 2:
                    totalDenda = pengembalianBuku(bukuPeminjaman);
                    break;
                case 3:
                    informasiLengkap(bukuPeminjaman);
                    break;
                case 4:
                    cariBuku(bukuPeminjaman);
                    break;
                case 5:
                    System.out.print("Terimakasih telah mengunjungi perpustakaan");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
            }
        } while (choice != 5);
    }

    static String[][] peminjamanBuku() {
        // String NIM, nama;
        // int jumlahBuku;
        
        System.out.print("Masukkan nama peminjam: ");
        nama = input.nextLine();
        System.out.print("Masukkan NIM peminjam: ");
        NIM = input.nextLine();

        System.out.print("Masukkan jumlah buku yang ingin dipinjam: ");
        jumlahBuku = input.nextInt();
        input.nextLine(); // Consume the newline character

        String[][] bukuPeminjaman = new String[jumlahBuku][10];

        for (int i = 0; i < jumlahBuku; i++) {
            System.out.println("\nMasukkan informasi buku ke-" + (i + 1));
            System.out.print("Masukkan nama buku: ");
            bukuPeminjaman[i][0] = input.nextLine();

            for (int j = 1; j <= 3; j++) {
                System.out.print("Lama meminjam (" + (j == 1 ? "DD" : (j == 2 ? "MM" : "YYYY")) + "): ");
                bukuPeminjaman[i][j] = input.nextLine();
            }

            for (int j = 4; j <= 6; j++) {
                System.out.print("Tanggal peminjaman (" + ((j - 4) % 3 == 0 ? "DD" : ((j - 4) % 3 == 1 ? "MM" : "YYYY")) + "): ");
                bukuPeminjaman[i][j] = input.nextLine();
            }
            System.out.println("===============================");
        }

        return bukuPeminjaman;
    }

    static int pengembalianBuku(String[][] bukuPeminjaman) {
        int totalDenda = 0;
        int hariTerlambat = 0;
        int bulanTerlambat = 0;
        int tahunTerlambat = 0;
    
        for (int i = 0; i < bukuPeminjaman.length; i++) {
            // System.out.print("Masukkan tanggal pengembalian buku ke-" + (i + 1) + " (DD/MM/YYYY): ");
            // String tanggalKembaliStr = input.nextLine();
    
            inputTanggalPengembalian(bukuPeminjaman, i);
            // Parsing tanggal pengembalian
            int tanggalKembali = Integer.parseInt(bukuPeminjaman[i][7]);
            int bulanKembali = Integer.parseInt(bukuPeminjaman[i][8]);
            int tahunKembali = Integer.parseInt(bukuPeminjaman[i][9]);
            // int tanggalKembali = Integer.parseInt(tanggalKembaliStr.substring(0, 2));
            // int bulanKembali = Integer.parseInt(tanggalKembaliStr.substring(3, 5));
            // int tahunKembali = Integer.parseInt(tanggalKembaliStr.substring(6, 10));
    
            // Parsing tanggal peminjaman
            int tanggalPinjam = Integer.parseInt(bukuPeminjaman[i][4]);
            int bulanPinjam = Integer.parseInt(bukuPeminjaman[i][5]);
            int tahunPinjam = Integer.parseInt(bukuPeminjaman[i][6]);
    
            // Menghitung keterlambatan
            hariTerlambat = tanggalKembali - tanggalPinjam;
            bulanTerlambat = bulanKembali - bulanPinjam;
            tahunTerlambat = tahunKembali - tahunPinjam;
    
            if (tahunTerlambat > 0 || bulanTerlambat > 0 || hariTerlambat > 0) {
                int totalHariTerlambat = tahunTerlambat * 365 + bulanTerlambat * 30 + hariTerlambat;
                int denda = totalHariTerlambat * 500;
                totalDenda += denda;
                System.out.println("Anda terkena denda keterlambatan sebesar: " + denda);
                System.out.println("\n=======INFORMASI LENGKAP=======");
                System.out.println("Buku ke-" + (i + 1) + ": " + bukuPeminjaman[i][0]);
                System.out.println("Total Denda: " + denda + "\n===============================");
            } else {
                System.out.print("\n=======INFORMASI LENGKAP======= \nBuku ke-" + (i + 1) + ": " + bukuPeminjaman[i][0] + "\nBuku Berhasil dikembalikan tanpa denda\n");
            }
        }
        System.out.println("\n=======INFORMASI DENDA KESLEURUHAN=======");
        System.out.println("Total Denda Keseluruhan: " + totalDenda);
        System.out.println("==========================================\n");
    
        return totalDenda;
    }

    static void inputTanggalPengembalian(String[][] bukuPeminjaman, int i) {
        System.out.println("Masukkan tanggal pengembalian buku ke-" + (i + 1));
        System.out.print("Tanggal : ");
        bukuPeminjaman[i][7] = input.nextLine(); // Tanggal
        System.out.print("Bulan : ");
        bukuPeminjaman[i][8] = input.nextLine(); // Bulan
        System.out.print("Tahun : ");
        bukuPeminjaman[i][9] = input.nextLine(); // Tahun
    }

    static void informasiLengkap(String[][] bukuPeminjaman) {
        int jumlahBuku = bukuPeminjaman.length;
        String jawaban;

        System.out.println("\n=======INFORMASI LENGKAP=======");
        System.out.println("NAMA : " + nama + "\nNIM : " + NIM + "\nJUMLAH BUKU : " + jumlahBuku + "\nTOTAL DENDA : " + totalDenda);
    
        if (totalDenda > 0) {
            System.out.println("\nTotal denda keseluruhan: " + totalDenda);
            System.out.print("Apakah anda ingin membayar denda? (ya/tidak): ");
            jawaban = input.next();
    
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
