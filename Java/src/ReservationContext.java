import java.time.LocalDateTime;
import java.util.*;

public class ReservationContext {

    Reservation reservation= new Reservation();
    Room room= new Room();
    ReservationContext(){

        ReservationItems();
    }
    static List<Reservation> reservationList =new ArrayList<>(); //전체 예약목록
    static List<Reservation> reservation1 =new ArrayList<>();
    static List<Reservation> reservation2 =new ArrayList<>();
    static List<Reservation> reservation3 =new ArrayList<>();

    static Map<UUID, List<Reservation>> reservationNumberMap = new HashMap<UUID, List<Reservation>>();
    public void ReservationItems(){
        Room roomItme1 = new Room(100,52,1000000);
        Room roomItme2 = new Room(200,88,2000000);
        Room roomItme3 = new Room(300,40,3000000);
        reservation.setReservationDate(LocalDateTime.now());

        UUID number1 = reservation.setReservationNumber(UUID.randomUUID());
        reservation1.add(new Reservation(roomItme1,"서예린","010-1234-1234",reservation.getReservationDate(),reservation.getReservationNumber()));
        reservationNumberMap.put(number1,reservation1);

        UUID number2=reservation.setReservationNumber(UUID.randomUUID());
        reservation2.add(new Reservation(roomItme2,"홍길동","010-5481-8618",reservation.getReservationDate(),reservation.getReservationNumber()));
        reservationNumberMap.put(number2,reservation2);

        UUID number3=reservation.setReservationNumber(UUID.randomUUID());
        reservation.setReservationNumber(UUID.randomUUID());
        reservation3.add(new Reservation(roomItme3,"김가나","010-1361-5489",reservation.getReservationDate(),reservation.getReservationNumber()));
        reservationNumberMap.put(number3,reservation3);



    }

    public static void selectAllReservation(){ //전체 예약리스트 출력
//        for (int i = 0; i <reservationList.size() ; i++) {
//            System.out.println("["+(i+1)+"] "+reservationList.get(i));
//        }
        reservationNumberMap.forEach((key, value) -> {
            System.out.println(value);
        });
    }

    public void selectReservationUseNumber(UUID reservationNumber){ // 예약번호로 찾는 리스트

            System.out.println("예약번호:"+reservationNumber+", 예약정보:"+ reservationNumberMap.get(reservationNumber));

    }



}
