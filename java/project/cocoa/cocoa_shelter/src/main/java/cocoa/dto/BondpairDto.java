package cocoa.dto;

import cocoa.entity.Bondpair;
import cocoa.entity.BondpairPet;
import lombok.Data;
import java.util.List;
/**
 * Data Transfer Object for Bondpair entities.
 * Extends the {@link cocoa.entity.Bondpair} class to include additional properties.
 */
@Data
public class BondpairDto extends Bondpair {

    /**
     * A list of pets associated with the bond pair.
     */
    private List<BondpairPet> bondpairPets;

    /**
     * The name of the category associated with the bond pair.
     */
    private String categoryName;
}
