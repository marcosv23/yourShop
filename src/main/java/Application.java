import domain.repository.OrderRepository;
import domain.repository.impl.OrderRepositoryInMemory;

public  class Application {

    public static void main (String[] args){

        OrderRepository orderRepository = new OrderRepositoryInMemory();

    }
}
