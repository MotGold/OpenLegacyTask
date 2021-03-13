package app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.beans.Item;
import app.exceptions.DoesNotExistException;
import app.exceptions.DuplicateException;
import app.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository editItem;

	public List<Item> getAllItems() {
		return editItem.findAll();
	}

	public Item getItem(int itemNo) throws DoesNotExistException {
		if (editItem.existsById(itemNo))
			return editItem.findById(itemNo).get();
		else
			throw new DoesNotExistException();
	}

	public void addItem(Item item) throws DuplicateException {
		if (editItem.existsById(item.getItemNo()))
			throw new DuplicateException();
		else
			editItem.save(item);
	}

	public void deleteItem(int itemNo) throws DoesNotExistException {
		if (editItem.existsById(itemNo))
			editItem.deleteById(itemNo);
		else
			throw new DoesNotExistException();
	}

	public void WithdrawStock(int itemNo, int remove) throws DoesNotExistException {
		Item temp = getItem(itemNo);
		temp.setStock(temp.getStock() - remove);
		editItem.save(temp);
	}

	public void addStock(int itemNo, int add) throws DoesNotExistException {
		Item temp = getItem(itemNo);
		temp.setStock(temp.getStock() + add);
		editItem.save(temp);
	}

}
