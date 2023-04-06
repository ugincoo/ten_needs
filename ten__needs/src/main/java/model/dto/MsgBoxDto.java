package model.dto;

public class MsgBoxDto {
	
	private String type;
    private BallDto data;

	public MsgBoxDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MsgBoxDto(String type, BallDto data) {
		super();
		this.type = type;
		this.data = data;
	}

	@Override
	public String toString() {
		return "MsgBoxDto [type=" + type + ", data=" + data + "]";
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public BallDto getData() {
		return data;
	}


	public void setData(BallDto data) {
		this.data = data;
	}

    
    
    
}
