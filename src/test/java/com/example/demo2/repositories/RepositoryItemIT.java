package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.Bid;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Course;
import com.example.demo2.models.CourseMaterial;
import com.example.demo2.models.Item;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryCourseIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;

@TestConfig
public class RepositoryItemIT {
	
	@Autowired
	private RepositoryItem repoItem;
	@Autowired
	private RepositoryBid repoBid;

	
	@Test
	@Transactional
	void testCreateItemWithBids() {
		
		Item item = new Item("Phone");
		Bid bid = new Bid(18);
		
		item.getBids().add(bid);
		bid.setItem(item);
		
		repoItem.save(item);

		assertNotNull(item.getBids());
		assertNotNull(bid.getItem());
		assertEquals(1, repoItem.count());
		assertEquals(1, repoBid.count());
		
	}
	
	@Test
	@Transactional
	void testDeleteCourseCascade() {
		Long idItem = createItemWithBids();
		Item item = repoItem.findById(idItem).get();
		
		repoItem.delete(item);
		assertEquals(0, repoItem.count());
		assertEquals(0, repoBid.count());
	}
	
	@Test
	@Transactional
	void testDeleteChildBidOrphanRemoval() {
		Long idItem = createItemWithBids();
		Item item = repoItem.findById(idItem).get();
		Bid bid = item.getBids().get(0);
		
		bid.setItem(null);
		item.getBids().remove(bid);
		
		repoItem.save(item);
		assertEquals(0, repoBid.count());
		
	}

	Long createItemWithBids() {
		Item item = new Item("Phone");
		Bid bid = new Bid(18);
		
		item.getBids().add(bid);
		bid.setItem(item);
		
		repoItem.save(item);

		return item.getId();
	}
	
	
	
	
	
}
