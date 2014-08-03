package com.themallmall.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

/**
 * @author Edward P. Legaspi
 **/
public class PaginationConfiguration implements Serializable {

	private static final long serialVersionUID = -2750287256630146681L;

	private int firstRow, numberOfRows;

	/** Search filters (key = field name, value = search pattern or value). */
	private Map<String, Object> filters;
	private Map<String, String> sortOrdering;

	/**
	 * Fields that needs to be fetched when selecting (like lists or other
	 * entities).
	 */
	private List<String> fetchFields;

	private String sortField;
	private SortOrder ordering;

	/**
	 * @param firstRow
	 * @param numberOfRows
	 * @param filters
	 * @param fetchFields
	 * @param sortField
	 * @param sortOrder
	 */
	public PaginationConfiguration(int firstRow, int numberOfRows,
			Map<String, Object> filters, List<String> fetchFields,
			String sortField, SortOrder sortOrder) {
		this(firstRow, numberOfRows, filters, fetchFields, sortField,
				sortOrder, null);
	}

	/**
	 * @param firstRow
	 * @param numberOfRows
	 * @param filters
	 * @param fetchFields
	 * @param sortField
	 * @param ordering
	 * @param sortOrdering
	 */
	public PaginationConfiguration(int firstRow, int numberOfRows,
			Map<String, Object> filters, List<String> fetchFields,
			String sortField, SortOrder ordering,
			Map<String, String> sortOrdering) {
		this.firstRow = firstRow;
		this.numberOfRows = numberOfRows;
		this.filters = filters;
		this.fetchFields = fetchFields;
		this.sortField = sortField;
		this.ordering = ordering;
		this.sortOrdering = sortOrdering;
	}

	/**
	 * @param filters
	 */
	public PaginationConfiguration(Map<String, Object> filters) {
		this.filters = filters;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public String getSortField() {
		return sortField;
	}

	public Map<String, String> getOrderings() {
		return sortOrdering;
	}

	public SortOrder getOrdering() {
		return ordering;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public List<String> getFetchFields() {
		return fetchFields;
	}

	public void setFetchFields(List<String> fetchFields) {
		this.fetchFields = fetchFields;
	}

	public boolean isSorted() {
		return ordering != null && sortField != null
				&& sortField.trim().length() != 0;
	}

	public boolean isAscendingSorting() {
		return ordering != null && ordering == SortOrder.ASCENDING;
	}
}
