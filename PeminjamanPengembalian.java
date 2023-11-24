import java.util.Scanner;

public class PeminjamanPengembalian {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama peminjam: ");
        String nama = input.nextLine();
        System.out.print("Masukkan nama buku: ");
        String namaBuku = input.nextLine();
        System.out.print("Masukkan NIM peminjam: ");
        String NIM = input.nextLine();
        System.out.print("Masukkan jumlah buku: ");
        int jumlahBuku = input.nextInt();
        //LAMA ANDA MEMINJAM BUKU
        System.out.print("Lama meminjam (DD): ");
        int hariLamaPnjm = input.nextInt();
        System.out.print("Lama meminjam (MM): ");
        int blnLamaPnjm = input.nextInt();
        System.out.print("Lama meminjam (YYYY): ");
        int thnLamaPnjm = input.nextInt();
        //TANGGAL PEMINJAMAN BUKU
        System.out.print("Tanggal peminjaman (DD): ");
        int tglpnjm = input.nextInt();
        System.out.print("Tanggal pengembalian (MM): ");
        int blnpnjm = input.nextInt();
        System.out.print("Tanggal pengembalian (YYYY): ");
        int thnpnjm = input.nextInt();
        //TANGGAL PENGEMBALIAN BUKU 
        System.out.print("Tanggal pengembalian (DD): ");
        int tglkmbl = input.nextInt();
        System.out.print("Tanggal pengembalian (MM): ");
        int blnkmbl = input.nextInt();
        System.out.print("Tanggal pengembalian (YYYY): ");
        int thnkmbl = input.nextInt();

        //int lamapnjm = thnLamaPnjm + blnLamaPnjm + hariLamaPnjm;
        int hariTerlambat = tglkmbl - (tglpnjm + hariLamaPnjm);
        int bulanTerlambat =  blnkmbl - (blnpnjm + blnLamaPnjm);
        int tahunTerlambat = thnkmbl - (thnpnjm + thnLamaPnjm);

        if (tahunTerlambat > 0 || bulanTerlambat > 0 || hariTerlambat > 0) {
            int totalHariTerlambat = tahunTerlambat * 365 + bulanTerlambat * 30 + hariTerlambat;
            int denda = totalHariTerlambat * 500 * jumlahBuku;
            System.out.println("Anda terkena denda keterlambatan sebesar: " + denda);
            System.out.println("\n=======INFORMASI LENGKAP=======");
            System.out.println("Nama Peminjam: " + nama + "\nNIM Peminjam: " + NIM + "\nBuku Yang Dipinjam: " + namaBuku + 
                                "\nJumlah Buku Yang Dipinjam: " + jumlahBuku + "\nTotal Denda: " + denda);
        } else {
            System.out.println("Buku berhasil dikembalikan");
        }
    }
}