import java.time.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        Hotel hotel = new Hotel();
        Reservation reservation = new Reservation();
        Room room = new Room();
        HotelReservationApp app = new HotelReservationApp(sc, hotel, room, reservation, customer);

        ZoneOffset seoul = ZoneOffset.of("+09:00");
        System.out.println(ZonedDateTime.now(seoul).withNano(0));

        app.startApp();

    }
}
