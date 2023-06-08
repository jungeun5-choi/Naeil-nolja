import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Room room = new Room();
        Reservation reservation = new Reservation();
        Customer customer = new Customer();
=======
        Device device = new Device();
        device.inputHotel();
        device.showRoomList();
>>>>>>> select-woojin

        HotelReservationApp app = new HotelReservationApp(sc, hotel, room, reservation, customer);

        app.startApp();
    }
}
