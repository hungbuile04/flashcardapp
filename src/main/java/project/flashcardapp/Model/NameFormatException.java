package project.flashcardapp.Model;

/**
 * Exception thrown after bad user's input during category creation
 *
 * @author Bartlomiej Gladys
 * @Date: 04/11/2018
 * @version: 1.0
 */
//Lớp này blh đã xoá liên kết với lớp khác cho code dễ đọc. Chưa dùng để làm j
public class NameFormatException extends Exception {

    /**
     * Exception constructor
     *
     * @param message to display on client side
     */
    public NameFormatException(String message) {
        super(message);
    }
}
