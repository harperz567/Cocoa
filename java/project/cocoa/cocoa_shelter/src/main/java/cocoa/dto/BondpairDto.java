package cocoa.dto;

import cocoa.entity.Bondpair;
import cocoa.entity.BondpairPet;
import lombok.Data;
import java.util.List;

@Data
public class BondpairDto extends Bondpair {

    private List<BondpairPet> bondpairPets;

    private String categoryName;
}
