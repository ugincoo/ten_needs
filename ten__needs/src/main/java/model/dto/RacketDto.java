package model.dto;

public class RacketDto {
	private int rNo;
    private String rName;
    private String rImg;
    private int rLevle;
    private int rSize_x;
    private int rSize_y;
    
    public RacketDto() {
		// TODO Auto-generated constructor stub
	}

	public RacketDto(int rNo, String rName, String rImg, int rLevle, int rSize_x, int rSize_y) {
		super();
		this.rNo = rNo;
		this.rName = rName;
		this.rImg = rImg;
		this.rLevle = rLevle;
		this.rSize_x = rSize_x;
		this.rSize_y = rSize_y;
	}

	@Override
	public String toString() {
		return "RacketDto [rNo=" + rNo + ", rName=" + rName + ", rImg=" + rImg + ", rLevle=" + rLevle + ", rSize_x="
				+ rSize_x + ", rSize_y=" + rSize_y + "]";
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrImg() {
		return rImg;
	}

	public void setrImg(String rImg) {
		this.rImg = rImg;
	}

	public int getrLevle() {
		return rLevle;
	}

	public void setrLevle(int rLevle) {
		this.rLevle = rLevle;
	}

	public int getrSize_x() {
		return rSize_x;
	}

	public void setrSize_x(int rSize_x) {
		this.rSize_x = rSize_x;
	}

	public int getrSize_y() {
		return rSize_y;
	}

	public void setrSize_y(int rSize_y) {
		this.rSize_y = rSize_y;
	}
    
}
