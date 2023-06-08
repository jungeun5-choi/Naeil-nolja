import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Room room = new Room();
        Reservation reservation = new Reservation();
        Customer customer = new Customer();


        HotelReservationApp app = new HotelReservationApp(sc, hotel, room, reservation, customer);

        app.startApp();
    }
}
