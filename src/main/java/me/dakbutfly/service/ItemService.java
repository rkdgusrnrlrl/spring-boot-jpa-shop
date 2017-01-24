package me.dakbutfly.service;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import me.dakbutfly.domain.Item;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by rkdgusrnrlrl on 17. 1. 13.
 */
@Service

public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void saveItem(Item item) throws DataValidateExption {
        if (Strings.isNullOrEmpty(item.getName())) throw new DataValidateExption("item name is empty");
        if (item.getPrice() == null) throw new DataValidateExption("item price is null");
        itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
