public class Room {

    private int roomNumber; // 번호
    // private int roomSize; // 크기

    public enum RoomSize {
        Standard, Twin,
        Delux, Family, Suite,
    }
    private RoomSize roomSize;

    private int roomPrice; // 가격

    /* 생성자 */
    Room() {}
    Room(int roomNumber, int roomSize, int roomPrice) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.roomPrice = roomPrice;
    }

    /* getter */
    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public int getRoomPrice() {
        return roomPrice;
    }
}
