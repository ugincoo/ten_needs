package model.dto;

import java.util.ArrayList;

public class PageDto {
	private int page; //현재 페이지
	private int startRow; //현재 페이지에서 시작되는 게시물 번호
	private int totalsize; // 총 게시물수
	private int totalpage; //총 페이지수
	private int btnSize; //페이지별 최대 페이징버튼 수
	private int startBtn; //페이지별 시작 페이징버튼의 번호
	private int endBtn; //페이지별 끝 페이징버튼의 번호
	
	//!! : 실질적인 게시물 목록 
	ArrayList<BoardDto> boardList; //출력할 데이터[게시물] 리스트
	ArrayList<GameroomDto> gameList;
	ArrayList<RacketDto> racketList;
	int type; // -- gameList 공용 사용을 위함
	String racket;
	
	//게시물용
	public PageDto(int page, int startRow, int totalsize, int totalpage, int btnSize, int startBtn,
			int endBtn, ArrayList<BoardDto> boardList) {
		super();
		this.page = page;
		this.startRow = startRow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnSize = btnSize;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.boardList = boardList;
	}
	
	// 게임용
	public PageDto(int page, int startRow, int totalsize, int totalpage, int btnSize, int startBtn,
			int endBtn, ArrayList<GameroomDto> gameList, int type) {
		super();
		this.page = page;
		this.startRow = startRow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnSize = btnSize;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.gameList = gameList;
		this.type = type;
	}

	// 라켓용
	public PageDto(int page, int startRow, int totalsize, int totalpage, int btnSize, int startBtn,
			int endBtn, ArrayList<RacketDto> racketList, String racket) {
		super();
		this.page = page;
		this.startRow = startRow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnSize = btnSize;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.racketList = racketList;
		this.racket = racket;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getBtnSize() {
		return btnSize;
	}

	public void setBtnSize(int btnSize) {
		this.btnSize = btnSize;
	}

	public int getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(int startBtn) {
		this.startBtn = startBtn;
	}

	public int getEndBtn() {
		return endBtn;
	}

	public void setEndBtn(int endBtn) {
		this.endBtn = endBtn;
	}

	public ArrayList<BoardDto> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<BoardDto> boardList) {
		this.boardList = boardList;
	}

	public ArrayList<GameroomDto> getGameList() {
		return gameList;
	}

	public void setGameList(ArrayList<GameroomDto> gameList) {
		this.gameList = gameList;
	}
	
	public ArrayList<RacketDto> getRacketList() {
		return racketList;
	}

	public void setRacketList(ArrayList<RacketDto> racketList) {
		this.racketList = racketList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PageDto [page=" + page + ", startRow=" + startRow + ", totalsize=" + totalsize + ", totalpage="
				+ totalpage + ", btnSize=" + btnSize + ", startBtn=" + startBtn + ", endBtn=" + endBtn + ", boardList="
				+ boardList + ", gameList=" + gameList + ", type=" + type + "]";
	}


	
}