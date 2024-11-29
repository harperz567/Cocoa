package reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import reggie.dto.BondpairDto;
import reggie.entity.Bondpair;

public interface BondpairService extends IService<Bondpair> {

    /**
     * Add new family, save animal and animal family relations
     * @param bondpairDto
     */
    public void saveWithPet(BondpairDto bondpairDto);

    /**
     * Delete a family, and also animal relations data
     * @param ids
     */
    public void removeWithPet(List<Long> ids);

    public BondpairDto getBondpairData(Long id);

}
