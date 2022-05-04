/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gruda
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Socket s = null;
        DataOutputStream output;
        DataInputStream input;
        Scanner sc;
        String barang;
        String total;
        String diskon;
        int item;

        try {
            // IP Server tujuan dan port-nya
            s = new Socket("127.0.0.1", 98);

            // menampilkan data barang
            System.out.println("-------------------------");
            System.out.println("|      Data Barang      |");
            System.out.println("-------------------------");
            System.out.println("|   Barang  |   Harga   |");
            System.out.println("-------------------------");
            System.out.println("|    Obat   | Rp. 10.000|");
            System.out.println("|    Roti   | Rp.  5.000|");
            System.out.println("|    Aqua   | Rp.  3.000|");
            System.out.println("-------------------------");

            // mengambil inputan user
            sc = new Scanner(System.in);
            System.out.println("Nama barang yang dibeli:");
            barang = sc.nextLine();
            System.out.println("Jumlah item yang dibeli:");
            item = sc.nextInt();

            // mengirim data ke server
            output = new DataOutputStream(s.getOutputStream());
            output.writeUTF(barang);
            output.writeInt(item);

            // menerima data dari server
            input = new DataInputStream(s.getInputStream());
            total = input.readUTF();
            diskon = input.readUTF();

            // menampilkan total
            System.out.println("\n--------------------------------------------");
            System.out.println("Total Harga                 : Rp. " + total);
            System.out.println("Selamat anda mendapatkan diskon sebesar 20%");
            System.out.println("Total yang harus dibayar    : Rp. " + diskon);
            System.out.println("--------------------------------------------");

            output.close();
            input.close();
            s.close();
        } catch (IOException e) {

        }
    }
}
