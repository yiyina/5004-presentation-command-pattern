## Command pattern-group1
Team Member: Siyi Ling (Sierra), Jiaxin Yan (Mia), Yina Yi

# Introduction of the Command pattern
The Command Pattern is a design pattern that separates the object that invokes a request from the object that performs the action. It consists of three main components: the Command, which encapsulates the request as an object, the Invoker, which sends the command to the Receiver to perform the requested action, and the Receiver, which performs the action[1].

To implement the Command Pattern, a set of Command objects must be defined that represent the actions that can be performed in the system. The Invoker object invokes the commands, while the Receiver object performs the requested action.

One advantage of the Command Pattern is that it provides a way to undo and redo actions, as well as the ability to execute complex sequences of commands as a single transaction. It is also useful in scenarios where there is a need to decouple a requester object from the object that performs the action, or to create generic and reusable commands that can be executed and undone at a later time.

The Command Pattern is widely used in various scenarios, from text editors to home entertainment systems to transaction processing systems. It enables the addition of new features and behaviors to an object without modifying its existing code, resulting in a flexible, modular, and easily maintainable codebase.

## 图片

Figure 1. A sample UML class and sequence diagram for the Command design pattern.[1]

The Invoker class in the Command Pattern refers to the Command interface to perform a request, making it independent of how the request is performed. The Command class implements the Command interface by performing an action on a receiver. This run-time interaction is shown in a UML sequence diagram. [1]

# Application (When to use it?)
The Command Pattern is a versatile design pattern that can be applied in various scenarios. Some of the common use cases of the Command Pattern are:

Encapsulating requests as objects: When you want to decouple the object that invokes a request from the object that performs the action, you can encapsulate the request as an object using the Command Pattern.

Undo and redo functionality: One advantage of the Command Pattern is that it provides a way to undo and redo actions, which is useful in scenarios where there is a need to roll back or replay changes made to an object. This feature can be particularly useful in transactional processing systems, where the ability to undo and redo actions can prevent data inconsistencies and errors.
For example: A calculator application could use the Command Pattern to encapsulate mathematical operations such as addition and subtraction, allowing them to be executed and undone as needed.

Complex command sequences: The Command Pattern can be used to execute complex sequences of commands as a single transaction. This allows for the creation of atomic actions that can be rolled back in case of failures or errors, providing reliability and fault tolerance to the system.
For example: the Command Pattern is a useful tool for implementing undo/redo functionality and saving game progress in a game development scenario. In a video game, a Memento object can be implemented to save the game state, including the current level, player position, score, and stack of executed commands. This enables the player to continue the game from where they left off, even if the game crashes or is closed, resulting in a better player experience and increased reliability of the game.

Decoupling requester and receiver objects: The Command Pattern is useful in scenarios where there is a need to decouple a requester object from the object that performs the action. This decoupling can simplify the design of the system and promote code reusability and maintainability. 

Our code example below will demonstrate the encapsulating and decoupling features of the Command Pattern. This usage allows for greater flexibility and reusability of the code, and it makes it easier to add new commands to the system.

# UML Class diagram

## 图片

# Code Walkthrough

What is the problem?

In the example implementation of the Command Pattern from the Tutorialspoint [4], the main goal we want to achieve is to decouple the object that requests an action (the client) from the object that performs the action (the receiver). In particular, the code demonstrates how to encapsulate requests as objects, which allows the request to be passed around as a first-class object and executed at a later time.

How does the pattern solve the problem?

The Command pattern encapsulates a request as an object, thereby decoupling the requester from the object that performs the action. This capability allows for the creation of generic and reusable commands that can be executed and undone at a later time.

In the code example, the Command pattern is implemented using the Stock and Order classes, which represent the receiver and the request, respectively. The BuyStock and SellStock classes represent concrete implementations of the Order class, and they encapsulate the request as an object.

The Broker class acts as the invoker, and it is responsible for executing the requests. The Broker class accepts requests in the form of Order objects, which it stores in a list. When the Broker wants to execute the requests, it simply iterates over the list and calls the execute() method on each Order object.

By using the Command pattern in this way, the client can make requests without knowing anything about the receiver, and the receiver can perform the request without knowing anything about the client.


Let’s walk through the code.

The example starts with defining an interface called Order. This interface defines a method called execute() which will be implemented by concrete classes.

public interface Order {
  void execute();
}

Next, we define two concrete classes called BuyStock and SellStock that implement the Order interface.

public class BuyStock implements Order{
  private Stock abcStock;

  public BuyStock(Stock abcStock){
    this.abcStock = abcStock;
  }

  @Override
  public void execute() {
    abcStock.buy();
  }
}

public class SellStock implements Order {
  private Stock abcStock;

  public SellStock(Stock abcStock){
    this.abcStock = abcStock;
  }

  @Override
  public void execute() {
    abcStock.sell();
  }
}

Both BuyStock and SellStock classes have a constructor that takes a Stock object and assigns it to a private variable. They also implement the execute() method by calling the buy() and sell() methods respectively of the Stock object.

The next step is to create a Broker class that acts as the invoker and executes the commands.

public class Broker {
  private List<Order> orderList = new ArrayList<Order>();

  public void takeOrder(Order order){
    orderList.add(order);
  }

  public void placeOrders(){

    for (Order order : orderList) {
      order.execute();
    }
    orderList.clear();
  }
}

The Broker class has two methods: takeOrder() and placeOrders(). The takeOrder() method takes an Order object and adds it to a list of orders. The placeOrders() method iterates over the list of orders and executes each order by calling its execute() method. Finally, it clears the list of orders.

To test the implementation of the Command Pattern, it can create a CommandPatternDemo class that creates objects of the Stock, BuyStock, SellStock, and Broker classes and executes the orders.

public class CommandPatternDemo {
  public static void main(String[] args) {
    Stock abcStock = new Stock();

    BuyStock buyStockOrder = new BuyStock(abcStock);
    SellStock sellStockOrder = new SellStock(abcStock);

    Broker broker = new Broker();
    broker.takeOrder(buyStockOrder);
    broker.takeOrder(sellStockOrder);

    broker.placeOrders();
  }
}

In this example, it creates a Stock object and two Order objects: BuyStock and SellStock. Then it creates a Broker object and adds the orders to its order list using the takeOrder() method. Finally, it calls the placeOrders() method of the Broker object to execute the orders.

# Citation
[1] L. Bosse and J. Oestereich, "Command pattern," Wikipedia, 20-Sep-2021. [Online]. Available: https://en.wikipedia.org/wiki/Command_pattern#:~:text=In%20object%2Doriented%20programming%2C%20the,values%20for%20the%20method%20parameters. [Accessed: 21-Apr-2023].
[2] Tutorialspoint. (n.d.). Command pattern. Retrieved April 20, 2023, from https://www.tutorialspoint.com/design_pattern/command_pattern.htm
[3] Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). Design patterns: elements of reusable object-oriented software. Addison-Wesley Professional.
[4] Freeman, E., & Robson, E. (2004). Head first design patterns. O'Reilly Media, Inc.
[5] P. M. Mellor, M. J. Balcer, and S. J. Mellor, "The Command Design Pattern," in Advances in Object-Oriented Metamodeling, Springer US, 1998, pp. 233-240. [Online]. Available: https://doi.org/10.1007/978-1-4615-5593-0_22 
