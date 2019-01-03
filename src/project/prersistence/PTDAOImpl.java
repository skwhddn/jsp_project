package project.prersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.vo.BoardVO;
import project.vo.cartlistVO;
import project.vo.orderVO;
import project.vo.orderokVO;
import project.vo.projectVO;
import project.vo.stockVO;

public class PTDAOImpl implements PTDAO {
	// 컨넥션 객체를 보관
	DataSource datasource;

	private static PTDAOImpl instance = new PTDAOImpl();

	public static PTDAOImpl getInstance() {
		return instance;
	}

	// 생성자
	private PTDAOImpl() {
		try {

			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11gg");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 중복환인
	@Override
	public int idCheck(String strid) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM member WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

			}
		}
		return cnt;
	}

	// 회원가입 처리
	@Override
	public int insertMember(projectVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection(); // 컨넥션 풀
			String sql = "INSERT INTO member(m_id, m_pwd, m_name,m_address, m_hp, m_email, m_reg_date) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getHp());
			pstmt.setString(6, vo.getEmail());
			pstmt.setTimestamp(7, vo.getReg_date());

			insertCnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return insertCnt;
	}

	// 로그인
	@Override
	public int idpwdcheck(String strId, String strPwd) {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection(); // 컨넥션 풀
			String sql = "SELECT * FROM member WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (strPwd.equals(rs.getString("m_pwd"))) {
					selectCnt = 1;
				} else {
					selectCnt = -1;
				}
			} else {
				selectCnt = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return selectCnt;
	}

	// 삭제
	@Override
	public int deleteMember(String strId) {
		int deletCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "DELETE FROM member WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);

			deletCnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return deletCnt;
	}

	// 회원수정 상세페이지
	@Override
	public projectVO getMemberInfo(String strid) {
		projectVO vo = new projectVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "SELECT *  FROM member WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setId(rs.getString("m_id"));
				vo.setPwd(rs.getString("m_pwd"));
				vo.setName(rs.getString("m_name"));
				vo.setAddress(rs.getString("m_address"));
				vo.setHp(rs.getString("m_hp"));
				vo.setEmail(rs.getString("m_email"));
				vo.setReg_date(rs.getTimestamp("m_reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return vo;
	}

	// 회원수정처리
	@Override
	public int updateMember(projectVO vo) {
		int upateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = datasource.getConnection(); // 컨넥션 풀
			String sql = "UPDATE member SET m_pwd=?, m_hp=?, m_email=? WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			upateCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return upateCnt;
	}

	// 게시글갯수 구하기
	@Override
	public int getArticleCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) FROM mvc_board";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) { // 값이 존재한다면
				selectCnt = rs.getInt(1); // 1은 columIndex
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return selectCnt;
	}

	@Override
	public ArrayList<BoardVO> getArticleList(int start, int end) {
		// 큰바구니 선언
		ArrayList<BoardVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * " + "FROM(SELECT num, m_id, pwd, subject, content, readCnt, "
					+ "ref, ref_step, ref_level, reg_date, ip, rownum rNum " + "FROM (" + "SELECT * FROM mvc_board "
					+ "ORDER BY ref DESC, ref_step ASC" + // 1. 최신글부터 SELECT
					")" + // 2. 최신글부터 SELECT한 헤코드에 rowNum을 추가한다.(삭제데이터 제외한 실제 데이터를 최신글부터 넘버링)
					") " + "WHERE rNum >= ? AND rNum <= ?"; // 3.넘겨받은 start값 end값으로 rowNum을 조회
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				// 2. 큰바구니 생성(dtos)
				dtos = new ArrayList<BoardVO>(end - start + 1);

				do {
					// 3. 작은 바구니 생성

					BoardVO dto = new BoardVO();

					// 4. 게시글 1건을 읽어서 rs를 작은 바구니 (dto) 에 담겠다.
					dto.setNum(rs.getInt("num"));
					dto.setM_id(rs.getString("m_id"));
					dto.setPwd(rs.getString("pwd"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setReadCnt(rs.getInt("readCnt"));
					dto.setRef(rs.getInt("ref"));
					dto.setRef_step(rs.getInt("ref_step"));
					dto.setRef_level(rs.getInt("ref_level"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					dto.setIp(rs.getString("ip"));

					// 5. 큰바구니에(Array List dtos)에 작은 바구니(dto, 게시글 1건씩) 읽는다.
					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 6.큰바구니(dtos)를 return
		return dtos;
	}

	@Override
	public BoardVO getArticle(int num) {
		BoardVO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM mvc_board WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 작은 바구니 생성
				dto = new BoardVO();

				// 게시글 1건을 읽어서 작으 바구니에 컬럼별로 담는다.
				dto.setNum(rs.getInt("num"));
				dto.setM_id(rs.getString("m_id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadCnt(rs.getInt("readCnt"));
				dto.setRef(rs.getInt("ref"));
				dto.setRef_step(rs.getInt("ref_step"));
				dto.setRef_level(rs.getInt("ref_level"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setIp(rs.getString("ip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Override
	public void addReadCnt(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "update mvc_board set readCnt = readCnt + 1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}

	}

	// 비밀번호 확인(게시글 수정, 게시글 삭제)
	@Override
	public int pwdCheck(String strPwd, int num) {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM mvc_board WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, strPwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				selectCnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return selectCnt;
	}

	// 글수정 처리
	@Override
	public int updateBoard(BoardVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "UPDATE mvc_board SET subject=?, content=?, pwd=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setInt(4, vo.getNum());

			updateCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return updateCnt;
	}

	// 글추가
	@Override
	public int insertBoard(BoardVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = datasource.getConnection();

			int num = vo.getNum();
			int ref = vo.getRef();
			int ref_step = vo.getRef_step();
			int ref_level = vo.getRef_level();

			// 답변글이 아닌 경우(제목글인 경우)
			if (num == 0) {
				sql = "SELECT MAX(num) FROM mvc_board"; // 최신글부터 가져온다.(최신글부터 뿌려주므로 게시글작성시 글번호는 최신글번호이어야 한다.)
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				// 첫글이 아닌 경우
				if (rs.next()) {
					ref = rs.getInt(1) + 1; // ref = MAX(num) + 1;
					System.out.println("---- 첫글이 아닌 경우 ----");
					// 첫글인 경우
				} else {
					ref = 1;
					System.out.println("---- 첫글인 경우 ----");
				}
				ref_step = 0;
				ref_level = 0;
				// 답변글인 경우

			} else {
				// 삽입한 글보다 아래쪽 글을의 ref_step 즉 행을 UPDATE
				sql = "update mvc_board set ref_step = ref_step+1 WHERE ref=? AND ref_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);

				pstmt.executeUpdate();

				ref_step++;
				ref_level++;
			}

			sql = "INSERT INTO mvc_board(num, m_id, pwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) "
					+ "VALUES(board_seq.nextval,?,?,?,?,0,?,?,?,?,?)";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getM_id());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, ref_step);
			pstmt.setInt(7, ref_level);
			pstmt.setTimestamp(8, vo.getReg_date());
			pstmt.setString(9, vo.getIp());

			insertCnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

			}
		}
		return insertCnt;
	}

	@Override
	public int deleteBoard(int num) {

		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = datasource.getConnection();
			sql = "SELECT * FROM mvc_board WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			// 넘겨받은 num에 해당하는 키값이 존재하면
			if (rs.next()) {
				int ref = rs.getInt("ref");
				int ref_step = rs.getInt("ref_step");
				int ref_level = rs.getInt("ref_level");

				// 답글이 존재하는지 여부 (
				sql = "SELECT * FROM mvc_board WHERE ref=? AND ref_step=?+1 AND ref_level > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);
				pstmt.setInt(3, ref_level);

				rs.close();
				rs = pstmt.executeQuery();

				// 위 sql 내용이 존재하는지 물어본다 존재하면 rs의 값을 가지고 밑에있는 내용을 실행해라
				// 답글이 존재하는 경우 else는 존재하지 않을때
				if (rs.next()) {
					sql = "DELETE mvc_board WHERE num=? OR (ref=? AND ref_level >?)";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.setInt(2, ref);
					pstmt.setInt(3, ref_level);

					deleteCnt = pstmt.executeUpdate();
					System.out.println("답글이 존재하는 경우 deleteCnt : " + deleteCnt);

					sql = "UPDATE mvc_board SET ref_step=ref_step-1 WHERE ref=? AND ref_step > ?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, ref_step);

					pstmt.executeUpdate();
				} else {
					sql = "DELETE mvc_board WHERE num=?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);

					deleteCnt = pstmt.executeUpdate();
					System.out.println("답글이 존재하지않는 경우 deleteCnt : " + deleteCnt);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

			}
		}
		return deleteCnt;
	}

	// 상의리스트
	@Override
	public ArrayList<stockVO> getTopList(int start, int end) {
		// 큰바구니 선언
		ArrayList<stockVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM stocklist WHERE kind='TOP'";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<stockVO>(end - start + 1);

				do {

					stockVO dto = new stockVO();

					dto.setNum(rs.getInt("num"));
					dto.setAge(rs.getInt("age"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setImg(rs.getString("img"));
					dto.setPrice(rs.getString("price"));
					dto.setKind(rs.getString("kind"));

					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	// 바지리스트
	@Override
	public ArrayList<stockVO> getPantsList(int start, int end) {
		// 큰바구니 선언
		ArrayList<stockVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM stocklist WHERE kind='PANTS'";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<stockVO>(end - start + 1);

				do {

					stockVO dto = new stockVO();

					dto.setNum(rs.getInt("num"));
					dto.setAge(rs.getInt("age"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setImg(rs.getString("img"));
					dto.setPrice(rs.getString("price"));
					dto.setKind(rs.getString("kind"));

					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	// 신발리스트
	@Override
	public ArrayList<stockVO> getShoseList(int start, int end) {
		// 큰바구니 선언
		ArrayList<stockVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM stocklist WHERE kind='SHOSE'";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<stockVO>(end - start + 1);

				do {

					stockVO dto = new stockVO();

					dto.setNum(rs.getInt("num"));
					dto.setAge(rs.getInt("age"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setImg(rs.getString("img"));
					dto.setPrice(rs.getString("price"));
					dto.setKind(rs.getString("kind"));

					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	// 제품상세페이지
	@Override
	public stockVO detailpage(int num) {
		stockVO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM stocklist WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				dto = new stockVO();

				dto.setNum(rs.getInt("num"));
				dto.setColor(rs.getString("color"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getString("price"));
				dto.setSubject(rs.getString("subject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	// 장바구니담기
	@Override
	public int cart(cartlistVO vo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "INSERT INTO cartlist(c_num, num, m_id, img, subject, color, age, price) "
					+ "VALUES(cartlist_seq.NEXTVAL,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getC_img());
			pstmt.setString(4, vo.getC_subject());
			pstmt.setString(5, vo.getC_color());
			pstmt.setInt(6, vo.getC_age());
			pstmt.setString(7, vo.getC_price());
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// 장바구니리스트뿌려주기
	@Override
	public ArrayList<cartlistVO> getcartlist(String strid) {
		// 큰바구니 선언
		ArrayList<cartlistVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM cartlist WHERE m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<cartlistVO>();

				do {

					cartlistVO dto = new cartlistVO();

					dto.setM_id(rs.getString("m_id"));
					dto.setC_num(rs.getInt("c_num"));
					dto.setNum(rs.getInt("num"));
					dto.setM_id(rs.getString("m_id"));
					dto.setC_img(rs.getString("img"));
					dto.setC_subject(rs.getString("subject"));
					dto.setC_color(rs.getString("color"));
					dto.setC_age(rs.getInt("age"));
					dto.setC_price(rs.getString("price"));

					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	// 장바구니삭제
	@Override
	public int cartdelete(String[] num) {
		int deletCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		for (int i = 0; i < num.length; i++) {

			try {
				conn = datasource.getConnection();
				String sql = "DELETE FROM cartlist WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num[i]));

				deletCnt = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				}
			}
		}
		return deletCnt;
	}

	// 장바구니 개별구매
	@Override
	public int order(String[] num, orderVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] number = vo.getTest().split(",");
		for (int i = 0; i < number.length; i++) {
		
				try {
					conn = datasource.getConnection();
					String sql = "INSERT INTO orderlist(o_num,num, m_id, img, subject, color, age, price) "
							+ "VALUES(orderlist_seq.NEXTVAL,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(number[i]));
					System.out.println("number[i]" + number[i]);
					pstmt.setString(2, vo.getM_id());
					pstmt.setString(3, vo.getImg());
					pstmt.setString(4, vo.getSubject());
					pstmt.setString(5, vo.getColor());
					pstmt.setInt(6, vo.getAge());
					pstmt.setString(7, vo.getPrice());

					insertCnt = pstmt.executeUpdate();
					System.out.println("insertCnt1 : " + insertCnt);
					pstmt.close();
					
					sql = "DELETE FROM cartlist WHERE num=?";
					pstmt = conn.prepareStatement(sql);
					System.out.println("number[i] : " + number[i]);
					pstmt.setInt(1, Integer.parseInt(number[i]));
					
					insertCnt = pstmt.executeUpdate();
					
					System.out.println("insertCnt2 : " + insertCnt);


				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (conn != null)
							conn.close();
						if (pstmt != null)
							pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
					}
				}
			}
		return insertCnt;
	}

	@Override
	public ArrayList<orderVO> getorderlist(String strid) {
		// 큰바구니 선언
		ArrayList<orderVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM orderlist WHERE m_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<orderVO>();

				do {

					orderVO dto = new orderVO();
					
					dto.setC_num(rs.getInt("num"));
					dto.setM_id(rs.getString("m_id"));
					dto.setImg(rs.getString("img"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setAge(rs.getInt("age"));
					dto.setPrice(rs.getString("price"));

					dtos.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	@Override
	public ArrayList<orderokVO> orderoklist(int start, int end) {
		ArrayList<orderokVO> vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM ordercomplete";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new ArrayList<orderokVO>(end - start + 1);

				do {
					orderokVO dto = new orderokVO();
					
					
					dto.setOc_num(rs.getInt("oc_num"));
					dto.setC_num(rs.getInt("num"));
					dto.setM_id(rs.getString("m_id"));
					dto.setImg(rs.getString("img"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setAge(rs.getInt("age"));
					dto.setPrice(rs.getString("price"));
					

					vo.add(dto);

				} while (rs.next());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	@Override
	public int orderno(String[] num) {
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		for (int i = 0; i < num.length; i++) {

			try {
				conn = datasource.getConnection();
				String sql = "DELETE FROM orderlist WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num[i]));

				deleteCnt = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				}
			}
		}
		return deleteCnt;
	}
	
	//환불신청
	@Override
	public int ordercancel(String[] num, orderokVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] number = vo.getTest().split(",");
		for (int i = 0; i < number.length; i++) {
		
				try {
					conn = datasource.getConnection();
					String sql = "INSERT INTO ordercancel(cc_num,num, m_id, img, subject, color, age, price) "
							+ "VALUES(ordercancel_seq.NEXTVAL,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					System.out.println("number[i] : " + number[i]);
					pstmt.setInt(1, Integer.parseInt(number[i]));
					pstmt.setString(2, vo.getM_id());
					pstmt.setString(3, vo.getImg());
					pstmt.setString(4, vo.getSubject());
					pstmt.setString(5, vo.getColor());
					pstmt.setInt(6, vo.getAge());
					pstmt.setString(7, vo.getPrice());

					insertCnt = pstmt.executeUpdate();
					System.out.println("insertCnt2: " + insertCnt);
					pstmt.close();
					
					sql = "DELETE FROM ordercomplete WHERE num=?";
					pstmt = conn.prepareStatement(sql);
					System.out.println("number[i] : " + number[i]);
					pstmt.setInt(1, Integer.parseInt(number[i]));
					
					insertCnt = pstmt.executeUpdate();
					System.out.println("insertCnt3 : " + insertCnt);


				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (conn != null)
							conn.close();
						if (pstmt != null)
							pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
					}
				}
			}
		return insertCnt;
	}
	
	//즉시구매
	@Override
	public int cartgo(orderVO vo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "INSERT INTO orderlist(o_num, num, m_id, img, subject, color, age, price) "
					+ "VALUES(orderlist_seq.NEXTVAL,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getO_num());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getSubject());
			pstmt.setString(5, vo.getColor());
			pstmt.setInt(6, vo.getAge());
			pstmt.setString(7, vo.getPrice());
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}