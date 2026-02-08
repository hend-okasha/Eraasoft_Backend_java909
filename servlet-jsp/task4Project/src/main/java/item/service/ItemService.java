package item.service;

import java.util.List;

import item.model.Item;

public interface ItemService {
	List<Item> getItems();
	Item getItem(Long id);
	Boolean createItem(Item item);
	Boolean updateItem(Item item);
	Boolean removeItem(Long id);
	
	

}
