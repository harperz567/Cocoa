package cocoa.dto;

import cocoa.entity.Pet;
import cocoa.entity.PetDetail;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class PetDto extends Pet {
    // Animals' corresponding details
    private List<PetDetail> details = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
