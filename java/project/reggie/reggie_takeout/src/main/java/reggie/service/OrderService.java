package reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import reggie.entity.Orders;

public interface OrderService extends IService<Orders> {

    /**
     * Place order
     * @param orders
     */
    public void submit(Orders orders);
}
