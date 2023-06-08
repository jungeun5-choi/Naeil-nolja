import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ReservationContext reservationContext=new ReservationContext();

        ReservationContext.selectAllReservation();
        Scanner scanner = new Scanner(System.in);
        String codeNumber = scanner.next();
        reservationContext.selectReservationUseNumber(UUID.fromString(codeNumber));

    }
}
