package com.marketplace.backend.controller;



import com.marketplace.backend.model.Item;
import com.marketplace.backend.model.User;
import com.marketplace.backend.respository.ItemRepository;
import com.marketplace.backend.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    // POST /items
    @PostMapping
    public Item createItem(@RequestBody Item item) {

        Long userId = item.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        item.setUser(user);

        return itemRepository.save(item);
    }

    // GET /items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}