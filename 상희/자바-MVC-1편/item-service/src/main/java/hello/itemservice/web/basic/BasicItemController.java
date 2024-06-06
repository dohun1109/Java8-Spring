package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // Get방식으로 add로 들어왔을 때
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // 상품수정
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId} ";
    }

    //    @PostMapping("/add")
    // itemName은 html에 있는 name이다.
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        model.addAttribute("item", item);

        itemRepository.save(item);
        return "/basic/item";
    }

    //    @PostMapping("/add")
    // itemName은 html에 있는 name이다.
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "/basic/item";
    }

    //    @PostMapping("/add")
    // itemName은 html에 있는 name이다.
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "/basic/item";
    }

    // 추천은 안함(김영한 강사님 개인적 의견)
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능

        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
//        model.addAttribute("item", item); // 자동 추가, 생략 가능
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 아이템 추가
     */
    @PostConstruct // 객체의 초기화가 모두 끝난 후, 즉 모든 의존성이 주입된 후에 실행되는 메소
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
