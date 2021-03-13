package app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.beans.Item;
import app.exceptions.DoesNotExistException;
import app.exceptions.DuplicateException;
import app.services.ItemService;
import io.swagger.annotations.Api;

@Api(tags = "OpenLegacy")
@RestController
@RequestMapping("OpenLegacy")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ItemService itemService;

	@GetMapping("items")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@GetMapping("item/{itemNo}")
	public ResponseEntity<?> itemDetails(@PathVariable int itemNo) {
		try {
			return ResponseEntity.ok(itemService.getItem(itemNo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping("addItem")
	public ResponseEntity<?> addItem(@RequestBody Item newItem) {
		try {
			if (newItem.getItemNo() <= 0)
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("item's number can't be lower than 1");
			else {
				itemService.addItem(newItem);
				return ResponseEntity.ok(newItem);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{itemNo}")
	public ResponseEntity<?> deleteItem(@PathVariable int itemNo) {
		try {
			itemService.deleteItem(itemNo);
			return ResponseEntity.ok(itemNo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("withdrawStock/{itemNo}/{stock}")
	public ResponseEntity<?> withdrawStock(@PathVariable int itemNo, @PathVariable int stock) {
		try {
			Item temp = itemService.getItem(itemNo);
			if (temp.getStock() - stock >= 0) {
				itemService.WithdrawStock(itemNo, stock);
				return ResponseEntity.ok(itemNo);
			} else
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Error! Withdraw causes negative item stock");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("addStock/{itemNo}/{stock}")
	public ResponseEntity<?> addStock(@PathVariable int itemNo, @PathVariable int stock) {
		try {
			itemService.addStock(itemNo, stock);
			return ResponseEntity.ok(itemNo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
