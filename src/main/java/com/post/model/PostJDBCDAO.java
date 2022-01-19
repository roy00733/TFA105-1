package com.post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostJDBCDAO implements PostDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/AAA?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"insert into POST (MEMBER_ID,POST_TITLE,POST_TYPE_ID,POST_CONTENT) values (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select POST_ID,MEMBER_ID,POST_TITLE,POST_TYPE_ID,POST_CONTENT,POST_TIME,POST_STATUS"
		+ ",POST_REWARD_POINTS from POST order by POST_ID";
	private static final String GET_ONE_STMT = 
		"select POST_ID,MEMBER_ID,POST_TITLE,POST_TYPE_ID,POST_CONTENT,POST_TIME,POST_STATUS,POST_REWARD_POINTS"
		+ " from POST where POST_ID = ?";
	private static final String DELETE = 
		"delete from POST where POST_ID = ?";
	private static final String UPDATE = 
		"update POST set MEMBER_ID=?, POST_TITLE=?, POST_TYPE_ID=?, POST_CONTENT=?, POST_TIME=?,"
		+ " POST_STATUS=?, POST_REWARD_POINTS=? where POST_ID = ?";
	
	@Override
	public void insert(PostVO postVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getMemberid());
			pstmt.setString(2, postVO.getPosttitle());
			pstmt.setInt(3, postVO.getPosttypeid());
			pstmt.setString(4, postVO.getPostcontent());
//			pstmt.setTimestamp(5, postVO.getPosttime());
//			pstmt.setInt(6, postVO.getPoststatus());
//			pstmt.setInt(7, postVO.getPostrewardpoints());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
		
		@Override
		public void update(PostVO postVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, postVO.getMemberid());
				pstmt.setString(2, postVO.getPosttitle());
				pstmt.setInt(3, postVO.getPosttypeid());
				pstmt.setString(4, postVO.getPostcontent());
				pstmt.setTimestamp(5, postVO.getPosttime());
				pstmt.setInt(6, postVO.getPoststatus());
				pstmt.setInt(7, postVO.getPostrewardpoints());
				pstmt.setInt(8, postVO.getPostid());
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public void delete(Integer postid) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, postid);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

		@Override
		public PostVO findByPrimaryKey(Integer postid) {

			PostVO postVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, postid);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// postVo 也稱為 Domain objects
					postVO = new PostVO();
					postVO.setPostid(rs.getInt("POST_ID"));
					postVO.setMemberid(rs.getInt("MEMBER_ID"));
					postVO.setPosttitle(rs.getString("POST_TITLE"));
					postVO.setPosttypeid(rs.getInt("POST_TYPE_ID"));
					postVO.setPostcontent(rs.getString("POST_CONTENT"));
					postVO.setPosttime(rs.getTimestamp("POST_TIME"));
					postVO.setPoststatus(rs.getInt("POST_STATUS"));
					postVO.setPostrewardpoints(rs.getInt("POST_REWARD_POINTS"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return postVO;
		}

		@Override
		public List<PostVO> getAll() {
			List<PostVO> list = new ArrayList<PostVO>();
			PostVO postVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects		
					postVO = new PostVO();
					postVO.setPostid(rs.getInt("POST_ID"));
					postVO.setMemberid(rs.getInt("MEMBER_ID"));
					postVO.setPosttitle(rs.getString("POST_TITLE"));
					postVO.setPosttypeid(rs.getInt("POST_TYPE_ID"));
					postVO.setPostcontent(rs.getString("POST_CONTENT"));
					postVO.setPosttime(rs.getTimestamp("POST_TIME"));
					postVO.setPoststatus(rs.getInt("POST_STATUS"));
					postVO.setPostrewardpoints(rs.getInt("POST_REWARD_POINTS"));
					list.add(postVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}

		public static void main(String[] args) {

			PostJDBCDAO dao = new PostJDBCDAO();
			Date date = new Date();
			Timestamp now = new Timestamp(date.getTime());

			// 新增
			PostVO postVO1 = new PostVO();
			postVO1.setMemberid(4);
			postVO1.setPosttitle("一二三");
			postVO1.setPosttypeid(4);
			postVO1.setPostcontent("今天其實原本要上課的");
//			postVO1.setPosttime();
//			postVO1.setPoststatus();
//			postVO1.setPostrewardpoints();
//			dao.insert(postVO1);
			

			// 修改
			PostVO postVO2 = new PostVO();	
			postVO2.setPostid(1);
			postVO2.setMemberid(3);
			postVO2.setPosttitle("不要在台北101了");
			postVO2.setPosttypeid(2);
			postVO2.setPostcontent("就說了不要再101");
			postVO2.setPosttime(now);
			postVO2.setPoststatus(0);
			postVO2.setPostrewardpoints(5);
			dao.update(postVO2);
			
			
			// 刪除
//			dao.delete(11);

			
			// 查詢
			PostVO postVO3 = dao.findByPrimaryKey(2);
			System.out.print(postVO3.getPostid() + ",");
			System.out.print(postVO3.getMemberid() + ",");
			System.out.print(postVO3.getPosttitle() + ",");
			System.out.print(postVO3.getPosttypeid() + ",");
			System.out.print(postVO3.getPostcontent() + ",");
			System.out.print(postVO3.getPosttime() + ",");
			System.out.println(postVO3.getPoststatus()+ ",");
			System.out.println(postVO3.getPostrewardpoints());
			System.out.println("---------------------");

			// 查詢
			List<PostVO> list = dao.getAll();
			for (PostVO aPost : list) {
				System.out.print(aPost.getPostid() + ",");
				System.out.print(aPost.getMemberid() + ",");
				System.out.print(aPost.getPosttitle() + ",");
				System.out.print(aPost.getPosttypeid() + ",");
				System.out.print(aPost.getPostcontent() + ",");
				System.out.print(aPost.getPosttime() + ",");
				System.out.print(aPost.getPoststatus()+ ",");
				System.out.print(aPost.getPostrewardpoints());
				System.out.println();
			}
		}

}
