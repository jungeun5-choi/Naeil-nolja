import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ReservationContext {

    Reservation reservation= new Reservation();
    Room room= new Room();
    ReservationContext(){

        ReservationItems();
    }
    static List<Reservation> reservationList =new ArrayList<>(); //전체 예약목록
    static Map<UUID, Reservation> reservationNumberMap = new HashMap<UUID, Reservation>();
    public void ReservationItems(){
        Room roomItme1 = new Room(100,RoomSize.Standard,1000000);
        Room roomItme2 = new Room(200,RoomSize.Twin,2000000);
        Room roomItme3 = new Room(300,RoomSize.Suite,3000000);
        Room roomItme4 = new Room(400,RoomSize.Family,4000000);
        reservation.setReservationDate(LocalDate.now());

        UUID number1 = reservation.setReservationNumber(UUID.randomUUID());
        Reservation reservation1 = new Reservation(roomItme1,"서예린","010-1234-1234",reservation.getReservationDate(),reservation.getReservationNumber());
        reservationNumberMap.put(number1,reservation1);

        UUID number2=reservation.setReservationNumber(UUID.randomUUID());
        Reservation reservation2 = new Reservation(roomItme2,"홍길동","010-5481-8618",reservation.getReservationDate(),reservation.getReservationNumber());
        reservationNumberMap.put(number2,reservation2);

        UUID number3=reservation.setReservationNumber(UUID.randomUUID());
        Reservation reservation3 = new Reservation(roomItme3,"김가나","010-1361-5489",reservation.getReservationDate(),reservation.getReservationNumber());
        reservationNumberMap.put(number3,reservation3);

        UUID number4=reservation.setReservationNumber(UUID.randomUUID());
        Reservation reservation4 = new Reservation(roomItme4,"서예린","010-8688-7848",reservation.getReservationDate(),reservation.getReservationNumber());
        reservationNumberMap.put(number4,reservation4);


    }

    public static void selectAllReservation(){ //전체 예약리스트 출력
        reservationNumberMap.forEach((key, value) -> {
            System.out.println(value);
        });
    }

    public void selectReservationUseNumber(UUID reservationNumber){ // 예약번호로 찾는 리스트

            System.out.println("예약번호:"+reservationNumber+", 예약정보:"+ reservationNumberMap.get(reservationNumber));

    }

    public void selectReservationUseName(String customerName){ // 고객이름으로 찾는 리스트 (보류)

//        System.out.println(reservation3.contains(customerName));

    }
    public void selectReservationRooms(){
        for(Reservation reservation :reservationNumberMap.values()){
            System.out.println(reservation.getRoom());
        }
    }

}
