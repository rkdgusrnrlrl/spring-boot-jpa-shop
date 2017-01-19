package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.domain.Order;
import me.dakbutfly.domain.OrderLine;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.repository.ItemRepository;
import me.dakbutfly.repository.OrderRepository;
import me.dakbutfly.service.ItemService;
import me.dakbutfly.service.OrderService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * Created by dakbutfly on 2017-01-04.
 */
public class TestOrder extends TestService {
    private static OrderRepository orderRepository;
    private static ItemRepository itemRepository;

    private static ItemService itemService;


    @BeforeClass
    public static void setup() {
        readyForJPATest();
        EntityManager em = emf.createEntityManager();
        EntityManager entityManager = getEntityManager(dataSource(), hibernateProperties());

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(emf);
        jpaTransactionManager.setDataSource(dataSource());

        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);

        orderRepository = factory.getRepository(OrderRepository.class);
        itemRepository = factory.getRepository(ItemRepository.class);
        itemService = new ItemService(itemRepository);


        TransactionDefinition transactionDefinition = new TransactionDefinition() {
            @Override
            public int getPropagationBehavior() {
                return PROPAGATION_REQUIRED;
            }

            @Override
            public int getIsolationLevel() {
                return ISOLATION_DEFAULT;
            }

            @Override
            public int getTimeout() {
                return TIMEOUT_DEFAULT;
            }

            @Override
            public boolean isReadOnly() {
                return false;
            }

            @Override
            public String getName() {
                return "";
            }
        };
        //TransactionManager resource 에 LocalContainarEnitiyManagerFactoryBean 을 key EntityMangerHolder를 value 로 set함
        //안하면 EnitiyManager 에 트랜젝션이 세팅 되지 않음
        jpaTransactionManager.getTransaction(transactionDefinition);
    }


    @Test
    public void JPA_테스트() throws Exception {
        Item item = Fixture.getItemFixtrue();

        itemService.saveItem(item);

        Long id = item.getId();
        assertNotNull(id);
        assertThat(id, is(1L));
    }

    @Test
    public void 등록된_주문내역_검색() throws Exception {
        //given

        OrderService orderService = new OrderService(orderRepository);
        IntStream.range(1, 5).forEach((i) -> {
            Item item = Fixture.getItemFixtrue();
            OrderLine orderLine = new OrderLine();
            orderLine.setItem(item);
            String address = "대전 서구 도마2동 333-28";

            Member member = Fixture.getMemberFixture("rkdgusrnrlrl" + i, "강현구");
            Order order = Fixture.getOrderFixture(orderLine, address, member);
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
        assertThat(orders.size(), is(4));
    }


    /*@Test
    public void 등록_회원_번호와_상품_번호로_테스트() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();


        OrderService orderService = new OrderService(orderRepository);
        Order order = getOrderFixture();

        // when
        orderService.order();
        List<Order> orders = orderService.findOrders();

        //then
        assertNotNull(order.getId());
        Member orderMember = order.getMember();
        assertThat(orderMember.getId(), is(member.getId()));
    }*/

    @Test
    public void 등록_테스트() throws Exception {
        //given
        Member member = Fixture.getMemberFixture();

        OrderService orderService = new OrderService(orderRepository);
        Order order = getOrderFixture();

        // when
        orderService.registerOrder(order);
        List<Order> orders = orderService.findOrders();

        //then
        assertNotNull(order.getId());
        Member orderMember = order.getMember();
        assertThat(orderMember.getId(), is(member.getId()));
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
