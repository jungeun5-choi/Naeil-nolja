import java.util.Scanner;
import java.util.UUID;

public class HotelReservationApp {
    Scanner sc;
    Customer customer;
    Device device;
    Hotel hotel;
    Reservation reservation;
    ReservationContext reservationContext;
    Reserve reserve;
    Room room;
    RoomSize roomSize;

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
        System.out.print("\n");
        System.out.println("==========스파르타 호텔==========");
        System.out.printf("%-10s %-10s %s\n","1. 고객", "2. 호텔","3. 종료");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMode = sc.nextInt();
        switch(selectMode){
            case 1 : // 고객모드
                customerMode();
                break;
            case 2 : // 호텔모드
                hotelMode();
                break;
            case 3 : // 종료
                System.out.println("프로그램을 종료합니다.");
                break;
        }
    }

    // 1. 고객 모드
    public void customerMode(){
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("스파르타 호텔에 오신것을 환영합니다.");
        System.out.println("1. 객실 조회");
        System.out.println("2. 예약 하기");
        System.out.println("3. 예약 조회");
        System.out.println("4. 예약 취소");
        System.out.println("5. 돌아 가기");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 객실 조회

                break;
            case 2 : // 예약 하기
                break;
            case 3 : // 예약 조회
                System.out.print("예약번호를 입력하시오 : ");
                //uuid 입력 후 맞으면
//                reserve.viewMyReservation(new UUID());
                break;
            case 4 : // 예약 취소
                break;
            case 5 : // 모드선택으로 돌아가기
                selectModeInput();
                break;
        }
    }

    // 1-1. 객실 목록 조회

    // 1-2-1. 이름, 번호, 소지금 입력
    public String enterNameInput(){
        return sc.next();
    }
    public String enterPhoneNumberInput(){
        return sc.next();
    }
    public int enterMoneyInput(){
        return sc.nextInt();
    }

    // 1-2. 객실 예약하기
    public void reserveRoom(){
        System.out.println("이름을 입력하시오 : ");
        String name = enterNameInput();
        System.out.println("번호를 입력하시오 : ");
        String phoneNumber = enterPhoneNumberInput();
        System.out.println("소지금을 입력하시오 : ");
        int money = enterMoneyInput();
        System.out.println("===============================");
        reserve.viewAllReservation();
        System.out.println("객실을 선택하시오");
        reserve.createReservation(room, customer);
    }

    // 1-3. 객실 예약조회

    // 1-4. 객실 예약취소

    // 2. 호텔 모드
    public void hotelMode(){
        System.out.print("\n");
        System.out.println("관리자 비밀번호를 입력하세요.");
        // 비밀번호가 같다면 관리자모드로 진입
        System.out.print("\n");
        System.out.println("===============================");
        System.out.printf("%-8s %-8s %s\n","1. 보유자산", "2. 예약목록", "3. 돌아가기");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 보유자산 조회
                hotelAsset();
                break;
            case 2 : // 예약목록 조회
                reserve.viewAllReservation();
                break;
            case 3 : // 돌아가기
                selectModeInput();
                break;
        }
    }

    // 2-1 보유자산 조회
    public void hotelAsset(){
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("스파르타 호텔의 총 자산은 : " + hotel.getAsset() + " 입니다.");
        System.out.println("===============================");
        System.out.printf("%-10s %-10s\n","1. 돌아가기", "2. 종료");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 돌아가기
                hotelMode();
                break;
            case 2 : // 종료
                System.out.println("프로그램을 종료합니다.");
                break;
        }
    }
    // 2-2 예약목록 조회

    // 2-3 남은 객실 조회?
}
