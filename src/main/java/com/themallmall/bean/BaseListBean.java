package com.themallmall.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.themallmall.model.IEntity;
import com.themallmall.service.PersistenceService;

public abstract class BaseListBean<E extends IEntity, P extends PersistenceService<E>>
		implements Serializable {

	private static final long serialVersionUID = -2528942065567377913L;

	private LazyDataModel<E> dataModel;
	private E[] selectedEntities;
	private DataTable dataTable;
	protected E entity;
	protected Class<E> entityClass;
	protected Class<P> serviceClass;
	protected Map<String, Object> filters = new HashMap<String, Object>();

	public BaseListBean() {
		super();
	}

	public BaseListBean(Class<E> entityClass, Class<P> serviceClass) {
		super();
		this.entityClass = entityClass;
		this.serviceClass = serviceClass;
	}

	protected void addFilter() {

	}

	public Map<String, Object> getFilters() {
		if (filters == null) {
			filters = new HashMap<String, Object>();
		}
		addFilter();

		return filters;
	}

	protected String getDefaultSort() {
		return null;
	}

	protected SortOrder getDefaultSortOrder() {
		return null;
	}

	protected List<String> getListFieldsToFetch() {
		return null;
	}

	public abstract P getPersistenceService();

	public LazyDataModel<E> getLazyDataModel() {
		return getLazyDataModel(getFilters());
	}

	public LazyDataModel<E> getLazyDataModel(Map<String, Object> inputFilters) {
		if (dataModel == null) {
			final Map<String, Object> filters = inputFilters;
			dataModel = new LazyDataModel<E>() {
				private static final long serialVersionUID = 1L;
				private Integer rowCount;
				private Integer rowIndex;

				@Override
				public List<E> load(int first, int pageSize, String sortField,
						SortOrder sortOrder, Map<String, Object> loadingFilters) {

					if (getDefaultSort() != null && sortField == null) {
						sortField = getDefaultSort();
					}

					if (getDefaultSortOrder() != null) {
						sortOrder = getDefaultSortOrder();
					}

					Map<String, Object> copyOfFilters = new HashMap<String, Object>();
					copyOfFilters.putAll(filters);
					setRowCount((int) getPersistenceService().count(
							new PaginationConfiguration(first, pageSize,
									copyOfFilters, getListFieldsToFetch(),
									sortField, sortOrder)));
					if (getRowCount() > 0) {
						copyOfFilters = new HashMap<String, Object>();
						copyOfFilters.putAll(filters);
						return getPersistenceService().list(
								new PaginationConfiguration(first, pageSize,
										copyOfFilters, getListFieldsToFetch(),
										sortField, sortOrder));
					} else {
						return null;
					}
				}

				@Override
				public E getRowData(String rowKey) {
					return getPersistenceService().findById(
							Long.valueOf(rowKey));
				}

				@Override
				public Object getRowKey(E object) {
					return object.getId();
				}

				@Override
				public void setRowIndex(int rowIndex) {
					if (rowIndex == -1 || getPageSize() == 0) {
						this.rowIndex = rowIndex;
					} else {
						this.rowIndex = rowIndex % getPageSize();
					}
				}

				@SuppressWarnings("unchecked")
				@Override
				public E getRowData() {
					return ((List<E>) getWrappedData()).get(rowIndex);
				}

				@SuppressWarnings({ "unchecked" })
				@Override
				public boolean isRowAvailable() {
					if (getWrappedData() == null) {
						return false;
					}

					return rowIndex >= 0
							&& rowIndex < ((List<E>) getWrappedData()).size();
				}

				@Override
				public int getRowIndex() {
					return this.rowIndex;
				}

				@Override
				public void setRowCount(int rowCount) {
					this.rowCount = rowCount;
				}

				@Override
				public int getRowCount() {
					if (rowCount == null) {
						rowCount = (int) getPersistenceService().count();
					}
					return rowCount;
				}

			};
		}

		return dataModel;
	}

	public LazyDataModel<E> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<E> dataModel) {
		this.dataModel = dataModel;
	}

	public E[] getSelectedEntities() {
		return selectedEntities;
	}

	public void setSelectedEntities(E[] selectedEntities) {
		this.selectedEntities = selectedEntities;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<P> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<P> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}
}
