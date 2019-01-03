package project.prersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.vo.orderVO;
import project.vo.ordernoVO;
import project.vo.orderokVO;
import project.vo.stockVO;

public class hostDAOImpl implements hostDAO {
	private static final String[] String = null;

	// 컨넥션 객체를 보관
	DataSource datasource;

	private static hostDAOImpl instance = new hostDAOImpl();

	public static hostDAOImpl getInstance() {
		return instance;
	}

	// 생성자
	private hostDAOImpl() {
		try {

			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11gg");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
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
			String sql = "SELECT * FROM host WHERE h_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (strPwd.equals(rs.getString("h_pwd"))) {
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

	// 상품갯수 구하기
	@Override
	public int getArticleCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) FROM stocklist";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				selectCnt = rs.getInt(1);
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
	public ArrayList<stockVO> getArticleList(int start, int end) {

		ArrayList<stockVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM stocklist";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<stockVO>(end - start + 1);

				do {

					stockVO dto = new stockVO();

					dto.setNum(rs.getInt("num"));
					dto.setImg(rs.getString("img"));
					dto.setSubject(rs.getString("subject"));
					dto.setColor(rs.getString("color"));
					dto.setAge(rs.getInt("age"));
					dto.setPrice(rs.getString("price"));
					dto.setKind(rs.getString("kind"));
					dto.setReg_date(rs.getTimestamp("reg_date"));

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
	public int stockadd(stockVO dto) {
		int res = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();

			String sql = "INSERT INTO stocklist(num,img,subject,color,age,price,kind,reg_date)"
					+ "VALUES(stocklist_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getImg());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getColor());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getPrice());
			pstmt.setString(6, dto.getKind());
			pstmt.setTimestamp(7, dto.getReg_date());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
		}

		return res;
	}

	// 재고수정상세페이지
	@Override
	public stockVO updatefage(String strid) {
		stockVO vo = new stockVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "SELECT *  FROM stocklist WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setColor(rs.getString("color"));
				vo.setPrice(rs.getString("price"));
				vo.setSubject(rs.getString("subject"));
				vo.setImg(rs.getString("img"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setAge(rs.getInt("age"));
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

	// 재고수정처리
	@Override
	public int update(stockVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			String sql = "UPDATE stocklist SET img=?,subject=?,color=?,age=?,price=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getImg());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getColor());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getPrice());
			pstmt.setInt(6, vo.getNum());

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

	@Override
	public stockVO getArticle(int num) {
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
				dto.setAge(rs.getInt("age"));
				dto.setImg(rs.getString("img"));
				dto.setPrice(rs.getString("price"));
				dto.setColor(rs.getString("color"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getTimestamp("reg_date"));

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
	public int stockdelete(String[] num) {
		int deletCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		for (int i = 0; i < num.length; i++) {

			try {
				conn = datasource.getConnection();
				String sql = "DELETE FROM stocklist WHERE num=?";
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

	@Override
	public ArrayList<orderVO> getorderlist(int start, int end) {
		ArrayList<orderVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM orderlist";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<orderVO>(end - start + 1);

				do {
					orderVO dto = new orderVO();

					dto.setO_num(rs.getInt("o_num"));
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
	
	//주문승인
	@Override
	public int guestorderadd(String[] num, orderokVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String[] number = vo.getTest().split(",");
		for (int i = 0; i < number.length; i++) {
		
				try {
					conn = datasource.getConnection();
					String sql = "INSERT INTO ordercomplete(oc_num,num, m_id, img, subject, color, age,price) "
							+ "VALUES(ordercomplete_seq.NEXTVAL,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(number[i]));
					pstmt.setString(2, vo.getM_id());
					pstmt.setString(3, vo.getImg());
					pstmt.setString(4, vo.getSubject());
					pstmt.setString(5, vo.getColor());
					pstmt.setInt(6, vo.getAge());
					pstmt.setString(7, vo.getPrice());

					insertCnt = pstmt.executeUpdate();
					System.out.println("insertCnt1 : " + insertCnt);
					pstmt.close();
					
					sql = "DELETE FROM orderlist WHERE num=?";
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
	public ArrayList<ordernoVO> getcancellist(int start, int end) {
		ArrayList<ordernoVO> dtos = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM ordercancel";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dtos = new ArrayList<ordernoVO>(end - start + 1);

				do {
					ordernoVO dto = new ordernoVO();

					dto.setCc_num(rs.getInt("cc_num"));
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

	// 환불처리
	@Override
	public int ordercancel(String[] num) {
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		for (int i = 0; i < num.length; i++) {

			try {
				conn = datasource.getConnection();
				String sql = "DELETE FROM ordercancel WHERE cc_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(num[i]));

				deleteCnt = pstmt.executeUpdate();
				
				System.out.println("deleteCnt1 : " + deleteCnt);
				
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
	
	//총판매액
	@Override
	public int getTotalSale() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = datasource.getConnection(); // 컨넥션 풀
			sql = "SELECT SUM(price) FROM ordercomplete";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt(1);
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
	
	//종류별 구매수
	@Override
	public Object getFirstChat() {
		Map<String, Integer> m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection(); // 컨넥션 풀
			sql = "SELECT SUM(o.price), s.kind FROM ordercomplete o JOIN stocklist s "
					+ "ON o.num = s.num WHERE s.kind IS NOT NULL GROUP BY s.kind";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			m = new HashMap<String, Integer>();
			m.put("TOP", 0);
			m.put("PANST", 0);
			m.put("SHOSE", 0);
			
			while(rs.next()) {
			String kind = rs.getString(1);
			Integer value = rs.getInt(2);
			System.out.println("kind : " + kind + "value : " + value);
			m.put(kind, value);
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
		return m;
	}	
}
