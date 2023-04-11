package commandpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a broker that can take and place orders.
 */
public class Broker {
  private List<Order> orderList = new ArrayList<Order>();

  /**
   * Takes an order and adds it to the broker's list of orders.
   *
   * @param order the order to take
   */
  public void takeOrder(Order order) {
    orderList.add(order);
  }

  /**
   * Places all orders in the broker's list of orders by executing them and then clears the list.
   */
  public void placeOrders() {

    for (Order order : orderList) {
      order.execute();
    }
    orderList.clear();
  }
}
