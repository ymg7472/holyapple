package mask.model;

import java.util.ArrayList;

public class ResponseStores {
	private Integer count;
	private Integer page;
	private ArrayList<Stores> storeInfos;
	private Integer totalCount;
	private Integer totalPages;
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
	public ArrayList<Stores> getStoreInfos() {
		return storeInfos;
	}
	public void setStoreInfos(ArrayList<Stores> storeInfos) {
		this.storeInfos = storeInfos;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPages;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPages = totalPage;
	}
	@Override
	public String toString() {
		return "ResponseStores [count=" + count + ", page=" + page + ", storeInfos=" + storeInfos + ", totalCount="
				+ totalCount + ", totalPage=" + totalPages + "]";
	}

}
