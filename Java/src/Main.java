import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        Device device = new Device();
        Hotel hotel = new Hotel();
        Reservation reservation = new Reservation();
        ReservationContext reservationContext = new ReservationContext();
        Reserve reserve = new Reserve();
        Room room = new Room();

        HotelReservationApp app = new HotelReservationApp(sc, hotel, room, reservation, customer);

        app.startApp();
    }
}
