package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.domain.Order;
import me.dakbutfly.domain.OrderLine;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.repository.OrderRepository;
import me.dakbutfly.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by dakbutfly on 2017-01-04.
 */
public class TestOrder extends TestService {
    private OrderRepository orderRepository;

    @Before
    public void setup() {
        EntityManager em = emf.createEntityManager();
        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        orderRepository = factory.getRepository(OrderRepository.class);
    }

    @Test
    public void 등록된_주문내역_검색() throws Exception {
        //given

        OrderService orderService = new OrderService(orderRepository);
        IntStream.range(1, 5).forEach((i) -> {
            Order order = getOrderFixture();
            try {
                orderService.registerOrder(order);
                List<Order> orders = orderService.findOrders();
                System.out.println(orders);
            } catch (DataValidateExption dataValidateExption) {
                dataValidateExption.printStackTrace();
            }
        });
        //when
        List<Order> orders = orderService.findOrders();

        //then
        assertNotNull(orders);
        assertThat(orders.size(), is(5));
    }

    @Test
    public void 등록_테스트() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();

        OrderService orderService = new OrderService(orderRepository);
        Order order = getOrderFixture();

        // when
        orderService.registerOrder(order);
        List<Order> orders = orderService.findOrders();
        System.out.println(orders);

        //then
        assertNotNull(order.getId());
        assertThat(order.getId(), is(110L));
        Member orderMember = order.getMember();
        assertThat(orderMember, is(member));
    }

    private Order getOrderFixture() {
        Item item = Fixture.getItemFixtrue();
        OrderLine orderLine = new OrderLine();
        orderLine.setItem(item);
        String address = "대전 서구 도마2동 333-28";

        Order order = new Order();
        order.setMember(Fixture.getMemberFixture());
        order.setOrderLine(orderLine);
        order.setAddress(address);
        return order;
    }

    @Test(expected = DataValidateExption.class)
    public void 등록_시_사용자추가_필수_테스트() throws Exception {
        //given
        Order order = new Order();
        OrderService orderService = new OrderService(orderRepository);

        // when
        orderService.registerOrder(order);

        //then
    }

    @Test(expected = DataValidateExption.class)
    public void 등록_시_주문목록_필수_테스트() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();

        Order order = new Order();
        order.setMember(member);
        OrderService orderService = new OrderService(orderRepository);

        // when
        orderService.registerOrder(order);

        //then
    }

    @Test(expected = DataValidateExption.class)
    public void 등록_시_주문목록은_상품을_갖고있음() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();

        Order order = new Order();
        order.setMember(member);
        OrderService orderService = new OrderService(orderRepository);
        order.setOrderLine(new OrderLine());

        // when
        orderService.registerOrder(order);

        //then
        assertNotNull(order.getId());
        Member orderMember = order.getMember();
        assertThat(orderMember, is(member));
    }

    @Test(expected = DataValidateExption.class)
    public void 등록_시_주소가_있음() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();

        Order order = new Order();
        order.setMember(member);
        OrderService orderService = new OrderService(orderRepository);
        OrderLine orderLine = new OrderLine();

        orderLine.setItem(Fixture.getItemFixtrue());
        order.setOrderLine(orderLine);

        // when
        orderService.registerOrder(order);

        //then
        assertNotNull(order.getId());
        Member orderMember = order.getMember();
        assertThat(orderMember, is(member));
    }
}
