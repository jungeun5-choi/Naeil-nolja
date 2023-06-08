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

        app.startApp();

//        Device device = new Device();
//        device.inputHotel();
//        device.display();
    }
}
