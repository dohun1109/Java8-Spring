package hello.item_service.web.basic;

import hello.item_service.domain.item.Item;
import hello.item_service.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
//    @Autowired : 생성자가 하나면 생략 가능 @RequiredArgsConstructor : final 이 붙은 애들 생성자 생성
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

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }
    
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) { //Model model 도 생략가능
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { //Model model 도 생략가능
        //생략시 클래스 명에 첫글자만 소문자로 변경되서 Item -> item
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) { //Model model 도 생략가능 , @ModelAttribute 도 생략가능
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) { //Model model 도 생략가능 , @ModelAttribute 도 생략가능
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) { //Model model 도 생략가능 , @ModelAttribute 도 생략가능
        Item savedItem = itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,  @ModelAttribute Item item) {
        itemRepository.update(itemId,item );
        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }


}
