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
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket ss;
        Socket s;
        DataInputStream input;
        DataOutputStream output;
        String barang;
        int item;
        int harga = 0;
        int total;
        int diskon;

        try {
            // membuat server socket dengan parameter port
            ss = new ServerSocket(98);
            System.out.println("Server Kasir Ready ...");
            System.out.println("=========================");

            // menghubungkan s dengan ss
            s = ss.accept();
            System.out.println("\nAda pesanan masuk ...");

            // menerima input dari client
            input = new DataInputStream(s.getInputStream());
            barang = input.readUTF();
            item = input.readInt();

            // menampilkan input dari client
            System.out.println("\nNama barang yang dibeli : " + barang);
            System.out.println("Jumlah item yang dibeli : " + item);

            // cek barang
            if ("obat".equals(barang) || "Obat".equals(barang)) {
                harga = 10000;
            } else if ("roti".equals(barang) || "Roti".equals(barang)) {
                harga = 5000;
            } else if ("aqua".equals(barang) || "Aqua".equals(barang)) {
                harga = 3000;
            }
            System.out.println("Harga per item          : Rp. " + harga);

            // hitung total dan diskon
            total = harga * item;
            diskon = (total * 80) / 100;
            System.out.println("\n----------------------------------------");
            System.out.println("Total Harga             : Rp. " + total);
            System.out.println("Harga setelah diskon    : Rp. " + diskon);
            System.out.println("-----------------------------------------");

            // mengirim data ke client
            output = new DataOutputStream(s.getOutputStream());
            output.writeUTF(String.valueOf(total));
            output.writeUTF(String.valueOf(diskon));

            input.close();
            output.close();
            s.close();
            ss.close();
        } catch (IOException e) {
        }
    }
}
