import java.util.Scanner;

public class PeminjamanPengembalian {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama peminjam: ");
        String nama = input.nextLine();
        System.out.print("Masukkan NIM peminjam: ");
        String NIM = input.nextLine();
        System.out.print("Masukkan jumlah buku: ");
        int jumlahBuku = input.nextInt();
        //LAMA ANDA MEMINJAM BUKU
        String[] namaBuku = new String[jumlahBuku];
        int[] hariLamaPnjm = new int[jumlahBuku];
        int[] blnLamaPnjm = new int[jumlahBuku];
        int[] thnLamaPnjm = new int[jumlahBuku];
        int[] tglpnjm = new int[jumlahBuku];
        int[] blnpnjm = new int[jumlahBuku];
        int[] thnpnjm = new int[jumlahBuku];
        int[] tglkmbl = new int[jumlahBuku];
        int[] blnkmbl = new int[jumlahBuku];
        int[] thnkmbl = new int[jumlahBuku];

        int totalDenda = 0;

        for (int i = 0; i < jumlahBuku; i++) {
            System.out.print("\nMasukkan nama buku ke-" + (i+1) + ": ");
            namaBuku[i] = input.next();
            System.out.print("Lama meminjam (DD)");
            hariLamaPnjm[i] = input.nextInt();
            System.out.print("Lama meminjam (MM)");
            blnLamaPnjm[i] = input.nextInt();
            System.out.print("Lama meminjam (YYYY)");
            thnLamaPnjm[i] = input.nextInt();
            System.out.print("Tanggal peminjaman (DD)");
            tglpnjm[i] = input.nextInt();
            System.out.print("Tanggal peminjaman (MM)");
            blnpnjm[i] = input.nextInt();
            System.out.print("Tanggal peminjaman (YYYY)");
            thnpnjm[i] = input.nextInt();
            System.out.print("Tanggal pengembalian (DD)");
            tglkmbl[i] = input.nextInt();
            System.out.print("Tanggal pengembalian (MM)");
            blnkmbl[i] = input.nextInt();
            System.out.print("Tanggal pengembalian (YYYY)");
            thnkmbl[i] = input.nextInt();
            input.nextLine();

            int hariTerlambat = tglkmbl[i] - (tglpnjm[i] + hariLamaPnjm[i]);
            int bulanTerlambat =  blnkmbl[i] - (blnpnjm[i] + blnLamaPnjm[i]);
            int tahunTerlambat = thnkmbl[i] - (thnpnjm[i] + thnLamaPnjm[i]);

            if (tahunTerlambat > 0 || bulanTerlambat > 0 || hariTerlambat > 0) {
                int totalHariTerlambat = tahunTerlambat * 365 + bulanTerlambat * 30 + hariTerlambat;
                int denda = totalHariTerlambat * 500 * jumlahBuku;
                totalDenda += denda;
                System.out.println("Anda terkena denda keterlambatan sebesar: " + denda);
                System.out.println("\n=======INFORMASI LENGKAP=======");
                System.out.println("Nama Peminjam: " + nama + "\nNIM Peminjam: " + NIM + "\nBuku Yang Dipinjam: " + namaBuku + 
                                    "\nJumlah Buku Yang Dipinjam: " + jumlahBuku + "\nTotal Denda: " + denda);
                System.out.print("\nApakah anda ingin membayar denda? (ya/tidak): ");
                String jawaban = input.next();

                if (jawaban.equalsIgnoreCase("ya")) {
                    System.out.print("Masukkan nominal pembayaran anda: ");
                    int bayar = input.nextInt();
            
                    while (bayar < denda){
                        System.out.println("Nominal pembayaran anda tidak mencukupi, silahkan masukkan nominal yang mencukupi");
                        System.out.print("Masukkan nominal pembayaran anda: ");
                        bayar = input.nextInt();
                    }
                    if (bayar > denda) {
                        double kembalian = bayar - denda;
                        System.out.println("Kembalian anda adalah: " + kembalian);
                        System.out.println("Buku berhasil dikembalikan");
                    } else {
                        System.out.print("Uang anda pas, ");
                        System.out.print("Buku berhasil dikembalikan");
                    }
                } else if (jawaban.equalsIgnoreCase("tidak")) {
                    System.out.println("Anda memilih untuk tidak membayar denda \n" +nama+ "berhutang denda sebesar: " +denda);
                    System.out.println("Buku berhasil dikembalikan");
                } else {
                    System.out.println("Input tidak valid, Buku berhasil dikembalikan tanpa pembayaran");
                }
            } else {
                System.out.print("\n=======INFORMASI LENGKAP======= \nNama Peminjam: " + nama + "\nNIM Peminjam: " + NIM +
                "\nBuku Yang Dipinjam: " + namaBuku + "\nJumlah Buku Yang Dipinjam: " + jumlahBuku + "\nBuku Berhasil Dikembalikan");
            }
        }
    }
}