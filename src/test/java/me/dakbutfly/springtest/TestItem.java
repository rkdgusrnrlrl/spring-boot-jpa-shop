package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.service.ItemService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by dakbutfly on 2017-01-04.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestItem {

    @Autowired
    ItemService itemService;

    private static long start;

    @BeforeClass
    public static void start() {
        start = System.currentTimeMillis();
        System.out.println("시작");
    }

    @AfterClass
    public static void end() {
        System.out.println("걸린 시간 : "+(System.currentTimeMillis() - start));
    }

    @Test
    public void 상품등록_테스트() throws Exception {
        Item item = Fixture.getItemFixtrue();

        itemService.saveItem(item);

        Long id = item.getId();
        assertNotNull(id);
        assertThat(id, is(1L));
    }

    @Test
    public void 상품찾기_테스트() throws Exception {
        //given
        Item item = Fixture.getItemFixtrue();

        itemService.saveItem(item);
        //when

        Item findedItem = itemService.findItemById(item.getId());

        assertThat(findedItem, is(item));
    }

    @Test
    public void 전체상품_가져오기_테스트() throws Exception {
        //given
        IntStream.range(1, 5).forEach((i) -> {
            Item item = Fixture.getItemFixtrue();
            itemService.saveItem(item);
        });

        //when
        List<Item> items = itemService.findItems();

        //then
        assertNotNull(items);
        assertThat(items.size(), is(4));
    }
}
