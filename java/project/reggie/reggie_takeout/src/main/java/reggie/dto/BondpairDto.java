package reggie.dto;

import reggie.entity.Bondpair;
import reggie.entity.BondpairPet;
import lombok.Data;
import java.util.List;

@Data
public class BondpairDto extends Bondpair {

    private List<BondpairPet> bondpairPetes;

    private String categoryName;
}
