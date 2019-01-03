package project.vo;

import java.sql.Timestamp;

public class BoardVO {
    private int       num; 			//글번호
	private String    m_id;		//작성자
    private String    pwd;    		//비밀번호
    private String    subject;      //글제목
    private String    content;      //글내용
    private int       readCnt;     	//조회수
    private int       ref;         	//답변글 그룹화 아이디 -- 답변글번호로서 원 글번호와 일치해야 한다.
    private int       ref_step;    	//답변글 그룹 스텝
    private int       ref_level;   	//답변글 그룹 레벨
    private Timestamp reg_date; 	//작성일
    private String    ip;          	//ip
    public BoardVO() {
		
	}
    public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
    public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRef_step() {
		return ref_step;
	}
	public void setRef_step(int ref_step) {
		this.ref_step = ref_step;
	}
	public int getRef_level() {
		return ref_level;
	}
	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
    
    
}
