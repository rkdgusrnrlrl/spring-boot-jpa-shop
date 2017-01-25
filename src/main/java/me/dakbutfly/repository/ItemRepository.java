package me.dakbutfly.repository;

import me.dakbutfly.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rkdgusrnrlrl on 17. 1. 13.
 */
public interface ItemRepository extends JpaRepository<Item, Long>{
    Item save(Item item);
    Item findById(Long id);
    List<Item> findAll();

    Item findByName(String name);
}
