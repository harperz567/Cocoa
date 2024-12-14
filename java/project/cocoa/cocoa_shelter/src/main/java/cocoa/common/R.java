package cocoa.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;
/**
 * A generic response wrapper class for standardizing API responses.
 *
 * @param <T> The type of data to be returned in the response.
 */
@Data
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap();
    /**
     * Creates a success response.
     *
     * @param object The data to include in the response.
     * @param <T>    The type of the data.
     * @return An instance of {@code R<T>} with a success code, message, and the provided data.
     */
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.msg = object.toString();
        r.code = 1;
        return r;
    }
    /**
     * Creates an error response.
     *
     * @param msg A message describing the error.
     * @param <T> The type of the data (null in error cases).
     * @return An instance of {@code R<T>} with an error code and message.
     */
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }
    /**
     * Adds a dynamic key-value pair to the response map.
     *
     * This allows for adding extra data to the response without modifying the main structure.
     *
     * @param key   The key for the data.
     * @param value The value associated with the key.
     * @return The current instance of {@code R<T>}, enabling method chaining.
     */
    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
