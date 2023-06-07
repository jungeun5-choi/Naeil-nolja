import java.util.*;

public class Device {
    HashMap<String, Hotel> hotels = new HashMap<>();
    ArrayList<Room> allRooms = new ArrayList<>();

    public void inputHotel() {

        ArrayList<Room> ihgRooms = new ArrayList<>();
        ihgRooms.add(new Room(101, 30, 300000));
        ihgRooms.add(new Room(102, 40, 400000));
        ihgRooms.add(new Room(103, 40, 600000));
        ihgRooms.add(new Room(104, 20, 120000));
        Hotel ihg = new Hotel("인터컨티넨탈", ihgRooms, 0);

        ArrayList<Room> jwRooms = new ArrayList<>();

        jwRooms.add(new Room(101, 30, 350000));
        jwRooms.add(new Room(102, 40, 450000));
        jwRooms.add(new Room(103, 30, 250000));
        Hotel jw = new Hotel("JW 메리어트", jwRooms, 0);


        hotels.put(jw.getHotelName(), jw);
        hotels.put(ihg.getHotelName(), ihg);

    }

    public void setup() {
        //조우진 테스트용
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--------------------------------");
            System.out.println("\"내일 놀자에 오신 것을 환영합니다!\"");
            System.out.println("[호텔 목록]");
            System.out.println(hotels.keySet());

            System.out.println("조회할 방법을 선택하세요.");
            System.out.println(hotels.get("JW 메리어트").getCheapestRoom().toString());
            System.out.println("1. 호텔 전체 객실 조회   2. 호텔별 최저가 검색 3. 호텔 이름으로 검색");
            System.out.println("--------------------------------");
            int input = sc.nextInt();
            sc.nextLine();
            if (input == 1) {
                selectAll();
            } else if (input == 2) {

            } else if (input == 3) {
                System.out.println("호텔 이름을 입력하세요.");
                String hotelName = sc.nextLine();
                while(true) {
                    Hotel selectHotel = selectHotelName(hotelName);
                    System.out.println("1. 예약하기 2.최저가순으로 보기 3.최고가순으로 보기");
                    System.out.println("--------------------------------");
                    input = sc.nextInt();
                    sc.nextLine();
                    if (input == 1) {
                        //예약하는 메서드
                        break;
                    } else if (input == 2) {
                        selectHotel.sortCheap();
                    } else if (input == 3) {
                        selectHotel.sortExpansive();
                    }
                }
            }
        }

    }

    public Hotel selectHotelName(String hotelName) {

        if (hotels.containsKey(hotelName)) {
            hotels.get(hotelName).showRooms();
            return hotels.get(hotelName);
        } else {
            System.out.println("해당 이름의 호텔이 존재하지 않습니다.");
        }
        return null;
    }

    public void selectAll() {
        for(Hotel hotel : hotels.values()){
            hotel.showRooms();
        }
    }
}
