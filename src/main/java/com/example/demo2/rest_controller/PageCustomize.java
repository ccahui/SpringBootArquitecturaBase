package com.example.demo2.rest_controller;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageCustomize<T> {
	
	public List<T> content;
	public Object pageSize;
	public Object totalPages;
	public Object currentPage;
	public Object totalElements;
	public Object numberOfElements;
	
	public PageCustomize(Page<T> page) {
		this.content = page.getContent();
		this.pageSize = page.getSize();
		this.totalPages = page.getTotalPages();
		this.currentPage = page.getNumber();
		this.totalElements = page.getTotalElements();
		this.numberOfElements = page.getNumberOfElements();
	}

	public List<T> getContent() {
		return content;
	}

	public Object getPageSize() {
		return pageSize;
	}

	public void setPageSize(Object pageSize) {
		this.pageSize = pageSize;
	}

	public Object getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Object totalPages) {
		this.totalPages = totalPages;
	}

	public Object getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Object currentPage) {
		this.currentPage = currentPage;
	}

	public Object getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Object totalElements) {
		this.totalElements = totalElements;
	}

	public Object getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Object numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	
	
}
