package cc.codehub.newkit.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the access database table.
 * 
 */
@Entity
@Table(name="access")
public class Access implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="access_id")
	private Integer accessId;

	private String name;

	private String param;

	@Column(name="parent_id")
	private Integer parentId;

	private String url;

	public Access() {
	}

	public Integer getAccessId() {
		return this.accessId;
	}

	public void setAccessId(Integer accessId) {
		this.accessId = accessId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}