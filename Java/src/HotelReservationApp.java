import java.util.Scanner;

public class HotelReservationApp {
    Scanner sc;
    Hotel hotel;
    Room room;
    Reservation reservation;
    Customer customer;

    public HotelReservationApp(Scanner sc, Hotel hotel, Room room, Reservation reservation, Customer customer) {
        this.sc = sc;
        this.hotel = hotel;
        this.room = room;
        this.reservation = reservation;
        this.customer = customer;
    }
    /* 메서드 */

    // 최초 진입점
    public void startApp(){
        selectModeInput();
    }

    // 모드 선택
    // 고객, 호텔
    public void selectModeInput(){
        System.out.println("모드를 선택해주세요.");
        System.out.printf("%-10s %s\n","1. 고객", "2. 호텔");
        int selectMode = sc.nextInt();
        switch(selectMode){
            case 1 : // 고객모드
                customerMode();
                break;
            case 2 : // 호텔모드
                hotelMode();
                break;
        }
    }

    // 1. 고객 모드
    public void customerMode(){
        System.out.println("스파르타 호텔에 오신것을 환영합니다.");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 객실 목록 조회

            case 2 : // 예약하기

            case 3 : // 예약조회

            case 4 : // 예약취소

        }
    }

    // 1-1. 객실 목록 조회

    // 1-2. 객실 예약하기

    // 1-3. 객실 예약조회

    // 1-4. 객실 예약취소

    // 2. 호텔 모드
    public void hotelMode(){

    }

    // 2-1 예약목록 조회

    // 2-2 보유자산 조회

    // 2-3 남은 객실 조회?
}
