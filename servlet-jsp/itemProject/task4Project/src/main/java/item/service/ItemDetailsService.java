package item.service;

import item.model.ItemDetails;

public interface ItemDetailsService {
	
	ItemDetails getItemDetailsByItemId(Long itemId);
    Boolean createItemDetails(ItemDetails itemDetails);
    Boolean deleteItemDetails(Long itemId);


}
