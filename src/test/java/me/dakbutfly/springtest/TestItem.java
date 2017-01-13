package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.service.ItemService;
import me.dakbutfly.service.MemberService;
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

    @Test
    public void 상품등록_테스트() throws Exception {
        Item item = new Item();
        item.setName("상품01");
        item.setPrice(1000);

        itemService.saveItem(item);

        Long id = item.getId();
        assertNotNull(id);
        assertThat(id, is(1L));
    }

    @Test
    public void 상품찾기_테스트() throws Exception {
        //given
        Item item = new Item();
        item.setName("상품01");
        item.setPrice(1000);

        itemService.saveItem(item);
        //when

        Item findedItem = itemService.findItemById(item.getId());

        assertThat(findedItem, is(item));
    }

    @Test
    public void 전체상품_가져오기_테스트() throws Exception {
        //given
        IntStream.range(1, 5).forEach((i) -> {
            Item item = new Item();
            item.setName("상품01");
            item.setPrice(1000);
            itemService.saveItem(item);
        });

        //when
        List<Item> items = itemService.findItems();

        //then
        assertNotNull(items);
        assertThat(items.size(), is(4));
    }
}
