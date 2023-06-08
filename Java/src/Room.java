public class Room implements Comparable<Room>{

    private int roomNumber; // 번호
    // private int roomSize; // 크기


    private RoomSize roomSize;

    private int roomPrice; // 가격

    /* 생성자 */
    Room() {}
    Room(int roomNumber, RoomSize roomSize, int roomPrice) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.roomPrice = roomPrice;
    }

    /* getter */
    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    @Override
    public String toString() {
        return "번호 : " + roomNumber + ", 크기 : " + roomSize + ", 가격 : " + roomPrice;
    }
    public void showIntroduce() {
        System.out.printf("객실 번호 : %-4s | 객실 크기 : %-8s | 가격 : %,7d원\n", getRoomNumber(), getRoomSize(), getRoomPrice());

    }

    //Collections.sort()메소드는 객체를 정렬할 때
    //해당 객체의 Comparable을 구현한 compareTo()메소드를 참조하여 정렬순서를 결정한다.
    //따라서 정렬할 객체가 Comparable interface를 구현하고, compareTo()메소드 안에 정렬기준을 정의해준다면
    //Collections.sort()메서드를 사용하여 객체를 사용자 정의형태로 정렬할 수 있다.
    @Override
    public int compareTo(Room room){
        if (room.getRoomPrice() < this.roomPrice){
            return 1;
        }else if(room.getRoomPrice() > this.roomPrice){
            return -1;
        }
        return 0;
    }
}
