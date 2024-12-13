package cocoa.dto;

import cocoa.entity.Pet;
import cocoa.entity.PetDetail;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Transfer Object for Pet entities.
 * Extends the {@link cocoa.entity.Pet} class to include additional properties.
 */
@Data
public class PetDto extends Pet {

    /**
     * A list of detailed information about the pet.
     */
    private List<PetDetail> details = new ArrayList<>();

    /**
     * The name of the category the pet belongs to.
     */
    private String categoryName;

    /**
     * The number of copies of the pet (if applicable).
     */
    private Integer copies;
}
