package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.beans.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
