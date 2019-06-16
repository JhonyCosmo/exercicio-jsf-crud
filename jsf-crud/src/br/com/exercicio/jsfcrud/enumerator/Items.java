package br.com.exercicio.jsfcrud.enumerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.exercicio.jsfcrud.vo.Item;

public enum Items {
	
	INSTANCE;
	
	private List<Item> listItems;

	private Items() {
		listItems = new ArrayList<>();
	}

	public void add(Item u) {
		UUID uuid = UUID.randomUUID();
		u.id = uuid.toString();  
		listItems.add(u);
	}
	
	public Item get(String id) {		
		if (listItems != null) {
			for (Item u : listItems) {
				if (u.getId().equals(id)) {
					return u;
				}
			}
		}		
		return null;
	}
	
	public Item getByName(String name) {		
		if (listItems != null) {
			for (Item u : listItems) {
				if (u.getDescricao().equals(name)) {
					return u;
				}
			}
		}		
		return null;
	}
	
	public void remove(Item u) {
		listItems.remove(u);
	}
	
	public List<Item> all() {
		return listItems;
	}
}
