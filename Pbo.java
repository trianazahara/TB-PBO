import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

//Subkelas dari kelas warnet 
public class Pbo extends Warnet 
{
    //declarasi scanner 
    static Scanner input = new Scanner (System.in);
    static Connection conn;
    private static String url;

    public static void main(String[] args) throws Exception {
        
        Login Admin = new Login();
        Admin.login();

        String pilihan;
        boolean next = true;

        //Koneksi database 
        String url = "jdbc:mysql://localhost:3306/badminton";

        //exception
        try{

            //koneksi database 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");

            datestring d = new datestring();

            //perulangan 
            while (next) {
                d.set();
                d.date();
                System.out.println("\n\n-------------------------------------");
                System.out.println("------------- Badminton ------------- ");
                System.out.println("1. Lihat Data pelanggan");
                System.out.println("2. Tambah pelanggan");
                System.out.println("3. Ubah pelanggan");
                System.out.println("4. Hapus pelanggan");

                System.out.print("\nInputkan Pilihan: ");
                pilihan = input.next();

                //Percabangan 
                switch (pilihan){
                    case "1":
                        lihatdata();
                        break;
                    
                    case "2":
                        tambahdata();
                        break;

                    case "3":
                        updatedata();
                        break;

                    case "4":
                        hapusdata();
                        break;

                    default:
                        System.out.println("Input tidak sesuai");
                }
            }
            System.out.println("Program Selesai");

        } catch (SQLException e) {
            System.err.println("Tidak Berhasil Koneksi");
        }
        catch (InputMismatchException n) {
            System.out.println("Inputan Salah!!!");
        }
    }

    //method yang di extend 
    public static void tambahdata() throws SQLException{
        System.out.println("\n-------------------------------------");
        System.out.println("------- Input Data Pelanggan --------");

        //Exception
        try{
            System.out.print("ID Pelanggan\t : ");
            id_pelanggan = input.nextInt();
            
            System.out.print("Nama Pelanggan\t : ");
            nama = input.next();

            System.out.print("No Lapangan\t : Lapangan ");
            lapangan = input.next();

            System.out.print("Durasi bermain\t : ");
            durasi = input.nextInt();

            //proses matematika
            totalharga = durasi * 40000;
            System.out.print("Total Harga\t : Rp" + totalharga);
            System.out.println();

            //syntax untuk tambah data pada database 
            String sql = "INSERT INTO sewaLapangan (id_pelanggan, nama_pelanggan, no_lapangan, durasi, total_harga) VALUES ('" + id_pelanggan + "','" + nama + "','" + lapangan +"','" + durasi +"','" + totalharga + "')";
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("\ndata Berhasil Ditambahkan!!!\n\n");
        } catch (SQLException e ){
            System.out.println(" Data tidak Berhasil Ditambahkan!!!");
        } catch (InputMismatchException e ){
            System.out.println(" Error!!!");
        } catch (NullPointerException n ){
            System.out.println(" Data tidak Berhasil Ditambahkan!!!");
        }
    }

    //Method yang di extend 
    public static void lihatdata() throws SQLException{
        System.out.println("\n-------------------------------------");
        System.out.println("---------- Data Pelanggan -----------");
        //syntax untuk lihatdata() dari database
        String sql = "SELECT * FROM sewaLapangan";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //perulangan
        while(result.next()){
            System.out.println();
            System.out.println("Id Pelanggan\t: " + result.getInt("id_pelanggan"));
            System.out.println("Nama Pelanggan\t: " + result.getString("nama_pelanggan"));
            System.out.println("No Lapangan\t: Lapangan " + result.getInt("no_lapangan")  );
            System.out.println("Durasi bermain\t: " + result.getInt("durasi") + " jam");
            System.out.println("Total harga\t: " + "Rp " + result.getInt("total_harga"));
        }
    }

    //Method yang di extend 
    public static void updatedata() throws SQLException {
        Scanner input = new Scanner (System.in);

        //exception 
        try {
            lihatdata();
            System.out.println("\n-------------------------------------");
            System.out.println("---------- Ubah Pelanggan -----------");
            System.out.print("Pilih Id Pelanggan : ");
            Integer id_pelanggan = Integer.parseInt(input.nextLine());

            //syntax untuk memilih data yang akan diubah
            String sql = "SELECT * FROM sewaLapangan WHERE id_pelanggan = " + id_pelanggan;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //percabangan 
            if (result.next()){
                
                System.out.print("Nama [" + result.getString("nama_pelanggan")+"]\t   : ");
                String nama = input.next();

                System.out.print("Durasi [" + result.getDouble("durasi") + "]\t   : ");
                Double durasi = input.nextDouble();

                Double totalharga = durasi* 40000;
                System.out.print("Total Harga [" + result.getInt("total_harga")+"]: " + totalharga);
                

                //syntax untuk menyimpan update data
                sql = "UPDATE sewaLapangan SET nama_pelanggan ='" + nama + "', durasi='" + durasi + "', total_harga='" + totalharga + "'WHERE id_pelanggan='" + id_pelanggan + "'";
                if (statement.executeUpdate(sql)>0) {
                    System.out.println ("\n\nData Diperbarui (Id Pelanggan " + id_pelanggan + ")");
                }
            }
        } catch (SQLException e) {
            System.out.println(" terjadi kesalahan dalam mengedit data");
            System.out.println(e.getMessage());
        }
    }

    //Method yang di extend
    public static void hapusdata() throws SQLException {
        Scanner input = new Scanner(System.in);

        //Exception
        try{
            lihatdata();    
            System.out.print("\n\nId Pelanggan\t : ");
            Integer id_pelanggan = Integer.parseInt(input.nextLine());

            //syntax untuk menghapus data 
            String sql = "DELETE FROM sewaLapangan WHERE id_pelanggan = " + id_pelanggan;

            Statement statement = conn.createStatement();
            if (statement.executeUpdate(sql)>0){
                System.out.println("\nData Berhasil Dihapus (id_pelanggan" + id_pelanggan + ")");
            }
        } catch (InputMismatchException e){
            System.out.println("terjadi kesalahan!!!");
        } catch (SQLException e){
            System.out.println("terjadi kesalahan dalam menghapus data barang");
        }
    }

}
