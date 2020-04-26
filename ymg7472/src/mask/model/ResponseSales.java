package mask.model;

import java.util.ArrayList;

public class ResponseSales {
	private Integer count;
	private Integer page;
	private ArrayList<Sales> sales;
	private Integer totalCount;
	private Integer totalPages;
	@Override
	public String toString() {
		return "ResponseSales [count=" + count + ", page=" + page + ", sales=" + sales + ", totalCount=" + totalCount
				+ ", totalPages=" + totalPages + "]";
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public ArrayList<Sales> getSales() {
		return sales;
	}
	public void setSales(ArrayList<Sales> sales) {
		this.sales = sales;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
