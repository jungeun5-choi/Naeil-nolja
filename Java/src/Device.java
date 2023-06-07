import java.util.*;

public class Device {
    private ArrayList<Room> rooms = new ArrayList<>();
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_BLUE = "\u001B[34m";
    private Hotel hotel;
    public void inputHotel() {

        rooms.add(new Room(101, RoomSize.Standard, 62000));
        rooms.add(new Room(102, RoomSize.Standard, 74000));
        rooms.add(new Room(201, RoomSize.Twin, 80000));
        rooms.add(new Room(202, RoomSize.Twin, 76000));
        rooms.add(new Room(301, RoomSize.Delux, 90000));
        rooms.add(new Room(402, RoomSize.Family, 110000));
        rooms.add(new Room(502, RoomSize.Suite, 150000));

        hotel = new Hotel("스파르타 호텔", rooms, 0);


    }

    public void setup() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------------------");
            System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
            System.out.println(FONT_GREEN+"현재 투숙가능한 객실은 "+FONT_BLUE+rooms.size()+FONT_GREEN+"개 입니다."+FONT_RESET);
            System.out.println("조회할 방법을 선택하세요.");
            System.out.println("1. 전체 객실 조회   2. 최저가 순 조회 3. 최고가 순 조회");
            System.out.println("--------------------------------");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1: {
                    selectAll();
                    break;
                }
                case 2: {
                    hotel.sortCheap();
                    selectAll();
                    break;
                }
                case 3: {
                    hotel.sortExpansive();
                    selectAll();
                    break;
                }
            }
            System.out.println("1. 예약하기 2. 뒤로가기");
            input = sc.nextInt();
            if (input == 1) {
                System.out.println("예약할 호실번호를 입력하세요.");
                input = sc.nextInt();

            }

        }
    }
    public void selectAll() {
        hotel.showRooms();
    }
}
