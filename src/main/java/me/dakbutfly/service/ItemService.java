package me.dakbutfly.service;

import lombok.AllArgsConstructor;
import me.dakbutfly.domain.Item;
import me.dakbutfly.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rkdgusrnrlrl on 17. 1. 13.
 */
@Service

public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemService() {
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
